import Enums.RecoveryTypes;

import java.lang.reflect.Method;

public class LimitedFeature extends Feature{
    public Method[] calculateUsages = new Method[20];
    public RecoveryTypes[] recoveryPeriod = new RecoveryTypes[20];
    // public //TODO: how to get alternative optional resources in here (like expending spell slots, key points, or
    public LimitedFeature(String name, Action action, PC pc, Method[] numberOfUsages, RecoveryTypes[] recoveryPeriod) {
        super(name,action,pc);
        this.calculateUsages = numberOfUsages;
        this.recoveryPeriod = recoveryPeriod;
    }
}