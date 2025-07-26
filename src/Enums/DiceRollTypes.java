package Enums;

public enum DiceRollTypes {
    ADVANTAGE(1),
    FLAT(0),
    DISADVANTAGE(-1);

    public final int id;
    DiceRollTypes(int id) {
        this.id = id;
    }
}