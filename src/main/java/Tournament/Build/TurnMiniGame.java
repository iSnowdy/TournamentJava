package Tournament.Build;

public class TurnMiniGame {
    private final String rules = "rockscissorspaperrockscissorspaper";

    public String declareWinner(String player1choice, String player2choice) {
        String output;

        String result = player1choice.equals(player2choice) ? "Draw!" : "Player " + (rules.contains(player1choice + player2choice) ? 1 : 2) + " Won!";
        output = player1choice.equals(player2choice) ? "Draw" : rules.contains(player1choice + player2choice) ? "Player1" : "Player2";

        // Re factor this later on to show the UserName / CPU
        System.out.println(result);
        return output;
    }

    /*
    Things to take into account here:
        - return the username
        - if it is the CPU playing, then what? -> draw the random choice of the CPU from the Randomizer
     */
}
