package Tournament.Build;

public interface Actions {
    int attack();
    void defend();
    boolean specialAttack();
    boolean specialDefense();
    int dodge(int damageReceived);
}
