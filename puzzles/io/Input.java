package puzzles.io;
import java.util.Scanner;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleBoard;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleCell;

/*
 * This class handles user input for the sliding puzzle game.
 * It provides methods to input username, puzzle size, moves, and new game prompts.
 */
public class Input{

    private final Scanner scanner;

    // Constructor to initialize the scanner for user input.
    public Input() {
        scanner = new Scanner(System.in);
    }

    public  String readLineOrExit() {
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting game. Goodbye!");
            System.exit(0);
        }
        return input;
    }
    // function overloading
    public  String readLineOrExit(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting game. Goodbye!");
            System.exit(0);
        }
        return input;
    }

    // Overloading the method
    public int readIntOrExit(String prompt) {
        return readIntOrExit(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public  int readIntOrExit( String prompt, int min_value, int max_value) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = readLineOrExit();
            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }
            if (value < min_value || value > max_value) {
                System.out.println("Invalid input!");
                System.out.printf("The input should be between %d and %d.%n", min_value, max_value);
                continue;
            }
            break;
        }
        return value;
    }

    public  String readDirectionOrExit(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = readLineOrExit().toUpperCase();
            if (input.equals("H") || input.equals("V")) {
                return input;
            }
            System.out.println("Invalid direction. Please enter H or V.");
        }
    }

    public String readStringOrExit(String prompt){
            while (true) {
                System.out.print(prompt);
                String input = readLineOrExit();
                if (!input.isEmpty()) {
                    return input;
                }
                System.out.printf("Input cannot be empty. Please enter a valid string.");
            }
    }

    


    // Method to read the user's move input and return the selected cell and the empty cell.
    public SlidingPuzzleCell[] readSlidingPuzzleMove(SlidingPuzzleBoard board){
        // int move;
        int maxValue = (board.getHeight()) * (board.getWidth())-1;

        while(true){
            int move = readIntOrExit(String.format("Enter cell to move (1 to %d) >>> ", maxValue), 1, maxValue);

            SlidingPuzzleCell cell = null;
            SlidingPuzzleCell emptyCell = null;
            String target = Integer.toString(move);

            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    SlidingPuzzleCell temp = board.getCell(i, j);
                    String value = temp.getValue();
                    if (value == null) emptyCell = temp;
                    else if (value.equals(target)) cell = temp;
                }
            }

            if (!board.areAdjacent(cell, emptyCell)) {
                System.out.println("That move is not possible since there is no neighbouring empty space. Try again.");
                continue;
            }

            return new SlidingPuzzleCell[]{cell, emptyCell};
        }

    }
    
    // Method to prompt the user for starting a new game or exiting.
    public boolean inputYesOrExit(String prompt){
        //scanner.nextLine();

        System.out.print(prompt);
        String input = readLineOrExit().toUpperCase();
        if (input.equals("Y") ) {
            return true;
        }
        System.out.println("Exiting game. Goodbye!");
        System.exit(0);

        return false;
    }


    // Method to wait for the user to press any key.
    public void getAnyKey(){
        scanner.nextLine();
    }


}