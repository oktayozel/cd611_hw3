package puzzles.io;

import java.util.Scanner;

public class InputCheck {
    public static String readLineOrExit(Scanner scanner) {
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting game. Goodbye!");
            System.exit(0);
        }
        return input;
    }

    public static int readIntOrExit(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = readLineOrExit(scanner);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String readDirectionOrExit(Scanner scanner) {
        while (true) {
            System.out.print("Enter direction (H for horizontal, V for vertical): ");
            String input = readLineOrExit(scanner).toUpperCase();
            if (input.equals("H") || input.equals("V")) {
                return input;
            }
            System.out.println("Invalid direction. Please enter H or V.");
        }
    }
}