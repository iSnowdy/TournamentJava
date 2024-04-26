package Tournament.Build;

public abstract class Statistics {

    // Attributes
    private static int vitality = 1;
    private static int strength = 1;
    private static int dexterity = 1;
    protected int availableStatPoints;
    protected int totalStatPoints;

    String statsDescription =
            "";

    // Abstract classes that will be implemented by child classes

    /* Added to interface
    abstract int increaseVitality();
    abstract int increaseStrength();
    abstract int increaseDexterity();
     */
    abstract void showStats();


    // Constructor

    public Statistics (int vitality, int strength, int dexterity) {
        this.vitality = vitality;
        this.strength = strength;
        this.dexterity = dexterity;
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

    public String getStatsDescription() {
        return statsDescription;
    }

    // Specific methods



}
