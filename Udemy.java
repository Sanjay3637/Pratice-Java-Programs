import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/* ===== ENUMS ===== */
public enum Role { ADMIN, INSTRUCTOR, STUDENT }

public enum CourseStatus { DRAFT, SUBMITTED, APPROVED }

public enum LessonType { VIDEO, TEXT, QUIZ }

/* ===== MODELS ===== */
public class User implements Serializable {
    public int id;
    public String name;
    public String email;
    public String passwordHash;  // salted SHA-256
    public String salt;
    public boolean banned = false;
    public EnumSet<Role> roles = EnumSet.of(Role.STUDENT); // default
    public LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "User{" + id + ", " + name + ", roles=" + roles + (banned ? ", BANNED" : "") + "}";
    }
}

public class Lesson implements Serializable {
    public int id;
    public String title;
    public LessonType type;
    public String contentOrUrl; // VIDEO: url; TEXT: content; QUIZ: prompt
    public boolean isPreview;

    @Override
    public String toString() {
        return id + ". [" + type + "] " + title + (isPreview ? " (preview)" : "");
    }
}

public class Review implements Serializable {
    public int userId;
    public int stars; // 1..5
    public String comment;
    public LocalDateTime createdAt = LocalDateTime.now();
}

public class Course implements Serializable {
    public int id;
    public int instructorId;
    public String title;
    public String description;
    public String category;
    public double price;
    public CourseStatus status = CourseStatus.DRAFT;
    public List<Lesson> lessons = new ArrayList<>();
    public List<Review> reviews = new ArrayList<>();
    public double avgRating = 0.0;
    public int enrollCount = 0;

    @Override
    public String toString() {
        return String.format(
            "#%d %s | %s | ₹%.2f | %s | lessons:%d | rating:%.2f (%d) | %s",
            id, title, category, price, status, lessons.size(),
            avgRating, reviews.size(),
            (status == CourseStatus.APPROVED ? "LIVE" : "NOT LIVE")
        );
    }
}

public class Enrollment implements Serializable {
    public int id;
    public int userId;
    public int courseId;
    public LocalDateTime enrolledAt = LocalDateTime.now();
    public Set<Integer> completedLessonIds = new HashSet<>();
    public boolean completed = false;
    public String certificateId; // set when completed

    public double progressPercent(Course c) {
        if (c.lessons.isEmpty()) return 0.0;
        return 100.0 * completedLessonIds.size() / c.lessons.size();
    }
}
