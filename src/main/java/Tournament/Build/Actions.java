package Tournament.Build;

public interface Actions {
    int attack(int str);
    void defend();
    boolean specialAttack();
    boolean specialDefense();
    boolean dodge(int dex);
}
