package wordcount;

public class Application {

    private IOController ioController;
    private IOOperations ioOperations;

    public Application() {
        this.ioController = new IOController();
        ioOperations = ioController.getController();
    }

    public void getInputFromUser() {
        ioOperations.print("Hello World");
        String input = ioOperations.getUserInput();
        ioOperations.print("Your input was: " + input);
    }
}
