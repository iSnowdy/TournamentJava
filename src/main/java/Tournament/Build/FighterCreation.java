package Tournament.Build;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class FighterCreation extends Statistics implements StatsManager {
    private final GSONCreator gsonCreator; // To be able to use JSON-related methods
    private Opponent opponent;
    private JsonObject jsonObject;
    protected final String fighterName;
    protected final String fighterType;
    private Scanner scanner;
    private int choice;
    private boolean exit = true;
    private String rank;
    private final String filePath = "fighterstest.json";
    private HashMap hashMap;
    Statistics stats;

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


    public FighterCreation() {
        this.scanner = ScannerCreator.getScanner();
        this.hashMap = new HashMap<>();
        this.gsonCreator = new GSONCreator();

        chooseOption();

        System.out.println("Now that you have acquired a Fighter, lets name it: ");
        this.fighterName = scanner.nextLine();
        this.fighterType = setFighterType(fighterName);
        System.out.println("Your username and Fighter name will be added to the Log");
        hashMap.put(opponent.getUserName1(), fighterName);


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
                System.out.println("Wrong input type. Please type in a number between 1 - 3");
                scanner.next();
                continue;
            }
            switch (choice) {
                case 1 -> {
                    System.out.println("Place holder. Method here");
                    createFighter();
                    ScannerCreator.closeScanner();
                    exit = false;
                }
                case 2 -> {
                    System.out.println("Place holder. Another method here");
                    loadFighter();
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
                gsonCreator.getFighterByString("Rank", rank, jsonObject);
                this.rank = rank;
                exit = false;
                // Here we must implement somehow a way to load the characteristics of this Fighter and
                // assign it to the user
            }
        } while (exit);
    }

    private void createFighter() {
        int vitPoints = 0;
        int strPoints = 0;
        int dexPoints = 0;

        System.out.println("You have decided to create a Fighter from scratch");
        System.out.println("You will be given a fixed amount of Statistics (stats) points to" +
                "distribute amongst all three different types of stats. Choose wisely where to put" +
                "your stats as you won't be able to respec later on");
        System.out.println("Your Fighter will start with all stats at 1");

        // I'm not sure if this should be here or not. The attribute is inherited from the Statistics class
        setTotalStatPoints(10);
        setAvailableStatPoints(7); // 3 points are already in each stat

        while (getAvailableStatPoints() != 0) { // Needs to be tested

            System.out.println("You currently have " + getAvailableStatPoints() + " available points.");
            System.out.println("How many points would you like to invest in Vitality? ");
            vitPoints = scanner.nextInt();
            setAvailableStatPoints(getAvailableStatPoints() - vitPoints);
            System.out.println("You know have " + getAvailableStatPoints() + " available points");

            System.out.println("How many points would you like to invest in Strength? ");
            strPoints = scanner.nextInt();
            setAvailableStatPoints(getAvailableStatPoints() - strPoints);
            System.out.println("You know have " + getAvailableStatPoints() + " available points");

            System.out.println("How many points would you like to invest in Dexterity? ");
            dexPoints = scanner.nextInt();
            setAvailableStatPoints(getAvailableStatPoints() - dexPoints);
            System.out.println("You know have " + getAvailableStatPoints() + " available points");

            ScannerCreator.closeScanner();
        }

        // We add the Fighter to the JSON File. But it is still not created inside the program! (Fighter class)
        gsonCreator.addNewFighter(fighterName, rank, vitPoints + 1, strPoints + 1, dexPoints + 1);
        // Heavily consider changing the way parameters are given. I don't like adding that + 1 there

    }

    public HashMap getHashMap() {
        for (Object username : hashMap.keySet()) {
            System.out.println("Username: " + username + " | Fighter: " + hashMap.get(username));
            break; // So we only print the last entry of the HashMap; which is the first print of the List
        }
        return hashMap;
    }

    // Abstract method from Statistics
    @Override
    public int[] getStats(String fighterName, String filePath) {
        jsonObject = gsonCreator.loadFile(filePath);

        return gsonCreator.getFighterStats(fighterName, jsonObject);
    }

    // According to the ratio between the stats, a Fighter Type will be adjudicated
    public String setFighterType(String fighterName) { // Take as a parameter if it is the first or second user?
        int[] fighterStats = getStats(fighterName, filePath);

        int vitality = fighterStats[0];
        int strength = fighterStats[1];
        int dexterity = fighterStats[2];
        String fighterType;

        String stats =
                "Fighter " + fighterName + " stats are:\n" +
                        "Vitality: " + vitality + "\n" +
                        "Strength: " + strength + "\n" +
                        "Dexterity: " + dexterity + "\n";

        int totalPoints = vitality + strength + dexterity;
        // Differences between all the stats (absolute)
        double vitalityRatio = (double)vitality / totalPoints;
        double strengthRatio = (double)strength / totalPoints;
        double dexterityRatio = (double)dexterity / totalPoints;
        // Now we determine the FighterType according to the difference between the stats
        if (vitalityRatio >= 0.6) {
            return fighterType = "Tank";
        } else if (strengthRatio >= 0.6) {
            return fighterType = "Glass Cannon";
        } else if (dexterityRatio >= 0.6) {
            return fighterType = "Agile";
        } else if (vitalityRatio >= 0.4) {
            return fighterType = "Vitality Oriented";
        } else if (strengthRatio >= 0.4) {
            return fighterType = "Strength Oriented";
        } else if (dexterityRatio >= 0.4) {
            return fighterType = "Dexterity Oriented";
        } else {
            return fighterType = "Balanced";
        }
    }


    public String getRank() {
        return rank;
    }

    public String getFighterName() {
        return fighterName;
    }

    public String getFighterType() {
        return fighterType;
    }


}
