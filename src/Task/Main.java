/***
 * The program should run automatically once executed, and the user should
 * have the option to switch between tasks and execute them as needed.
 * Owner: Mayank Aitan
 * Date of creation: 9 Sep 2024
 */
package Task;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskOperation taskOperation = new TaskOperation();
        Scanner scanner = new Scanner(System.in);
        MessageConstants messages = new MessageConstants();
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println(messages.mainMenuHeader);
            System.out.println(messages.optionGenerateCombinations);
            System.out.println(messages.optionDigitSumLoop);
            System.out.println(messages.optionFindConsecutiveSum);
            System.out.println(messages.optionEncryptVariableShift);
            System.out.println(messages.optionEncodeHighestChars);
            System.out.println(messages.optionExit);
            System.out.print(messages.promptEnterOption);
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    taskOperation.generateCombinations();
                    break;
                case "2":
                    taskOperation.digitSumLoop();
                    break;
                case "3":
                    taskOperation.findConsecutiveSums();
                    break;
                case "4":
                    taskOperation.encryptWithVariableShift();
                    break;
                case "5":
                    taskOperation.encodeHighestChars();
                    break;
                case "6":
                    continueProgram = false;
                    System.out.println(messages.exitMessage);
                    break;
                default:
                    System.out.println(messages.invalidOption);
                    break;
            }
        }

        scanner.close();
    }
}


