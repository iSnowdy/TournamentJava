package Tournament.Build;

import java.io.*;

public class FeedBack { // Consider making this static
    private String line;
    private final String fileName = "FeedBack.txt";

    private FileReader fileReader = null;
    private BufferedReader bufferedReader = null;
    private FileWriter fileWriter = null;
    private PrintWriter printWriter = null;

    public FeedBack() {}

    public void askFeedBack() {
        System.out.println("Please write in a line your thoughts about this MiniGame. It will greatly help to improve" +
                "the program later on. Any kind of advice is appreciated.\nThank you.");
        line = ScannerCreator.nextLine();
    }

    private void createTXT() {
        try {
            File newFile = new File(fileName);
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

    private void addToTXT() {
        try {
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);

            /*
            Few things to note:
            Scanner line = new Scanner(new File("filePath"));
            ^ Offers the possibility to input directly to the .txt

            String read = "";
            Scanner sc = new Scanner(System.in);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                read += line + "\n";
            }
            System.out.println(read);
             */
        } catch (IOException exception) {
            System.out.println("Error while trying to add the FeedBack .txt");
            exception.printStackTrace();
        }
    }

    public void printTXT() {
        try {
            fileReader = new FileReader(fileName);
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

    public void printTXT(int startLine, int endLine) {

    }

    public String getLine() {
        return line;
    }


}
