import Enums.AbilityScores;
import Enums.Skills;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public PC pc;

public void main() throws IOException {
    this.pc = new PC();
    pc.appliedClasses.add(new Class(1,this.pc));
    System.out.println("Welcome to test. Please enter a ");
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    String s = r.readLine();
    System.out.println(s);
    System.out.println("\n\n");
    int[] aScoreImp = new int[6];
    for (AbilityScores ability : AbilityScores.values()) {
        System.out.println(ability);
        aScoreImp[ability.index] = Integer.parseInt(r.readLine());
        System.out.println(aScoreImp[ability.index]);
    }
    AbilityScoreIncrease testASI = new AbilityScoreIncrease(aScoreImp, pc);
    System.out.println(Arrays.toString(pc.abilityScores.getAbilityScores()));
    System.out.println(Arrays.toString(pc.skillProficiencies.getSkillBonuses()));
    System.out.println("Now Adding Proficiency");
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String[] selectedString = input.split(",");
    List<Skills> selectedEnum = new java.util.ArrayList<>(List.of());
    for (String str : selectedString) {
        selectedEnum.add(Skills.valueOf(str));
    }
    for (Skills choice : selectedEnum) {
        pc.skillProficiencies.addProficiency(choice);
    }
    System.out.println(Arrays.toString(pc.skillProficiencies.getSkillBonuses()));
}

