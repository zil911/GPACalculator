public class GPAController {

    private final Student student = Student.getInstance();
    private final GPAView view;

    public GPAController(GPAView view) {
        this.view = view;

        view.onAddCourse(e -> addCourse());
        view.onCalculate(e -> calculate());

        view.onReset(e -> {
            student.reset();
            view.clearForm();
        });
    }

    private void addCourse() {
        try {
            String code = view.getCourseCode();

            int hours = Integer.parseInt(
                    view.getCourseHours());

            double points = Double.parseDouble(
                    view.getCoursePoints());

            Course course = new Course(code, hours, points);

            if (student.addCourse(course)) {
                view.showAddedCourse(course);
            } else {
                view.showError(
                        "This course code has already been added.");
            }

        } catch (NumberFormatException e) {
            view.showError(
                    "Enter whole credit hours and numeric grade points.");

        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private void calculate() {
        try {
            double gpa = Double.parseDouble(
                    view.getPreviousGpa());

            int hours = Integer.parseInt(
                    view.getPreviousHours());

            student.setInfo(
                    view.getStudentName(),
                    view.getStudentId(),
                    gpa,
                    hours);

            view.showResults(student);

        } catch (NumberFormatException e) {
            view.showError(
                    "Enter a numeric GPA and whole completed hours.");

        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }
}