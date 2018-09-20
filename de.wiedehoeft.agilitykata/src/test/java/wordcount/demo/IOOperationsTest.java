package wordcount.demo;

import org.junit.jupiter.api.Test;
import wordcount.IOOperations;
import wordcount.ScannerIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class IOOperationsTest {

    @Test
    void getInput() {

        // Arrange
        IOOperations scannerIO = new ScannerIO(new ByteArrayInputStream("Any input".getBytes()), new ByteArrayOutputStream());

        // Act
        String input = scannerIO.getInput();

        // Assert
        assertThat(input).isEqualTo("Any input");
    }

    @Test
    void printOutput() throws IOException {
        // Arrange
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOOperations scannerIO = new ScannerIO(new ByteArrayInputStream("Any input".getBytes()), output);

        // Act
        scannerIO.printOutput("Any output");

        // Assert
        assertThat(output.toString()).isEqualTo("Any output");
    }
}
