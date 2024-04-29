package Tournament.Build;

import com.google.gson.JsonObject;

import java.util.Scanner;

public class MainMenu {
    private int choice;
    private boolean exit = true;
    private final String filePath = "fighterstest.json";


    private final Scanner scanner;
    private final GSONCreator gsonCreator;
    private Statistics statistics; // To load up the stats and print 'em
    private FighterCreation fighterCreation;
    private Opponent opponent;
    private Log log; // Maybe not necessary

    public MainMenu() { // Do we need any input parameters?
        this.scanner = ScannerCreator.getScanner();
        this.gsonCreator = new GSONCreator();
    }



    // Methods

    private void printMainMenu() {
        System.out.println("=================================================");
        System.out.println("|        Welcome, " + opponent.getUserName1() + ", to the Main Menu      |"); // Somehow need to be changed so it shows the CURRENT user
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
                choice = scanner.nextInt();

                while (!(1 <= choice && choice <= 6)) {
                    System.out.println("You see the numbers? Yeah, those. Type 1 - 6 for a correct option");
                    choice = scanner.nextInt();
                }
            } catch (Exception exception00) {
                System.out.println("Wrong input type. Please type a number between 1 - 6");
                scanner.next();
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
                    scanner.next();
                    output = scanner.nextLine();
                    if (output.equalsIgnoreCase("y")) {
                        System.out.println("Are you extremely sure!? Type Y/N ");
                        output = scanner.nextLine();
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
            System.out.println("1. Vitality" +
                               "2. Strength" +
                               "3. Dexterity" +
                               "4. Exit");

            try {
                choice = scanner.nextInt();

                while (!(1 <= choice && choice <= 3)) {
                    System.out.println("Type a number between 1 - 4");
                    choice = scanner.nextInt();
                }
            } catch (Exception exception01) {
                System.out.println("Wrong input type. Please type a number between 1 - 4");
                scanner.next();
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Redirecting...");
                    int points = statistics.increaseVitality(fighterCreation.getFighterName());
                    System.out.println("You have increased your Vitality by " + points + " points. Would you like to further increase your Stats? Y/N");
                    output = scanner.nextLine();
                    if (output.equalsIgnoreCase("y")) {
                        levelUp();
                    } else {
                        printMainMenu();
                    }
                }
                case 2 -> {
                    System.out.println("Redirecting...");
                    int points = statistics.increaseStrength(fighterCreation.getFighterName());
                    System.out.println("You have increased your Strength by " + points + " points. Would you like to further increase your Stats? Y/N");
                    output = scanner.nextLine();
                    if (output.equalsIgnoreCase("y")) {
                        levelUp();
                    } else {
                        printMainMenu();
                    }
                }
                case 3 -> {
                    System.out.println("Redirecting...");
                    int points = statistics.increaseDexterity(fighterCreation.getFighterName());
                    System.out.println("You have increased your Dexterity by " + points + " points. Would you like to further increase your Stats? Y/N");
                    output = scanner.nextLine();
                    if (output.equalsIgnoreCase("y")) {
                        levelUp();
                    } else {
                        printMainMenu();
                    }
                }
                case 4 -> {
                    System.out.println("Going back to the Main Menu");
                    printMainMenu();
                    exit = false;
                }
            }
        }


    }

    private void showStats (String desiredFeature, String feature) {
        JsonObject jsonObject = gsonCreator.loadFile(filePath);
        gsonCreator.getFighterByString(desiredFeature, feature, jsonObject);
    }


}
