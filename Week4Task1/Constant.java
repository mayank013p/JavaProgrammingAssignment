package Week4Task1;

public class Constant {

    final static String RESET = "\u001B[0m";
    final static String RED = "\u001B[31m";
    final static String GREEN = "\u001B[32m";
    final static String YELLOW = "\u001B[33m";
    final static String BLUE = "\u001B[34m";
    final static String CYAN = "\u001B[36m";
    final static String BOLD = "\u001B[1m";
    final static String UNDERLINE = "\u001B[4m";

    
    public static void mainMenu(){
    System.out.println();
            System.out.println(BOLD + RED + "Choose the number of operation you want to perform:" + RESET);
            System.out.println("(Enter '" + YELLOW + "2" + RESET + "' to view the stored values)");

            System.out.println(BLUE + "1." + " "  + "Enter a number for conversion" + RESET);
            System.out.println(BLUE + "2." + " "  + "View stored values" + RESET);
            System.out.println(BLUE + "3." + " "  + "Perform operations on stored values" + RESET);
            System.out.println(BLUE + "4." + " "  + "Exit" + RESET);
            System.out.println();
    }
}
