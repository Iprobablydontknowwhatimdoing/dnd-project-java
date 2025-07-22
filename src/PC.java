import java.util.*;

public class PC {

    public List<AbilityScoreIncrease> appliedAbilityScoreIncreases = new ArrayList<>();
    public List<AbilityBonusIncrease> appliedAbilityBonusIncreases = new ArrayList<>();
    public List<Class> appliedClasses = new ArrayList<>();

    protected AbilityScores abilityScores;

    public PC() {
        this.abilityScores = new AbilityScores(this);
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
    public class AbilityScores {

        public int[] abilityScores;

        public AbilityScores(PC pc) {
            this.abilityScores = new int[6];
        }
        public int[] getAbilityScores() {
            int[] tmpAbilityScores = abilityScores;
            for (AbilityScoreIncrease asi : PC.this.appliedAbilityScoreIncreases) {
                tmpAbilityScores = asi.adjustScores(tmpAbilityScores);
            }
            return tmpAbilityScores;
        }
        public int getAbilityScore(PC.AbilityScores.AbilityScoreEnum ability) {
            int[] tmpAbilityScores = abilityScores;
            for (AbilityScoreIncrease asi : PC.this.appliedAbilityScoreIncreases) {
                tmpAbilityScores = asi.adjustScores(tmpAbilityScores);
            }
            return tmpAbilityScores[PC.AbilityScores.AbilityScoreEnum.getValue(ability)];
        }
        public enum AbilityScoreEnum {
            STRENGTH,
            DEXTERITY,
            CONSTITUTION,
            INTELLIGENCE,
            WISDOM,
            CHARISMA;

            public static int getValue(PC.AbilityScores.AbilityScoreEnum abilityScore) {
                return switch (abilityScore) {
                    case STRENGTH -> 0;
                    case DEXTERITY -> 1;
                    case CONSTITUTION -> 2;
                    case INTELLIGENCE -> 3;
                    case WISDOM -> 4;
                    case CHARISMA -> 5;
                    default -> throw new IllegalStateException("Unexpected value: " + abilityScore);
                };
            }
        }
    }
    public class skillProficiencies {
        public double[] proficiencies = new double[18];

        public skillProficiencies() {}
        public void setProficiencies(double[] proficiencies) {
            this.proficiencies = proficiencies;
        }
        public void addProficiency(Skills skill) {
            if (this.proficiencies[Skills.getArrayValue(skill)] >= 1) {
                throw IllegalArgumentException;
            } else {
                this.proficiencies[Skills.getArrayValue(skill)] = 1;
            }
        }
        public void addExpertise(Skills skill) {
            if (this.proficiencies[Skills.getArrayValue(skill)] >= 2) {
                throw IllegalStateException;
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
            double abilityScore = (double) PC.this.AbilityScores.getAbilityScore(Skills.getAbility(skill));
            double proficiencyScore = (double) this.proficiencies[Skills.getArrayValue(skill)]*PC.this.getProficiencyBonus();
            return (int) Math.round(abilityScore + proficiencyScore); //TODO: figure out if its rounded up or down
        }
    }
    public static enum Skills {
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
        public static PC.AbilityScores.AbilityScoreEnum getAbility(Skills skill) {
            //TODO: help
            return switch (skill) {
                case ATHLETICS -> PC.AbilityScores.AbilityScoreEnum.STRENGTH;
                case ACROBATICS, SLIGHT_OF_HAND, STEALTH -> PC.AbilityScores.AbilityScoreEnum.DEXTERITY;
                case ARCANA, HISTORY, INVESTIGATION, NATURE, RELIGION -> PC.AbilityScores.AbilityScoreEnum.INTELLIGENCE;
                case ANIMAL_HANDLING, INSIGHT, MEDICINE, PERCEPTION, SURVIVAL -> PC.AbilityScores.AbilityScoreEnum.WISDOM;
                case DECEPTION, INTIMIDATION, PERFORMANCE, PERSUASION -> PC.AbilityScores.AbilityScoreEnum.CHARISMA;
                case null, default -> {
                    throw IllegalArgumentException; //TODO: help
                }
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
                case null, default -> {
                    throw IllegalArgumentException;
                }
            };
        }
    }
}
