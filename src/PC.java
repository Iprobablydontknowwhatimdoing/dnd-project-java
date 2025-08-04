import Enums.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

public class PC {

    public final CLI cli;
    
    public String name;
    public String height;
    public String weight;
    public String gender;
    public String hairColor;
    public String eyeColor;
    public String skin;
    public String age;
    public String faith;
    public Alignment alignment;
    public boolean inspiration = false;

    public List<Class> appliedClasses = new ArrayList<>();
    public Race appliedRace;
    public static List<Race> raceOptions = new ArrayList<>();
    public List<AbilityScoreIncrease> appliedAbilityScoreIncreases = new ArrayList<>();
    public List<Weapon> individualWeaponProficiencies;
    public boolean[][] armorProficiencies = new boolean[20][4];
    public List<Action> enabledActions = new ArrayList<>();
    public List<Callable<Integer>> innitiativeModifiers = new ArrayList<Callable<Integer>>(); //TODO: add override options & look at evaluation order
    public List<Callable<Integer>> armorModifiers = new ArrayList<>(); //TODO: get armor automatically add to this, etc
    public List<Callable<Integer>[]> savingThrowModifiers; //TODO: make sure innitialized where everything is () -> { return 0 } for everything which it does not modify . //TODO: also I don't think this is used ever in the game, so...
    public boolean[] savingThrowProficiencies = new boolean[6]; //TODO: consult, Should work ... ?
    public HashMap<String, Resource> optionalLimitedResources = new HashMap<>();


    public AbilityScores abilityScores;
    public SkillProficiencies skillProficiencies;


    public PC(CLI cli) {
        this.cli = cli;
        
        this.abilityScores = new AbilityScores(this);
        this.skillProficiencies = new SkillProficiencies(this);
        this.innitiativeModifiers.add(() -> {this.abilityScores.getAbilityScore(AbilityScore.DEXTERITY);}); //TODO: ask daddy if this will work
        // this.appliedRace = askRace();    //TODO: figure out how to find all subclasses of race for this list
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

    public int getInnitiativeBonus() throws Exception {
        int innitiativeBonus = 0;
        for (Callable<Integer> modifier : innitiativeModifiers) {
            innitiativeBonus += modifier.call();
        }
        return innitiativeBonus;
    }

    public int getArmorClass() throws Exception {   //TODO: check for overrides (like wildshape)
        int armorClass = 10; //TODO: check if this is right init value
        for (Callable<Integer> modifier : armorModifiers) {
            armorClass += modifier.call();
        }
        return armorClass;
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

        public int getAbilityScore(AbilityScore ability) {
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
                abilityBonuses[i] = (int) Math.floor(((double) abilityScores[i] - 10.0) / 2.0);
            }
            return abilityBonuses;
        }

        public int getAbilityBonus(AbilityScore ability) {
            return (int) Math.floor(((double) getAbilityScore(ability) - 10.0) / 2.0);
        }

        public int getSavingThrowBonus(AbilityScore ability) throws Exception {
            int bonusValue = getAbilityBonus(ability);
            if (this.pcInstance.savingThrowProficiencies[ability.index]) {
                bonusValue += this.pcInstance.getProficiencyBonus();
            }
            for (Callable<Integer>[] bonus : this.pcInstance.savingThrowModifiers) {
                bonusValue += bonus[ability.index].call();
            }
            return bonusValue;
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

        public void addProficiency(Skill skill) {
            if (this.proficiencies[skill.value] >= 1) {
                throw new IllegalArgumentException();
            } else {
                this.proficiencies[skill.value] = 1;
            }
        }

        public void addExpertise(Skill skill) {
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

        public int getSkillBonus(Skill skill) {
            double abilityScore = pcInstance.abilityScores.getAbilityScore(skill.ability);
            double proficiencyScore = this.proficiencies[skill.value] * pcInstance.getProficiencyBonus();
            return (int) Math.round(abilityScore + proficiencyScore); //TODO: figure out if its rounded up or down
        }

        public int[] getSkillBonuses() {
            int[] skills = new int[Skill.values().length];
            for (Skill skill: Skill.values()) {
                skills[skill.value] = this.getSkillBonus(skill);
            }
            return skills;
        }
    }

}