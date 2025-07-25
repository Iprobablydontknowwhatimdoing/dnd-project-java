import Enums.AbilityScores;

public class AbilityBonusIncrease {

    int[] bonuses;
    private final PC pc;

    public AbilityBonusIncrease(int[] bonuses, PC pc) {
        //  try { assert Attributes.AbilityBonuses.values().length == bonuses.length;} catch (AssertionError e) {System.out.println( 'Whoops! you encountered error ' + e + '\n\n Please fix this!');}
        this.bonuses = bonuses;
        this.pc = pc;
        pc.appliedAbilityBonusIncreases.add(this);
    }

    public int[] getBonuses() {
        return this.bonuses;
    }

    public int[] adjustBonuses(int[] existingBonuses) {
        try {
            assert existingBonuses.length == this.bonuses.length;
        } catch (AssertionError e) {
            System.out.println("Oh No! An Error!");
        }

        for (int i = 0; i < existingBonuses.length; i++) {
            existingBonuses[i] += this.bonuses[i];
        }

        return existingBonuses;
    }
    public int adjustBonus(AbilityScores ability, int currentAbilityBonus) {
        return currentAbilityBonus += this.bonuses[ability.index];
    }
}
