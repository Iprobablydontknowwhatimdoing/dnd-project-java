package Enums;

public enum Sizes {
    TINY(0),
    SMALL(1),
    MEDIUM(2),
    LARGE(3),
    GIANT(4),
    GARGANTUAN(5);

    public final int arrayValue;

    Sizes(int array) {
        this.arrayValue = array;
    }
}