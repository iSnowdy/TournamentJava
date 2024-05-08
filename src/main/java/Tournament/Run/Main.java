package Tournament.Run;

import Tournament.Build.FighterCreation;
import Tournament.Build.MainMenu;
import Tournament.Build.Opponent;

import java.util.Objects;

public class Main {
    public static void main (String[] args) {

        System.out.println("====================================================");
        System.out.println("|           WELCOME TO THE ARROW TOURNAMENT!      |");
        System.out.println("====================================================");
        System.out.println("|           In this minigame you will face        |");
        System.out.println("|           several opponents via a minigame      |");
        System.out.println("|           We wish you the best of luck!         |");
        System.out.println("|           May the fastest typer win >:D         |");
        System.out.println("====================================================");
        System.out.println();
        System.out.println();

        // Welcome message


        MainMenu mainMenu;
        do {
            mainMenu = new MainMenu();
            /*Opponent opponent = new Opponent();
            String username1 = opponent.getUserName1();
            String username2 = opponent.getUserName2();

            FighterCreation fighterCreation;
            // This is a way to handle if we are going to be playing against one or two players
            if (!Objects.equals(username1, "default") && !Objects.equals(username2, "CPU")) {
                System.out.println(username1);
                System.out.println(username2);
                System.out.println("Two users");
                fighterCreation = new FighterCreation(opponent.getUserName1(), opponent.getUserName2());
                mainMenu.chooseOption();
            } else if (!Objects.equals(username1, "default") || !Objects.equals(username2, "CPU")) {
                System.out.println(username1);
                System.out.println(username2);
                System.out.println("One user");
                fighterCreation = new FighterCreation(opponent.getUserName1());
                mainMenu.chooseOption();
            }
*/
            mainMenu.exitProgram();
        } while (!mainMenu.exitRequest);
    }
}
