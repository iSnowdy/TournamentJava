package Tournament.Build;

import com.google.gson.JsonObject;

class Fighter extends Statistics implements Actions {

    // Attributes

    private String userName1;
    private String fighterName1;
    private String userName2;
    private String fighterName2;
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
    private final FighterCreation fighterCreation = new FighterCreation();


    // Constructors
    public Fighter() {}
    // One user
    public Fighter (String userName1, String fighterName1, int currentHP, int currentSTR, int currentDEX, int rankPoints, String fighterRank) {
        super(userName1, fighterName1, currentHP, currentSTR, currentDEX, rankPoints);
        this.fighterRank = fighterRank;
        System.out.println(toString());

        System.out.println("Hello");
    }

    // Two users

    // Getters and Setters

    public String getFighterName1() {
        return fighterName1;
    }

    public void setFighterName(String fighterName) {
        this.fighterName1 = fighterName;
    }

    public int getCurrentHealth() {
        return currentHP;
    }
    public int getCurrentSTR() {return currentSTR;}
    public int getCurrentDEX() {return currentDEX;}









    // Abstract method from Statistics
    public int[] getStats(String fighterName) {
        jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        return GSONCreator.getFighterStats("FighterName", fighterName, jsonObject);
    }



    public static String declareWinner(Fighter firstFighter, Fighter secondFighter, String firstAttacker) { // firstAttacker calls Randomizer

        // Like this we will manage the turns
        Fighter currentAttacker = firstAttacker.equals(firstFighter.getFighterName1()) ? firstFighter : secondFighter;
        Fighter currentDefender = currentAttacker == firstFighter ? secondFighter : firstFighter;

        // Now we need to implement the MiniGame and the Damage to HP
        // If the MiniGame is done properly, then the current attacker/defender can attack/defend
        // Call the attack/dodge methods accordingly

        while (currentAttacker.getCurrentHealth() > 0 && currentDefender.getCurrentHealth() > 0) {
            currentDefender.currentHP -= currentAttacker.getStrength();

            // Roles are swapped. This way we will simulate a turn based fight
            Fighter temp = currentAttacker;
            currentAttacker = currentDefender;
            currentDefender = temp;

        }

        if (firstFighter.currentHP <= 0) {
            return secondFighter.fighterName1;
        } else {
            return firstFighter.fighterName1;
        }
    }

    @Override
    public int attack() {
        int healthPoints = this.getCurrentHealth();
        int damageDealt = this.getCurrentSTR();
        int dexterityPoints = this.getCurrentDEX();

        if (randomizer.generate10Probability()) {
            System.out.println("A critical hit!");
            return damageDealt * 2;
        } else {
            return damageDealt;
        }
    }

    @Override
    public void defend() {}

    // Consider adding another method. Sort of a "Special Attack" move. This movement would be unlocked with Achievement points

    @Override
    public boolean specialAttack() {
        System.out.println("Amazing! Your Fighter has launched a Special Attack!");

        return false;
    }

    @Override
    public boolean specialDefense() {
        return false;
    }

    @Override
    public int dodge(int damageReceived) {
        int healthPoints = this.getCurrentHealth();
        int damageDealt = this.getCurrentSTR();
        int dexterityPoints = this.getCurrentDEX();

        int damageTaken = damageReceived;

        if (randomizer.dodgeChance(dexterityPoints)) {
            System.out.println("So agile! Your Fighter has dodged that attack");
            return damageTaken = 0;
        } else {
            return damageTaken;
        }
    }

    // Update the type and ranking. These methods must be called each time the Fighter does a levelUp method
    public String typeUpdate(String userName) {
        return fighterCreation.setFighterType(userName);
    }
    public String rankingUpdate(String userName, int rankPoints) {
        return ranking.setRankingPoints(userName, rankPoints);
    }

    // Consider changing the showStats abstract to just a toString
    @Override
    public String toString() {
        return "******* SHOWING STATS *******\n" +
                "Your fighter " + this.fighterName1 + " statistics are:\n" +
                "Vitality: " + this.currentHP + "\n" +
                "Strength: " + this.currentSTR + "\n" +
                "Dexterity: " + this.currentDEX + "\n" +
                "Furthermore, you have a total of " + getTotalStatPoints() +
                " stat points. Of those, you have " + getAvailableStatPoints() +
                " available stat points to spend\n" +
                "******* SHOWING STATS *******\n";
    }
}
