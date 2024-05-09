package Tournament.Build;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.MatchResult;

import static java.lang.System.in;

public class ScannerCreator {
    // We have a class only to create Scanners since we will be using them in a few classes throughout the
    // code. Like this we will be accomplishing encapsulation and also re-utilizing code
    private static final Scanner scanner = new Scanner(in);
    // static: the attribute or Object scanner that we create here will belong to the class itself. Meaning that
    // only one instance of Scanner will be shared across all the code
    // final: to protect the scanner Object. All in all, we are giving extreme protection to the scanner Object

    private ScannerCreator() {
        // private so that only this class can directly access to the created Scannner object
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static int nextInt() {
        return scanner.nextInt();
    }

    public static String nextLine() {
        return scanner.nextLine();
    }

    public static String next() { // I don't really know if this works because it is returning a String but should be empty?
        return scanner.next();
    }

    public static boolean hasNextLine() {
        return scanner.hasNextLine();
    }
}