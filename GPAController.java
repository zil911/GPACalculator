import java.util.Scanner;

public class GPAController {
    private final Student student = Student.getInstance();
    private final Scanner input = new Scanner(System.in);
    private final GPAView view;

    public GPAController(GPAView view) {
        this.view = view;
    }

    private String ask(String message) {
        view.showPrompt(message);
        return input.nextLine().trim();
    }

    public void start() {
        try {
            String name = ask("Student name: ");
            String id = ask("Student ID: ");

            double gpa = Double.parseDouble(
                    ask("Previous GPA (0 to 5): "));

            int hours = Integer.parseInt(
                    ask("Previously completed hours: "));

            student.setInfo(name, id, gpa, hours);

            int count = Integer.parseInt(
                    ask("Number of courses: "));

            if (count <= 0) {
                view.showMessage("Enter at least one course.");
                return;
            }

            for (int i = 1; i <= count; i++) {
                view.showMessage("\nCourse " + i);

                String code = ask("Course code: ");

                int credits = Integer.parseInt(
                        ask("Credit hours: "));

                double points = Double.parseDouble(
                        ask("Grade points (0 to 5): "));

                Course course = new Course(code, credits, points);

                if (!student.addCourse(course)) {
                    view.showMessage(
                            "Duplicate course. Enter a different course.");
                    i--;
                }
            }

            view.showResults(student);

        } catch (IllegalArgumentException e) {
            view.showMessage(
                    "Invalid input. Check your values and restart.");
        }
    }
}