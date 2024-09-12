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

public class TaskOperation {
    public static Scanner scanner = new Scanner(System.in);
    public static final MessageConstants MESSAGES = new MessageConstants();

    // Task 1: Count Palindromes:
    public void countPalindromes() {
        processPalindromeTask(true);
    }

    private void processPalindromeTask(boolean continueProcessing) {
        if (continueProcessing) {
            System.out.println("Enter the string to count palindromes: ");
            String input = scanner.nextLine();
            input = removeSpaces(input, 0, input.length() - 1);

            if (input.length() == 0) {
                System.out.println("Input is empty or only whitespace. Please enter a valid string.");
                processPalindromeTask(true);
                return;
            }

            System.out.println("Processing string: " + input);

            UniquePalindromes uniquePalindromes = new UniquePalindromes();
            findAllPalindromes(input, 0, 0, uniquePalindromes);

            System.out.println("Number of unique palindrome substrings: " + uniquePalindromes.getCount());

            System.out.println(MESSAGES.chooseOption);
            System.out.println(MESSAGES.optionPerformAgain);
            System.out.println(MESSAGES.optionReturnToMenu);

            int choice = getUserChoice();
            processPalindromeTask(choice != 2);
        }
    }

    private String removeSpaces(String input, int start, int end) {
        if (start > end)
            return "";

        if (input.charAt(start) == ' ') {
            return removeSpaces(input, start + 1, end);
        } else if (input.charAt(end) == ' ') {
            return removeSpaces(input, start, end - 1);
        } else {
            return input.substring(start, end + 1);
        }
    }

    private void findAllPalindromes(String input, int start, int end, UniquePalindromes uniquePalindromes) {
        if (start >= input.length())
            return;
        if (end >= input.length()) {
            findAllPalindromes(input, start + 1, start + 1, uniquePalindromes);
            return;
        }

        if (isPalindrome(input, start, end)) {
            uniquePalindromes.add(input.substring(start, end + 1));
        }
        findAllPalindromes(input, start, end + 1, uniquePalindromes);
    }

    private boolean isPalindrome(String input, int left, int right) {
        if (left >= right)
            return true;
        return input.charAt(left) == input.charAt(right) && isPalindrome(input, left + 1, right - 1);
    }

    private int getUserChoice() {
        System.out.println("Enter option number (1 or 2):");
        String input = scanner.nextLine();
        return validateChoice(removeSpaces(input, 0, input.length() - 1));
    }

    private int validateChoice(String input) {
        if (input.equals("1"))
            return 1;
        if (input.equals("2"))
            return 2;
        System.out.println("Invalid input. Please enter 1 or 2.");
        return getUserChoice();
    }

    private class UniquePalindromes {
        private String[] uniquePalindromes = new String[1000];
        private int count = 0;

        public void add(String palindrome) {
            if (!contains(palindrome, 0)) {
                uniquePalindromes[count++] = palindrome;
            }
        }

        public int getCount() {
            return count;
        }

        private boolean contains(String palindrome, int index) {
            if (index >= count) {
                return false;
            }
            if (uniquePalindromes[index].equals(palindrome)) {
                return true;
            }
            return contains(palindrome, index + 1);
        }
    }

    // Task 2: Nth Fibonacci
    public void nthFibonacci() {
        boolean firstRun = true;

        while (true) {
            if (!firstRun) {
                System.out.println(MESSAGES.chooseOption);
                System.out.println(MESSAGES.optionPerformAgain);
                System.out.println(MESSAGES.optionReturnToMenu);

                System.out.print("Enter option number: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Exiting...");
                    break;
                }

                int option = scanner.nextInt();

                if (option == 1) {
                    firstRun = true;
                } else if (option == 2) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid option! Exiting...");
                    break;
                }
            }

            System.out.println("Enter a positive integer to find the Fibonacci number at that position:");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid positive integer.");
                scanner.next();
                firstRun = false;
                continue;
            }

            int input = scanner.nextInt();

            if (input <= 0) {
                System.out.println("Input must be a positive integer greater than zero.");
                firstRun = false;
                continue;
            }

            if (input > 92) {
                System.out.println("Input is out of range. Please enter a number less than or equal to 92.");
                firstRun = false;
                continue;
            }

            long result = fibonacci(input);
            System.out.println("Fibonacci number at position " + input + " is: " + result);

            firstRun = false;
        }
    }

    private long fibonacci(int input) {
        return fibonacciHelper(input, 1, 1);
    }

    private long fibonacciHelper(int input, long prev1, long prev2) {
        if (input == 1 || input == 2) {
            return prev2;
        }
        return fibonacciHelper(input - 1, prev2, prev1 + prev2);
    }

    // Task 3: Snake to Camel
    public void snakeToCamel() {
        System.out.println("Enter the string to change the form of string into Camel: ");
        String input = scanner.nextLine();
        String snakeCase = toSnakeCase(input);
        String camelCase = toCamelCase(snakeCase);

        System.out.println("The Camle case Output is: " + camelCase);
    }

    private String toSnakeCase(String input) {
        return toSnakeCaseHelper(input, "", 0);
    }

    private String toSnakeCaseHelper(String input, String result, int index) {
        if (index == input.length()) {
            return result;
        }

        char currentChar = input.charAt(index);
        if (Character.isUpperCase(currentChar)) {
            result += "_" + Character.toLowerCase(currentChar);
        } else if (currentChar == ' ') {
            result += "_";
        } else {
            result += currentChar;
        }
        return toSnakeCaseHelper(input, result, index + 1);
    }

    private String toCamelCase(String input) {
        return toCamelCaseHelper(input, "", 0, false);
    }

    private String toCamelCaseHelper(String input, String result, int index, boolean capitalizeNext) {
        if (index == input.length()) {
            return result;
        }

        char currentChar = input.charAt(index);
        if (currentChar == '_') {
            return toCamelCaseHelper(input, result, index + 1, true);
        } else if (capitalizeNext) {
            result += Character.toUpperCase(currentChar);
            capitalizeNext = false;
        } else {
            result += currentChar;
        }
        return toCamelCaseHelper(input, result, index + 1, capitalizeNext);
    }

    @SuppressWarnings("unused")
    private void showOptions() {
        System.out.println(MESSAGES.chooseOption);
        System.out.println(MESSAGES.optionPerformAgain);
        System.out.println(MESSAGES.optionReturnToMenu);

        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 1) {
            snakeToCamel();
        } else {
            System.out.println("Exiting...");
        }
    }

    // Task4: Count Consonents
    public void countConsonants() {
        System.out.println("Enter a string: ");
        String input = scanner.nextLine();

        if (input.trim().isEmpty() || isNumeric(input)) {
            System.out.println("Invalid input. Please enter a valid string.");
            showOption();
            return;
        }

        int consonantCount = countConsonantsHelper(input, 0, 0);
        System.out.println("Number of consonants: " + consonantCount);
        showOption();
    }

    private int countConsonantsHelper(String input, int index, int count) {
        if (index == input.length()) {
            return count;
        }

        char currentChar = input.charAt(index);
        if (isConsonant(currentChar)) {
            count++;
        }

        return countConsonantsHelper(input, index + 1, count);
    }

    private boolean isConsonant(char input) {
        input = Character.toLowerCase(input);
        return ((input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z'))
                && !(input == 'a' || input == 'e' || input == 'i' || input == 'o' || input == 'u' || input == 'A'
                        || input == 'E' || input == 'I' || input == 'O' || input == 'U');
    }

    private boolean isNumeric(String input) {
        return isNumericHelper(input, 0);
    }

    private boolean isNumericHelper(String input, int index) {
        if (index == input.length()) {
            return true;
        }

        char currentChar = input.charAt(index);
        if (currentChar < '0' || currentChar > '9') {
            return false;
        }

        return isNumericHelper(input, index + 1);
    }

    private void showOption() {
        System.out.println(MESSAGES.chooseOption);
        System.out.println(MESSAGES.optionPerformAgain);
        System.out.println(MESSAGES.optionReturnToMenu);

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            countConsonants();
        } else {
            System.out.println("Exiting...");
        }
    }

    // Task5: Binary To Decimalt
    public void binaryToDecimal() {
        System.out.println("Enter a binary number: ");
        String binaryInput = scanner.nextLine();

        if (!isValidBinary(binaryInput)) {
            System.out.println("Invalid binary number. Please enter a valid binary number.");
            showOption1();
            return;
        }

        int decimalValue = binaryToDecimalHelper(binaryInput, 0, 0);
        System.out.println("Decimal value: " + decimalValue);
        showOption1();
    }

    private int binaryToDecimalHelper(String binary, int index, int decimal) {
        if (index == binary.length()) {
            return decimal;
        }

        char currentChar = binary.charAt(index);
        if (currentChar == '1') {
            decimal += (1 << (binary.length() - index - 1));
        }

        return binaryToDecimalHelper(binary, index + 1, decimal);
    }

    private boolean isValidBinary(String input) {
        return isValidBinaryHelper(input, 0);
    }

    private boolean isValidBinaryHelper(String input, int index) {
        if (index == input.length()) {
            return true;
        }

        char currentChar = input.charAt(index);
        if (currentChar != '0' && currentChar != '1') {
            return false;
        }

        return isValidBinaryHelper(input, index + 1);
    }

    private void showOption1() {
        System.out.println(MESSAGES.chooseOption);
        System.out.println(MESSAGES.optionPerformAgain);
        System.out.println(MESSAGES.optionReturnToMenu);

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            binaryToDecimal();
        } else {
            System.out.println("Exiting...");
        }
    }

}
