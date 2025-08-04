package Enums;

public enum Alignment {
    LAWFUL_GOOD(0),
    LAWFUL_NEUTRAL(1),
    LAWFUL_EVIL(2),
    NEUTRAL_GOOD(3),
    TRUE_NEUTRAL(4),
    NEUTRAL_EVIL(5),
    CHAOTIC_GOOD(6),
    CHAOTIC_NEUTRAL(7),
    CHAOTIC_EVIL(8);

    public final int arrayValue;
    Alignment(int i) {
        this.arrayValue = i;
    }
}
