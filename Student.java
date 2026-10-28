import java.util.HashSet;
import java.util.Set;

public class Student {
    private static final Student instance = new Student();

    private String name;
    private String studentId;
    private double currentGpa;
    private int completedHours;

    private final Set<Course> courses = new HashSet<>();

    // Private constructor prevents creating other instances.
    private Student() {
    }

    public static Student getInstance() {
        return instance;
    }

    public void setInfo(String name, String studentId,
                        double currentGpa, int completedHours) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Student name cannot be empty.");
        }

        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Student ID cannot be empty.");
        }

        if (!Double.isFinite(currentGpa)
                || currentGpa < 0 || currentGpa > 5) {
            throw new IllegalArgumentException(
                "Current GPA must be between 0 and 5.");
        }

        if (completedHours < 0) {
            throw new IllegalArgumentException(
                "Completed hours cannot be negative.");
        }

        this.name = name.trim();
        this.studentId = studentId.trim();
        this.currentGpa = currentGpa;
        this.completedHours = completedHours;
    }

    public boolean addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException(
                "Course cannot be null.");
        }

        return courses.add(course);
    }

    public int getSemesterHours() {
        int total = 0;

        for (Course course : courses) {
            total += course.getCreditHours();
        }

        return total;
    }

    private double getSemesterPoints() {
        double total = 0;

        for (Course course : courses) {
            total += course.getWeightedPoints();
        }

        return total;
    }

    public double calculateSemesterGpa() {
        int hours = getSemesterHours();

        if (hours == 0) {
            throw new IllegalStateException(
                "Add at least one course first.");
        }

        return getSemesterPoints() / hours;
    }

    public double calculateCumulativeGpa() {
        int totalHours = getTotalHours();

        if (totalHours == 0) {
            throw new IllegalStateException(
                "GPA cannot be calculated without credit hours.");
        }

        double previousPoints = currentGpa * completedHours;

        return (previousPoints + getSemesterPoints()) / totalHours;
    }

    public int getTotalHours() {
        return completedHours + getSemesterHours();
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getCurrentGpa() {
        return currentGpa;
    }

    public int getCompletedHours() {
        return completedHours;
    }
}   