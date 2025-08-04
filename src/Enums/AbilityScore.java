package Enums;

public enum AbilityScore {
    STRENGTH(0),
    DEXTERITY(1),
    CONSTITUTION(2),
    INTELLIGENCE(3),
    WISDOM(4),
    CHARISMA(5);

    public final int index;
    AbilityScore(int index) {
        this.index = index;
    }
}
