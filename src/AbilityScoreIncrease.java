public class AbilityScoreIncrease {
    int[] scores;
    private final PC pc;
    public AbilityScoreIncrease(int[] scores, PC pc) {
        //  TODO: fix this, intellisence doesn't like it.
        //  try { assert Attributes.AbilityScores.values().length == scores.length;} catch (AssertionError e) {System.out.println( 'Whoops! you encountered error ' + e + '\n\n Please fix this!');}
        this.scores = scores;
        this.pc = pc;
        pc.appliedAbilityScoreIncreases.add(this);
    }
    public final int[] getScores() {
        return this.scores;
    }
    public int[] getScores(int[] existingScores) {
        try {
            assert existingScores.length == this.scores.length;
        } catch (AssertionError e) {
            System.out.println("Oh No! An Error!");
        }

        for (int i = 0; i < existingScores.length; i++) {
            existingScores[i] += this.scores[i];
        }

        return existingScores;
    }
}