import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PC {

    public List<Class> classOptions = new ArrayList<>();
    public List<Class> appliedClasses = new ArrayList<>();
    public List<Race> raceOptions = new ArrayList<>();
    public Race appliedRace;
    public List<AbilityScoreIncrease> appliedAbilityScoreIncreases = new ArrayList<>();
    public List<AbilityBonusIncrease> appliedAbilityBonusIncreases = new ArrayList<>();
    public String name;
    public List<Weapon> weaponProficiencies;
    public AbilityScores abilityScores;
    public SkillProficiencies skillProficiencies;

    public PC() {
        this.abilityScores = new AbilityScores(this);
        this.skillProficiencies = new SkillProficiencies(this);
        this.appliedRace = askRace();    //TODO: figure out how to find all subclasses of race for this list
    }

    public Race askRace() {
        switch (choice(new String[]{"Hobgoblin"})) {
            case 0 -> {return new Race(this)} //TODO: need to change to hobgoblin rather than race before commit
            default -> {System.out.println("Invalid choice, try again! \n");return askRace();}
        }
    }

    public static int choice(String[] choices) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose one of the following:");
        for (int i = 0; i < choices.length; i++) {
            System.out.println(((char) i + 'a') + ". " + choices[i]);
        }
        return (int) scan.nextLine().charAt(0) - 'a';
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

    public enum Skills {
        ACROBATICS(0, AbilityScoreEnum.DEXTERITY),
        ANIMAL_HANDLING(1, AbilityScoreEnum.WISDOM),
        ARCANA(2, AbilityScoreEnum.INTELLIGENCE),
        ATHLETICS(3, AbilityScoreEnum.STRENGTH),
        DECEPTION(4, AbilityScoreEnum.CHARISMA),
        HISTORY(5, AbilityScoreEnum.INTELLIGENCE),
        INSIGHT(6, AbilityScoreEnum.WISDOM),
        INTIMIDATION(7, AbilityScoreEnum.CHARISMA),
        INVESTIGATION(8, AbilityScoreEnum.INTELLIGENCE),
        MEDICINE(9, AbilityScoreEnum.WISDOM),
        NATURE(10, AbilityScoreEnum.INTELLIGENCE),
        PERCEPTION(11, AbilityScoreEnum.WISDOM),
        PERFORMANCE(12, AbilityScoreEnum.CHARISMA),
        PERSUASION(13, AbilityScoreEnum.CHARISMA),
        RELIGION(14, AbilityScoreEnum.INTELLIGENCE),
        SLIGHT_OF_HAND(15, AbilityScoreEnum.DEXTERITY),
        STEALTH(16, AbilityScoreEnum.DEXTERITY),
        SURVIVAL(17, AbilityScoreEnum.WISDOM);

        public final AbilityScoreEnum ability;
        public final int value;

         Skills(int value, AbilityScoreEnum abilityScore) {
            this.ability = abilityScore;
            this.value = value;

        }

        public static AbilityScoreEnum getAbility(Skills skill) {
            return switch (skill) {
                case ATHLETICS -> AbilityScoreEnum.STRENGTH;
                case ACROBATICS, SLIGHT_OF_HAND, STEALTH -> AbilityScoreEnum.DEXTERITY;
                case ARCANA, HISTORY, INVESTIGATION, NATURE, RELIGION -> AbilityScoreEnum.INTELLIGENCE;
                case ANIMAL_HANDLING, INSIGHT, MEDICINE, PERCEPTION, SURVIVAL -> AbilityScoreEnum.WISDOM;
                case DECEPTION, INTIMIDATION, PERFORMANCE, PERSUASION -> AbilityScoreEnum.CHARISMA;
            };
        }
    }

    public enum Speeds {
        WALKING(0),
        WALKING_ENCUMBERED(1),
        CLIMBING(2),
        CLIMBING_ENCUMBERED(3),
        FLYING(4),
        FLYING_ENCUMBERED(5),
        SWIMMING(6),
        SWIMMING_ENCUMBERED(7),
        BURROWING(8),
        BURROWING_ENCUMBERED(9);

        public final int arrayValue;
        Speeds(int array) {
            this.arrayValue = array;
        }
    }

    public enum Sizes {
        TINY(0),
        SMALL(1),
        MEDIUM(2),
        LARGE(3),
        GIANT(4),
        GARGANTUAN(5);

        public final int arrayValue;

        Sizes(int array) {
            this.arrayValue = array;
        }
    }

    public enum DamageTypes {
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

        DamageTypes(int array) {
            this.arrayValue = array;
        }
    }

    public enum DiceRollTypes {
        ADVANTAGE(1),
        FLAT(0),
        DISADVANTAGE(-1);

        public final int id;
        DiceRollTypes(int id) {
            this.id = id;
        }
    }

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

    public static class AbilityScores {

        public final PC pcInstance;
        public int[] abilityScores;

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

        public int getAbilityScore(AbilityScoreEnum ability) {
            int abilityScore = abilityScores[ability.index];
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
            for (AbilityBonusIncrease abi : pcInstance.appliedAbilityBonusIncreases) {
                abilityBonuses = abi.adjustBonuses(abilityBonuses);
            }
            return abilityBonuses;
        }

        public int getAbilityBonus(AbilityScoreEnum ability) {
            int abilityBonus = getAbilityScore(ability);
            for (AbilityBonusIncrease bonus : pcInstance.appliedAbilityBonusIncreases) {
                abilityBonus = bonus.adjustBonus(ability, abilityBonus);
            }
            return abilityBonus;
        }
    }

    public static class SkillProficiencies {
        private final PC pcInstance;
        public double[] proficiencies = new double[18];

        public SkillProficiencies(PC pc) {
            this.pcInstance = pc;
        }

        public void setProficiencies(double[] proficiencies) {
            this.proficiencies = proficiencies;
        }

        public void addProficiency(Skills skill) {
            if (this.proficiencies[skill.value] >= 1) {
                throw new IllegalArgumentException();
            } else {
                this.proficiencies[skill.value] = 1;
            }
        }

        public void addExpertise(Skills skill) {
            if (this.proficiencies[skill.value] >= 2 || this.proficiencies[skill.value] < 1) {
                throw new IllegalStateException();
            } else {
                this.proficiencies[skill.value] = 2;
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
            double abilityScore = pcInstance.abilityScores.getAbilityScore(skill.ability);
            double proficiencyScore = this.proficiencies[skill.value] * pcInstance.getProficiencyBonus();
            return (int) Math.round(abilityScore + proficiencyScore); //TODO: figure out if its rounded up or down
        }

        public int[] getSkillBonuses() {
            int[] skills = new int[Skills.values().length];
            for (Skills skill: Skills.values()) {
                skills[skill.value] = this.getSkillBonus(skill);
            }
            return skills;
        }
    }

}