package Week2Task2;

import java.util.Scanner;

public class TaskOperation {
    public static Scanner scanner = new Scanner(System.in);
    public static final MessageConstants MESSAGES = new MessageConstants();

// Task 1: Count Palindromes
    public void countPalindromes(){
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

            System.out.println("Choose an option:");
            System.out.println("1. Process another string");
            System.out.println("2. Return to main menu");

            int choice = getUserChoice();
            processPalindromeTask(choice != 2);
        }
    }

    private String removeSpaces(String input, int start, int end) {
        if (start > end) return "";

        if (input.charAt(start) == ' ') {
            return removeSpaces(input, start + 1, end);
        } else if (input.charAt(end) == ' ') {
            return removeSpaces(input, start, end - 1);
        } else {
            return input.substring(start, end + 1);
        }
    }

    private void findAllPalindromes(String input, int start, int end, UniquePalindromes uniquePalindromes) {
        if (start >= input.length()) return;
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
        if (left >= right) return true;
        return input.charAt(left) == input.charAt(right) && isPalindrome(input, left + 1, right - 1);
    }

    private int getUserChoice() {
        System.out.println("Enter option number (1 or 2):");
        String input = scanner.nextLine();
        return validateChoice(removeSpaces(input, 0, input.length() - 1));
    }

    private int validateChoice(String input) {
        if (input.equals("1")) return 1;
        if (input.equals("2")) return 2;
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
            System.out.println("Choose an option:");
            System.out.println("1. Perform again with a new input");
            System.out.println("2. Exit");

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

        int n = scanner.nextInt();

        if (n <= 0) {  
            System.out.println("Input must be a positive integer greater than zero.");
            firstRun = false; 
            continue;
        }

        if (n > 92) {  
            System.out.println("Input is out of range. Please enter a number less than or equal to 92.");
            firstRun = false; 
            continue;
        }

        long result = fibonacci(n);
        System.out.println("Fibonacci number at position " + n + " is: " + result);

        firstRun = false; 
    }
}

private long fibonacci(int n) {
    if (n == 1 || n == 2) {
        return 1;  
    }
    return fibonacci(n - 1) + fibonacci(n - 2); 
}

// Task 3: Snake to Camel
public void snakeToCamel(){
    
}
// Task4: Count Consonents
public void countConsonants(){
    
}

// Task5: Binary To Decimal
public void binaryToDecimal(){
    
}


}
