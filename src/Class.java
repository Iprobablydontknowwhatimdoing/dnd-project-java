import org.jetbrains.annotations.NotNull;

public class Class {
    public int level;
    public final PC pc;
    public Class(int level, @NotNull PC pc) {
        this.level = level;
        this.pc = pc;
        pc.appliedClasses.add(this);
    }

    public Class(@NotNull PC pc) {
        this.level = 1; // Assumes that level starts at one
        this.pc = pc;
        pc.appliedClasses.add(this);
    }
}
