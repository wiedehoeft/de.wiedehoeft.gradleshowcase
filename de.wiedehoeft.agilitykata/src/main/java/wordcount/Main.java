package wordcount;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ScannerIO ioOperations = new ScannerIO(System.in, System.out);
        WordCounterImpl wordCounter = new WordCounterImpl();

        Application application = new Application(ioOperations, wordCounter);

        application.getSentence();
        application.printResult();
    }
}
