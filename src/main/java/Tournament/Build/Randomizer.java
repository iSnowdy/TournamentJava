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
}
