package Tournament.Build;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class FighterCreation extends Statistics implements StatsManager {
    private GSONCreator gsonCreator; // To be able to use JSON-related methods
    private JsonObject jsonObject;
    protected final String fighterName;
    protected final String fighterType;
    private Scanner scanner;
    private int choice;
    private boolean exit = true;
    private String rank;
    private final String filePath = "fighterstest.json";

    /*

     Tener 2 constructores. Uno para instanciar en general y otro para
     heredar las estadisticas de Statistics y asi poder acceder a ellas y
     modificarlas?
     Una vez modificadas en esta clase, se guardarían en un JSON que correspondería al jugador ese y solo ese
     Luego, cuando el juego avance, en la primera pelea se cargaría en otra clase ese JSON con las estadisticas
     Esas estadisticas se iran modificando a medida que avance el juego, pero desde otra clase. No esta.
     Esta en concreto es solo para la creacion inicial del PJ / seleccion de uno predefinido

     Dividir Statistics entre 2: una interfaz con los metodos para controlar la ganancia/subida de stats y
     luego una clase abstract que contenga las stats


     Usar ArrayList para almacenar datos para el Log? Nombre luchadores, jugadores, stats, wins/losses
     O HashMap Jugador:Luchador

    */

    public FighterCreation() { // Constructor
        this.scanner = ScannerCreator.getScanner();
    }

    protected void printFighterWelcomeMessage() {
        System.out.println("================================================================");
        System.out.println("|                 Welcome to the Fighter Creator              |");
        System.out.println("================================================================");
        System.out.println("|      Here you will select if you want to create a custom    |");
        System.out.println("|      Fighter or if you want to pick an already built one    |");
        System.out.println("================================================================");
        System.out.println("|                     1. Customized Fighter                   |");
        System.out.println("|                     2. Pre created Fighter                  |");
        System.out.println("|                     3. Statistics explanation               |");
        System.out.println("================================================================");
    }

    private int chooseOption() { // Similar method to the one in Opponent. Re factor?
        while (exit) {
            printFighterWelcomeMessage();
            try {
                choice = scanner.nextInt();

                while (!(1 <= choice && choice <= 3)) {
                    System.out.println("Come on man. Type something between 1 - 3 for a proper option.");
                    choice = scanner.nextInt();
                }
            } catch (Exception exception01) { //
                System.out.println("Wrong input type. Please tpye in a number between 1 - 3");
                scanner.next();
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Place holder. Method here");
                    ScannerCreator.closeScanner();
                    exit = false;
                }
                case 2 -> {
                    System.out.println("Place holder. Another method here");
                    ScannerCreator.closeScanner();
                    exit = false;
                }
                case 3 -> { // File reading for the instructions associated with this menu. txt should be inside the project directory
                    System.out.println("------------------------------------------------------------------------------------");

                    int startLine = 17; // Modify if anything is added to the instructions.txt
                    int endLine = 40;

                    try {
                        FileReader fileReader = new FileReader("Instructions.txt");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        String line;
                        int lineNumber = 0;

                        while ((line = bufferedReader.readLine()) != null && lineNumber < startLine) {
                            lineNumber ++;
                        }
                        while ((line = bufferedReader.readLine()) != null && lineNumber < endLine) {
                            lineNumber ++;
                            System.out.println(line);
                        }
                        bufferedReader.close();
                    } catch (IOException exception02) {
                        System.err.println("Error while reading the file. Details: " + exception02.getMessage());
                        System.out.println("Information: \n");
                        exception02.printStackTrace();
                    }
                    System.out.println("------------------------------------------------------------------------------------");
                }
            }
        }
    return choice;
    }

    private void loadFighter() {
        System.out.println("You have decided to load an already existing Fighter");
        System.out.println("You are able to decide the Rank of the Fighter you would like to load. However," +
                "take into account that the higher the Rank, the harder the fights will be.\n" +
                "Finally, when fighting against higher ranked Fighters, if you suffer a defeat, you will lose\n" +
                "all of your Rank Points");

        do {
            System.out.println("The current available Fighters are:\n");
            gsonCreator.readFile(filePath); // We call the method to read the whole JSON first

            System.out.println("Out of those fighters, what Fighter Rank are you willing to load?");
            String rank = scanner.nextLine();
            System.out.println("Confirm Selection. Are you sure you want to load this Fighter? Type Y/N");
            String decision = scanner.nextLine();
            if (Objects.equals(decision.toLowerCase(), "Y")) {
                jsonObject = gsonCreator.loadFile(filePath);
                gsonCreator.getFighterByRank(rank, jsonObject);
                this.rank = rank;
                exit = false;
                // Here we must implement somehow a way to load the characteristics of this Fighter and
                // assign it to the user
            }
        } while (exit);
    }

    public String getRank() {
        return rank;
    }


}
