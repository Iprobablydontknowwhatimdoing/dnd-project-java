import java.util.ArrayList;
import java.util.List;

public class Feature {
    public String name;
    public String description;
    public int level;
    public List<Recovery> recoveries;
    public List<Action> enabledActions = new ArrayList<>();
}
