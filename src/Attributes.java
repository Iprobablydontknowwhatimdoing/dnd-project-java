import static java.lang.Math.floor;

public class Attributes {
    private int[] scores;
    private int[][] bonuses;

    public static final int RACE = 0;


    public int getStrengthBonus() {
        double tmpScore = strengthBaseScore;
        if (bonuses[6][0] > -1) {
            tmpScore = floor(((double) bonuses[6][0] - 10) / 2);
        } else {
            for (int[] row : bonuses) {
                tmpScore += row[0];
            }
            tmpScore = floor((tmpScore - 10) / 2);
        }
        return (int) tmpScore;
    }

    public int getDexterityBonus() {
        double tmpScore = dexterityBaseScore;
        if (bonuses[6][1] > -1) {
            tmpScore = floor(((double) bonuses[6][1] - 10) / 2);
        } else {
            for (int[] row : bonuses) {
                tmpScore += row[1];
            }
            tmpScore = floor((tmpScore - 10) / 2);
        }
        return (int) tmpScore;
    }

    public int getConstitutionBonus() {
        double tmpScore = constitutionBaseScore;
        if (bonuses[6][2] > -1) {
            tmpScore = floor(((double) bonuses[6][2] - 10) / 2);
        } else {
            for (int[] row : bonuses) {
                tmpScore += row[2];
            }
            tmpScore = floor((tmpScore - 10) / 2);
        }
        return (int) tmpScore;
    }

    public int getIntelligenceBonus() {
        double tmpScore = intelligenceBaseScore;
        if (bonuses[6][3] > -1) {
            tmpScore = floor(((double) bonuses[6][3] - 10) / 2);
        } else {
            for (int[] row : bonuses) {
                tmpScore += row[3];
            }
            tmpScore = floor((tmpScore - 10) / 2);
        }
        return (int) tmpScore;
    }

    public int getWisdomBonus() {
        double tmpScore = wisdomBaseScore;
        if (bonuses[6][4] > -1) {
            tmpScore = floor(((double) bonuses[6][4] - 10) / 2);
        } else {
            for (int[] row : bonuses) {
                tmpScore += row[4];
            }
            tmpScore = floor((tmpScore - 10) / 2);
        }
        return (int) tmpScore;
    }

    public int getCharismaBonus() {
        double tmpScore = charismaBaseScore;
        if (bonuses[6][5] > -1) {
            tmpScore = floor(((double) bonuses[6][5] - 10) / 2);
        } else {
            for (int[] row : bonuses) {
                tmpScore += row[5];
            }
            tmpScore = floor((tmpScore - 10) / 2);
        }
        return (int) tmpScore;
    }

    public void setScoreBonus(int attr, int category, int val) {
        this.bonuses[attr][category] = val;
    }

    public enum AbilityScores {
        STRENGTH,
        DEXTERITY,
        CONSTITUTION,
        INTELLIGENCE,
        WISDOM,
        CHARISMA;

        public int getValue(AbilityScores abilityScore) {
            switch (abilityScore) {
                case STRENGTH -> {
                    return 0;
                }
                case DEXTERITY -> {
                    return 1;
                }
                case CONSTITUTION -> {
                    return 2;
                }
                case INTELLIGENCE -> {
                    return 3;
                }
                case WISDOM -> {
                    return 4;
                }
                case CHARISMA -> {
                    return 5;
                }
                default -> throw new IllegalStateException("Unexpected value: " + abilityScore);
            }
        }
    }
}
