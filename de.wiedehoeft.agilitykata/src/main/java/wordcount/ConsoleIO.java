package wordcount;

import java.io.Console;

public class ConsoleIO extends IOOperations {

    private final Console console;

    public ConsoleIO() {
        console = System.console();
    }

    @Override
    void print(String line) {
        console.writer().print(line);
        console.flush();
    }

    @Override
    String getUserInput() {
        return console.readLine();
    }
}
