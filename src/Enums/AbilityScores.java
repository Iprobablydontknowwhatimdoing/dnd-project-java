package Enums;

public enum AbilityScores {
    STRENGTH(0),
    DEXTERITY(1),
    CONSTITUTION(2),
    INTELLIGENCE(3),
    WISDOM(4),
    CHARISMA(5);

    public final int index;
    AbilityScoreEnum(int i) {
        this.index = i;
    }

}
