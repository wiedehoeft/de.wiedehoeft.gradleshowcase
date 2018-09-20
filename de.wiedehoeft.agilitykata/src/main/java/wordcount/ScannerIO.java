package wordcount;

import wordcount.IOOperations;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ScannerIO implements IOOperations {

    private InputStream input;
    private OutputStream output;

    public ScannerIO(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(input);
        return scanner.nextLine();
    }

    @Override
    public void printOutput(String anyOutput) throws IOException {
        output.write(anyOutput.getBytes());
    }
}
