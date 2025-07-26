public class Feature {
    public String name;
    public Action action;
    public PC pc;
    protected Object self;

    public Feature(String name, Action action, PC pc) {
        this.name = name;
        this.action = action;
        this.pc = pc;
    }
}
