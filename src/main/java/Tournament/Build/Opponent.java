package Tournament.Build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Opponent {
    // We assign final to the userName(s) attributes so that once initialized they cannot be modified
    private String userName1 = "default";
    private String userName2 = "CPU";
    private int choice;
    private boolean exit = true;

    public Opponent() { // Constructor
        chooseOption();
    }

    private void createSingleUser() {
        this.userName1 = getUserName("Welcome Player 1! \n" +
                "Please type in your name. For the record: it will be registered in the logs together with your results :) \n" +
                "Player 1 Name: ");
        this.userName2 = "CPU";
    }

    private void createTwoUsers() {
        this.userName1 = getUserName("Welcome Player 1! \n" +
                "Please type in your name. For the record: it will be registered in the logs together with your results :) \n" +
                "Player 1 Name: ");
        this.userName2 = getUserName("Hello there Player2! \n" +
                "Please type in your name. For the record: it will be registered in the logs together with your results :) \n" +
                "Player 2 Name: ");
    }

    private String getUserName (String inputMessage) {
        System.out.println(inputMessage);
        return ScannerCreator.nextLine();
    }

    // Menu to show the user the available options
    protected void printOpponentMenu () {
        System.out.println("=====================================");
        System.out.println("|         Choose your opponent     |");
        System.out.println("=====================================");
        System.out.println("|           1. A fellow human      |");
        System.out.println("|           2. Java Console        |");
        System.out.println("|           3. Explanation         |");
        System.out.println("=====================================");
    }

    private int chooseOption() {
        while (exit) {
            printOpponentMenu(); // As long as we are inside the loop, we will keep printing the message
            try {
                choice = ScannerCreator.nextInt();
                ScannerCreator.nextLine();

                while (!(1 <= choice && choice <= 3)) {
                    System.out.println("Come on man. Type something between 1 - 3 for a proper option.");
                    choice = ScannerCreator.nextInt();
                }
            } catch (Exception exception01) { //
                System.out.println("Wrong input type. Please type in a number between 1 - 3\n");
                ScannerCreator.next();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    this.createTwoUsers();
                    exit = false;
                }
                case 2 -> {
                    this.createSingleUser();
                    exit = false;
                }
                case 3 -> { // File reading for the instructions associated with this menu. txt should be inside the project directory
                    System.out.println("------------------------------------------------------------------------------------");

                    int endLine = 15; // Modify if anything is added to the instructions.txt

                    try {
                        FileReader fileReader = new FileReader("Instructions.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        int lineNumber = 0;

                        while ((line = bufferedReader.readLine()) != null && lineNumber < endLine) {
                            System.out.println(line);
                            lineNumber ++;
                        }
                        bufferedReader.close();
                        fileReader.close();
                    } catch (IOException exception02) {
                        System.err.println("Error while reading the file. Details: " + exception02.getMessage());
                        System.out.println("Information: \n");
                        exception02.printStackTrace();
                    }
                    System.out.println("------------------------------------------------------------------------------------");
                }
            }
        }
        return choice;
    }

    public String getUserName1() {
        return userName1;
    }
    public String getUserName2() {
        return userName2;
    }
}
