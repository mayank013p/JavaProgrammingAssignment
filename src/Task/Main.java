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
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("Main Menu:");
            System.out.println("1. Generate combinations");
            System.out.println("2. Digit sum loop");
            System.out.println("3. Exit");
            System.out.print("Enter option number: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    taskOperation.generateCombinations();
                    break;
                case "2":
                    taskOperation.digitSumLoop();
                    break;
                case "3":
                    continueProgram = false;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option! Please choose a valid option.");
                    break;
            }
        }

        scanner.close();
    }
}


