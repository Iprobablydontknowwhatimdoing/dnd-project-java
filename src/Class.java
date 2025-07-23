public class Class {
    public int level;
    public final PC pc;
    public Class(int level, PC pc) {
        this.level = level;
        this.pc = pc;
        pc.appliedClasses.add(this);
    }
}
