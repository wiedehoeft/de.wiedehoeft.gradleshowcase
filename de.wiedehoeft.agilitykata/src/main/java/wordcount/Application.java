package wordcount;

import java.io.IOException;

public class Application {

    private IOOperations ioOperations;
    private Model model;
    private WordCounter wordCounter;

    public Application(IOOperations ioOperations, WordCounter wordCounter) {
        this.ioOperations = ioOperations;
        this.model = new Model();
        this.wordCounter = wordCounter;
    }

    public void getSentence() throws IOException {
        ioOperations.printOutput("Enter sentence");
        model.setSentence(ioOperations.getInput());
    }

    public void printResult() throws IOException {
        int numberOfWords = wordCounter.countFrom(model.getSentence());
        ioOperations.printOutput("Number of words: " + numberOfWords);
    }

    public Model getModel() {
        return model;
    }
}
