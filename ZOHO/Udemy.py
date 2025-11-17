import hashlib
import json
import os
import re
from datetime import datetime


role_list = {}
user_list = {}
course_list = {}
category_list = {}
enrollment_list = {}
review_list = {}

class User:
    def __init__(self, email, password, name):
        self.email = email
        self.__password = self._hash_password(password)
        self.__role_id = None
        self.name = name
        self.date_joined = datetime.now()
        self.is_active = True

    def _hash_password(self, password):
        """Hash a password for storing."""
        salt = os.urandom(32)
        key = hashlib.pbkdf2_hmac('sha256', password.encode('utf-8'), salt, 100000)
        return salt + key

    def verify_password(self, password):
        """Verify a stored password against one provided by user"""
        salt = self.__password[:32]
        stored_key = self.__password[32:]
        new_key = hashlib.pbkdf2_hmac('sha256', password.encode('utf-8'), salt, 100000)
        return new_key == stored_key

    def get_password_hash(self):
        return self.__password

    def set_role(self, role_id):
        self.__role_id = role_id

    def get_role(self):
        return self.__role_id
    
    def switch_role(self, new_role_name):
        """Allow users to switch roles if they have multiple roles"""
        new_role_id = get_role_id(new_role_name)
        if new_role_id and self.email in user_roles and new_role_id in user_roles[self.email]:
            self.__role_id = new_role_id
            return True
        return False

class Student(User):
    def __init__(self, email, password, name):
        super().__init__(email, password, name)
        self.courses_enrolled = []
        self.courses_completed = []
        self.wishlist = []

class Admin(User):
    def __init__(self, email, password, name):
        super().__init__(email, password, name)
    
    def create_admin(self, email, password, name):
        admin = Admin(email, password, name)
        return admin

class Instructor(User):
    def __init__(self, email, password, name):
        super().__init__(email, password, name)
        self.courses_created = []
        self.bio = ""
        self.rating = 0
        self.total_ratings = 0

class Role:
    def __init__(self, role_id, role_name):
        self.__role_id = role_id
        self.__role_name = role_name

    def get_role_id(self):
        return self.__role_id
    
    def get_role_name(self):
        return self.__role_name
    
    def display_role(self):
        return f"{self.__role_id}:{self.__rolename}"

class Category:
    def __init__(self, category_id, name, description):
        self.category_id = category_id
        self.name = name
        self.description = description
        self.courses = []

class Course:
    def __init__(self, course_id, title, description, instructor_email, price, category_id):
        self.course_id = course_id
        self.title = title
        self.description = description
        self.instructor_email = instructor_email
        self.price = price
        self.category_id = category_id
        self.lessons = []
        self.date_created = datetime.now()
        self.is_published = False
        self.is_approved = False
        self.rating = 0
        self.total_ratings = 0
        self.total_enrollments = 0
        self.total_lessons = 0

    def add_lesson(self, lesson_title, lesson_content, content_type="text", content_url=None):
        lesson_id = len(self.lessons) + 1
        lesson = {
            'lesson_id': lesson_id,
            'title': lesson_title,
            'content': lesson_content,
            'content_type': content_type,
            'content_url': content_url,
            'duration': 0  # in minutes
        }
        self.lessons.append(lesson)
        self.total_lessons += 1
        return lesson_id

class Enrollment:
    def __init__(self, enrollment_id, student_email, course_id):
        self.enrollment_id = enrollment_id
        self.student_email = student_email
        self.course_id = course_id
        self.enrollment_date = datetime.now()
        self.progress = 0  # percentage
        self.completed_lessons = []
        self.last_accessed = datetime.now()
        self.is_completed = False
        self.completion_date = None

class Review:
    def __init__(self, review_id, course_id, student_email, rating, comment):
        self.review_id = review_id
        self.course_id = course_id
        self.student_email = student_email
        self.rating = rating
        self.comment = comment
        self.date_posted = datetime.now()
        self.is_verified = False  


def get_role_id(role_name):
    global role_list
    for role_id, role_obj in role_list.items():
        if role_obj.get_role_name() == role_name:
            return role_id
    return None

def is_valid_email(email):
    pattern = r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
    return re.match(pattern, email) is not None

def generate_id(prefix):
    return f"{prefix}_{len(globals()[f'{prefix}_list'])+1}"

def register_user(email, password, name, role_name):
    global user_list, user_roles
    
    if email in user_list:
        print("Email already registered!")
        return False
    
    if not is_valid_email(email):
        print("Invalid email format!")
        return False
    
    if len(password) < 6:
        print("Password must be at least 6 characters!")
        return False
    
    role_id = get_role_id(role_name)
    if not role_id:
        print("Invalid role!")
        return False
    
    if role_name.lower() == "student":
        user = Student(email, password, name)
    elif role_name.lower() == "instructor":
        user = Instructor(email, password, name)
    elif role_name.lower() == "admin":
        user = Admin(email, password, name)
    else:
        print("Invalid role!")
        return False
    
    user.set_role(role_id)
    user_list[email] = user
    
    # Initialize user roles mapping
    if email not in user_roles:
        user_roles[email] = []
    user_roles[email].append(role_id)
    
    print(f"Registration successful as {role_name}!")
    return True

def login_user(email, password):
    global user_list
    
    if email not in user_list:
        print("Email not registered!")
        return None
    
    user = user_list[email]
    
    if not user.verify_password(password):
        print("Invalid password!")
        return None
    
    if not user.is_active:
        print("Account is deactivated. Please contact admin.")
        return None
    
    print("Login successful!")
    return user

def create_course(instructor_email, title, description, price, category_id):
    global course_list
    
    course_id = generate_id("course")
    course = Course(course_id, title, description, instructor_email, price, category_id)
    course_list[course_id] = course
    
    # Add course to instructor's created courses
    if instructor_email in user_list and isinstance(user_list[instructor_email], Instructor):
        user_list[instructor_email].courses_created.append(course_id)
    
    # Add course to category
    if category_id in category_list:
        category_list[category_id].courses.append(course_id)
    
    print(f"Course created successfully with ID: {course_id}")
    return course_id

def enroll_course(student_email, course_id):
    global enrollment_list, course_list
    
    # Check if already enrolled
    for enrollment_id, enrollment in enrollment_list.items():
        if enrollment.student_email == student_email and enrollment.course_id == course_id:
            print("You are already enrolled in this course!")
            return False
    
    # Create new enrollment
    enrollment_id = generate_id("enrollment")
    enrollment = Enrollment(enrollment_id, student_email, course_id)
    enrollment_list[enrollment_id] = enrollment
    
    # Update student's enrolled courses
    if student_email in user_list and isinstance(user_list[student_email], Student):
        user_list[student_email].courses_enrolled.append(course_id)
    
    # Update course enrollment count
    if course_id in course_list:
        course_list[course_id].total_enrollments += 1
    
    print("Enrollment successful!")
    return True

def search_courses(keyword=None, category_id=None, min_rating=0, max_price=None, sort_by="popularity"):
    global course_list
    
    results = []
    
    for course_id, course in course_list.items():
        # Skip unpublished or unapproved courses
        if not course.is_published or not course.is_approved:
            continue
        
        # Apply filters
        if keyword and keyword.lower() not in course.title.lower() and keyword.lower() not in course.description.lower():
            continue
        
        if category_id and course.category_id != category_id:
            continue
        
        if course.rating < min_rating:
            continue
        
        if max_price is not None and course.price > max_price:
            continue
        
        results.append(course)
    
    # Sort results
    if sort_by == "popularity":
        results.sort(key=lambda x: x.total_enrollments, reverse=True)
    elif sort_by == "newest":
        results.sort(key=lambda x: x.date_created, reverse=True)
    elif sort_by == "highest-rated":
        results.sort(key=lambda x: x.rating, reverse=True)
    elif sort_by == "price-low":
        results.sort(key=lambda x: x.price)
    elif sort_by == "price-high":
        results.sort(key=lambda x: x.price, reverse=True)
    
    return results

def update_progress(enrollment_id, lesson_id, mark_completed=True):
    global enrollment_list
    
    if enrollment_id not in enrollment_list:
        print("Enrollment not found!")
        return False
    
    enrollment = enrollment_list[enrollment_id]
    
    if mark_completed and lesson_id not in enrollment.completed_lessons:
        enrollment.completed_lessons.append(lesson_id)
        
        # Calculate new progress
        course = course_list.get(enrollment.course_id)
        if course and course.total_lessons > 0:
            enrollment.progress = (len(enrollment.completed_lessons) / course.total_lessons) * 100
            
            # Check if course is completed
            if enrollment.progress >= 100 and not enrollment.is_completed:
                enrollment.is_completed = True
                enrollment.completion_date = datetime.now()
                
                # Add to student's completed courses
                student = user_list.get(enrollment.student_email)
                if student and isinstance(student, Student):
                    student.courses_completed.append(enrollment.course_id)
                
                print("Congratulations! You've completed the course!")
    
    enrollment.last_accessed = datetime.now()
    return True

def add_review(course_id, student_email, rating, comment):
    global review_list, course_list
    
    # Check if student has completed the course
    student_completed = False
    for enrollment_id, enrollment in enrollment_list.items():
        if enrollment.student_email == student_email and enrollment.course_id == course_id and enrollment.is_completed:
            student_completed = True
            break
    
    if not student_completed:
        print("You must complete the course before reviewing it!")
        return False
    
    # Check if student has already reviewed this course
    for review_id, review in review_list.items():
        if review.student_email == student_email and review.course_id == course_id:
            print("You have already reviewed this course!")
            return False
    
    # Create new review
    review_id = generate_id("review")
    review = Review(review_id, course_id, student_email, rating, comment)
    review.is_verified = True  # Verified since they completed the course
    review_list[review_id] = review
    
    # Update course rating
    course = course_list[course_id]
    total_ratings = course.total_ratings
    course.rating = (course.rating * total_ratings + rating) / (total_ratings + 1)
    course.total_ratings += 1
    
    print("Review added successfully!")
    return True

def admin_approve_course(admin_email, course_id):
    global course_list
    
    if course_id not in course_list:
        print("Course not found!")
        return False
    
    course = course_list[course_id]
    course.is_approved = True
    print(f"Course {course_id} approved by admin {admin_email}")
    return True

def admin_manage_user(admin_email, user_email, action):
    global user_list
    
    if user_email not in user_list:
        print("User not found!")
        return False
    
    user = user_list[user_email]
    
    if action == "ban":
        user.is_active = False
        print(f"User {user_email} has been banned.")
    elif action == "unban":
        user.is_active = True
        print(f"User {user_email} has been unbanned.")
    elif action == "promote":
        # For simplicity, promoting to admin
        if not isinstance(user, Admin):
            new_admin = Admin(user.email, user.get_password_hash(), user.name)
            new_admin.set_role(get_role_id("admin"))
            user_list[user_email] = new_admin
            
            # Add admin role to user roles
            if user_email in user_roles:
                user_roles[user_email].append(get_role_id("admin"))
            
            print(f"User {user_email} promoted to admin.")
        else:
            print("User is already an admin.")
    else:
        print("Invalid action!")
        return False
    
    return True

# Initialize system with default data
def initialize_system():
    global role_list, category_list, user_roles
    
    # Create roles
    roles = ['admin', 'instructor', 'student']
    for i, role_name in enumerate(roles, 1):
        role_list[i] = Role(i, role_name)
    
    # Create categories
    categories = [
        ('Programming', 'Learn programming languages and software development'),
        ('Business', 'Business courses for entrepreneurs and professionals'),
        ('Design', 'Graphic design, UX/UI, and creative courses'),
        ('Marketing', 'Digital marketing, SEO, and growth strategies'),
        ('Music', 'Music production, instruments, and theory')
    ]
    
    for i, (name, description) in enumerate(categories, 1):
        category_list[i] = Category(i, name, description)
    
    # Create default admin
    admin_email = "admin@udemy.com"
    admin_password = "admin123"
    admin_name = "System Admin"
    
    admin = Admin(admin_email, admin_password, admin_name)
    admin.set_role(get_role_id("admin"))
    user_list[admin_email] = admin
    
    # Initialize user roles mapping
    user_roles = {}
    user_roles[admin_email] = [get_role_id("admin")]
    
    print("System initialized with default admin:")
    print(f"Email: {admin_email}, Password: {admin_password}")

# User roles mapping (email -> list of role IDs)
user_roles = {}

def main():
    global user_list, role_list, course_list, category_list, enrollment_list, review_list
    
    initialize_system()
    current_user = None
    
    while True:
        if current_user is None:
            print("\n=== Udemy-like Platform ===")
            print("1. Login")
            print("2. Register")
            print("3. Exit")
            
            choice = input("Enter your choice: ")
            
            if choice == "1":
                email = input("Email: ")
                password = input("Password: ")
                current_user = login_user(email, password)
            
            elif choice == "2":
                print("\n=== Registration ===")
                email = input("Email: ")
                password = input("Password: ")
                name = input("Full Name: ")
                
                print("Select role:")
                print("1. Student")
                print("2. Instructor")
                
                role_choice = input("Enter choice (1 or 2): ")
                role_name = "student" if role_choice == "1" else "instructor"
                
                if register_user(email, password, name, role_name):
                    print("Registration successful! Please login.")
            
            elif choice == "3":
                print("Goodbye!")
                break
            
            else:
                print("Invalid choice!")
        
        else:
            # User is logged in
            user_role = role_list[current_user.get_role()].get_role_name()
            
            print(f"\n=== Welcome {current_user.name} ({user_role}) ===")
            
            if user_role == "admin":
                print("1. Manage Users")
                print("2. Manage Courses")
                print("3. View Platform Analytics")
                print("4. Logout")
                
                choice = input("Enter your choice: ")
                
                if choice == "1":
                    print("\n=== Manage Users ===")
                    action = input("Enter action (ban/unban/promote): ")
                    user_email = input("Enter user email: ")
                    admin_manage_user(current_user.email, user_email, action)
                
                elif choice == "2":
                    print("\n=== Manage Courses ===")
                    for course_id, course in course_list.items():
                        status = "Approved" if course.is_approved else "Pending"
                        print(f"{course_id}: {course.title} ({status})")
                    
                    course_id = input("Enter course ID to approve: ")
                    admin_approve_course(current_user.email, course_id)
                
                elif choice == "3":
                    print("\n=== Platform Analytics ===")
                    print(f"Total Users: {len(user_list)}")
                    print(f"Total Courses: {len(course_list)}")
                    print(f"Total Enrollments: {len(enrollment_list)}")
                    
                    # Calculate revenue (simple implementation)
                    revenue = sum(course.price * course.total_enrollments for course in course_list.values())
                    print(f"Total Revenue: ${revenue:.2f}")
                
                elif choice == "4":
                    current_user = None
                    print("Logged out successfully!")
                
                else:
                    print("Invalid choice!")
            
            elif user_role == "instructor":
                print("1. Create Course")
                print("2. My Courses")
                print("3. Switch to Student View")
                print("4. Logout")
                
                choice = input("Enter your choice: ")
                
                if choice == "1":
                    print("\n=== Create Course ===")
                    title = input("Course Title: ")
                    description = input("Course Description: ")
                    
                    print("Available Categories:")
                    for cat_id, category in category_list.items():
                        print(f"{cat_id}. {category.name}")
                    
                    category_id = int(input("Select Category ID: "))
                    price = float(input("Price: $"))
                    
                    course_id = create_course(current_user.email, title, description, price, category_id)
                    
                    add_more = True
                    while add_more:
                        lesson_title = input("Lesson Title: ")
                        lesson_content = input("Lesson Content: ")
                        course_list[course_id].add_lesson(lesson_title, lesson_content)
                        
                        add_more = input("Add another lesson? (y/n): ").lower() == "y"
                    
                    publish = input("Publish course? (y/n): ").lower() == "y"
                    if publish:
                        course_list[course_id].is_published = True
                        print("Course published! Waiting for admin approval.")
                
                elif choice == "2":
                    print("\n=== My Courses ===")
                    if isinstance(current_user, Instructor):
                        for course_id in current_user.courses_created:
                            course = course_list.get(course_id)
                            if course:
                                status = "Approved" if course.is_approved else "Pending Approval"
                                print(f"{course_id}: {course.title} - Enrollments: {course.total_enrollments} - Rating: {course.rating:.1f} - Status: {status}")
                
                elif choice == "3":
                    
                    if current_user.email in user_roles and get_role_id("student") in user_roles[current_user.email]:
                        current_user.set_role(get_role_id("student"))
                        print("Switched to Student view!")
                    else:
                        print("You don't have a Student account. Please register as a student first.")
                
                elif choice == "4":
                    current_user = None
                    print("Logged out successfully!")
                
                else:
                    print("Invalid choice!")
            
            elif user_role == "student":
                print("1. Browse Courses")
                print("2. My Courses")
                print("3. My Certificates")
                print("4. Switch to Instructor View")
                print("5. Logout")
                
                choice = input("Enter your choice: ")
                
                if choice == "1":
                    print("\n=== Browse Courses ===")
                    keyword = input("Search keyword (press enter to skip): ")
                    
                    print("Categories:")
                    for cat_id, category in category_list.items():
                        print(f"{cat_id}. {category.name}")
                    
                    category_input = input("Category ID (press enter to skip): ")
                    category_id = int(category_input) if category_input else None
                    
                    min_rating = float(input("Minimum rating (0-5, press enter for 0): ") or 0)
                    
                    max_price_input = input("Maximum price (press enter to skip): ")
                    max_price = float(max_price_input) if max_price_input else None
                    
                    print("Sort by: 1. Popularity, 2. Newest, 3. Highest Rated, 4. Price Low-High, 5. Price High-Low")
                    sort_choice = input("Enter choice (1-5): ")
                    sort_options = ["popularity", "newest", "highest-rated", "price-low", "price-high"]
                    sort_by = sort_options[int(sort_choice)-1] if sort_choice and sort_choice in "12345" else "popularity"
                    
                    results = search_courses(keyword, category_id, min_rating, max_price, sort_by)
                    
                    print(f"\nFound {len(results)} courses:")
                    for i, course in enumerate(results, 1):
                        print(f"{i}. {course.title} - ${course.price} - Rating: {course.rating:.1f} - Enrollments: {course.total_enrollments}")
                    
                    if results:
                        view_course = input("Enter course number to view details (or 0 to go back): ")
                        if view_course and view_course != "0":
                            course_index = int(view_course) - 1
                            if 0 <= course_index < len(results):
                                course = results[course_index]
                                print(f"\n=== {course.title} ===")
                                print(f"Description: {course.description}")
                                print(f"Instructor: {course.instructor_email}")
                                print(f"Price: ${course.price}")
                                print(f"Rating: {course.rating:.1f} ({course.total_ratings} reviews)")
                                print(f"Lessons: {course.total_lessons}")
                                print(f"Enrollments: {course.total_enrollments}")
                                
                                enroll = input("Enroll in this course? (y/n): ").lower() == "y"
                                if enroll:
                                    enroll_course(current_user.email, course.course_id)
                
                elif choice == "2":
                    print("\n=== My Courses ===")
                    if isinstance(current_user, Student):
                        for course_id in current_user.courses_enrolled:
                            course = course_list.get(course_id)
                            if course:
                                # Find enrollment
                                enrollment = None
                                for e_id, e in enrollment_list.items():
                                    if e.student_email == current_user.email and e.course_id == course_id:
                                        enrollment = e
                                        break
                                
                                if enrollment:
                                    status = "Completed" if enrollment.is_completed else f"{enrollment.progress:.1f}%"
                                    print(f"{course_id}: {course.title} - Progress: {status}")
                                    
                                    if not enrollment.is_completed:
                                        access = input("Access this course? (y/n): ").lower() == "y"
                                        if access:
                                            print(f"\n=== {course.title} ===")
                                            for lesson in course.lessons:
                                                completed = "✓" if lesson['lesson_id'] in enrollment.completed_lessons else " "
                                                print(f"{completed} Lesson {lesson['lesson_id']}: {lesson['title']}")
                                            
                                            lesson_choice = input("Enter lesson number to view (or 0 to go back): ")
                                            if lesson_choice and lesson_choice != "0":
                                                lesson_id = int(lesson_choice)
                                                if 1 <= lesson_id <= len(course.lessons):
                                                    lesson = course.lessons[lesson_id-1]
                                                    print(f"\n=== {lesson['title']} ===")
                                                    print(lesson['content'])
                                                    
                                                    complete = input("Mark as completed? (y/n): ").lower() == "y"
                                                    if complete:
                                                        update_progress(enrollment.enrollment_id, lesson_id)
                                                        
                                                        # Check if course is now completed
                                                        if enrollment.is_completed:
                                                            # Add review option
                                                            review = input("Course completed! Would you like to leave a review? (y/n): ").lower() == "y"
                                                            if review:
                                                                rating = float(input("Rating (1-5): "))
                                                                comment = input("Comment: ")
                                                                add_review(course_id, current_user.email, rating, comment)
                
                elif choice == "3":
                    print("\n=== My Certificates ===")
                    if isinstance(current_user, Student):
                        for course_id in current_user.courses_completed:
                            course = course_list.get(course_id)
                            if course:
                                print(f"Certificate of Completion for: {course.title}")
                                print(f"Completed on: {datetime.now().strftime('%Y-%m-%d')}")
                                print("---")
                
                elif choice == "4":
                    # Switch to instructor role if available
                    if current_user.email in user_roles and get_role_id("instructor") in user_roles[current_user.email]:
                        current_user.set_role(get_role_id("instructor"))
                        print("Switched to Instructor view!")
                    else:
                        print("You don't have an Instructor account. Please register as an instructor first.")
                
                elif choice == "5":
                    current_user = None
                    print("Logged out successfully!")
                
                else:
                    print("Invalid choice!")

if __name__ == "__main__":
    main()