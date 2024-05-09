package Tournament.Build;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class GSONCreator {
    /*
    The purpose of this class is to:
        - Instantiate GSON class
        - Load .json files
        - Read
        - Modify
        - Print specific String/int of the .json file?
        - Save changes made to the file itself (so we don't keep the changes in the Object memory)


    Consider re factoring all of these methods to static. It should be a good practice since:

        - It is used in a lot of classes across the project
        - We would not need to create a new object of GSON every time we use it (which is the main purpose of this class)
        - We would be able to access this class in static methods
     */

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Line breaks, blanks, spaces, etc
    protected static String filepathJSON1 = "fighterstest.json";
    protected static String filepathJSON2 = "tournamentlog.json";

    public GSONCreator() {}

    public static JsonObject loadFile(String filePath) { // return the JSON
        try {
            FileReader fileReader = new FileReader(filePath);
            return gson.fromJson(fileReader, JsonObject.class);
        } catch (IOException exception00) {
            System.err.println("Error while loading JSON File.\n" + exception00.getMessage());
            exception00.printStackTrace();
            return null;
        }
    }

    public static void saveFile(JsonObject jsonObject, String filePath) { // Given the JSON we want to save and the Filepath ...
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            gson.toJson(jsonObject, fileWriter);
            fileWriter.close();
        } catch (IOException exception01) {
            System.err.println("Error while saving the JSON File.\n" + exception01.getMessage());
            exception01.printStackTrace();
        }
    }
    // Polymorphism
    public static void readFile(String filePath) { // Reads the whole JSON
        try {
            FileReader fileReader = new FileReader(filePath);
            JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);
            System.out.println("Reading JSON... \n\n");
            System.out.println(gson.toJson(jsonObject)); // Must print the object, not the fileReader too KEK
            fileReader.close();
        } catch (IOException exception02) {
            System.err.println("Error while reading the entirety JSON file\n" + exception02.getMessage());
            exception02.printStackTrace();
        }
    }

    public static void getFighterByString(String desiredFeature, String feature, JsonObject jsonObject) {
        int counter = 0; // Maybe we don't need it
        try {
            System.out.println("=====================================\n");
            for (int i = 0; i < jsonObject.getAsJsonArray("Fighters").size(); i ++) {
                JsonObject fighter = jsonObject.getAsJsonArray("Fighters").get(i).getAsJsonObject();
                if (Objects.equals(fighter.get(desiredFeature).getAsString(), feature)) { // Example: string = Rank, desiredString = One
                    System.out.println("Fighter of " + feature + " details are: \n");
                    for (String key : fighter.keySet()) {
                        System.out.println(key + ": " + fighter.get(key));
                    }
                    System.out.println();
                }
            }
            System.out.println("=====================================\n");
        } catch (Exception exception04) {
            System.err.println("Error while parsing\n" + exception04.getMessage());
            exception04.printStackTrace();
        }
    }

    public static int[] getFighterStats(String desiredFeature, String feature, JsonObject jsonObject) { // Retrieves the stats of a specific Fighter
        int[] fighterStats = new int[3];
        int size = jsonObject.getAsJsonArray("Fighters").size();

        try {
            for (int i = 0; i < size; i ++) {
                JsonObject fighter = jsonObject.getAsJsonArray("Fighters").get(i).getAsJsonObject();
                if (Objects.equals(fighter.get(desiredFeature).getAsString(), feature)) {
                    fighterStats[0] = fighter.get("Vitality").getAsInt();
                    fighterStats[1] = fighter.get("Strength").getAsInt();
                    fighterStats[2] = fighter.get("Dexterity").getAsInt();
                }
            }
        } catch (Exception exception03) {
            System.err.println("Error while trying to load " + desiredFeature + " stats.\n" + exception03.getMessage());
            exception03.printStackTrace();
        }

        return fighterStats;
    }

    // Method to see if the Fighter has already been added to the JSON file. Not the UserName
    // because maybe the same person wants to play multiple times
    public static boolean fighterExists(JsonObject newFighter, JsonArray allFighters) {
        for (int i = 0; i < allFighters.size(); i++) {
            JsonObject fighter = allFighters.get(i).getAsJsonObject();
            if (fighter.get("FighterName").getAsString().equals(newFighter.get("FighterName").getAsString())) {
                System.out.println("Fighter already exists");
                return true;
            }
        }
        return false;
    }

    public static void removeFighter(String fighterName, JsonObject jsonObject) {// Retrieves the stats of a specific Fighter
        JsonArray fightersArray = jsonObject.getAsJsonArray("Fighters");

        for (int i = 0; i < fightersArray.size(); i++) {
            JsonObject fighterObject = fightersArray.get(i).getAsJsonObject();
            if (fighterObject.get("FighterName").getAsString().equals(fighterName)) {
                fightersArray.remove(i);
                System.out.println("Fighter has been successfully removed from the JSON File");
                break;
                // Once it has been found & removed, we leave the iteration
                // Maybe modify later on so it removes all instances?
            }
        }
    }

    public static void addNewFighter(String username, String fighterName, String rank, int vitality, int strength, int dexterity) {
        String filePath = "fighterstest.json";
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        JsonObject newFighter = new JsonObject();

        newFighter.addProperty("UserName", username);
        newFighter.addProperty("FighterName", fighterName);
        newFighter.addProperty("Rank", rank);
        newFighter.addProperty("Type", ""); // Think about this. How to input the proper Type
        newFighter.addProperty("Vitality", vitality);
        newFighter.addProperty("Strength", strength);
        newFighter.addProperty("Dexterity", dexterity);

        JsonArray fighterArray = jsonObject.getAsJsonArray("Fighters");

        // Verification if the Fighter exists already
        if (!GSONCreator.fighterExists(newFighter, fighterArray)) { // if false -> add
            FighterCreation fighterCreation = new FighterCreation();
            String type = fighterCreation.setFighterType(fighterName);
            newFighter.addProperty("Type", type);
            fighterArray.add(newFighter);
            System.out.println("Fighter has been successfully added to the JSON File");
        } else { // if true -> exists; remove
            GSONCreator.removeFighter(fighterName, jsonObject);
            System.out.println("Fighter exists already. Elimination completed");
        }
        // Is the removal really needed?

        GSONCreator.saveFile(jsonObject, filePath); // Crucial to save the changes made to the file
        GSONCreator.readFile(filePath); // Print it. Maybe not needed
    }

    // Modifies specific parameters of a Fighter (previous methods were adding a whole new Object to the JSON
    // file instead of editing an already existing one
    public static void updateFighterStat(String fighterName, String stat, int value) {
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        JsonArray fighterArray = jsonObject.getAsJsonArray("Fighters");

        for (JsonElement fighterElement : fighterArray) {
            JsonObject fighter = fighterElement.getAsJsonObject();
            if (fighter.get("FighterName").getAsString().equals(fighterName)) {
                fighter.addProperty(stat, value);
                break;
            }
        }
        // Save changes
        GSONCreator.saveFile(jsonObject, GSONCreator.filepathJSON1);
    }

    // Modifies the Rank in the JSON
    public static void updateFighterRank(String fighterName, String newRank) {
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
        JsonArray fighterArray = jsonObject.getAsJsonArray("Fighters");

        for (JsonElement fighterElement : fighterArray) {
            JsonObject fighter = fighterElement.getAsJsonObject();
            if (fighter.get("FighterName").getAsString().equals(fighterName)) {
                fighter.addProperty("Rank", newRank);
                break;
            }
        }
        // Save changes
        GSONCreator.saveFile(jsonObject, GSONCreator.filepathJSON1);
    }

    // This will loop through any JSON in the Project and return the index of the Object of the Array we are
    // looping through. The search parameter can be any
    public static int getIndex(String desiredFeature, String feature, int desiredJSON) {
        switch (desiredJSON) {
            case 1 -> {
                JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON1);
                try {
                    for (int i = 0; i < jsonObject.getAsJsonArray("Fighters").size(); i ++) {
                        JsonObject fighter = jsonObject.getAsJsonArray("Fighters").get(i).getAsJsonObject();
                        if (Objects.equals(fighter.get(desiredFeature).getAsString(), feature)) {
                            return i;
                        }
                    }
                } catch (Exception exception05) {
                    System.err.println("Error while parsing\n" + exception05.getMessage());
                    exception05.printStackTrace();
                }
            }
            case 2 -> {
                JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON2);
                try {
                    for (int i = 0; i < jsonObject.getAsJsonArray("UserInfo").size(); i ++) {
                        JsonObject user = jsonObject.getAsJsonArray("UserInfo").get(i).getAsJsonObject();
                        if(Objects.equals(user.get(desiredFeature).getAsString(), feature)) {
                            return i;
                        }
                    }
                } catch (Exception exception06) {
                    System.err.println("Error while parsing\n" + exception06.getMessage());
                    exception06.printStackTrace();
                }
            }
            default -> {
                System.out.println("Wrong JSON File. Please try another one");
                return -1;
            }
        }
        return -1;
    }

    // For the Log. Same as getFighterByString, but for the other JSON. It can be unified with the other one
    // but fuck it
    public static void getLogByString(String desiredFeature, String feature, JsonObject jsonObject) {
        int counter = 0; // Maybe we don't need it
        try {
            System.out.println("=====================================\n");
            for (int i = 0; i < jsonObject.getAsJsonArray("UserInfo").size(); i ++) {
                JsonObject fighter = jsonObject.getAsJsonArray("UserInfo").get(i).getAsJsonObject();
                if (Objects.equals(fighter.get(desiredFeature).getAsString(), feature)) { // Example: string = Rank, desiredString = One
                    System.out.println(feature + " details are: \n");
                    for (String key : fighter.keySet()) {
                        System.out.println(key + ": " + fighter.get(key));
                    }
                    System.out.println();
                }
            }
            System.out.println("=====================================\n");
        } catch (Exception exception04) {
            System.err.println("Error while parsing\n" + exception04.getMessage());
            exception04.printStackTrace();
        }
    }
}
