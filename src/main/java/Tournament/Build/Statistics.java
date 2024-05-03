package Tournament.Build;

import com.google.gson.JsonObject;

import java.util.Scanner;

public abstract class Statistics implements StatsManager {

    // Attributes
    // Because the static attributes are private, they will only be able to be accessed from other classes
    // with static Getters and Setters. Normal ones won't do
    private static int vitality = 1;
    private static int strength = 1;
    private static int dexterity = 1;
    protected static int availableStatPoints;
    protected static int totalStatPoints;


    private FighterCreation fighterCreation;
    private static Scanner scanner = ScannerCreator.getScanner();


    // Abstract classes that will be implemented by child classes

    /* Added to interface
    abstract int increaseVitality();
    abstract int increaseStrength();
    abstract int increaseDexterity();
     */
    abstract int[] getStats(String fighterName, String filePath); // Abstract method to be implemented on Fighter

    // Constructor

    public Statistics(GSONCreator gsonCreator) {
    }

    // Getters & Setters

    public static int getVitality() {
        return vitality;
    }
    public static void setVitality(int vitPoints) {
        vitality += vitPoints;
    }

    public static int getStrength() {
        return strength;
    }
    public static void setStrength(int strPoints) {
        strength += strPoints;
    }

    public static int getDexterity() {
        return dexterity;
    }
    public static void setDexterity(int dexPoints) {
        dexterity += dexPoints;
    }

    public static int getTotalStatPoints() {
        return totalStatPoints;
    }
    public static void setTotalStatPoints(int points) {
        totalStatPoints = points;
    }

    public static int getAvailableStatPoints() {
        return availableStatPoints;
    }
    public static void setAvailableStatPoints(int points) { // Consider changing the return
        availableStatPoints += points;
    }



    // Specific methods

    // Level-Up Methods

    // These methods must be static if stat points are static, its methods must also be static
    // Or change somehow the attributes to not be static
    // Check Override error
    @Override
    public static int increaseVitality(String fighterName) {
        int vitPoints = 0;
        int availableStatPoints = getAvailableStatPoints();
        int vitality = getVitality();
        String output;
        boolean exit = true;

        System.out.println("Currently, your Fighter stats are... ");
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        int[] fighterStats = GSONCreator.getFighterStats(fighterName, jsonObject);

        String stats =
                "Fighter " + fighterName + " stats are:\n" +
                        "Vitality: " + fighterStats[0] + "\n" +
                        "Strength: " + fighterStats[1] + "\n" +
                        "Dexterity: " + fighterStats[2] + "\n";

        System.out.println(stats);

        // The available points are specific to Fighters instances. So this MUST be changed later on. Place-holder for now
        // This really needs testing ><
        while (exit) {
            System.out.println("You currently have " + getAvailableStatPoints() + " available points.");
            System.out.println("How many points would you like to invest in Vitality? ");
            vitPoints = scanner.nextInt();
            setAvailableStatPoints(availableStatPoints - vitPoints);
            availableStatPoints =- vitality;

            System.out.println("Now your Vitality is " + vitality + vitPoints);
            System.out.println("Would you like to further increase it? Y/N");
            output = scanner.nextLine();
            if (output.equalsIgnoreCase("n")) {
                exit = false;
            }
        }
        setVitality(vitality + vitPoints);

        return vitPoints;
    }

    @Override
    public static int increaseStrength(String fighterName) {
        int strPoints = 0;
        int availableStatPoints = getAvailableStatPoints();
        int strength = getStrength();
        String output;
        boolean exit = true;

        System.out.println("Currently, your Fighter stats are... ");
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        int[] fighterStats = GSONCreator.getFighterStats(fighterName, jsonObject);

        String stats =
                "Fighter " + fighterName + " stats are:\n" +
                        "Vitality: " + fighterStats[0] + "\n" +
                        "Strength: " + fighterStats[1] + "\n" +
                        "Dexterity: " + fighterStats[2] + "\n";

        System.out.println(stats);

        // The available points are specific to Fighters instances. So this MUST be changed later on. Place-holder for now
        // This really needs testing ><
        while (exit) {
            System.out.println("You currently have " + getAvailableStatPoints() + " available points.");
            System.out.println("How many points would you like to invest in Strength? ");
            strPoints = scanner.nextInt();
            setAvailableStatPoints(availableStatPoints - strPoints);
            availableStatPoints =- strPoints;

            System.out.println("Now your Strength is " + strength + strPoints);
            System.out.println("Would you like to further increase it? Y/N");
            output = scanner.nextLine();
            if (output.equalsIgnoreCase("n")) {
                exit = false;
            }
        }
        setStrength(strength + strPoints);

        return strPoints;
    }

    @Override
    public static int increaseDexterity(String fighterName) {
        int dexPoints = 0;
        int availableStatPoints = getAvailableStatPoints();
        int dexterity = getDexterity();
        String output;
        boolean exit = true;

        System.out.println("Currently, your Fighter stats are... ");
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        int[] fighterStats = GSONCreator.getFighterStats(fighterName, jsonObject);

        String stats =
                "Fighter " + fighterName + " stats are:\n" +
                        "Vitality: " + fighterStats[0] + "\n" +
                        "Strength: " + fighterStats[1] + "\n" +
                        "Dexterity: " + fighterStats[2] + "\n";

        System.out.println(stats);

        // The available points are specific to Fighters instances. So this MUST be changed later on. Place-holder for now
        // This really needs testing ><
        while (exit) {
            System.out.println("You currently have " + getAvailableStatPoints() + " available points.");
            System.out.println("How many points would you like to invest in Dexterity? ");
            dexPoints = scanner.nextInt();
            setAvailableStatPoints(availableStatPoints - dexPoints);
            availableStatPoints =- dexPoints;

            System.out.println("Now your Dexterity is " + dexterity + dexPoints);
            System.out.println("Would you like to further increase it? Y/N");
            output = scanner.nextLine();
            if (output.equalsIgnoreCase("n")) {
                exit = false;
            }
        }
        setDexterity(dexterity + dexPoints);

        return dexPoints;
    }
}
