import java.util.HashMap;
import java.util.Map;

public class Player {

    Map<Attributes, Integer> attributesBaseScore;

    public Player(Map<Attributes, Integer> baseScores) {
        this.attributesBaseScore = new HashMap<>(baseScores);
    }

    public int getAttributeModifier(Attributes attribute) {

    }
}
