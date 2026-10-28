public class GPAView {

    public void showPrompt(String message) {
        System.out.print(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showResults(Student student) {
        System.out.println("\n===== GPA Results =====");
        System.out.println("Name: " + student.getName());
        System.out.println("ID: " + student.getStudentId());

        System.out.printf("Semester GPA: %.2f / 5%n",
                student.calculateSemesterGpa());

        System.out.printf("Cumulative GPA: %.2f / 5%n",
                student.calculateCumulativeGpa());

        System.out.println("Total credit hours: "
                + student.getTotalHours());
    }
}