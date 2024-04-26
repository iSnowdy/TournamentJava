package Tournament.Build;

import java.util.Scanner;

public class ScannerCreator {
    // We have a class only to create Scanners since we will be using them in a few classes throughout the
    // code. Like this we will be accomplishing encapsulation and also re-utilizing code
    private static final Scanner scanner = new Scanner(System.in);
    // static: the attribute or Object scanner that we create here will belong to the class itself. Meaning that
    // only one instance of Scanner will be shared across all the code
    // final: to protect the scanner Object. All in all, we are giving extreme protection to the scanner Object

    private ScannerCreator() {
        // private so that only this class can directly access to the created Scannner object
    }

    protected static Scanner getScanner() {
        return scanner;
    }

    public static void closeScanner() {
        scanner.close();
    }
}
