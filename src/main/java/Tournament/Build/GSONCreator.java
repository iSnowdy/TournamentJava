package Tournament.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;

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

    public JsonObject loadFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            return gson.fromJson(fileReader, JsonObject.class);
        } catch (IOException exception00) {
            System.err.println("Error while loading JSON File.\n" + exception00.getMessage());
            exception00.printStackTrace();
            return null;
        }
    }

}
