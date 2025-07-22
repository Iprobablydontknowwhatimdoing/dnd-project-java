import java.util.*;

public class PC {

    public List<AbilityScoreIncrease> appliedAbilityScoreIncreases = new ArrayList<>();
    public List<AbilityBonusIncrease> appliedAbilityBonusIncreases = new ArrayList<>();
    public List<Class> appliedClasses = new ArrayList<>();

    protected AbilityScores abilityScores;
    protected SkillProficiencies skillProficiencies;

    public PC() {
        this.abilityScores = new AbilityScores(this);
        this.skillProficiencies = new SkillProficiencies(this);
    }
    public int getTotalLevel() {
        int level = 0;
        for (Class appliedClass : this.appliedClasses) {
            level += appliedClass.level;
        }
        return level;
    }
    public int getProficiencyBonus() {
        return (int) Math.ceil((double) getTotalLevel() / 2);
    }

    public enum AbilityScoreEnum {
        STRENGTH,
        DEXTERITY,
        CONSTITUTION,
        INTELLIGENCE,
        WISDOM,
        CHARISMA;

        public static int getValue(PC.AbilityScoreEnum abilityScore) {
            return switch (abilityScore) {
                case STRENGTH -> 0;
                case DEXTERITY -> 1;
                case CONSTITUTION -> 2;
                case INTELLIGENCE -> 3;
                case WISDOM -> 4;
                case CHARISMA -> 5;
            };
        }
    }

    public static class AbilityScores {

        public int[] abilityScores;
        public final PC pcInstance;

        public AbilityScores(PC pc) {
            this.abilityScores = new int[6];
            this.pcInstance = pc;
        }
        public int[] getAbilityScores() {
            int[] tmpAbilityScores = abilityScores;
            for (AbilityScoreIncrease asi : pcInstance.appliedAbilityScoreIncreases) {
                tmpAbilityScores = asi.adjustScores(tmpAbilityScores);
            }
            return tmpAbilityScores;
        }
        public int getAbilityScore(PC.AbilityScoreEnum ability) {
            int abilityScore = abilityScores[PC.AbilityScoreEnum.getValue(ability)];
            for (AbilityScoreIncrease asi : pcInstance.appliedAbilityScoreIncreases) {
                abilityScore = asi.adjustScore(ability, abilityScore);
            }
            return abilityScore;
        }
        public int[] getAbilityBonuses() {
            int[] abilityBonuses = new int[6];
            int[] abilityScores = getAbilityScores();
            for (int i = 0; i < abilityScores.length; i++) {
                abilityBonuses[i] = (int) Math.floor(((double) abilityScores[i] - 10) / 2);
            }
            for (AbilityBonusIncrease abi: pcInstance.appliedAbilityBonusIncreases) {
                abilityBonuses = abi.adjustBonuses(abilityBonuses);
            }
            return abilityBonuses;
        }
        public int getAbilityBonus(PC.AbilityScoreEnum ability) {
            int abilityBonus = getAbilityScore(ability);
            for (AbilityBonusIncrease bonus : pcInstance.appliedAbilityBonusIncreases) {
                abilityBonus = bonus.adjustBonus(ability, abilityBonus);
            }
            return abilityBonus;
        }
    }
    public class SkillProficiencies {
        public double[] proficiencies = new double[18];
        private final PC pcInstance;

        public SkillProficiencies(PC pc) {
            this.pcInstance = pc;
        }
        public void setProficiencies(double[] proficiencies) {
            this.proficiencies = proficiencies;
        }
        public void addProficiency(Skills skill) {
            if (this.proficiencies[Skills.getArrayValue(skill)] >= 1) {
                throw new IllegalArgumentException();
            } else {
                this.proficiencies[Skills.getArrayValue(skill)] = 1;
            }
        }
        public void addExpertise(Skills skill) {
            if (this.proficiencies[Skills.getArrayValue(skill)] >= 2) {
                throw new IllegalStateException();
            } else {
                this.proficiencies[Skills.getArrayValue(skill)] = 2;
            }
        }
        public void addJackOfAllTrades() {
            for (double i : this.proficiencies) {
                if (i == 0) {
                    i = 0.5;
                }
            }
        }
        public void removeJackOfAllTrades() {
            for (double i : this.proficiencies) {
                if (i == 0.5) {
                    i = 0;
                }
            }
        }
        public int getSkillBonus(Skills skill) {
            double abilityScore = PC.this.abilityScores.getAbilityScore(Skills.getAbility(skill));
            double proficiencyScore = this.proficiencies[Skills.getArrayValue(skill)]*PC.this.getProficiencyBonus();
            return (int) Math.round(abilityScore + proficiencyScore); //TODO: figure out if its rounded up or down
        }
    }
    public enum Skills {
        ACROBATICS,
        ANIMAL_HANDLING,
        ARCANA,
        ATHLETICS,
        DECEPTION,
        HISTORY,
        INSIGHT,
        INTIMIDATION,
        INVESTIGATION,
        MEDICINE,
        NATURE,
        PERCEPTION,
        PERFORMANCE,
        PERSUASION,
        RELIGION,
        SLIGHT_OF_HAND,
        STEALTH,
        SURVIVAL;
        public static PC.AbilityScoreEnum getAbility(Skills skill) {
            return switch (skill) {
                case ATHLETICS -> PC.AbilityScoreEnum.STRENGTH;
                case ACROBATICS, SLIGHT_OF_HAND, STEALTH -> PC.AbilityScoreEnum.DEXTERITY;
                case ARCANA, HISTORY, INVESTIGATION, NATURE, RELIGION -> PC.AbilityScoreEnum.INTELLIGENCE;
                case ANIMAL_HANDLING, INSIGHT, MEDICINE, PERCEPTION, SURVIVAL -> PC.AbilityScoreEnum.WISDOM;
                case DECEPTION, INTIMIDATION, PERFORMANCE, PERSUASION -> PC.AbilityScoreEnum.CHARISMA;
            };
        }
        public static int getArrayValue(Skills skill) {
            return switch (skill) {
                case ACROBATICS -> 0;
                case ANIMAL_HANDLING -> 1;
                case ARCANA -> 2;
                case ATHLETICS -> 3;
                case DECEPTION -> 4;
                case HISTORY -> 5;
                case INSIGHT -> 6;
                case INTIMIDATION -> 7;
                case INVESTIGATION -> 8;
                case MEDICINE -> 9;
                case NATURE -> 10;
                case PERCEPTION -> 11;
                case PERFORMANCE -> 12;
                case PERSUASION -> 13;
                case RELIGION -> 14;
                case SLIGHT_OF_HAND -> 15;
                case STEALTH -> 16;
                case SURVIVAL -> 17;
            };
        }
    }
}
