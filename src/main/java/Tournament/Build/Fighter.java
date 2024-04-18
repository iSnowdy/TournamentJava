package Tournament.Build;

class Fighter extends Statistics {

    // Attributes
    private String fighterName;
    // private String fighterRank; These two would probably be in the Ranking class
    // private String fighterType;
    private int currentHealth = vitality; // Fighters always start at full HP

    // Constructor
    public Fighter (int vitality, int strength, int dexterity, String fighterName) {
        super(vitality, strength, dexterity);
        this.fighterName = fighterName;
    }

    // Getters and Setters

    public String getFighterName() {
        return fighterName;
    }

    public void setFighterName(String fighterName) {
        this.fighterName = fighterName;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    // Abstract methods implemented to increase the Fighter stats

    public int increaseVitality() {

        return vitality;
    }

    public int increaseStrength() {

        return strength;
    }

    public int increaseDexterity() {

        return dexterity;
    }

    public void showStats() {

        System.out.println("******* SHOWING STATS *******");
        System.out.println("Your fighter " + getFighterName() + " statistics are:");
        System.out.println("Vitality: " + this.getVitality());
        System.out.println("Strength: " + this.getStrength());
        System.out.println("Dexterity: " + this.getDexterity());

        System.out.println("Furthermore, you have a total of " + this.getTotalStatPoints() +
                "stat points. Of those, you have " + this.getAvailableStatPoints()
                + " available stat points to spend");

        System.out.println("******* SHOWING STATS *******");
    }

    public static String declareWinner(Fighter firstFighter, Fighter secondFighter, String firstAttacker) {

        Fighter currentAttacker = firstAttacker.equals(firstFighter.getFighterName()) ? firstFighter : secondFighter;
        Fighter currentDefender = currentAttacker == firstFighter ? secondFighter : firstFighter;

        // Like this we will manage the turns

        while (currentAttacker.getCurrentHealth() > 0 && currentDefender.getCurrentHealth() > 0) {
            currentDefender.currentHealth -= currentAttacker.getStrength();

            // Roles are swapped. This way we will simulate a turn based fight
            Fighter temp = currentAttacker;
            currentAttacker = currentDefender;
            currentDefender = temp;

        }

        if (firstFighter.currentHealth <= 0) return secondFighter.fighterName;
        else return firstFighter.fighterName;
    }
}
