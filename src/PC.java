import Enums.*;

import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PC {

    public String name;
    public String height;
    public String weight;
    public String gender;
    public String hairColor;
    public String eyeColor;
    public String skin;
    public String age;
    public String faith;
    public Alignments alignment;
    public boolean inspiration = false;

    public static List<Class> classOptions = new ArrayList<>();
    public List<Class> appliedClasses = new ArrayList<>();
    public static List<Race> raceOptions = new ArrayList<>();
    public Race appliedRace;
    public List<AbilityScoreIncrease> appliedAbilityScoreIncreases = new ArrayList<>();
    public List<AbilityBonusIncrease> appliedAbilityBonusIncreases = new ArrayList<>();
    public List<Weapon> weaponProficiencies;
    public boolean[][] armorProficiencies = new boolean[20][4];
    public AbilityScores abilityScores;
    public SkillProficiencies skillProficiencies;
    public List<Action> enabledActions = new ArrayList<>();
    public List<Runnable> innitiativeModifiers = new ArrayList<Runnable>();

    public PC() {
        this.abilityScores = new AbilityScores(this);
        this.skillProficiencies = new SkillProficiencies(this);
        this.innitiativeModifiers.add(() -> {this.abilityScores.getAbilityScore(Enums.AbilityScores.DEXTERITY);}); //TODO: ask daddy if this will work
        // this.appliedRace = askRace();    //TODO: figure out how to find all subclasses of race for this list
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

        public int getAbilityScore(Enums.AbilityScores ability) {
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

        public int getAbilityBonus(Enums.AbilityScores ability) {
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