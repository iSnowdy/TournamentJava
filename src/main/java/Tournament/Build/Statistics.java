package Tournament.Build;

import com.google.gson.JsonObject;

import java.util.Objects;

public abstract class Statistics implements StatsManager {

    // Attributes
    // Because the attributes are private, they will only be able to be accessed from other classes
    // with Getters and Setters. Normal ones won't do
    private int vitality;
    private int strength;
    private int dexterity;
    protected int availableStatPoints;
    protected int totalStatPoints;
    private String fighterName1;
    private String fighterName2;
    private String userName1;
    private String userName2;
    private int rankPoints;
    public static final int pointsAwarded = 5; // Always the same? maybe not





    // Abstract class that will eventually be implemented by child classes
    abstract int[] getStats(String fighterName); // Abstract method to be implemented on Fighter
    // abstract void showStats();

    // Constructors

    public Statistics() {
    }

    public Statistics(String userName1, String fighterName1, int vitality, int strength, int dexterity, int rankPoints) {
        this.userName1 = userName1;
        this.fighterName1 = fighterName1;
        this.vitality = vitality;
        this.strength = strength;
        this.dexterity = dexterity;
        this.rankPoints = rankPoints;
    }

    // Getters & Setters

    public String getFighterName1() {
        return fighterName1;
    }

    public void setFighterName1(String fighterName1) {
        this.fighterName1 = fighterName1;
    }

    public String getFighterName2() {
        return fighterName2;
    }

    public void setFighterName2(String fighterName2) {
        this.fighterName2 = fighterName2;
    }

    public String getUserName1() {
        return userName1;
    }

    public String getUserName2() {
        return userName2;
    }

    public int getRankPoints() {
        return rankPoints;
    }

    public void setRankPoints(int rankPoints) {
        this.rankPoints = rankPoints;
    }

    public int getVitality() {
        return vitality;
    }
    public void setVitality(int vitPoints) {
        this.vitality = vitPoints;
    }

    public int getStrength() {
        return strength;
    }
    public void setStrength(int strPoints) {
        this.strength = strPoints;
    }

    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexPoints) {
        this.dexterity = dexPoints;
    }

    public int getTotalStatPoints() {
        return totalStatPoints;
    }
    public void setTotalStatPoints(int availableStatPoints) {
        totalStatPoints += availableStatPoints;
    }

    public int getAvailableStatPoints() {
        return availableStatPoints;
    }
    public void setAvailableStatPoints(int points) { // Consider changing the return
        this.availableStatPoints = points;
    }

    // Level-Up Method
    // This method *should* increase any kind of Stat that the user inputs in the MainMenu Class
    @Override
    public int increaseStat(String fighterName, StatType statType) {
        int statPoints = 0;
        int availableStatPoints = this.getAvailableStatPoints();
        int statValue = 0;
        String statName = "";
        String output;
        boolean exit = true;

        switch (statType) {
            case VITALITY -> {
                statValue = this.getVitality();
                statName = "Vitality";
            }
            case STRENGTH -> {
                statValue = this.getStrength();
                statName = "Strength";
            }
            case DEXTERITY -> {
                statValue = this.getDexterity();
                statName = "Dexterity";
            }
        }

        System.out.println("Inside the method..... Stat value: " + statValue + ". Avail points: " + availableStatPoints);

        System.out.println("Currently, your Fighter stats are... ");
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        int[] fighterStats = GSONCreator.getFighterStats("FighterName",fighterName, jsonObject);

        String stats =
                "Fighter " + fighterName + " stats are:\n" +
                        "Vitality: " + fighterStats[0] + "\n" +
                        "Strength: " + fighterStats[1] + "\n" +
                        "Dexterity: " + fighterStats[2] + "\n";

        System.out.println(stats);

        // This really needs testing ><
        while (exit) {
            System.out.println("You currently have " + this.getAvailableStatPoints() + " available points.");
            System.out.println("How many points would you like to invest in " + statName + "?");
            ScannerCreator.nextLine();
            statPoints = ScannerCreator.nextInt();
            this.setAvailableStatPoints(this.getAvailableStatPoints() - statPoints);
            availableStatPoints -= statPoints;

            // Most likely will need a way to check that if there is no stat points available then get out of here
            System.out.println("Now your " + statName + " is " + statValue + statPoints);
            if (this.getAvailableStatPoints() > 0) {
                System.out.println("Would you like to further increase it? Y/N");
                ScannerCreator.nextLine();
                output = ScannerCreator.nextLine();
                if (Objects.equals(output.toLowerCase(), "n")) {
                    exit = false;
                }
            }
        }

        switch (statType) {
            case VITALITY -> this.setVitality(statValue + statPoints);
            case STRENGTH -> this.setStrength(statValue + statPoints);
            case DEXTERITY -> this.setDexterity(statValue + statPoints);
        }
        return statPoints;
    }
}
