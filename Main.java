public class Main {
    public static void main(String[] args) {
        GPAView view = new GPAView();
        GPAController controller = new GPAController(view);

        controller.start();
    }
}