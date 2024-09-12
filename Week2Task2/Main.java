/***
 * These Java tasks involve converting strings between snake_case and camelCase, counting palindromes, counting consonants,
 *  calculating Fibonacci numbers, and converting binary to decimal. Each task uses recursion, avoids loops and built-in methods,
 *  and includes input validation and retry options.
 * Owner: Mayank Aitan
 * Date of Creation: 11 Sep 2024
 * Date of Updation: 12 Sep 2024
 */

package Week2Task2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskOperation taskOperation = new TaskOperation();
        Scanner scanner = new Scanner(System.in);
        MessageConstants messages = new MessageConstants();
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println(messages.mainMenuHeader);
            System.out.println(messages.optionCountPalindromes);
            System.out.println(messages.optionNthFibonacci);
            System.out.println(messages.optionSnakeToCamel);
            System.out.println(messages.optionCountConsonants);
            System.out.println(messages.optionBinaryToDecimal);
            System.out.println(messages.optionExit);
            System.out.print(messages.promptEnterOption);
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    taskOperation.countPalindromes();
                    break;
                case "2":
                    taskOperation.nthFibonacci();
                    break;
                case "3":
                    taskOperation.snakeToCamel();
                    break;
                case "4":
                    taskOperation.countConsonants();
                    break;
                case "5":
                    taskOperation.binaryToDecimal();
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

