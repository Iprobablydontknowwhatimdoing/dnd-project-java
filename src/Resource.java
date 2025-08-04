import Enums.RecoveryType;

import java.util.Optional;
import java.util.concurrent.Callable;

public class Resource {
    public Callable<Integer> maximum;
    public Callable<Integer> usagesAfterRecoveryLongRest;
    public Optional<Callable<Integer>> usagesAfterRecoveryShortRest;
    public int currentNumber = usagesAfterRecoveryLongRest.call();
    public void recover(Enums.RecoveryType recovery) throws Exception {
        if (recovery == RecoveryType.SHORT_REST) {
            if (usagesAfterRecoveryShortRest.isPresent()) {
                if (currentNumber + usagesAfterRecoveryShortRest.call() > maximum.call()) {
                    currentNumber = maximum.call();
                } else {
                    currentNumber += usagesAfterRecoveryShortRest.call();
                }
            }
        } else if (recovery == RecoveryType.LONG_REST) {
            if (currentNumber + usagesAfterRecoveryLongRest.call() > maximum.call()) {
                currentNumber = maximum.call();
            } else {
                currentNumber += usagesAfterRecoveryLongRest.call();
            }
        }
    }
}
