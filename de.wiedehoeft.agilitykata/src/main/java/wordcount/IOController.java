package wordcount;

import java.io.*;

public class IOController {

    public IOOperations getController() {
        if (System.console() != null) {
            System.out.println("Using Console");
            return new ConsoleIO();
        } else {
            System.out.println("Using Scanner");
            return streamDevice(System.in, System.out);
        }
    }

    public IOOperations streamDevice(InputStream in, OutputStream out) {
        return new ScannerIO(in, out);
    }
}
