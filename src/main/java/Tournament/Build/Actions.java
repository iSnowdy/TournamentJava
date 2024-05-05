package Tournament.Build;

public interface Actions {
    void attack(Fighter fighter);
    void defend(Fighter fighter);
    boolean specialAttack(Fighter fighter);
    boolean specialDefense(Fighter fighter);
    void dodge(Fighter fighter);
}
