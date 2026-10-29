import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GPAView view = new GPAView();
            new GPAController(view);
            view.setVisible(true);
        });
    }
}