package Enums;

public enum DiceRollType {
    ADVANTAGE(1),
    FLAT(0),
    DISADVANTAGE(-1);

    public final int id;
    DiceRollType(int id) {
        this.id = id;
    }
}