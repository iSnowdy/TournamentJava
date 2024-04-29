package Tournament.Build;

public interface StatsManager {
    // Public modifier is redundant
    int increaseVitality(String fighterName);
    int increaseStrength(String fighterName);
    int increaseDexterity(String fighterName);
    // Maybe I shouldn't add these here. Re factor likely
    int availableStatPoints();
    int increaseTotalStatPoints();
}