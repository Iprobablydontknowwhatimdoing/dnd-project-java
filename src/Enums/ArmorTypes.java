package Enums;

public enum ArmorTypes {
    LIGHT(0),
    MEDIUM(1),
    HEAVY(2),
    SHIELDS(3);

    public final int arrayValue;
    ArmorTypes(int index) {
        this.arrayValue = index;
    }
}