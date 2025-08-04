package Enums;

public enum ArmorType {
    LIGHT(0),
    MEDIUM(1),
    HEAVY(2),
    SHIELDS(3);

    public final int arrayValue;
    ArmorType(int index) {
        this.arrayValue = index;
    }
}