package wordcount;

import java.io.IOException;

public interface IOOperations {
    String getInput();

    void printOutput(String anyOutput) throws IOException;
}
