package Tournament.Build;

public interface StatsManager {
    // Public modifier is redundant
    int increaseVitality();
    int increaseStrength();
    int increaseDexterity();
    // Maybe I shouldn't add these here. Re factor likely
    int availableStatPoints();
    int increaseTotalStatPoints();
}