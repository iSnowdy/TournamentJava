package Tournament.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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
     */

    private Gson gson;

    public GSONCreator() {
        this.gson = new GsonBuilder().setPrettyPrinting().create(); // Line breaks, blanks, spaces, etc
    }



    public JsonObject loadFile(String filePath) { // return the JSON
        try {
            FileReader fileReader = new FileReader(filePath);
            return gson.fromJson(fileReader, JsonObject.class);
        } catch (IOException exception00) {
            System.err.println("Error while loading JSON File.\n" + exception00.getMessage());
            exception00.printStackTrace();
            return null;
        }
    }

    public void saveFile(JsonObject jsonObject, String filePath) { // Given the JSON we want to save and the Filepath ...
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
    public void readFile(String filePath) { // Reads the whole JSON
        try {
            FileReader fileReader = new FileReader(filePath);
            System.out.println("Reading JSON... \n\n");
            System.out.println(gson.toJson(fileReader, JsonObject.class));
            fileReader.close();
        } catch (IOException exception02) {
            System.err.println("Error while reading the entirety JSON file\n" + exception02.getMessage());
            exception02.printStackTrace();
        }
    }

    public void readFile(String name, JsonObject jsonObject) { // Reads a specific Fighter
        int counter = 0;
        try {
            for (int i = 0; i < jsonObject.getAsJsonArray("Fighters").size(); i ++) {
                JsonObject fighter = jsonObject.getAsJsonArray("Fighters").get(i).getAsJsonObject();
                if (Objects.equals(fighter.get("Name").getAsString(), name)) {
                    System.out.println("Fighter " + name + " details are:\n");
                    System.out.println("=====================================");
                    for (String key : fighter.keySet()) {
                        System.out.println(key + ": " + fighter.get(key)); // Prints the thingy with format
                    }
                    System.out.println("=====================================");
                    break; // So once it finds the match, it leaves the for iteration
                }
                counter ++;
            }
        } catch (Exception exception03) {
            System.err.println("Error while trying to read " + name + ". Which is Fighter Number "
                    + counter + "\n" + exception03.getMessage());
            exception03.printStackTrace();
        }
    }
    // Although we could also read specific values such as Vitality, Strength & Dexterity, it would be easier
    // to implement those methods using attributes in another class, not loading them from the JSON file. I think. Probably xd

    public void getFighterByRank(String rank, JsonObject jsonObject) {
        int counter = 0;
        try {
            for (int i = 0; i < jsonObject.getAsJsonArray("Fighters").size(); i ++) {
                JsonObject fighter00 =jsonObject.getAsJsonArray("Fighters").get(i).getAsJsonObject();
                System.out.println("=====================================");
                if (Objects.equals(fighter00.get("Rank").getAsString(), rank)) {
                    System.out.println("Fighter of Rank " + rank + " details are: \n");
                    for (String key : fighter00.keySet()) {
                        System.out.println(key + ": " + fighter00.get(key));
                    }
                    System.out.println();
                }
                System.out.println("=====================================");
            }
        } catch (Exception exception04) {
            System.err.println("Error while parsing\n" + exception04.getMessage());
            exception04.printStackTrace();
        }
    }
}
