package Tournament.Build;

import java.util.Random;

public class Randomizer {
    private final Random random = new Random();
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
        return randomNumber < 0.3; // So if the random + dexterity added is less than 0.3, it will be true
    }
}
