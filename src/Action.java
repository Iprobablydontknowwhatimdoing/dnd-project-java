import Enums.Actions;

public class Action {
    public String name;
    public Actions actionType;
    public PC pc;
    public String description;

    public Action(String name, String description, Actions actionType, PC pc) {
        this.name = name;
        this.actionType = actionType;
        this.pc = pc;
        this.description = description;
        pc.enabledActions.add(this);
    }
}
