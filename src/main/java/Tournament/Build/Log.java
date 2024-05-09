package Tournament.Build;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Log {
    public Log() {}

    void showLog() {
        GSONCreator.readFile(GSONCreator.filepathJSON2);
    }

    // Overloaded method
    void showLog(String username) {
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON2);
        GSONCreator.getLogByString("UserName", username, jsonObject); // username /= fighterName. This must be changed later on
    }

    // This is supposed to add the user info once he/she participates in at least 1 fight
    // And will only be used once. Then after that, the updateLog method will take over
    // So a condition must be added later on the class this will be used to differentiate 1st instance vs updates
    void addToLog(String username, String fighterName, int wins, int loses, int rankingPoints) { // Parameters for Opponent missing
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON2);
        JsonObject newUserInfo = new JsonObject();
        JsonObject newOpponentInfo = new JsonObject();
        JsonArray opponentsArray = new JsonArray(); // Cuz Opponents is an Array

        newUserInfo.addProperty("UserName", username);
        newUserInfo.addProperty("FighterName", fighterName);
        newUserInfo.addProperty("Wins", wins); // Must change this later on to getWins from Fighter class
        newUserInfo.addProperty("Loses", loses); // Same change needed here
        newUserInfo.addProperty("Ranking Points", rankingPoints);
        // Consider also adding FighterRank to the UserInfo

        // How should we add this here hmm...
        newOpponentInfo.addProperty("Opponent Name", "");
        newOpponentInfo.addProperty("WinsVS", 2);
        newOpponentInfo.addProperty("LosesVS", 1);
        newOpponentInfo.addProperty("FighterRank", "Three");

        // Adding Opponent as an Array
        opponentsArray.add(newOpponentInfo);
        // Adding Opponent Array to newUserInfo as an Object
        newUserInfo.add("Opponents", opponentsArray);

        // Adding UserInfo (+ Opponent now) to the whole Array
        jsonObject.getAsJsonArray("UserInfo").add(newUserInfo); // Watch out for NullPointers Exceptions
        GSONCreator.saveFile(jsonObject, GSONCreator.filepathJSON2);
    }

    void addToLog(String username) {}

    private void updateLog(String username) {
        /*
        Since adding and updating a JSON can be both done with .addProperty, consider using the same method for both
        things. For that, we would need:
            - To differentiate between the first instance of the creation of the Log / updating it
            - To give all parameters to the Log. Those would all be recovered from Fighter
            - Somehow get the Opponent Information as well
            - Then as a return a print that states if the Fighter was first added to the Log or their data updated
         */
    }

    private void deleteLog(String username) {
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON2);
        JsonArray jsonArray = jsonObject.getAsJsonArray("UserInfo");

        for (int i = 0; i < jsonArray.size(); i ++) {
            JsonObject userObject = jsonArray.get(i).getAsJsonObject();
            if (userObject.get("UserName").getAsString().equals(username)) {
                jsonArray.remove(i);
                System.out.println("Your information has been successfully deleted from the Log DataBase");
                break;
            }
        }
        jsonObject.add("UserInfo",jsonArray);
        GSONCreator.saveFile(jsonObject, GSONCreator.filepathJSON2);
        GSONCreator.readFile(GSONCreator.filepathJSON2);
    }

    // Method to look for a specific characteristic in the JSON file. Probably not needed or even be re factorized
    // so it prints all the information or doesn't need the JsonObject/Array as parameters
    private String checkFor(JsonObject searchedFighter, JsonArray allFighters) {
        String output = "";
        for (int i = 0; i < allFighters.size(); i ++) {
            JsonObject fighter = allFighters.get(i).getAsJsonObject();
            if (fighter.get("Name").getAsString().equals(searchedFighter.get("Name").getAsString())) {
                output = fighter.get("Name").getAsString();
            }
        }
        return output;
    }
}