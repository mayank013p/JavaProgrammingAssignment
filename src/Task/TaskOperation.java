/***
 * The program should run automatically once executed, and the user should
 * have the option to switch between tasks and execute them as needed.
 * Owner: Mayank Aitan
 * Date of creation: 9 Sep 2024
 */
package Task;
import java.util.Scanner;

public class TaskOperation {

    public static final MessageConstants MESSAGES = new MessageConstants();

// Task 1: Generate Combinations
    @SuppressWarnings("resource")
    public void generateCombinations() {
        Scanner scanner = new Scanner(System.in);
        boolean continueGenerating = true;

        while (continueGenerating) {
            System.out.print("Enter a string: ");
            String userInputString = scanner.nextLine();
            char[] inputArray = userInputString.toCharArray();
            int length = inputArray.length;

            System.out.println("Combinations and Permutations:");

            for (int i = 1; i < (1 << length); i++) {
                char[] subset = new char[length];
                int subsetLength = 0;

                for (int j = 0; j < length; j++) {
                    if ((i & (1 << j)) != 0) {
                        subset[subsetLength++] = inputArray[j];
                    }
                }

                generatePermutations(subset, 0, subsetLength);
            }

            System.out.println(MESSAGES.chooseOption);
            System.out.println(MESSAGES.optionPerformAgain);
            System.out.println(MESSAGES.optionReturnToMenu);
            System.out.print(MESSAGES.promptEnterOption);
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
    private void generatePermutations(char[] array, int left, int right) {
        if (left == right) {
            for (int i = 0; i < right; i++) {
                System.out.print(array[i]);
            }
            System.out.println();
        } else {
            for (int i = left; i < right; i++) {
                char temp = array[left];
                array[left] = array[i];
                array[i] = temp;

                generatePermutations(array, left + 1, right);

                temp = array[left];
                array[left] = array[i];
                array[i] = temp;
            }
        }
    }


// Task 2: Digit Sum
    @SuppressWarnings("resource")
    public void digitSumLoop() {
        Scanner scanner = new Scanner(System.in);
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

            System.out.println(MESSAGES.chooseOption);
            System.out.println(MESSAGES.optionPerformAgain);
            System.out.println(MESSAGES.optionReturnToMenu);
            System.out.print(MESSAGES.promptEnterOption);
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
    }


    // Task 3 Find Consecutive Sum
    @SuppressWarnings("resource")
    public void findConsecutiveSums() {
        Scanner scanner = new Scanner(System.in);
        boolean continueFinding = true;

        while (continueFinding) {
            System.out.print("Enter a positive integer: ");
            String userInput = scanner.nextLine();
            
            boolean isValidNumber = true;
            for (int i = 0; i < userInput.length(); i++) {
                if (userInput.charAt(i) < '0' || userInput.charAt(i) > '9') {
                    isValidNumber = false;
                    break;
                }
            }

            if (!isValidNumber || userInput.isEmpty()) {
                System.out.println("Invalid input! Please enter a valid positive integer.");
                continue;
            }
            
            int number = Integer.parseInt(userInput);
            boolean found = false;

            for (int start = 1; start <= number / 2; start++) {
                int sum = 0;
                for (int i = start; sum < number; i++) {
                    sum += i;
                    if (sum == number) {
                        for (int j = start; j <= i; j++) {
                            if (j > start) {
                                System.out.print(" + ");
                            }
                            System.out.print(j);
                        }
                        System.out.println();
                        found = true;
                        break;
                    }
                }
            }
            
            if (!found) {
                System.out.println("No combinations found.");
            }

            System.out.println(MESSAGES.chooseOption);
            System.out.println(MESSAGES.optionPerformAgain);
            System.out.println(MESSAGES.optionReturnToMenu);
            System.out.print(MESSAGES.promptEnterOption);
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    break;
                case "2":
                    continueFinding = false;
                    break;
                default:
                    System.out.println("Invalid option! Returning to main menu.");
                    continueFinding = false;
                    break;
            }
        }
    }

// Task 4: Encryption With Variable Shift
    @SuppressWarnings("resource")
    public void encryptWithVariableShift() {
        Scanner scanner = new Scanner(System.in);
        boolean continueEncrypting = true;

        while (continueEncrypting) {
            System.out.print("Enter a string to encrypt: ");
            String inputString = scanner.nextLine().trim();

            if (inputString.isEmpty() || !inputString.chars().allMatch(Character::isLetter)) {
                System.out.println("Invalid input! Please enter a non-empty string with only alphabetic characters.");
                continue;
            } 

            System.out.print("Enter shift pattern array (comma-separated, e.g., 1,2,3): ");
            String[] patternInput = scanner.nextLine().split(",");
            if (patternInput.length == 0 || patternInput[0].trim().isEmpty()) {
                System.out.println("Shift pattern cannot be empty. Please enter valid pattern values.");
                continue;
            }

            int[] shiftPattern = new int[patternInput.length];
            boolean validPattern = true;

            for(int i = 0; i < patternInput.length; i++) {
                try {
                    shiftPattern[i] = Integer.parseInt(patternInput[i].trim());
                    if (shiftPattern[i] < 0) {
                        System.out.println("Shift values must be non-negative integers.");
                        validPattern = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid pattern value. Please enter integers only.");
                    validPattern = false;
                    break;
                }
            }

            if (!validPattern) {
                continue;
            }
            StringBuilder encryptedString = new StringBuilder();
            int patternLength = shiftPattern.length;
            for (int i = 0; i < inputString.length(); i++) {
                char ch = inputString.charAt(i);
                int shift = shiftPattern[i % patternLength];

                if (Character.isLetter(ch)) {
                    char base = Character.isUpperCase(ch) ? 'A' : 'a';
                    char encryptedChar = (char) ((ch - base + shift) % 26 + base);
                    encryptedString.append(encryptedChar);
                } else {
                    encryptedString.append(ch);
                }
            }

            System.out.println("Encrypted String: " + encryptedString);

            System.out.println(MESSAGES.chooseOption);
            System.out.println(MESSAGES.optionPerformAgain);
            System.out.println(MESSAGES.optionReturnToMenu);
            System.out.print(MESSAGES.promptEnterOption);
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    break;
                case "2":
                    continueEncrypting = false;
                    break;
                default:
                    System.out.println("Invalid option! Returning to main menu.");
                    continueEncrypting = false;
                    break;
            }
        }
}


//Task 5: Encoding for digits
 @SuppressWarnings("resource")
 public void encodeHighestChars() {
    Scanner scanner = new Scanner(System.in);

    boolean continueEncoding = true;

    while (continueEncoding) {
        String inputArray = "";
        boolean validArray = false;

        while (!validArray) {
            System.out.print("Enter the array of characters (e.g., 2a3b1C): ");
            inputArray = scanner.nextLine();

            // Validate if the input contains only alphanumeric characters
            if (inputArray.matches("[a-zA-Z0-9]+")) {
                validArray = true;
            } else {
                System.out.println("Invalid input! Please enter only alphanumeric characters.");
            }
        }

        char[] chars = inputArray.toCharArray(); // Convert input to char array

        // Sort the characters in descending order based on their ASCII values
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] < chars[j]) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
        }

        String inputSeries = "";
        boolean validSeries = false;

        while (!validSeries) {
            System.out.print("Enter the series of integers (e.g., 123): ");
            inputSeries = scanner.nextLine();

            // Validate if the input contains only digits
            if (inputSeries.matches("\\d+")) {
                validSeries = true;
            } else {
                System.out.println("Invalid input! Please enter only digits.");
            }
        }

        StringBuilder encodedString = new StringBuilder();
        int seriesLength = inputSeries.length();
        for (int i = 0; i < seriesLength; i++) {
            // Ensure we don't go out of bounds
            char highestChar = i < chars.length ? chars[i] : '0'; // Default to '0' if out of bounds
            int asciiValue = (int) highestChar; // Convert character to its ASCII value
            encodedString.append(asciiValue);   // Append the ASCII value to the string
        }

        System.out.println(MESSAGES.chooseOption);
            System.out.println(MESSAGES.optionPerformAgain);
            System.out.println(MESSAGES.optionReturnToMenu);
            System.out.print(MESSAGES.promptEnterOption);
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                break;
            case "2":
                continueEncoding = false;
                break;
            default:
                System.out.println("Invalid option! Returning to main menu.");
                continueEncoding = false;
                break;
        }
    }
}
}
