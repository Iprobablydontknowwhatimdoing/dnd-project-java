package Enums;

public enum Skills {
    ACROBATICS(0, AbilityScores.DEXTERITY),
    ANIMAL_HANDLING(1, AbilityScores.WISDOM),
    ARCANA(2, AbilityScores.INTELLIGENCE),
    ATHLETICS(3, AbilityScores.STRENGTH),
    DECEPTION(4, AbilityScores.CHARISMA),
    HISTORY(5, AbilityScores.INTELLIGENCE),
    INSIGHT(6, AbilityScores.WISDOM),
    INTIMIDATION(7, AbilityScores.CHARISMA),
    INVESTIGATION(8, AbilityScores.INTELLIGENCE),
    MEDICINE(9, AbilityScores.WISDOM),
    NATURE(10, AbilityScores.INTELLIGENCE),
    PERCEPTION(11, AbilityScores.WISDOM),
    PERFORMANCE(12, AbilityScores.CHARISMA),
    PERSUASION(13, AbilityScores.CHARISMA),
    RELIGION(14, AbilityScores.INTELLIGENCE),
    SLIGHT_OF_HAND(15, AbilityScores.DEXTERITY),
    STEALTH(16, AbilityScores.DEXTERITY),
    SURVIVAL(17, AbilityScores.WISDOM);

    public final AbilityScores ability;
    public final int value;

    Skills(int value, AbilityScores abilityScore) {
        this.ability = abilityScore;
        this.value = value;

    }
}