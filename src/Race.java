public class Race {

    public int size;    //tiny:0, small:1, medium:2, large:3, huge:4, gargantuan:5
    public int[] speeds = new int[10];
    public int[] damageMultiplier = new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    public int[] saves;
    public int[] armorProficiencies;
    public final int abilitySavingThrows = -1;

    public final PC pc;
    public AbilityScoreIncrease abilityScoreIncrease;
    public Race(PC pc) {
        this.pc = pc;
    }
    public AbilityBonusIncrease setAbilityBonusIncrease() {
        String[] possibleAbilityScores = new String[6];
        int i = 0;
        for (PC.AbilityScoreEnum ability : PC.AbilityScoreEnum.values()) {
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
