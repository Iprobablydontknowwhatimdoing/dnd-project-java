package Enums;

public enum DamageType {
    ACID(0),
    BLUDGEONING(1),
    COLD(2),
    FIRE(3),
    FORCE(4),
    LIGHTNING(5),
    NECROTIC(6),
    PIERCING(7),
    POISON(8),
    PSYCHIC(9),
    RADIANT(10),
    SLASHING(11),
    THUNDER(12),
    BLUEBERRY(13);

    public final int arrayValue;

    DamageType(int array) {
        this.arrayValue = array;
    }
}