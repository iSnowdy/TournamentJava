package Tournament.Build;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MainMenu {
    private int choice;
    private String name;
    private boolean exit = true;
    public boolean exitRequest = false;
    private final String plainFilePath = "Instructions.txt";
    private Statistics statistics; // To load up the stats and print 'em
    private FighterCreation fighterCreation;
    private Opponent opponent;
    private Log log; // Maybe not necessary

    public MainMenu() { // Do we need any input parameters?
    }


    // Methods
    // No need to really show the current user. But if an option that is specific to username is triggered, then
    // we must ask that person's username


    private void printMainMenu() {
        System.out.println("=================================================");
        System.out.println("|        Welcome, " + opponent.getUserName1() + ", to the Main Menu      |");
        System.out.println("=================================================");
        System.out.println("|        1. This... is... Spa- I mean Fight!    |");
        System.out.println("|        2. Level Up                            |");
        System.out.println("|        3. Show Stats                          |");
        System.out.println("|        4. Show Log                            |");
        System.out.println("|        5. Instructions                        |");
        System.out.println("|        6. Exit                                |");
        System.out.println("=================================================");
    }

    private int chooseOption() {
        while (exit) {
            printMainMenu();

            try {
                choice = ScannerCreator.nextInt();

                while (!(1 <= choice && choice <= 6)) {
                    System.out.println("You see the numbers? Yeah, those. Type 1 - 6 for a correct option");
                    choice = ScannerCreator.nextInt();
                }
            } catch (Exception exception00) {
                System.out.println("Wrong input type. Please type a number between 1 - 6\n");
                ScannerCreator.next();
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Blood for the Blood God, Skulls for the Skull Throne!");
                    System.out.println("Place holder. Fight method here");
                    ScannerCreator.closeScanner();
                    exit = false;
                }
                case 2 -> {
                    System.out.println("Let's get those Stats up!");
                    System.out.println("Level up stats method here");
                    ScannerCreator.closeScanner();
                    exit = false;
                }
                case 3 -> {
                    System.out.println("This is your Fighter's current status...");
                    showStats("Name", fighterCreation.getFighterName());
                    // Although I'm filtering here by Name, it could be any variable of the JSON File
                    ScannerCreator.closeScanner();
                    exit = false;
                }
                case 4 -> {
                    System.out.println("Showing Log now...");
                    System.out.println("Getting lazy");
                    ScannerCreator.closeScanner();
                    exit = false;
                }
                case 5 -> {
                    System.out.println("A newbie huh? Have fun reading haha!");
                    System.out.println("Concatenate w/ another method that will call diff stuff");
                    ScannerCreator.closeScanner();
                    exit = false;
                }
                case 6 -> {
                    String output;
                    System.out.println("Wait wait wait. Let's talk about this. Are you sure you want to leave? Type Y/N");
                    ScannerCreator.next();
                    output = ScannerCreator.nextLine();
                    if (output.equalsIgnoreCase("y")) {
                        System.out.println("Are you extremely sure!? Type Y/N ");
                        output = ScannerCreator.nextLine();
                        if (output.equalsIgnoreCase("y")) {
                            System.out.println("Alright then... But come back soon!");
                            System.out.println("Exit method here");
                            ScannerCreator.closeScanner();
                            exit = false;
                        }
                    } else {
                        System.out.println("I thought so!");
                    }
                }
            }
        }
        return choice;
    }

    private void levelUp () {
        String output;

        // Consider removing the while loop or the 4th case
        while (exit) {
            System.out.println("Currently your Fighter Status is...\n");
            showStats("Name", fighterCreation.getFighterName());
            System.out.println("What stat are you willing to Level Up?\n");
            System.out.println("1. Vitality\n" +
                               "2. Strength\n" +
                               "3. Dexterity\n" +
                               "4. Exit\n");

            try {
                choice = ScannerCreator.nextInt();

                while (!(1 <= choice && choice <= 3)) {
                    System.out.println("Type a number between 1 - 4");
                    choice = ScannerCreator.nextInt();
                }
            } catch (Exception exception01) {
                System.out.println("Wrong input type. Please type a number between 1 - 4\n");
                ScannerCreator.next();
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Redirecting...");
                    int points = statistics.increaseStat(fighterCreation.getFighterName(), StatType.VITALITY); // Accessing ENUM
                    System.out.println("You have increased your Vitality by " + points + " points. Would you like to further increase your Stats? Y/N");
                    output = ScannerCreator.nextLine();
                    if (output.equalsIgnoreCase("y")) {
                        levelUp();
                    } else {
                        printMainMenu();
                    }
                    ScannerCreator.closeScanner();
                }
                case 2 -> {
                    System.out.println("Redirecting...");
                    int points = statistics.increaseStat(fighterCreation.getFighterName(), StatType.STRENGTH);
                    System.out.println("You have increased your Strength by " + points + " points. Would you like to further increase your Stats? Y/N");
                    output = ScannerCreator.nextLine();
                    if (output.equalsIgnoreCase("y")) {
                        levelUp();
                    } else {
                        printMainMenu();
                    }
                    ScannerCreator.closeScanner();
                }
                case 3 -> {
                    System.out.println("Redirecting...");
                    int points = statistics.increaseStat(fighterCreation.getFighterName(), StatType.DEXTERITY);
                    System.out.println("You have increased your Dexterity by " + points + " points. Would you like to further increase your Stats? Y/N");
                    output = ScannerCreator.nextLine();
                    if (output.equalsIgnoreCase("y")) {
                        levelUp();
                    } else {
                        printMainMenu();
                    }
                    ScannerCreator.closeScanner();
                }
                case 4 -> {
                    System.out.println("Going back to the Main Menu");
                    ScannerCreator.closeScanner();
                    printMainMenu();
                    exit = false;
                }
            }
        }
    }
    private void showStats (String desiredFeature, String feature) {
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        GSONCreator.getFighterByString(desiredFeature, feature, jsonObject);
    }
    private void showLog() {}

    private void showInstructions() {
        while (exit) {
            System.out.println("Select the Instructions that you would like to read: ");
            System.out.println( "1. Opponent Type\n" +
                                "2. Stats Explanation\n" +
                                "3. Tournament Format Explanation\n" +
                                "4. MiniGames\n" +
                                "5. Exit\n");

            try {
                choice = ScannerCreator.nextInt();

                while (!(1 <= choice && choice <= 5)) {
                    System.out.println("Type a number between 1 - 5");
                    choice = ScannerCreator.nextInt();
                }
            } catch (Exception exception02) {
                System.out.println("Wrong input type. Please type a number between 1 - 5\n");
                ScannerCreator.next();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("------------------------------------------------------------------------------------");

                    int endLine = 15; // Modify if anything is added to the instructions.txt

                    try {
                        FileReader fileReader = new FileReader(this.plainFilePath);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        int lineNumber = 0;

                        while ((line = bufferedReader.readLine()) != null && lineNumber < endLine) {
                            System.out.println(line);
                            lineNumber ++;
                        }
                        bufferedReader.close();
                        fileReader.close();
                    } catch (IOException exception03) {
                        System.err.println("Error while reading the file. Details: " + exception03.getMessage());
                        System.out.println("Information: \n");
                        exception03.printStackTrace();
                    }
                    System.out.println("------------------------------------------------------------------------------------");
                    ScannerCreator.closeScanner();
                    this.printMainMenu();
                }
                case 2 -> {
                    System.out.println("------------------------------------------------------------------------------------");

                    int startLine = 20; // Modify if anything is added to the instructions.txt
                    int endLine = 42;

                    try {
                        FileReader fileReader = new FileReader(this.plainFilePath);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        int lineNumber = 0;

                        while ((line = bufferedReader.readLine()) != null && lineNumber < startLine) {
                            lineNumber ++;
                        }
                        while ((line = bufferedReader.readLine()) != null && lineNumber < endLine) {
                            lineNumber ++;
                            System.out.println(line);
                        }
                        bufferedReader.close();
                    } catch (IOException exception04) {
                        System.err.println("Error while reading the file. Details: " + exception04.getMessage());
                        System.out.println("Information: \n");
                        exception04.printStackTrace();
                    }
                    System.out.println("------------------------------------------------------------------------------------");
                    ScannerCreator.closeScanner();
                    this.printMainMenu();
                }
                case 3 -> {
                    System.out.println("------------------------------------------------------------------------------------");

                    int startLine = 47; // Modify if anything is added to the instructions.txt
                    int endLine = 71;

                    try {
                        FileReader fileReader = new FileReader(this.plainFilePath);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        int lineNumber = 0;

                        while ((line = bufferedReader.readLine()) != null && lineNumber < startLine) {
                            lineNumber ++;
                        }
                        while ((line = bufferedReader.readLine()) != null && lineNumber < endLine) {
                            lineNumber ++;
                            System.out.println(line);
                        }
                        bufferedReader.close();
                    } catch (IOException exception05) {
                        System.err.println("Error while reading the file. Details: " + exception05.getMessage());
                        System.out.println("Information: \n");
                        exception05.printStackTrace();
                    }
                    System.out.println("------------------------------------------------------------------------------------");
                    ScannerCreator.closeScanner();
                    this.printMainMenu();
                }
                case 4 -> {
                    System.out.println("------------------------------------------------------------------------------------");

                    int startLine = 76; // Modify if anything is added to the instructions.txt
                    int endLine = 96;

                    try {
                        FileReader fileReader = new FileReader(this.plainFilePath);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        int lineNumber = 0;

                        while ((line = bufferedReader.readLine()) != null && lineNumber < startLine) {
                            lineNumber ++;
                        }
                        while ((line = bufferedReader.readLine()) != null && lineNumber < endLine) {
                            lineNumber ++;
                            System.out.println(line);
                        }
                        bufferedReader.close();
                    } catch (IOException exception06) {
                        System.err.println("Error while reading the file. Details: " + exception06.getMessage());
                        System.out.println("Information: \n");
                        exception06.printStackTrace();
                    }
                    System.out.println("------------------------------------------------------------------------------------");
                    ScannerCreator.closeScanner();
                    this.printMainMenu();
                }
                case 5 -> {
                    System.out.println("Going back to the Main Menu");
                    ScannerCreator.closeScanner();
                    printMainMenu();
                    exit = false;
                }
            }
        }
    }
    void exitProgram() {
        System.out.println("Heard that! Hope to see you soon again! :D\nPreparing to exit the program...\n");
        this.exitRequest = true; // Then in the Main .java do a while(!)
    }
}