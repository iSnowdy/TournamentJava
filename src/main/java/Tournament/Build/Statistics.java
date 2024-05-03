package Tournament.Build;

import com.google.gson.JsonObject;

public abstract class Statistics implements StatsManager {

    // Attributes
    // Because the attributes are private, they will only be able to be accessed from other classes
    // with Getters and Setters. Normal ones won't do
    private int vitality;
    private int strength;
    private int dexterity;
    protected int availableStatPoints;
    protected int totalStatPoints;
    private FighterCreation fighterCreation;


    // Abstract class that will eventually be implemented by child classes
    abstract int[] getStats(String fighterName, String filePath); // Abstract method to be implemented on Fighter

    // Constructor

    public Statistics() {
    }

    // Getters & Setters

    public int getVitality() {
        return vitality;
    }
    public void setVitality(int vitPoints) {
        vitality += vitPoints;
    }

    public int getStrength() {
        return strength;
    }
    public void setStrength(int strPoints) {
        strength += strPoints;
    }

    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexPoints) {
        dexterity += dexPoints;
    }

    public int getTotalStatPoints() {
        return totalStatPoints;
    }
    public void setTotalStatPoints(int points) {
        totalStatPoints = points;
    }

    public int getAvailableStatPoints() {
        return availableStatPoints;
    }
    public void setAvailableStatPoints(int points) { // Consider changing the return
        availableStatPoints += points;
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
                statValue = this.getStrength();
                statName = "Dexterity";
            }
        }

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
            System.out.println("You currently have " + this.getAvailableStatPoints() + " available points.");
            System.out.println("How many points would you like to invest in " + statName + "?");
            statPoints = ScannerCreator.nextInt();
            this.setAvailableStatPoints(this.availableStatPoints - statPoints);
            availableStatPoints -= statPoints;

            // Most likely will need a way to check that if there is no stat points available then get out of here
            System.out.println("Now your " + statName + " is " + statValue + statPoints);
            if (this.getAvailableStatPoints() > 0) {
                System.out.println("Would you like to further increase it? Y/N");
                output = ScannerCreator.nextLine();
                if (output.equalsIgnoreCase("n")) {
                    exit = false;
                }
            }
        }

        switch (statType) {
            case VITALITY -> setVitality(statValue + statPoints);
            case STRENGTH -> setStrength(statValue + statPoints);
            case DEXTERITY -> setDexterity(statValue + statPoints);
        }
        return statPoints;
    }
}
