import Enums.AbilityScores;
import Enums.DiceRollTypes;

public class Race {

    public int[] size = new int[20];    //tiny:1, small:2, medium:3, large:4, huge:5, gargantuan:6. 0: Undefined
    public int[][] speeds = new int[20][10]; //First array is for level, second is for speeds
    public boolean[] speedsOverride; //Per Level
    public int[][] damageMultiplier = new int[][]{{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    public int[][] saves; // first is level, second is the  +modifier
    public DiceRollTypes[][] savesRollType = new DiceRollTypes[20][6]; // first is level, second is for each roll (in ability score order)
    public boolean[][] armorProficiencies = new boolean[20][4]; // first is level, second is for light, medium, heavy armor, and shield proficiencies
    public final int abilitySavingThrows = -1;

    public final PC pc;
    public AbilityScoreIncrease abilityScoreIncrease;
    public Race(PC pc) {
        this.pc = pc;
    }

    public AbilityBonusIncrease setAbilityBonusIncrease() {
        String[] possibleAbilityScores = new String[6];
        int i = 0;
        for (AbilityScores ability : AbilityScores.values()) {
            possibleAbilityScores[i] = ability.toString();
            i++;
        }
        i = 0;
        int choice = PC.choice(new String[]{"Add +2 to one score and +1 to another", "Add +1 to three scores"});
        int[] abilityBonusIncreaseValues = new int[6];
        System.out.println("DEBUG your choice was: " + String.valueOf(choice));
        switch (choice) {
            case 0 -> {
                System.out.println("Please choose which ability score you want to be +2");
                abilityBonusIncreaseValues[PC.choice(possibleAbilityScores)] = 2;
                System.out.println("Please choose which ability score you want to be +1");
                abilityBonusIncreaseValues[PC.choice(possibleAbilityScores)] = 1;
            } case 1 -> {
                System.out.println("Please choose the first ability you would like to be +1");
                abilityBonusIncreaseValues[PC.choice(possibleAbilityScores)] = 1;
                System.out.println("Please choose the second ability you would like to be +1");
                abilityBonusIncreaseValues[PC.choice(possibleAbilityScores)] = 1;
                System.out.println("Please choose the third ability you would like to be +1");
                abilityBonusIncreaseValues[PC.choice(possibleAbilityScores)] = 1;
            }
        }
        return new AbilityBonusIncrease(abilityBonusIncreaseValues, pc);
    }
}
