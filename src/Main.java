import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public PC pc;
    public void main() throws IOException {
        this.pc = new PC();
        System.out.println("Welcome to test. Please enter a ");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s = r.readLine();
        System.out.println(s);
        System.out.println("\n\n");
        int[] aScoreImp = new int[6];
        for (PC.AbilityScoreEnum ability : PC.AbilityScoreEnum.values()) {
            aScoreImp[ability.index] =
        }
    }
}
