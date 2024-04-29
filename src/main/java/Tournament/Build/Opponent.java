package Tournament.Build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Opponent {
    // We assign final to the userName(s) attributes so that once initialized they cannot be modified
    private final String userName1;
    private final String username2;
    private int choice;
    private boolean exit = true;
    private final Scanner scanner;

    public Opponent() { // Constructor
        this.scanner = ScannerCreator.getScanner(); // Re factorized to call class

        chooseOption();

        if (choice == 1 ) {
            this.userName1 = getUserName("Welcome Player 1! \n" +
                    "Please type in your name. For the record: it will be registered in the logs together with your results :) \n" +
                    "Player 1 Name: ");
            this.username2 = "CPU";
        } else if (choice == 2) {
            this.userName1 = getUserName("Welcome Player 1! \n" +
                    "Please type in your name. For the record: it will be registered in the logs together with your results :) \n" +
                    "Player 1 Name: ");
            this.username2 = getUserName("Hello there Player2! \n" +
                    "Please type in your name. For the record: it will be registered in the logs together with your results :) \n" +
                    "Player 2 Name: ");
        } else {
            System.out.println("Invalid choice input. Please try again");
            this.userName1 = null;
            this.username2 = null;
            chooseOption();
        }

        // With this if-else we will handle the amount of players

        System.out.println("And now that we know each other, lets rock & roll!");
    }

    private String getUserName (String inputMessage) {
        System.out.println(inputMessage); // Probably refactor later on?
        return scanner.nextLine();
    }

    protected int getChoice () {
        return choice; // This getter will give us the choice option for other classes
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
                choice = scanner.nextInt();

                while (!(1 <= choice && choice <= 3)) {
                    System.out.println("Come on man. Type something between 1 - 3 for a proper option");
                    choice = scanner.nextInt();
                }
            } catch (Exception exception01) { // We try to handle any input that is not an int
                System.out.println("Wrong input type. Please type in a number between 1 - 3");
                scanner.next(); // Like this we clear the input. It is important since it sometimes saves up old inputs and can mess things
                continue; // Skip all the code following to the catch every time we enter here. Like this we will be sure we won't get wrong inputs
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("Place holder. Method here");
                    ScannerCreator.closeScanner();
                    exit = false;
                }
                case 2 -> {
                    System.out.println("Place holder. Another method here");
                    ScannerCreator.closeScanner();
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

                    // We have no interest in returning choice = 3 since it does not affect the Constructor
                    // Similar logic to closing the Scanner. We can't close it since option 3 is intended to loop

                }
            }
        }
        return choice;
    }

    public String getUserName1() {
        return userName1;
    }
    public String getUsername2() {
        return username2;
    }
}
