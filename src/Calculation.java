import Enums.AbilityScore;
import Enums.Skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class Calculation implements Callable<Integer> { //TODO: figure out how to do dice notation (2d4 + 4) in addition to just straight numbers
    private List<String> calculation; //TODO: figure out how to impliment choice
    public final PC pc;
    @Override
    public Integer call() throws Exception {
        return evaluateString(calculation);
    }
    private int evaluateString(List<String> calc) throws Exception { // something like "( 4.0 5.6 * ) 14.0 +"
        double returnValue = 0;
        double firstNumber = 0;
        double secondNumber = 0;
        double evaluatedNumber = 0;
        double number = 0;
        List<String> calculation = calc;
        for (int i = 0; i < calculation.size(); i++) {
            if (Objects.equals(calculation.get(i), "(")) {
                for (int j = calculation.size() - 1; j == 0; j--) {
                    if (Objects.equals(calculation.get(j), ")")) {
                        List<String> substring = new ArrayList<>();
                        for (int k = i + 1; k == j - 1; k++) {
                            substring.add(calculation.get(k));
                            calculation.remove(k);
                        }
                        calculation.remove(j);
                        calculation.set(i, Integer.toString(evaluateString(substring)));
                    }
                }
            }
            number = (double) switch (calculation.get(i)) {
                case "StrBonus" -> pc.abilityScores.getAbilityBonus(AbilityScore.STRENGTH);
                case "StrScore" -> pc.abilityScores.getAbilityScore(AbilityScore.STRENGTH);
                case "DexBonus" -> pc.abilityScores.getAbilityBonus(AbilityScore.DEXTERITY);
                case "DexScore" -> pc.abilityScores.getAbilityScore(AbilityScore.DEXTERITY);
                case "ConBonus" -> pc.abilityScores.getAbilityBonus(AbilityScore.CONSTITUTION);
                case "ConScore" -> pc.abilityScores.getAbilityScore(AbilityScore.CONSTITUTION);
                case "IntBonus" -> pc.abilityScores.getAbilityBonus(AbilityScore.INTELLIGENCE);
                case "IntScore" -> pc.abilityScores.getAbilityScore(AbilityScore.INTELLIGENCE);
                case "WisBonus" -> pc.abilityScores.getAbilityBonus(AbilityScore.WISDOM);
                case "WisScore" -> pc.abilityScores.getAbilityScore(AbilityScore.WISDOM);
                case "ChaBonus" -> pc.abilityScores.getAbilityBonus(AbilityScore.CHARISMA);
                case "ChaScore" -> pc.abilityScores.getAbilityScore(AbilityScore.CHARISMA);
                case "Prof" -> pc.getProficiencyBonus();
                case "Acrobatics" -> pc.skillProficiencies.getSkillBonus(Skill.ACROBATICS);
                case "Animal_Handling" -> pc.skillProficiencies.getSkillBonus(Skill.ANIMAL_HANDLING);
                case "Arcana" -> pc.skillProficiencies.getSkillBonus(Skill.ARCANA);
                case "Athletics" -> pc.skillProficiencies.getSkillBonus(Skill.ATHLETICS);
                case "Deception" -> pc.skillProficiencies.getSkillBonus(Skill.DECEPTION);
                case "History" -> pc.skillProficiencies.getSkillBonus(Skill.HISTORY);
                case "Insight" -> pc.skillProficiencies.getSkillBonus(Skill.INSIGHT);
                case "Intimidation" -> pc.skillProficiencies.getSkillBonus(Skill.INTIMIDATION);
                case "Investigation" -> pc.skillProficiencies.getSkillBonus(Skill.INVESTIGATION);
                case "Medicine" -> pc.skillProficiencies.getSkillBonus(Skill.MEDICINE);
                case "Nature" -> pc.skillProficiencies.getSkillBonus(Skill.NATURE);
                case "Perception" -> pc.skillProficiencies.getSkillBonus(Skill.PERCEPTION);
                case "Performance" -> pc.skillProficiencies.getSkillBonus(Skill.PERFORMANCE);
                case "Persuasion" -> pc.skillProficiencies.getSkillBonus(Skill.PERSUASION);
                case "Religion" -> pc.skillProficiencies.getSkillBonus(Skill.RELIGION);
                case "Slight_of_Hand" -> pc.skillProficiencies.getSkillBonus(Skill.SLIGHT_OF_HAND);
                case "Stealth" -> pc.skillProficiencies.getSkillBonus(Skill.STEALTH);
                case "Survival" -> pc.skillProficiencies.getSkillBonus(Skill.SURVIVAL);
                case "AC" -> pc.getArmorClass();
                case "Str_Saving_Throw" -> pc.abilityScores.getSavingThrowBonus(AbilityScore.STRENGTH);
                case "Dex_Saving_Throw" -> pc.abilityScores.getSavingThrowBonus(AbilityScore.DEXTERITY);
                case "Con_Saving_Throw" -> pc.abilityScores.getSavingThrowBonus(AbilityScore.CONSTITUTION);
                case "Int_Saving_Throw" -> pc.abilityScores.getSavingThrowBonus(AbilityScore.INTELLIGENCE);
                case "Wis_Saving_Throw" -> pc.abilityScores.getSavingThrowBonus(AbilityScore.WISDOM);
                case "Cha_Saving_Throw" -> pc.abilityScores.getSavingThrowBonus(AbilityScore.CHARISMA);
                case null, default -> 0; //TODO: Spell dc, Class / Ability Resource, Hit Points, Innitiative, Speeds
            };
            if (secondNumber == 0.0) {
                if (firstNumber == 0.0) {
                    firstNumber = number;
                } else {
                    secondNumber = number;
                }
            } else {
                assert (calculation.get(i).length() == 1);
                switch (calculation.get(i)) {
                    case "+" -> {return (int) Math.floor(firstNumber + secondNumber);}
                    case "-" -> {return (int) Math.floor(firstNumber - secondNumber);}
                    case "/" -> {return (int) Math.floor(firstNumber / secondNumber);}
                    case "*" -> {return (int) Math.floor(firstNumber * secondNumber);}
                }
            }
        }
        return (int) Math.floor(returnValue);
    }
    public Calculation(PC pc, String calculation) {
        this.calculation = Arrays.stream(calculation.split(" ")).toList();
        this.pc = pc;
    }
}
