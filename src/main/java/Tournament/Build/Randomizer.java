package Tournament.Build;

import java.util.HashSet;
import java.util.Random;

public class Randomizer {
    private final Random random = new Random();
    private final String arrowCombination = "↑→↓←";
    /*
    This class must handle:
        - Percentages
        - Dexterity: chance to dodge
        - What the CPU will do:
            - Attack
            - Defend
            - Chance of Winning the KeyListener MiniGame
            - Chance of Winning the Rock-Papers-Scissors MiniGame
        - Shuffle the Arrows Input
        -
     */

    // Randomize CPU choice for the Rock-Papers-Scissors MiniGame
    public String turnRandomizer() {
        String[] options = {"rock", "paper", "scissors"};
        int randomIndex = random.nextInt(options.length);
        return options[randomIndex];
    }

    public boolean generate10Probability() {
        double randomNumber = random.nextDouble();
        return randomNumber < 0.1; // So if the random was less than 0.1, meaning 10%, it will be true
    }

    public boolean dodgeChance(int dexterity) {
        double randomNumber = random.nextDouble();;
        randomNumber *= (dexterity/10.0);
        return randomNumber < 0.1; // So if the random + dexterity added is less than 0.3, it will be true
    }

    public int timerGenerator(String rank, int dex) {
        int timer;
        switch (rank.toUpperCase()) {
            case "CLOWN":
                timer = 20;
                break;
            case "ALMOST_HUMAN":
                timer = 15;
                break;
            case "EXPERT_FIGHTER":
                timer = 10;
                break;
            case "SENSEI":
                timer = 8;
                break;
            case "MAA":
                timer = 5;
                break;
            default:
                System.out.println("Error. Giving default value");
                timer = 10;
                break;
        }
        return (int) (timer + (dex * 0.1)); // Every point in dexterity gives a boost of 0.1 seconds
    }

    public String arrowGenerator(String rank) {
        StringBuilder randomizedArrows = new StringBuilder();
        int maxInputs;

        switch (rank.toUpperCase()) {
            case "CLOWN":
                maxInputs = 5;
                break;
            case "ALMOST_HUMAN":
                maxInputs = 7;
                break;
            case "EXPERT_FIGHTER":
                maxInputs = 10;
                break;
            case "SENSEI":
                maxInputs = 15;
                break;
            case "MAA":
                maxInputs = 20;
                break;
            default:
                System.out.println("Error. Giving default value");
                maxInputs = 10;
                break;
        }
        for (int i = 0; i < maxInputs; i++) {
            int index = this.random.nextInt(this.arrowCombination.length()); // Random index
            randomizedArrows.append(this.arrowCombination.charAt(index));
            if (i < maxInputs - 1) {  // Add space only if not the last element
                randomizedArrows.append(" ");
            }
        }
        return randomizedArrows.toString();
    }
}
