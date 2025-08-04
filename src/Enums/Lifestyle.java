package Enums;

public enum Lifestyle {
    WRETCHED(0),
    SQUALID(1),
    POOR(2),
    MODEST(10),
    COMFORTABLE(20),
    WEALTHY(40),
    ARISTOCRATIC(100);

    public final int costInSilver;
    Lifestyle(int i) {
        this.costInSilver = i;
    }
}
