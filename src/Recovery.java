import Enums.RecoveryType;

import java.util.concurrent.Callable;

public class Recovery {
    public RecoveryType rechargePrompt;
    public Callable<Integer> recoveryAmount;
}
