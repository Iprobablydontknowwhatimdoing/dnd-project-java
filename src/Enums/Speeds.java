package Enums;

public enum Speeds {
    WALKING(0),
    WALKING_ENCUMBERED(1),
    CLIMBING(2),
    CLIMBING_ENCUMBERED(3),
    FLYING(4),
    FLYING_ENCUMBERED(5),
    SWIMMING(6),
    SWIMMING_ENCUMBERED(7),
    BURROWING(8),
    BURROWING_ENCUMBERED(9);

    public final int arrayValue;
    Speeds(int array) {
        this.arrayValue = array;
    }
}