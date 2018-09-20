package wordcount.dynamic;

import wordcount.dynamic.IOOperationsDynamic;

import java.io.Console;

public class ConsoleIO extends IOOperationsDynamic {

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
