import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GPAView extends JFrame {

    private final JTextField nameField = new JTextField();
    private final JTextField idField = new JTextField();
    private final JTextField gpaField = new JTextField();
    private final JTextField hoursField = new JTextField();

    private final JTextField codeField = new JTextField();
    private final JTextField creditsField = new JTextField();
    private final JTextField pointsField = new JTextField();

    private final JButton addButton = new JButton("Add Course");
    private final JButton calculateButton =
            new JButton("Calculate GPA");
    private final JButton resetButton = new JButton("Reset");

    private final JTextArea courseList = new JTextArea(7, 35);
    private final JTextArea results = new JTextArea(6, 35);

    private final JScrollPane resultsScroll =
            new JScrollPane(results);

    public GPAView() {
        setTitle("Student GPA Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        setContentPane(content);

        // Student information
        JPanel studentPanel =
                new JPanel(new GridLayout(4, 2, 8, 8));

        studentPanel.setBorder(
                BorderFactory.createTitledBorder("Student Information"));

        studentPanel.add(new JLabel("Student name:"));
        studentPanel.add(nameField);

        studentPanel.add(new JLabel("Student ID:"));
        studentPanel.add(idField);

        studentPanel.add(new JLabel("Previous GPA (0 to 5):"));
        studentPanel.add(gpaField);

        studentPanel.add(new JLabel("Previously completed hours:"));
        studentPanel.add(hoursField);

        content.add(studentPanel, BorderLayout.NORTH);

        // Course information
        JPanel coursePanel = new JPanel(new BorderLayout(8, 8));

        coursePanel.setBorder(
                BorderFactory.createTitledBorder("Semester Courses"));

        JPanel courseInputs =
                new JPanel(new GridLayout(4, 2, 8, 8));

        courseInputs.add(new JLabel("Course code:"));
        courseInputs.add(codeField);

        courseInputs.add(new JLabel("Credit hours:"));
        courseInputs.add(creditsField);

        courseInputs.add(new JLabel("Grade points (0 to 5):"));
        courseInputs.add(pointsField);

        courseInputs.add(new JLabel(""));
        courseInputs.add(addButton);

        courseList.setEditable(false);

        coursePanel.add(courseInputs, BorderLayout.NORTH);
        coursePanel.add(
                new JScrollPane(courseList), BorderLayout.CENTER);

        content.add(coursePanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonsPanel =
                new JPanel(new GridLayout(1, 2, 8, 8));

        buttonsPanel.add(calculateButton);
        buttonsPanel.add(resetButton);

        // Results are initially hidden
        results.setEditable(false);

        resultsScroll.setBorder(
                BorderFactory.createTitledBorder("Last Calculated Results"));

        resultsScroll.setVisible(false);

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 8));
        bottomPanel.add(buttonsPanel, BorderLayout.NORTH);
        bottomPanel.add(resultsScroll, BorderLayout.CENTER);

        content.add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public String getStudentName() {
        return nameField.getText().trim();
    }

    public String getStudentId() {
        return idField.getText().trim();
    }

    public String getPreviousGpa() {
        return gpaField.getText().trim();
    }

    public String getPreviousHours() {
        return hoursField.getText().trim();
    }

    public String getCourseCode() {
        return codeField.getText().trim();
    }

    public String getCourseHours() {
        return creditsField.getText().trim();
    }

    public String getCoursePoints() {
        return pointsField.getText().trim();
    }

    public void onAddCourse(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void onCalculate(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }

    public void onReset(ActionListener listener) {
        resetButton.addActionListener(listener);
    }

    public void showAddedCourse(Course course) {
        courseList.append(
                course.getCourseCode()
                + " | Hours: " + course.getCreditHours()
                + " | Points: " + course.getGradePoints()
                + "\n");

        codeField.setText("");
        creditsField.setText("");
        pointsField.setText("");

        results.setText("");
        resultsScroll.setVisible(false);
        pack();

        codeField.requestFocusInWindow();
    }

    public void showResults(Student student) {
        results.setText(String.format(
                "Name: %s%n"
                + "Student ID: %s%n"
                + "Semester GPA: %.2f / 5%n"
                + "Cumulative GPA: %.2f / 5%n"
                + "Semester hours: %d%n"
                + "Total credit hours: %d",
                student.getName(),
                student.getStudentId(),
                student.calculateSemesterGpa(),
                student.calculateCumulativeGpa(),
                student.getSemesterHours(),
                student.getTotalHours()));

        resultsScroll.setVisible(true);
        pack();
    }

    public void clearForm() {
        nameField.setText("");
        idField.setText("");
        gpaField.setText("");
        hoursField.setText("");

        codeField.setText("");
        creditsField.setText("");
        pointsField.setText("");

        courseList.setText("");
        results.setText("");

        resultsScroll.setVisible(false);
        pack();

        nameField.requestFocusInWindow();
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Check your input",
                JOptionPane.ERROR_MESSAGE);
    }
}