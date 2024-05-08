package Tournament.Build;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class FeedBack { // Consider making this static
    private String line;
    private final String filePath = "FeedBack.txt";

    private FileReader fileReader = null;
    private BufferedReader bufferedReader = null;
    private FileWriter fileWriter = null;
    private PrintWriter printWriter = null;
    private final Scanner sc = new Scanner(System.in);


    public FeedBack() {}

    public boolean askFeedBack() {
        System.out.println("Before you leave, would you like to leave some suggestions? FeedBack is always appreciated. Y/N ");
        String decision = ScannerCreator.nextLine();
        if (Objects.equals(decision.toLowerCase(), "y")) {
            return true;
        }
        return false;
    }

    private void createTXT() {
        try {
            File newFile = new File(filePath);
            if (newFile.createNewFile()) {
                System.out.println("The File has been created in " + newFile.getAbsolutePath() +
                " with the name " + newFile.getName());
            } else  {
                System.out.println("File already exists");
            }
        } catch (IOException excpetion0) {
            System.out.println("Unexpected error while creating the FeedBack.txt");
            excpetion0.printStackTrace();
        }
    }

    public void addToTXT(String userName) { // This *should* work... Should KEKW
        try {
            System.out.println("Please write in a line your thoughts about this MiniGame. It will greatly help to improve" +
                    "the program later on. Any kind of advice is appreciated.\nThank you.");

            fileWriter = new FileWriter(filePath);
            printWriter = new PrintWriter(fileWriter, true); // True to append at the end
            printWriter.println(userName);
            String line;
            while (sc.hasNextLine()) {
                line = ScannerCreator.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                printWriter.println(line);
            }
            printWriter.println(userName);

            printWriter.close();
            fileWriter.close();

            System.out.println("FeedBack has been successfully added. Thank you very much :) Hope to see you back!");
        } catch (IOException exception1) {
            System.out.println("Error while trying to add the FeedBack .txt");
            exception1.printStackTrace();
        }
    }

    public void printTXT() {
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String lineToRead;

            while ((lineToRead = bufferedReader.readLine()) != null) System.out.println(lineToRead);
        } catch (IOException exception1) {
            System.out.println("Error while reading FeedBack.txt");
            exception1.printStackTrace();
        } finally {
            try {
                if (null != fileReader) {
                    bufferedReader.close();
                    fileReader.close();
                }
            } catch (IOException exception2) {
                exception2.printStackTrace();
            }
        }
    }

    public void printTXT(String userName) { // Reads the specific FeedBack from a user
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            boolean keyWord = false; // Given a keyword (userName), it will act as a start and stop point to read the file

            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(userName) && !keyWord) {
                    keyWord = true; // The line with the keyWord has been found!
                    continue; // So if it is found, then skip to the next block of code
                }

                if (keyWord) {
                    if (line.startsWith(userName)) {
                        keyWord = false; // If it is found, then get out of the loop
                        break;
                    } else {
                        System.out.println(line); // If not, then keep printing shit
                    }
                }
            }
        } catch (IOException exception3) {
            exception3.printStackTrace();
            System.out.println("Error while reading FeedBack.txt");
        }
    }
}
