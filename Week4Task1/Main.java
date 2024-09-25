package Week4Task1;

import java.util.Scanner;

public class Main {

    static String[] binaryValues = new String[100];
    static String[] octalValues = new String[100];
    static String[] decimalValues = new String[100];
    static String[] hexValues = new String[100];
    final static String RESET = "\u001B[0m";
    final static String RED = "\u001B[31m";
    final static String GREEN = "\u001B[32m";
    final static String YELLOW = "\u001B[33m";
    final static String BLUE = "\u001B[34m";
    final static String CYAN = "\u001B[36m";
    final static String BOLD = "\u001B[1m";
    final static String UNDERLINE = "\u001B[4m";

    static int binaryCount = 0, octalCount = 0, decimalCount = 0, hexCount = 0;
    static Constant message = new Constant();
    public static boolean sign = true;

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            message.mainMenu();
            if (!sign) {
                scanner.nextLine();
            }
            String mainChoice = scanner.nextLine();

            switch (mainChoice) {
                case "1":
                    performConversion(scanner);
                    break;
                case "2":
                    viewStoredValues(scanner);
                    break;
                case "3":
                    performOperations(scanner);
                    break;
                case "4":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    public static void performConversion(Scanner scanner) {
        System.out.println("Enter a number to convert:");
        String number = scanner.next();

        System.out.println();
        System.out.println("Choose the current format of the number:");
        System.out.println("1. Binary\n2. Octal\n3. Decimal\n4. Hexadecimal");
        System.out.println();
        int choice = scanner.nextInt();

        int decimalValue = 0;

        switch (choice) {
            case 1:
                if (isValidBinary(number)) {
                    decimalValue = binaryToDecimal(number);
                } else {
                    System.out.println("Invalid binary input.");
                    return;
                }
                sign = false;
                break;
            case 2:
                if (isValidOctal(number)) {
                    decimalValue = octalToDecimal(number);
                } else {
                    System.out.println("Invalid octal input.");
                    return;
                }
                sign = false;
                break;
            case 3:
                if (isValidDecimal(number)) {
                    decimalValue = stringToDecimal(number);
                } else {
                    System.out.println("Invalid decimal input.");
                    return;
                }
                sign = false;
                break;
            case 4:
                if (isValidHexadecimal(number)) {
                    decimalValue = hexadecimalToDecimal(number);
                } else {
                    System.out.println("Invalid hexadecimal input.");
                    return;
                }
                sign = false;
                break;
            default:
                System.out.println("Invalid input format.");
                System.out.println();
                sign = false;
                return;
        }

        System.out.println();
        System.out.println("Choose the format to convert the number to:");
        System.out.println("1. Binary\n2. Octal\n3. Decimal\n4. Hexadecimal");
        System.out.println();
        int conversionChoice = scanner.nextInt();

        switch (conversionChoice) {
            case 1:
                String binaryResult = decimalToBinary(decimalValue);
                binaryValues[binaryCount++] = binaryResult;
                System.out.println();
                System.out.println("Binary: " + binaryResult);
                sign = false;
                break;
            case 2:
                String octalResult = decimalToOctal(decimalValue);
                octalValues[octalCount++] = octalResult;
                System.out.println("Octal: " + octalResult);
                sign = false;
                break;
            case 3:
                String decimalResult = Integer.toString(decimalValue);
                decimalValues[decimalCount++] = decimalResult;
                System.out.println("Decimal: " + decimalResult);
                sign = false;
                break;
            case 4:
                String hexResult = decimalToHexadecimal(decimalValue);
                hexValues[hexCount++] = hexResult;
                System.out.println("Hexadecimal: " + hexResult);
                sign = false;
                break;
            default:
                System.out.println("Invalid choice.");
                sign = false;
                return;
        }
        return;
    }

    public static void viewStoredValues(Scanner scanner) {
        System.out.println("Choose the type of stored values to view:");
        System.out.println("1. Binary\n2. Octal\n3. Decimal\n4. Hexadecimal");
        String viewChoice = scanner.nextLine();

        switch (viewChoice) {
            case "1":
                printStoredArray(binaryValues, binaryCount, "Binary");
                break;
            case "2":
                printStoredArray(octalValues, octalCount, "Octal");
                break;
            case "3":
                printStoredArray(decimalValues, decimalCount, "Decimal");
                break;
            case "4":
                printStoredArray(hexValues, hexCount, "Hexadecimal");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void performOperations(Scanner scanner) {
        System.out.println("Choose the type of stored values to operate on:");
        System.out.println("1. Binary\n2. Octal\n3. Decimal\n4. Hexadecimal");
        String typeChoice = scanner.nextLine();

        switch (typeChoice) {
            case "1":
                performArithmetic(scanner, binaryValues, binaryCount, 2);
                break;
            case "2":
                performArithmetic(scanner, octalValues, octalCount, 8);
                break;
            case "3":
                performArithmetic(scanner, decimalValues, decimalCount, 10);
                break;
            case "4":
                performArithmetic(scanner, hexValues, hexCount, 16);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static void performArithmetic(Scanner scanner, String[] values, int count, int base) {
        if (count < 2) {
            System.out.println("Not enough numbers stored for operation.");
            return;
        }

        System.out.println("Choose the first number (index):");
        printStoredArray(values, count, "Stored");
        int firstIndex = getValidIntegerInput(scanner) - 1;

        System.out.println("Choose the second number (index):");
        printStoredArray(values, count, "Stored");
        int secondIndex = getValidIntegerInput(scanner) - 1;

        if (firstIndex < 0 || firstIndex >= count || secondIndex < 0 || secondIndex >= count) {
            System.out.println("Invalid indices.");
            return;
        }

        int num1 = stringToDecimalBase(values[firstIndex], base);
        int num2 = stringToDecimalBase(values[secondIndex], base);

        System.out.println("Choose operation:");
        System.out.println("1. Addition\n2. Subtraction\n3. Multiplication\n4. Division");
        scanner.nextLine();
        String operationChoice = scanner.nextLine();

        int result = 0;
        switch (operationChoice) {
            case "1":
                result = num1 + num2;
                System.out.println("Result: " + result);
                break;
            case "2":
                result = num1 - num2;
                System.out.println("Result: " + result);
                break;
            case "3":
                result = num1 * num2;
                System.out.println("Result: " + result);
                break;
            case "4":
                if (num2 == 0) {
                    System.out.println("Cannot divide by zero.");
                    return;
                }
                result = num1 / num2;
                System.out.println("Result: " + result);
                break;
            default:
                System.out.println("Invalid operation choice.");
                return;
        }

        System.out.println("Choose the format to display the result:");
        System.out.println("1. Binary\n2. Octal\n3. Decimal\n4. Hexadecimal");
        int formatChoice = scanner.nextInt();

        switch (formatChoice) {
            case 1:
                System.out.println("Binary: " + decimalToBinary(result));
                break;
            case 2:
                System.out.println("Octal: " + decimalToOctal(result));
                break;
            case 3:
                System.out.println("Decimal: " + result);
                break;
            case 4:
                System.out.println("Hexadecimal: " + decimalToHexadecimal(result));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public static int getValidIntegerInput(Scanner scanner) {
        while (true) {
            String input = scanner.next();
            boolean isValid = true;

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (i == 0 && c == '-') {
                    continue;
                }
                if (c < '0' || c > '9') {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }
    }

    public static void printStoredArray(String[] array, int count, String type) {
        if (count == 0) {
            System.out.println("No " + type + " values stored.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + array[i]);
            }
        }
    }

    public static int binaryToDecimal(String binary) {
        int decimal = 0, base = 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                decimal += base;
            }
            base *= 2;
        }
        return decimal;
    }

    public static int octalToDecimal(String octal) {
        int decimal = 0, base = 1;
        for (int i = octal.length() - 1; i >= 0; i--) {
            int digit = octal.charAt(i) - '0';
            decimal += digit * base;
            base *= 8;
        }
        return decimal;
    }

    public static int stringToDecimal(String number) {
        int decimal = 0;
        for (int i = 0; i < number.length(); i++) {
            decimal = decimal * 10 + (number.charAt(i) - '0');
        }
        return decimal;
    }

    public static int hexadecimalToDecimal(String hex) {
        int decimal = 0, base = 1;
        for (int i = hex.length() - 1; i >= 0; i--) {
            char ch = hex.charAt(i);
            if (ch >= '0' && ch <= '9') {
                decimal += (ch - '0') * base;
            } else if (ch >= 'A' && ch <= 'F') {
                decimal += (ch - 'A' + 10) * base;
            }
            base *= 16;
        }
        return decimal;
    }

    public static String decimalToBinary(int decimal) {
        StringBuilder binary = new StringBuilder();
        while (decimal > 0) {
            binary.insert(0, decimal % 2);
            decimal /= 2;
        }
        return binary.length() == 0 ? "0" : binary.toString();
    }

    public static String decimalToOctal(int decimal) {
        StringBuilder octal = new StringBuilder();
        while (decimal > 0) {
            octal.insert(0, decimal % 8);
            decimal /= 8;
        }
        return octal.length() == 0 ? "0" : octal.toString();
    }

    public static String decimalToHexadecimal(int decimal) {
        StringBuilder hex = new StringBuilder();
        while (decimal > 0) {
            int remainder = decimal % 16;
            if (remainder < 10) {
                hex.insert(0, (char) (remainder + '0'));
            } else {
                hex.insert(0, (char) (remainder - 10 + 'A'));
            }
            decimal /= 16;
        }
        return hex.length() == 0 ? "0" : hex.toString();
    }

    public static int stringToDecimalBase(String number, int base) {
        int decimal = 0;
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= '0' && ch <= '9') {
                decimal = decimal * base + (ch - '0');
            } else if (ch >= 'A' && ch <= 'F') {
                decimal = decimal * base + (ch - 'A' + 10);
            }
        }
        return decimal;
    }

    // Validation methods
    public static boolean isValidBinary(String binary) {
        for (char c : binary.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidOctal(String octal) {
        for (char c : octal.toCharArray()) {
            if (c < '0' || c > '7') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDecimal(String decimal) {
        for (char c : decimal.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidHexadecimal(String hex) {
        for (char c : hex.toCharArray()) {
            if ((c < '0' || c > '9') && (c < 'A' || c > 'F') && (c<'a' || c>'f')) {
                return false;
            }
        }
        return true;
    }
}
