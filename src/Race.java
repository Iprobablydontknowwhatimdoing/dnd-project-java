import Enums.DiceRollType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Race {
//TODO: figure out how to make a race that is functionally the same race but has overrides (see racesublist in mpmb)
    public String name;
    public String source; // should be something like 'PHB2014 43'(book, page number)
    public String ageDescription;
    public String heightDescription;
    public String weightDescription;

    public List<Weapon> weaponProficiencies;
    public List<String> languages;
    public boolean[] weaponTypeProficiencies; // should be
    public byte size = 0;    //tiny:1, small:2, medium:3, large:4, huge:5, gargantuan:6. 0: Undefined, could also be a choice //TODO: help how to get it also a choice
    public Callable<Integer>[][] speeds = new Callable[20][10]; //First array is for level, second is for speeds // should be in form of callable, sometimes needed for stuff like if heavy armor, add 10 to encumberance speed
    public boolean[] speedsOverride = new boolean[20]; //Per Level speed override
    public float[][] damageMultiplier = new float[20][14]; // first is level, second is for each type of damage
    public int[][] saves = new int[20][6]; // first is level, second is the  +modifier
    public String[] savesText = new String[20]; // by level saves text
    public DiceRollType[][] savesRollType = new DiceRollType[20][6]; // first is level, second is for each roll (in ability score order)
    public boolean[][] armorProficiencies = new boolean[20][4]; // first is level, second is for light, medium, heavy armor, and shield proficiencies
    public final int abilitySavingThrows = -1;
    public final PC pc;
    public int[] abilityScoreIncrease = new int[6];
    public List<Feature> traits = new ArrayList<>();
    public SpellCastingList raceBonusSpellList; //TODO: set to default spellcastinglist with no spells
    public Race(PC pc) {
        this.pc = pc;
    }

}
