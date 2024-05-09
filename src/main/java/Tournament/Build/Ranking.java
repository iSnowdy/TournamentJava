package Tournament.Build;

import com.google.gson.JsonObject;

import java.util.*;

public class Ranking {
    private int rankingPoints;
    private int totalRankingPoints;
    private String fighterRank;
    private final Map<String, Integer> rankingCorrelation;

    public Ranking() {
        this.rankingCorrelation = new LinkedHashMap<>(); // HashMaps prints are not guaranteed to be in order :/
        setRankingCorrelation();
    }

    private void setRankingCorrelation() {
        this.rankingCorrelation.put("Clown", 0);
        this.rankingCorrelation.put("Almost-a-Human", 20);
        this.rankingCorrelation.put("Expert Fighter", 40);
        this.rankingCorrelation.put("Sensei", 60);
        this.rankingCorrelation.put("Maa", 100);
    }

    public void getRankingCorrelation() {
        System.out.println("The Ranking Points Correlation to Rank is:\n");
        for (Map.Entry<String, Integer> entry : this.rankingCorrelation.entrySet()) {
            System.out.println("Rank: " + entry.getKey() + " needs: " + entry.getValue() +
                    " victory points to reach");
        }
    }

    // Takes in as parameters if the fighter won, their current rankPoints and ranks
    protected int increaseRankingPoints(boolean status, int rankPoints, String fighterRank, String fighterRankOpponent) {
        int difference = rankDifference(fighterRank, fighterRankOpponent);
        int updatedRankPoints;

        if (status) { // Victory
            updatedRankPoints = 10 + (difference * 5); // 10 points are given per Victory + if there's a difference in rankings, 5 per ranking difference
        } else { // Defeat
            if (difference <= 1) {
                updatedRankPoints = - (10 + difference * 10); // 10 points are deducted per Loss + if there's a difference <= 1, 10 per rank difference
            } else {
                updatedRankPoints = - (9999); // You lose all points
            }
        }
        return updatedRankPoints; // Gives back the updated rankingPoints. So we must first retrieve the current Ranking Points from the Fighter
    }

    // Calculates the difference in the Rankings between the 2 Fighters and gives back the result as an int
    // This is needed to see if the Player is fighting against a higher ranked Fighter. So if he does, he would lose
    // all points upon defeat
    private int rankDifference(String fighterRank1, String fighterRank2) {
        List<String> rankList = new ArrayList<>(this.rankingCorrelation.keySet());
        int fighter1Index = rankList.indexOf(fighterRank1);
        int fighter2Index = rankList.indexOf(fighterRank2);
        return fighter2Index - fighter1Index;
        // *Should* always be positive, since you can't fight opponents that are lower rank than you; only higher
    }

    public int getTotalRankingPoints() { // Probably won't need this at all. Nor the attribute
        return totalRankingPoints;
    }

    // Withdraws the Ranking Points from the JSON
    public int getRankingPoints(String username) {
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON2);
        JsonObject user = jsonObject.getAsJsonArray("UserName").get(GSONCreator.getIndex("UserName", username, 2)).getAsJsonObject();
        return user.get("Ranking Points").getAsInt();
    }

    // Updates the Rankings Points to the JSON File and also returns the String
    public String setRankingPoints(String username, int rankingPoints) {
        JsonObject jsonObject = GSONCreator.loadFile(GSONCreator.filepathJSON2);
        JsonObject user = jsonObject.getAsJsonArray("UserInfo").get(GSONCreator.getIndex("UserName", username, 2)).getAsJsonObject();

        user.addProperty("Ranking Points", rankingPoints);
        System.out.println("The Ranking Points for " + username + " has been updated to " + rankingPoints);

        for (Map.Entry<String, Integer> entry : this.rankingCorrelation.entrySet()) {
            if (rankingPoints >= entry.getValue()) {
                this.fighterRank = entry.getKey();
                // Iterate the LinkedHashMap and if the points are higher or equal to any coincidence
                // it is assigned to that coincidence. Then, break upon the first match
            }
        }
        return fighterRank;
    }
}
