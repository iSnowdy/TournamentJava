package Tournament.Build;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FighterCreation extends Statistics {

    private final String username1;
    private final String username2;
    protected String fighterName;
    protected final String fighterType;
    private int choice;
    private boolean exit = true;
    private String rank;
    private final Map<String, String> namesCorr;



    private final Log log = new Log(); // To register in the Log
    private JsonObject jsonObject;

    // Consider a HashMap/Set for Rank:Time for the Cron

    public FighterCreation() {
        this.username1 = "";
        this.username2 = "";
        this.fighterType = "";
        this.namesCorr = new HashMap<>();
    }

    public FighterCreation(String username1) {
        this.namesCorr = new HashMap<>();
        this.username1 = username1;
        this.username2 = "";

        chooseOption();
        this.fighterType = setFighterType(fighterName);
        System.out.println("Your username and Fighter name will be added to the Log");
        namesCorr.put(this.username1, fighterName);
    }

    public FighterCreation(String username1, String username2) {
        this.username1 = username1;
        this.username2 = username2;


        this.namesCorr = new HashMap<>();

        chooseOption();
        this.fighterType = setFighterType(fighterName);
        System.out.println("Your username and Fighter name will be added to the Log");
        namesCorr.put(this.username1, fighterName);
        namesCorr.put(this.username2, fighterName);
    }

    protected void printFighterWelcomeMessage() {
        System.out.println("================================================================");
        System.out.println("|                 Welcome to the Fighter Creator              |");
        System.out.println("================================================================");
        System.out.println("|      Here you will select if you want to create a custom    |");
        System.out.println("|      Fighter or if you want to pick an already built one    |");
        System.out.println("================================================================");
        System.out.println("|                     1. Customized Fighter                   |");
        System.out.println("|                     2. Pre created Fighter                  |");
        System.out.println("|                     3. Statistics explanation               |");
        System.out.println("================================================================");
    }

    private void chooseOption() { // Similar method to the one in Opponent. Re factor?
        // Set fighterName
        System.out.println("First of all, please type your Fighter Name:");
        this.fighterName = ScannerCreator.nextLine();

        while (exit) {
            printFighterWelcomeMessage();
            try {
                choice = ScannerCreator.nextInt();

                while (!(1 <= choice && choice <= 3)) {
                    System.out.println("Come on man. Type something between 1 - 3 for a proper option.");
                    choice = ScannerCreator.nextInt();
                    ScannerCreator.nextLine();
                }
            } catch (Exception exception01) { //
                System.out.println("Wrong input type. Please type in a number between 1 - 3\n");
                ScannerCreator.next();
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Place holder. Method here");
                    ScannerCreator.nextLine();
                    createFighter();
                    exit = false;
                }
                case 2 -> {
                    System.out.println("Place holder. Another method here");
                    ScannerCreator.nextLine();
                    loadFighter();
                    exit = false;
                }
                case 3 -> { // File reading for the instructions associated with this menu. txt should be inside the project directory
                    System.out.println("------------------------------------------------------------------------------------");

                    int startLine = 20; // Modify if anything is added to the instructions.txt
                    int endLine = 42;

                    try {
                        FileReader fileReader = new FileReader("Instructions.txt");
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
                    } catch (IOException exception02) {
                        System.err.println("Error while reading the file. Details: " + exception02.getMessage());
                        System.out.println("Information: \n");
                        exception02.printStackTrace();
                    }
                    System.out.println("------------------------------------------------------------------------------------");
                }
            }
        }
    }

    private void loadFighter() {
        System.out.println("You have decided to load an already existing Fighter");
        System.out.println("You are able to decide the Rank of the Fighter you would like to load. \nHowever," +
                "take into account that the higher the Rank, the harder the fights will be.\n" +
                "\nFinally, when fighting against higher ranked Fighters, if you suffer a defeat, you will lose\n" +
                "all of your Ranking Points\n");

        String decision;
        do {
            System.out.println("The current available Fighters are:");
            GSONCreator.readFile(GSONCreator.filepathJSON1); // We call the method to read the whole JSON first

            System.out.println("Out of those fighters, what Fighter Rank are you willing to load?");
            String rank = ScannerCreator.nextLine();
            if (!(validateRank(rank))) {
                System.out.println("********** Please choose a valid Fighter Rank to proceed **********");
                continue; // If and only if the rank is valid, then it will continue with the next iteration
            }
            System.out.println("Confirm Selection. Are you sure you want to load this Fighter? Type Y/N");
            decision = ScannerCreator.nextLine();
            if (Objects.equals(decision.toLowerCase(), "y")) {
                this.rank = rank;
                jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
                // We load the stats of the Fighter selected. Then we must add it but with the new values
                // of the UserName and FighterName
                int[] stats = GSONCreator.getFighterStats("Rank", this.rank, jsonObject);
                GSONCreator.addNewFighter(this.username1, this.fighterName, rank, stats[0], stats[1], stats[2]);

                // We then confirm the Fighter Selection
                GSONCreator.getFighterByString("UserName", this.username1, jsonObject);

                // Now we add the Fighter to the Log
                System.out.println("Adding to Log ...");
                log.addToLog(this.username1, this.fighterName, 0, 0, 0);

                System.out.println("User " + this.username1 + " Log so far is:\n");
                log.showLog(this.username1);

                exit = false;
                // Here we must implement somehow a way to load the characteristics of this Fighter and
                // assign it to the user
            }
        } while (exit);
    }

    private void createFighter() {
        int vitPoints = 0;
        int strPoints = 0;
        int dexPoints = 0;

        System.out.println("You have decided to create a Fighter from scratch");
        System.out.println("You will be given a fixed amount of Statistics (stats) points to " +
                "distribute amongst all three different types of stats. Choose wisely where to put " +
                "your stats as you won't be able to respec later on");
        System.out.println("Your Fighter will start with all stats at 1");

        // I'm not sure if this should be here or not. The attribute is inherited from the Statistics class
        // This probably doesn't work since it is not changing the attribute from the superclass
        setTotalStatPoints(10);
        setAvailableStatPoints(7); // 3 points are already in each stat

        while (getAvailableStatPoints() != 0) { // Needs to be tested

            System.out.println("You currently have " + getAvailableStatPoints() + " available points.");
            System.out.println("How many points would you like to invest in Vitality? ");
            vitPoints = ScannerCreator.nextInt();
            setAvailableStatPoints(getAvailableStatPoints() - vitPoints);
            System.out.println("You know have " + getAvailableStatPoints() + " available points");

            System.out.println("How many points would you like to invest in Strength? ");
            strPoints = ScannerCreator.nextInt();
            setAvailableStatPoints(getAvailableStatPoints() - strPoints);
            System.out.println("You know have " + getAvailableStatPoints() + " available points");

            System.out.println("How many points would you like to invest in Dexterity? ");
            dexPoints = ScannerCreator.nextInt();
            setAvailableStatPoints(getAvailableStatPoints() - dexPoints);
            System.out.println("You know have " + getAvailableStatPoints() + " available points");
        }

        // We add the Fighter to the JSON File. But it is still not created inside the program! (Fighter class)
        GSONCreator.addNewFighter(this.username1, fighterName, rank, vitPoints + 1, strPoints + 1, dexPoints + 1);
        // Heavily consider changing the way parameters are given. I don't like adding that + 1 there
    }

    public Map<String, String> getHashMap() {
        for (Object username : namesCorr.keySet()) {
            System.out.println("Username: " + username + " | Fighter: " + namesCorr.get(username));
            break; // So we only print the last entry of the HashMap; which is the first print of the List
        }
        return namesCorr;
    }

    // Abstract method from Statistics
    @Override
    public int[] getStats(String fighterName) {
        jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        return GSONCreator.getFighterStats("FighterName", fighterName, jsonObject);
    }


    /*@Override
    void showStats() {}*/


    // According to the ratio between the stats, a Fighter Type will be adjudicated
    public String setFighterType(String fighterName) { // Take as a parameter if it is the first or second user?
        int[] fighterStats = getStats(fighterName);

        int vitality = fighterStats[0];
        int strength = fighterStats[1];
        int dexterity = fighterStats[2];
        String fighterType;

        String stats =
                "Fighter " + fighterName + " stats are:\n" +
                        "Vitality: " + vitality + "\n" +
                        "Strength: " + strength + "\n" +
                        "Dexterity: " + dexterity + "\n";

        int totalPoints = vitality + strength + dexterity;
        // Differences between all the stats (absolute)
        double vitalityRatio = (double)vitality / totalPoints;
        double strengthRatio = (double)strength / totalPoints;
        double dexterityRatio = (double)dexterity / totalPoints;
        // Now we determine the FighterType according to the difference between the stats
        if (vitalityRatio >= 0.6) {
            return fighterType = "Tank";
        } else if (strengthRatio >= 0.6) {
            return fighterType = "Glass Cannon";
        } else if (dexterityRatio >= 0.6) {
            return fighterType = "Agile";
        } else if (vitalityRatio >= 0.4) {
            return fighterType = "Vitality Oriented";
        } else if (strengthRatio >= 0.4) {
            return fighterType = "Strength Oriented";
        } else if (dexterityRatio >= 0.4) {
            return fighterType = "Dexterity Oriented";
        } else {
            return fighterType = "Balanced";
        }
    }

    // Re factoring must be done on these methods down here

    // Checks if the Rank selected by the user is correct. It is vital for the proper execution of the Script
    private boolean validateRank(String rank) {
        for (Ranks validRank : Ranks.values()) {
            if (validRank.toString().equalsIgnoreCase(rank)) {
                System.out.println("Rank confirmed: " + rank);
                return true;
            }
        }
        System.out.println("The Rank you have selected is not valid. Please choose a proper one: ");
        return false;
    }

    public String getRank() {
        return rank;
    }

    public String getFighterName() {
        return fighterName;
    }

    public String getFighterType() {
        return fighterType;
    }

    public String getUserName1() {
        return username1;
    }

    public String getUserName2() {
        return username2;
    }
}
