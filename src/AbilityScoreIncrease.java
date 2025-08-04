import Enums.AbilityScore;

public class AbilityScoreIncrease {
    int[] scores;
    private final PC pcInstance;
    public AbilityScoreIncrease(int[] scores, PC pcInstance) {
        //  TODO: fix this, intellisence doesn't like it.
        assert AbilityScore.values().length == scores.length;
        this.scores = scores;
        this.pcInstance = pcInstance;
        pcInstance.appliedAbilityScoreIncreases.add(this);
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
    public int adjustScore(AbilityScore ability, int currentAbilityScore) {
        return currentAbilityScore += this.scores[ability.index];
    }
}