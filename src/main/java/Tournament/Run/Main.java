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
            mainMenu.exitProgram();
        } while (!mainMenu.exitRequest);
    }
}
