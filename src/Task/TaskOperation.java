/***
 * The program should run automatically once executed, and the user should
 * have the option to switch between tasks and execute them as needed.
 * Owner: Mayank Aitan
 * Date of creation: 9 Sep 2024
 */
package Task;
import java.util.Scanner;

public class TaskOperation{

    public void generateCombinations() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continueGenerating = true;

            while (continueGenerating) {
                System.out.print("Enter a string: ");
                String userInputString = scanner.nextLine();
                int stringLength = userInputString.length();

                for (int i = 0; i < (1 << stringLength); i++) {
                    String combination = "";
                    for (int j = 0; j < stringLength; j++) {
                        if ((i & (1 << j)) != 0) {
                            combination += userInputString.charAt(j);
                        }
                    }
                    if (!combination.equals("")) {
                        System.out.println(combination);
                    }
                }

                System.out.println("Choose an option:");
                System.out.println("1. Perform again with a new string");
                System.out.println("2. Return to main menu");
                System.out.print("Enter option number: ");
                String option = scanner.nextLine();

                switch (option) {
                    case "1":
                        break;
                    case "2":
                        continueGenerating = false;
                        break;
                    default:
                        System.out.println("Invalid option! Returning to main menu.");
                        continueGenerating = false;
                        break;
                }
            }
        }
    }

    // Task 2: Method to calculate digit sum until a single digit is reached in a single method
    public void digitSumLoop() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continueSumming = true;

            while (continueSumming) {
                System.out.print("Enter a number: ");
                String userInputNumber = scanner.nextLine();

                boolean isValidNumber = true;
                for (int i = 0; i < userInputNumber.length(); i++) {
                    if (userInputNumber.charAt(i) < '0' || userInputNumber.charAt(i) > '9') {
                        isValidNumber = false;
                        break;
                    }
                }

                if (!isValidNumber) {
                    System.out.println("Invalid input! Please enter a valid positive integer.");
                    continue;  
                }

                int number = Integer.parseInt(userInputNumber);

                while (number >= 10) {
                    int sum = 0;
                    while (number > 0) {
                        sum += number % 10;
                        number /= 10;
                    }
                    number = sum;
                }
                System.out.println("Final single digit sum: " + number);

                System.out.println("Choose an option:");
                System.out.println("1. Perform again with a new number");
                System.out.println("2. Return to main menu");
                System.out.print("Enter option number: ");
                String option = scanner.nextLine();

                switch (option) {
                    case "1":
                        break;
                    case "2":
                        continueSumming = false;
                        break;
                    default:
                        System.out.println("Invalid option! Returning to main menu.");
                        continueSumming = false;
                        break;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
