package wordcount.dynamic;

import java.io.*;

public class IOControllerDynamic {

    public IOOperationsDynamic getController() {
        if (System.console() != null) {
            System.out.println("Using Console");
            return new ConsoleIO();
        } else {
            System.out.println("Using Scanner");
            return streamDevice(System.in, System.out);
        }
    }

    IOOperationsDynamic streamDevice(InputStream in, OutputStream out) {
        return new ScannerIODynamic(in, out);
    }
}
