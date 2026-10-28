public final class Course {
    private final String courseCode;
    private final int creditHours;
    private final double gradePoints;

    // RI:
    // courseCode != null
    // courseCode.trim().length() > 0
    // creditHours > 0
    // gradePoints >= 0.0 && gradePoints <= 5.0

    // AF:
    // Represents a course identified by courseCode,
    // worth creditHours credit hours, with gradePoints
    // representing its grade on a 5-point scale.

    public Course(String courseCode, int creditHours,
                  double gradePoints) {

        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Course code cannot be empty.");
        }

        if (creditHours <= 0) {
            throw new IllegalArgumentException(
                "Credit hours must be greater than zero.");
        }

        if (!Double.isFinite(gradePoints)
                || gradePoints < 0 || gradePoints > 5) {
            throw new IllegalArgumentException(
                "Grade points must be between 0 and 5.");
        }

        this.courseCode = courseCode.trim();
        this.creditHours = creditHours;
        this.gradePoints = gradePoints;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public double getGradePoints() {
        return gradePoints;
    }

    public double getWeightedPoints() {
        return gradePoints * creditHours;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Course)) {
            return false;
        }

        Course other = (Course) obj;
        return courseCode.equals(other.courseCode);
    }

    @Override
    public int hashCode() {
        return courseCode.hashCode();
    }
}