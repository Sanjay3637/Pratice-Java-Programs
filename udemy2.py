import os
import json
import hashlib
import binascii
import getpass
import uuid
from datetime import datetime
from typing import Dict, List, Any, Optional

DATA_FILE = 'data.json'
PBKDF2_ROUNDS = 100_000

# ----------------- Helpers: Persistence -----------------

def load_data() -> Dict[str, Any]:
    if os.path.exists(DATA_FILE):
        with open(DATA_FILE, 'r') as f:
            return json.load(f)
    # default structure
    return {
        'users': {},            # user_id -> user dict
        'courses': {},          # course_id -> course dict
        'enrollments': {},      # user_id -> list of course_id
        'progress': {},         # user_id -> {course_id: {lesson_index: True}}
        'reviews': {},          # course_id -> list of reviews
        'next_ids': {
            'user': 1,
            'course': 1
        }
    }


def save_data(data: Dict[str, Any]):
    with open(DATA_FILE, 'w') as f:
        json.dump(data, f, indent=2)

# ----------------- Security: Password hashing -----------------

def hash_password(password: str, salt: Optional[bytes] = None) -> Dict[str, str]:
    if salt is None:
        salt = os.urandom(16)
    dk = hashlib.pbkdf2_hmac('sha256', password.encode(), salt, PBKDF2_ROUNDS)
    return {'salt': binascii.hexlify(salt).decode(), 'hash': binascii.hexlify(dk).decode()}


def verify_password(stored: Dict[str, str], password_attempt: str) -> bool:
    salt = binascii.unhexlify(stored['salt'])
    expected = stored['hash']
    attempt = hashlib.pbkdf2_hmac('sha256', password_attempt.encode(), salt, PBKDF2_ROUNDS)
    return binascii.hexlify(attempt).decode() == expected

# ----------------- Utilities -----------------

def prompt_password(prompt_text='Password: '):
    # use getpass to avoid echoing
    return getpass.getpass(prompt_text)


def now_str():
    return datetime.utcnow().isoformat() + 'Z'

# ----------------- Platform Core -----------------

class Platform:
    def __init__(self):
        self.data = load_data()
        # if no admin exists, create a default admin user to bootstrap
        if not any(u.get('roles') and 'admin' in u['roles'] for u in self.data['users'].values()):
            self._create_default_admin()

    # ----- user management -----
    def _create_default_admin(self):
        print('No admin found. Creating default admin -> email: admin@example.com, password: admin')
        self.register('admin@example.com', 'admin', roles=['admin'])

    def register(self, email: str, password: str, roles: Optional[List[str]] = None) -> Dict[str, Any]:
        roles = roles or ['student']
        # check existing
        for user in self.data['users'].values():
            if user['email'].lower() == email.lower():
                raise ValueError('Email already registered')
        user_id = str(self.data['next_ids']['user'])
        self.data['next_ids']['user'] += 1
        pw = hash_password(password)
        user = {
            'id': user_id,
            'email': email,
            'pw': pw,
            'roles': list(set(roles)),
            'created_at': now_str(),
            'banned': False,
            'profile': {
                'name': '',
                'bio': ''
            }
        }
        self.data['users'][user_id] = user
        save_data(self.data)
        return user

    def login(self, email: str, password: str) -> Dict[str, Any]:
        for user in self.data['users'].values():
            if user['email'].lower() == email.lower():
                if user.get('banned'):
                    raise PermissionError('User is banned')
                if verify_password(user['pw'], password):
                    return user
                raise PermissionError('Incorrect password')
        raise LookupError('User not found')

    def change_roles(self, admin_user: Dict[str, Any], target_user_id: str, roles: List[str]):
        self._require_admin(admin_user)
        target = self.data['users'].get(target_user_id)
        if not target:
            raise LookupError('Target user not found')
        target['roles'] = roles
        save_data(self.data)

    def ban_user(self, admin_user: Dict[str, Any], target_user_id: str, ban: bool = True):
        self._require_admin(admin_user)
        target = self.data['users'].get(target_user_id)
        if not target:
            raise LookupError('Target user not found')
        target['banned'] = ban
        save_data(self.data)

    def reset_password(self, admin_user: Dict[str, Any], target_user_id: str, new_password: str):
        self._require_admin(admin_user)
        target = self.data['users'].get(target_user_id)
        if not target:
            raise LookupError('Target user not found')
        target['pw'] = hash_password(new_password)
        save_data(self.data)

    # ----- course management -----
    def create_course(self, instructor_user: Dict[str, Any], title: str, description: str, price: float, category: str, lessons: List[str]) -> Dict[str, Any]:
        self._require_role(instructor_user, 'instructor')
        course_id = str(self.data['next_ids']['course'])
        self.data['next_ids']['course'] += 1
        course = {
            'id': course_id,
            'title': title,
            'description': description,
            'price': price,
            'category': category,
            'lessons': lessons,  # list of lesson titles or dicts
            'instructor_id': instructor_user['id'],
            'created_at': now_str(),
            'approved': False,
            'views': 0,
            'rating': 0.0,
            'num_ratings': 0
        }
        self.data['courses'][course_id] = course
        save_data(self.data)
        return course

    def edit_course(self, instructor_user: Dict[str, Any], course_id: str, **kwargs):
        course = self._get_course(course_id)
        if course['instructor_id'] != instructor_user['id']:
            raise PermissionError('Not the instructor of this course')
        for k, v in kwargs.items():
            if k in ('title', 'description', 'price', 'category', 'lessons'):
                course[k] = v
        save_data(self.data)

    def delete_course(self, instructor_user: Dict[str, Any], course_id: str):
        course = self._get_course(course_id)
        if course['instructor_id'] != instructor_user['id']:
            raise PermissionError('Not the instructor of this course')
        del self.data['courses'][course_id]
        save_data(self.data)

    def approve_course(self, admin_user: Dict[str, Any], course_id: str, approve: bool = True):
        self._require_admin(admin_user)
        course = self._get_course(course_id)
        course['approved'] = approve
        save_data(self.data)

    # ----- browsing and search -----
    def search_courses(self, query: Optional[str] = None, category: Optional[str] = None,
                       price_max: Optional[float] = None, sort: Optional[str] = None, only_approved: bool = True) -> List[Dict[str, Any]]:
        results = []
        for c in self.data['courses'].values():
            if only_approved and not c.get('approved'):
                continue
            if category and c['category'].lower() != category.lower():
                continue
            if price_max is not None and c['price'] > price_max:
                continue
            if query:
                q = query.lower()
                if q not in c['title'].lower() and q not in c['description'].lower():
                    continue
            results.append(c)
        if sort == 'newest':
            results.sort(key=lambda x: x['created_at'], reverse=True)
        elif sort == 'price_asc':
            results.sort(key=lambda x: x['price'])
        elif sort == 'price_desc':
            results.sort(key=lambda x: x['price'], reverse=True)
        elif sort == 'rating':
            results.sort(key=lambda x: (x.get('rating', 0), x.get('num_ratings', 0)), reverse=True)
        elif sort == 'popularity':
            results.sort(key=lambda x: x.get('views', 0), reverse=True)
        return results

    def preview_course(self, course_id: str) -> Dict[str, Any]:
        course = self._get_course(course_id)
        # increment view counter for preview
        course['views'] = course.get('views', 0) + 1
        save_data(self.data)
        # return only outline and sample (first lesson)
        sample = {'outline': course['lessons'][:5], 'sample_lesson': course['lessons'][0] if course['lessons'] else None}
        return {'course': course, 'sample': sample}

    # ----- enrollment and progress -----
    def enroll(self, user: Dict[str, Any], course_id: str):
        course = self._get_course(course_id)
        uid = user['id']
        self.data['enrollments'].setdefault(uid, [])
        if course_id in self.data['enrollments'][uid]:
            raise ValueError('Already enrolled')
        self.data['enrollments'][uid].append(course_id)
        # initialize progress
        self.data['progress'].setdefault(uid, {})
        self.data['progress'][uid][course_id] = {}
        save_data(self.data)

    def get_enrollments(self, user: Dict[str, Any]) -> List[Dict[str, Any]]:
        uid = user['id']
        return [self.data['courses'][cid] for cid in self.data['enrollments'].get(uid, []) if cid in self.data['courses']]

    def mark_lesson_complete(self, user: Dict[str, Any], course_id: str, lesson_idx: int):
        uid = user['id']
        if course_id not in self.data['enrollments'].get(uid, []):
            raise PermissionError('Not enrolled in course')
        self.data['progress'].setdefault(uid, {})
        self.data['progress'][uid].setdefault(course_id, {})
        self.data['progress'][uid][course_id][str(lesson_idx)] = True
        save_data(self.data)

    def course_progress(self, user: Dict[str, Any], course_id: str) -> Dict[str, Any]:
        course = self._get_course(course_id)
        uid = user['id']
        lessons = course['lessons']
        completed = self.data['progress'].get(uid, {}).get(course_id, {})
        completed_count = len([k for k,v in completed.items() if v])
        percent = (completed_count / max(1, len(lessons))) * 100
        return {'completed': completed_count, 'total': len(lessons), 'percent': percent}

    def issue_certificate(self, user: Dict[str, Any], course_id: str) -> Optional[str]:
        prog = self.course_progress(user, course_id)
        if prog['percent'] >= 100:
            # simple certificate id
            cert_id = str(uuid.uuid4())
            return cert_id
        return None

    # ----- reviews & ratings -----
    def add_review(self, user: Dict[str, Any], course_id: str, rating: int, text: str):
        if rating < 1 or rating > 5:
            raise ValueError('Rating must be 1-5')
        uid = user['id']
        if course_id not in self.data['enrollments'].get(uid, []):
            raise PermissionError('Must be enrolled to review')
        # prevent multiple reviews by same user
        reviews = self.data['reviews'].setdefault(course_id, [])
        for r in reviews:
            if r['user_id'] == uid:
                raise ValueError('You have already reviewed this course')
        review = {'user_id': uid, 'rating': rating, 'text': text, 'created_at': now_str()}
        reviews.append(review)
        # update course aggregate
        course = self._get_course(course_id)
        total = course.get('rating', 0.0) * course.get('num_ratings', 0)
        total += rating
        course['num_ratings'] = course.get('num_ratings', 0) + 1
        course['rating'] = total / course['num_ratings']
        save_data(self.data)

    # ----- admin analytics -----
    def stats(self) -> Dict[str, Any]:
        courses = len(self.data['courses'])
        users = len(self.data['users'])
        enrollments = sum(len(v) for v in self.data['enrollments'].values())
        revenue = 0.0
        for uid, course_list in self.data['enrollments'].items():
            for cid in course_list:
                c = self.data['courses'].get(cid)
                if c: revenue += c.get('price', 0.0)
        return {'courses': courses, 'users': users, 'enrollments': enrollments, 'revenue': revenue}

    # ----- internal helpers -----
    def _require_admin(self, user: Dict[str, Any]):
        if not user or 'admin' not in user.get('roles', []):
            raise PermissionError('Admin privileges required')

    def _require_role(self, user: Dict[str, Any], role: str):
        if role not in user.get('roles', []):
            raise PermissionError(f"User must have role '{role}'")

    def _get_course(self, course_id: str) -> Dict[str, Any]:
        course = self.data['courses'].get(course_id)
        if not course:
            raise LookupError('Course not found')
        return course

# ----------------- Simple Console UI -----------------

def main_menu():
    p = Platform()
    current_user = None
    while True:
        print('\n=== Udemy Console (in-memory storage -> data.json) ===')
        if not current_user:
            print('1) Register')
            print('2) Login')
            print('0) Exit')
            choice = input('Choose: ').strip()
            if choice == '1':
                email = input('Email: ').strip()
                pw = prompt_password()
                # ask if wants to be instructor
                role_choice = input('Register as (s)tudent, (i)nstructor, (a)dmin? [s]: ').strip().lower() or 's'
                roles = ['student']
                if role_choice == 'i': roles.append('instructor')
                if role_choice == 'a': roles = ['admin']
                try:
                    user = p.register(email, pw, roles=roles)
                    print('Registered. You can now login.')
                except Exception as e:
                    print('Error:', e)
            elif choice == '2':
                email = input('Email: ').strip()
                pw = prompt_password()
                try:
                    user = p.login(email, pw)
                    current_user = user
                    print(f"Logged in as {user['email']} (roles: {', '.join(user['roles'])})")
                except Exception as e:
                    print('Login failed:', e)
            elif choice == '0':
                print('Goodbye')
                break
            else:
                print('invalid')
        else:
            # show role-specific menus
            print(f"\nLogged in: {current_user['email']} (roles: {', '.join(current_user['roles'])})")
            print('1) Browse/Search Courses')
            print('2) View My Enrollments / Progress')
            if 'instructor' in current_user['roles']:
                print('3) Instructor: Create/Edit/Delete Course')
            if 'admin' in current_user['roles']:
                print('4) Admin Panel')
            print('9) Logout')
            choice = input('Choose: ').strip()
            if choice == '1':
                query = input('Search query (enter to skip): ').strip() or None
                cat = input('Category (enter to skip): ').strip() or None
                price_max = input('Max price (enter to skip): ').strip()
                price_max = float(price_max) if price_max else None
                sort = input('Sort by (newest, rating, popularity, price_asc, price_desc): ').strip() or None
                results = p.search_courses(query, cat, price_max, sort)
                if not results:
                    print('No courses found')
                for c in results:
                    print(f"[{c['id']}] {c['title']} - {c['category']} - ${c['price']} - rating: {c.get('rating',0):.2f} (approved: {c.get('approved')})")
                cid = input('Enter course id to preview/enroll or press Enter: ').strip()
                if cid:
                    try:
                        preview = p.preview_course(cid)
                        course = preview['course']
                        print('\n--- PREVIEW ---')
                        print('Title:', course['title'])
                        print('Desc:', course['description'])
                        print('Outline:', preview['sample']['outline'])
                        print('Sample lesson:', preview['sample']['sample_lesson'])
                        action = input('Actions: (e)nroll, (r)eview, (b)ack: ').strip().lower()
                        if action == 'e':
                            try:
                                p.enroll(current_user, cid)
                                print('Enrolled!')
                            except Exception as e:
                                print('Enroll failed:', e)
                        elif action == 'r':
                            rating = int(input('Rating 1-5: ').strip())
                            text = input('Review text: ').strip()
                            try:
                                p.add_review(current_user, cid, rating, text)
                                print('Review added')
                            except Exception as e:
                                print('Review failed:', e)
                    except Exception as e:
                        print('Error:', e)
            elif choice == '2':
                enrolls = p.get_enrollments(current_user)
                if not enrolls:
                    print('No enrollments')
                for c in enrolls:
                    print(f"[{c['id']}] {c['title']} - {len(c['lessons'])} lessons")
                cid = input('Enter course id to open or press Enter: ').strip()
                if cid:
                    try:
                        prog = p.course_progress(current_user, cid)
                        print(f"Progress: {prog['percent']:.1f}% ({prog['completed']}/{prog['total']})")
                        course = p._get_course(cid)
                        for idx, lesson in enumerate(course['lessons']):
                            done = p.data['progress'].get(current_user['id'], {}).get(cid, {}).get(str(idx), False)
                            print(f"{idx}) {'[x]' if done else '[ ]'} {lesson}")
                        act = input('Mark lesson number as complete or press Enter: ').strip()
                        if act:
                            li = int(act)
                            p.mark_lesson_complete(current_user, cid, li)
                            print('Marked complete')
                        cert = p.issue_certificate(current_user, cid)
                        if cert:
                            print('You completed the course! Certificate id:', cert)
                    except Exception as e:
                        print('Error:', e)
            elif choice == '3' and 'instructor' in current_user['roles']:
                print('\nInstructor Menu:')
                print('1) Create course')
                print('2) Edit course')
                print('3) Delete course')
                sub = input('Choose: ').strip()
                if sub == '1':
                    title = input('Title: ').strip()
                    desc = input('Description: ').strip()
                    price = float(input('Price: ').strip() or '0')
                    cat = input('Category: ').strip() or 'General'
                    lessons = []
                    print('Enter lessons (blank line to end):')
                    while True:
                        l = input('Lesson title: ').strip()
                        if not l: break
                        lessons.append(l)
                    c = p.create_course(current_user, title, desc, price, cat, lessons)
                    print('Created course id:', c['id'], 'Note: Admin approval required for public listing')
                elif sub == '2':
                    cid = input('Course id: ').strip()
                    if not cid:
                        continue
                    try:
                        course = p._get_course(cid)
                        if course['instructor_id'] != current_user['id']:
                            print('Not your course')
                            continue
                        title = input(f"Title [{course['title']}]: ").strip() or course['title']
                        desc = input(f"Desc [{course['description']}]: ").strip() or course['description']
                        price = input(f"Price [{course['price']}]: ").strip()
                        price = float(price) if price else course['price']
                        cat = input(f"Category [{course['category']}]: ").strip() or course['category']
                        # lessons editing simple replace
                        change_lessons = input('Replace lessons? (y/N): ').strip().lower() == 'y'
                        lessons = course['lessons']
                        if change_lessons:
                            lessons = []
                            print('Enter lessons:')
                            while True:
                                l = input('Lesson title: ').strip()
                                if not l: break
                                lessons.append(l)
                        p.edit_course(current_user, cid, title=title, description=desc, price=price, category=cat, lessons=lessons)
                        print('Saved')
                    except Exception as e:
                        print('Error:', e)
                elif sub == '3':
                    cid = input('Course id to delete: ').strip()
                    try:
                        p.delete_course(current_user, cid)
                        print('Deleted')
                    except Exception as e:
                        print('Error:', e)
            elif choice == '4' and 'admin' in current_user['roles']:
                print('\nAdmin Panel:')
                print('1) List users')
                print('2) List pending courses')
                print('3) Approve course')
                print('4) Ban/unban user')
                print('5) View stats')
                sub = input('Choose: ').strip()
                if sub == '1':
                    for uid,u in p.data['users'].items():
                        print(uid, u['email'], 'roles:', u['roles'], 'banned:' , u.get('banned'))
                    uid = input('Enter user id to modify roles or press Enter: ').strip()
                    if uid:
                        roles = input('Enter roles comma separated (admin,instructor,student): ').strip().split(',')
                        roles = [r.strip() for r in roles if r.strip()]
                        try:
                            p.change_roles(current_user, uid, roles)
                            print('Updated')
                        except Exception as e:
                            print('Error:', e)
                elif sub == '2':
                    for c in p.data['courses'].values():
                        if not c.get('approved'):
                            print(c['id'], c['title'], 'by', c['instructor_id'])
                elif sub == '3':
                    cid = input('Course id to approve: ').strip()
                    try:
                        p.approve_course(current_user, cid, True)
                        print('Approved')
                    except Exception as e:
                        print('Error:', e)
                elif sub == '4':
                    uid = input('User id to ban/unban: ').strip()
                    ban = input('Ban? (y/N): ').strip().lower() == 'y'
                    try:
                        p.ban_user(current_user, uid, ban)
                        print('Updated')
                    except Exception as e:
                        print('Error:', e)
                elif sub == '5':
                    s = p.stats()
                    print('Stats:', s)
            elif choice == '9':
                current_user = None
            else:
                print('unknown choice')

if __name__ == '__main__':
    main_menu()
