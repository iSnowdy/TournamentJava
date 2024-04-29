package Tournament.Build;

import com.google.gson.JsonObject;

import java.util.Scanner;

public abstract class Statistics implements StatsManager {

    // Attributes
    private static int vitality = 1;
    private static int strength = 1;
    private static int dexterity = 1;
    protected int availableStatPoints;
    protected int totalStatPoints;
    final String filePath = "fighterstest.json";

    private final Scanner scanner;
    private final GSONCreator gsonCreator;
    private FighterCreation fighterCreation;



    // Abstract classes that will be implemented by child classes

    /* Added to interface
    abstract int increaseVitality();
    abstract int increaseStrength();
    abstract int increaseDexterity();
     */
    abstract int[] getStats(String fighterName, String filePath); // Abstract method to be implemented on Fighter

    // Constructor

    public Statistics(GSONCreator gsonCreator) {
        this.scanner = ScannerCreator.getScanner();
        this.gsonCreator = gsonCreator;

    }

    // Getters & Setters

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAvailableStatPoints() {
        return availableStatPoints;
    }

    public void setAvailableStatPoints(int availableStatPoints) {
        this.availableStatPoints = availableStatPoints;
    }

    public int getTotalStatPoints() {
        return totalStatPoints;
    }

    public void setTotalStatPoints(int totalStatPoints) {
        this.totalStatPoints = totalStatPoints;
    }


    // Specific methods

    // Level-Up Methods

    @Override
    public int increaseVitality(String fighterName) {
        int vitPoints = 0;
        int availableStatPoints = getAvailableStatPoints();
        int vitality = getVitality();
        String output;
        boolean exit = true;

        System.out.println("Currently, your Fighter stats are... ");
        JsonObject jsonObject = gsonCreator.loadFile(filePath);
        int[] fighterStats = gsonCreator.getFighterStats(fighterName, jsonObject);

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
    public int increaseStrength(String fighterName) {
        int strPoints = 0;
        int availableStatPoints = getAvailableStatPoints();
        int strength = getStrength();
        String output;
        boolean exit = true;

        System.out.println("Currently, your Fighter stats are... ");
        JsonObject jsonObject = gsonCreator.loadFile(filePath);
        int[] fighterStats = gsonCreator.getFighterStats(fighterName, jsonObject);

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
    public int increaseDexterity(String fighterName) {
        int dexPoints = 0;
        int availableStatPoints = getAvailableStatPoints();
        int dexterity = getDexterity();
        String output;
        boolean exit = true;

        System.out.println("Currently, your Fighter stats are... ");
        JsonObject jsonObject = gsonCreator.loadFile(filePath);
        int[] fighterStats = gsonCreator.getFighterStats(fighterName, jsonObject);

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
