import java.util.Scanner;

public class CLI {
    public CLI() {}
    public static int choice(String[] choices) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose one of the following:");
        for (int i = 0; i < choices.length; i++) {
            System.out.println(((char) i + 'a') + ". " + choices[i]);
        }
        return (int) scan.nextLine().charAt(0) - 'a';
    }
}
