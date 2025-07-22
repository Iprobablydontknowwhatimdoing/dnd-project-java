import java.util.Scanner;

public class Race {
    public final PC pc;
    public final String abilityScoreIncreaseText = "Choose one of: (a) Choose any +2; choose any other +1 (b) Choose three different +1"
    public AbilityScoreIncrease abilityScoreIncrease;
    public Race(PC pc) {
        this.pc = pc;
    }
    public void askAbilityScoreIncrease() {
        System.out.println("Please choose an ability score increase:");
        System.out.println(abilityScoreIncrease);
        Scanner answerScanner = new Scanner(System.in);
        System.out.println("Choice (character): ");
        char choice = answerScanner.nextLine().charAt(0);
        switch (choice) {
            case 'a' -> {
                System.out.println("Please choose your +2 ability score bonus");
            } case 'b' -> {
                System.out.println("Please choose your +1 ability score bonus");
            } default -> {
                System.out.println("Sorry, this is an invalid answer. Please try again. \n\n\n");
                askAbilityScoreIncrease();
            }
        }
    }
}
