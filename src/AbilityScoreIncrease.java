public class AbilityScoreIncrease {
    int[] scores;
    private final PC pc;
    public AbilityScoreIncrease(int[] scores, PC pc) {
        //  TODO: fix this, intellisence doesn't like it.
        assert PC.AbilityScoreEnum.values().length == scores.length;
        this.scores = scores;
        this.pc = pc;
        pc.appliedAbilityScoreIncreases.add(this);
    }
    public final int[] adjustScores() {
        return this.scores;
    }
    public int[] adjustScores(int[] existingScores) {
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
    public int adjustScore(PC.AbilityScoreEnum ability, int currentAbilityScore) {
        return currentAbilityScore += this.scores[PC.AbilityScoreEnum.getValue(ability)];
    }
}