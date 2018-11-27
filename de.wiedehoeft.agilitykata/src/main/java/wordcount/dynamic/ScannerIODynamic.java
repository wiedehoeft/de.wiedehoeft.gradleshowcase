package wordcount.dynamic;

import wordcount.IOOperations;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScannerIODynamic extends IOOperationsDynamic {

    private final Scanner scanner;
    private final PrintWriter writer;

    public ScannerIODynamic(InputStream in, OutputStream out) {
        scanner = new Scanner(in);
        writer = new PrintWriter(out, true);
    }

    void print(String line) {
        writer.println(line);
    }

    String getUserInput() {
        StringBuilder userInput = new StringBuilder();
        userInput.append(scanner.nextLine());
        return userInput.toString();
    }
}
