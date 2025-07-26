import Enums.RecoveryTypes;
import com.andrebreves.tuple.Tuple;
import com.andrebreves.tuple.Tuple2;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.function.Function;

public class LimitedFeature extends Feature{
    public Tuple2<String, Object>[] numberOfUsages = new Tuple2[20]; //TODO: help
    public RecoveryTypes[] recoveryPeriod = new RecoveryTypes[20];
    // public //TODO: how to get alternative optional resources in here (like expending spell slots, key points, or
    public LimitedFeature(String name, Action action, PC pc, Tuple2<String,Object>[] numberOfUsages, RecoveryTypes[] recoveryPeriod) {
        super(name,action,pc);
        this.numberOfUsages = numberOfUsages;
        this.recoveryPeriod = recoveryPeriod;
    }
    public int getNumberOfUsages(int level) {
        Object o = this.numberOfUsages[level-1].v2();
        if (o instanceof Integer) {
            //
        }
        else if (o instanceof Function<Integer>)
    }
}