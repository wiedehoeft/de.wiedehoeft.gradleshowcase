package wordcount;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScannerIO extends IOOperations {

    private final Scanner scanner;
    private final PrintWriter writer;

    public ScannerIO(InputStream in, OutputStream out) {
        scanner = new Scanner(in);
        writer = new PrintWriter(out, true);
    }

    @Override
    void print(String line) {
        writer.println(line);
    }

    @Override
    String getUserInput() {
        StringBuilder userInput = new StringBuilder();
        userInput.append(scanner.nextLine());
        return userInput.toString();
    }
}
