import Enums.Actions;
import Enums.RecoveryType;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

public class Action {
    public final PC pc;

    public String name;
    public Actions actionType;
    public String description;
    public Optional<Integer> numberOfUsagesLeft;
    public Optional<Callable<Integer>> difficultyClass;
    public Optional<Callable<Integer>> toAttack;
    public Optional<Callable<Integer>> usagesAfterRecoveryLongRest;
    public Optional<Callable<Integer>> usagesAfterRecoveryShortRest;
    public Optional<Callable<Integer>> alternateResourceCost;
    public Optional<String> alternateResourceName;
    public Optional<List<String>> requiredResources;

    public Action(String name, String description, Actions actionType, PC pc) {
        this.name = name;
        this.actionType = actionType;
        this.pc = pc;
        this.description = description;
        pc.enabledActions.add(this);
    }
    public void Recover(RecoveryType recovery) {
        if (recovery == RecoveryType.LONG_REST) {
            if (usagesAfterRecoveryLongRest.isPresent()) {
                numberOfUsagesLeft = usagesAfterRecoveryLongRest.call(); //TODO: why does this not work, how to say that if one callable exists, than all callable exist
            } else if (usagesAfterRecoveryShortRest.isPresent()) {
                numberOfUsagesLeft = usagesAfterRecoveryShortRest.call();
            }
        } else if (usagesAfterRecoveryShortRest.isPresent()) {
            numberOfUsagesLeft = usagesAfterRecoveryShortRest.call();
        }
    }
    public void Use(Boolean alternateResourceUsed) {
        //TODO: make choice for use of alternate resource or main resource (if applicable) inside so that I can call it later without adding boolean to all calls
        if (alternateResourceUsed) {
            if (this.pc.optionalLimitedResources.get(alternateResourceName).currentNumber - alternateResourceCost.call() < 0) {                 //TODO: why is this screaming at me?
                System.out.println("Sorry, can't use alternative resource");
            } else {
                this.pc.optionalLimitedResources.get(alternateResourceName).currentNumber -= alternateResourceCost.call();
            }
        } else {
            if (this.numberOfUsagesLeft == 0) {
                System.out.println("You can't use this again, you've already used it enough times today. Recover before using"); //TODO: get this to stop screaming
            } else {
                this.numberOfUsagesLeft -= 1; // Assumes that if you wanted a higher cost, you would have just reduced the number of usages or made a special resource.
            }
        }
    }
    public Action(PC pc) {
        this.pc = pc;
    }
}
