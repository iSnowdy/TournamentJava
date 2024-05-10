package Tournament.Build;

import com.google.gson.JsonObject;

import java.util.Objects;

class Fighter extends Statistics implements Actions {

    // Attributes

    private String fighterRank; // Consider changing to final
    private int rankPoints;
    private boolean fightResult;
    private boolean action;
    private int wins;
    private int defeats;


    private final Randomizer randomizer;
    private Ranking ranking;
    private Log log;
    private TurnMiniGame turnMiniGame;
    private final FighterCreation fighterCreation = new FighterCreation();


    // Constructors
    public Fighter() {
        this.randomizer = new Randomizer();
    }
    // One user
    public Fighter (String userName1, String fighterName1, int vitality, int strength, int dexterity, int rankPoints, String fighterRank) {
        super(userName1, fighterName1, vitality, strength, dexterity, rankPoints);
        this.fighterRank = fighterRank;

        this.randomizer = new Randomizer();
        System.out.println("Fighter " + fighterName1 + " has been created\n");
    }

    // Two users

    // Getters and Setters

    public String getFighterRank() {
        return this.fighterRank;
    }
    public void setFighterRank(String fighterRank) {
        this.fighterRank = fighterRank;
    }
    public int getWins() {
        return this.wins;
    }
    public void setWins(int wins) {
        this.wins += wins;
    }

    public int getDefeats() {
        return this.defeats;
    }
    public void setDefeats(int defeats) {
        this.defeats += defeats;
    }


    // Abstract method from Statistics
    public int[] getStats(String fighterName) {
        JsonObject jsonObject;
        jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        return GSONCreator.getFighterStats("FighterName", fighterName, jsonObject);
    }

    public String declareWinner(Fighter fighter1, Fighter fighter2, String firstAttacker) { // firstAttacker calls Randomizer

        // Like this we will manage who goes first. They are copies of the Objects, but they will modify the OG Object
        Fighter currentAttacker = firstAttacker.equals(fighter1.getUserName1()) ? fighter1 : fighter2;
        Fighter currentDefender = currentAttacker == fighter1 ? fighter2 : fighter1;

        // So we can restore the vitality of the original Object
        int currentAttackerOGHP = currentAttacker.getVitality();
        int currentDefenderOGHP = currentDefender.getVitality();

        String winner;
        String attackerName = currentAttacker.getUserName1();
        String defenderName = currentDefender.getUserName1();

        // Now we need to implement the MiniGame and the Damage to HP
        // If the MiniGame is done properly, then the current attacker/defender can attack/defend
        // Call the attack/dodge methods accordingly

        /*Object sleeper = new Object();
        boolean gameEnd = false;*/

        while (currentAttacker.getVitality() > 0 && currentDefender.getVitality() > 0) {
            int timer = randomizer.timerGenerator(currentAttacker.fighterRank, currentAttacker.getDexterity());
            String generatedArrows = randomizer.arrowGenerator(currentAttacker.fighterRank);
            KeyListenerMinigame minigame = new KeyListenerMinigame((generatedArrows.length() / 2 + 1), generatedArrows, timer);

            // gameEnd = minigame.getGameStatus();
            /*synchronized (sleeper) {
                try {
                    while (!gameEnd) {
                        sleeper.wait(timer * 1000L);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.err.println(exception);
                }
            }*/

            try{
                Thread.sleep(timer * 1000L);
            } catch (Exception exception){
                System.err.println(exception);
            }

            if (minigame.getResult()) {
                if (!(currentDefender.dodge(currentDefender.getDexterity()))) {
                    int damageDealt = currentAttacker.attack(currentAttacker.getStrength());
                    currentDefender.setVitality(currentDefender.getVitality() - damageDealt); // attack method

                    System.out.println(attackerName + " has inflicted " + damageDealt + " on " + defenderName);
                    System.out.println("Now " + defenderName + " has " + currentDefender.getVitality() + " HP remaining");
                } else {
                    System.out.println("Amazing! " + defenderName + " managed to dodge the attack! So he/she wont take any damage");
                }
            } else {
                // System.out.println("Since you have failed to do the MiniGame, " + currentAttacker.getUserName1() + " will be the one taking damage");
                int damageDealt = currentDefender.attack(currentDefender.getStrength());
                currentAttacker.setVitality(currentAttacker.getVitality() - damageDealt); // attack method

                System.out.println(attackerName + " has failed to do the MiniGame!!! So he/she will be taking " + damageDealt + " damage instead of " + defenderName);
                System.out.println("Now " + attackerName + " has " + currentAttacker.getVitality() + " HP remaining");
            }

            // Confirmation of what is going on
            /*System.out.println("Damage dealt by attacker: " + currentAttacker.attack(currentAttacker.getStrength()));
            System.out.println("Damage dealt by defender: " + currentDefender.attack(currentDefender.getStrength()));
            System.out.println("Current attacker HP: " + currentAttacker.getVitality());
            System.out.println("Current defender HP: " + currentDefender.getVitality());*/

            // Here we swap attacker and defender roles
            // Not sure if it works

            Fighter tempFighter = currentAttacker;
            currentAttacker = currentDefender;
            currentDefender = tempFighter;

            String tempFighterName = attackerName;
            attackerName = defenderName;
            defenderName = tempFighterName;
        }
        // Determine winner based on remaining HP before resetting HP
        if (fighter1.getVitality() <= 0) {
            winner = fighter2.getUserName1();
        } else {
            winner = fighter1.getUserName1();
        }

        fighter1.setVitality(currentAttackerOGHP);
        fighter2.setVitality(currentDefenderOGHP);
        return winner;
    }

    @Override
    public int attack(int str) {
        if (randomizer.generate10Probability()) {
            System.out.println("A critical hit!");
            return str * 2;
        } else {
            return str;
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
    public boolean dodge(int dex) {
        if (randomizer.dodgeChance(dex)) {
            System.out.println("DODGE!");
            return true;
        } else {
            System.out.println("Failed to dodge the attack");
            return false;
        }
    }

    // Update the type and ranking. These methods must be called each time the Fighter does a levelUp method
    public String typeUpdate(String fighterName) {
        return fighterCreation.setFighterType(fighterName);
    }
    public String rankingUpdate(String userName, int rankPoints) {
        return ranking.setRankingPoints(userName, rankPoints);
    }

    // Consider changing the showStats abstract to just a toString
    @Override
    public String toString() {
        return "******* SHOWING STATS *******\n" +
                "Your fighter " + this.getFighterName1() + " statistics are:\n" +
                "Vitality: " + this.getVitality() + "\n" +
                "Strength: " + this.getStrength() + "\n" +
                "Dexterity: " + this.getDexterity() + "\n" +
                "Furthermore, you have a total of " + getTotalStatPoints() +
                " stat points. Of those, you have " + getAvailableStatPoints() +
                " available stat points to spend\n" +
                "******* SHOWING STATS *******\n";
    }
}
