package Tournament.Build;

import com.google.gson.JsonObject;

class Fighter extends Statistics implements Actions {

    // Attributes
    private String fighterName;
    private String username;
    private int currentHP; // Fighters always start at full HP
    private int currentSTR;
    private int currentDEX;
    private String fighterRank; // Consider changing to final
    private int rankPoints;
    private boolean fightResult;
    private boolean action;

    private JsonObject jsonObject;

    private Randomizer randomizer;
    private Ranking ranking;
    private Log log;
    private TurnMiniGame turnMiniGame;


    // Constructors
    public Fighter() {}
    public Fighter (String fighterName, String username, int currentHP, int currentSTR, int currentDEX, int rankPoints, String fighterRank) {
        super(fighterName, username, currentHP, currentSTR, currentDEX, rankPoints);
        this.fighterRank = ranking.getFighterRank();
    }

    // Getters and Setters

    public String getFighterName() {
        return fighterName;
    }

    public void setFighterName(String fighterName) {
        this.fighterName = fighterName;
    }

    public int getCurrentHealth() {
        return currentHP;
    }

    // Abstract method from Statistics
    public int[] getStats(String fighterName) {
        jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        return GSONCreator.getFighterStats(fighterName, jsonObject);
    }
    public void showStats() {

        System.out.println("******* SHOWING STATS *******");
        System.out.println("Your fighter " + this.fighterName + " statistics are:");
        System.out.println("Vitality: " + this.currentHP);
        System.out.println("Strength: " + this.currentSTR);
        System.out.println("Dexterity: " + this.currentDEX);

        System.out.println("Furthermore, you have a total of " + getTotalStatPoints() +
                "stat points. Of those, you have " + getAvailableStatPoints()
                + " available stat points to spend");

        System.out.println("******* SHOWING STATS *******");
    }

    public static String declareWinner(Fighter firstFighter, Fighter secondFighter, String firstAttacker) {

        Fighter currentAttacker = firstAttacker.equals(firstFighter.getFighterName()) ? firstFighter : secondFighter;
        Fighter currentDefender = currentAttacker == firstFighter ? secondFighter : firstFighter;

        // Like this we will manage the turns

        while (currentAttacker.getCurrentHealth() > 0 && currentDefender.getCurrentHealth() > 0) {
            currentDefender.currentHP -= currentAttacker.getStrength(); // Attack / Defense Method would be introduced here

            // Roles are swapped. This way we will simulate a turn based fight
            Fighter temp = currentAttacker;
            currentAttacker = currentDefender;
            currentDefender = temp;

        }

        if (firstFighter.currentHP <= 0) return secondFighter.fighterName;
        else return firstFighter.fighterName;
    }

    @Override
    public void attack(Fighter fighter) {

    }

    @Override
    public void defend(Fighter fighter) {

    }

    @Override
    public boolean specialAttack(Fighter fighter) {
        System.out.println("Amazing! Your Fighter has launched a Special Attack!");

        return false;
    }

    @Override
    public boolean specialDefense(Fighter fighter) {
        return false;
    }

    @Override
    public void dodge(Fighter fighter) {

    }
}
