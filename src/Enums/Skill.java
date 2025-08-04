package Enums;

public enum Skill {
    ACROBATICS(0, AbilityScore.DEXTERITY),
    ANIMAL_HANDLING(1, AbilityScore.WISDOM),
    ARCANA(2, AbilityScore.INTELLIGENCE),
    ATHLETICS(3, AbilityScore.STRENGTH),
    DECEPTION(4, AbilityScore.CHARISMA),
    HISTORY(5, AbilityScore.INTELLIGENCE),
    INSIGHT(6, AbilityScore.WISDOM),
    INTIMIDATION(7, AbilityScore.CHARISMA),
    INVESTIGATION(8, AbilityScore.INTELLIGENCE),
    MEDICINE(9, AbilityScore.WISDOM),
    NATURE(10, AbilityScore.INTELLIGENCE),
    PERCEPTION(11, AbilityScore.WISDOM),
    PERFORMANCE(12, AbilityScore.CHARISMA),
    PERSUASION(13, AbilityScore.CHARISMA),
    RELIGION(14, AbilityScore.INTELLIGENCE),
    SLIGHT_OF_HAND(15, AbilityScore.DEXTERITY),
    STEALTH(16, AbilityScore.DEXTERITY),
    SURVIVAL(17, AbilityScore.WISDOM);

    public final AbilityScore ability;
    public final int value;

    Skill(int value, AbilityScore abilityScore) {
        this.ability = abilityScore;
        this.value = value;

    }
}