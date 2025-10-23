package puzzles.io;
import java.util.Scanner;
import puzzles.games.dots_and_boxes_components.DotsAndBoxesBoard;
import puzzles.games.dots_and_boxes_components.DotsAndBoxesUser;
import puzzles.games.quoridor_components.QuoridorBoard;
import puzzles.games.quoridor_components.QuoridorUser;
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
    // helper method to read a line of input and check for "exit" command.
    public  String readLineOrExit() {
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("exit")) {
            //System.out.println("Exiting game. Goodbye!");
            Animations.displayAnimationWithSleep100("closing");
            System.exit(0);
        }
        return input;
    }

    // function overloading for readLineOrExit method takes a prompt as argument
    public  String readLineOrExit(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("exit")) {
            //System.out.println("Exiting game. Goodbye!");
            Animations.displayAnimationWithSleep100("closing");
            System.exit(0);
        }
        return input;
    }

    // Overloading the method
    public int readIntOrExit(String prompt) {
        return readIntOrExit(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // read an integer within a specified range or exit
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

    // read directions for dots and boxes or exit
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
    // Method to read a non-empty string OR exit.
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
        System.out.println("\nWhich cell do you want to move?(example : 2)");
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
    // Method to read and validate a move in the Dots and Boxes game.
    public void readDotsAndBoxesMove(DotsAndBoxesBoard board, DotsAndBoxesUser currentPlayer) {
        boolean validMove = false;
        while (!validMove) {
            int row = readIntOrExit("Enter row: ", 0, board.getRows());
            int col = readIntOrExit("Enter column: ", 0, board.getCols());

            if (row >= board.getRows() && col >= board.getCols()) {
                System.out.println("Invalid position. That dot is outside the board.");
                continue;
            }

            String dir = readDirectionOrExit("Enter direction (H for horizontal right line, V for vertical down line): ");

            validMove = board.claimEdge(row, col, dir, currentPlayer);
            if (!validMove) {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    public boolean readQuoridorMove(QuoridorBoard board, QuoridorUser currentPlayer) {
    System.out.println("\nYour options:");
    System.out.println("  - MOVE <UP|DOWN|LEFT|RIGHT>");
    System.out.println("  - WALL <H|V> <row> <col> (You can only choose the yellow line to create the wall.)");
    System.out.println("Type 'exit' to quit.");

    while (true) {
        String inputLine = readLineOrExit(">>> ").toUpperCase().trim();
        String[] tokens = inputLine.split("\\s+");

        if (tokens.length == 2 && tokens[0].equals("MOVE")) {
            String direction = tokens[1];
            boolean success = board.movePlayer(currentPlayer, direction);
            if (success) return true;
            System.out.println("Invalid move. Either blocked by wall or out of bounds.");
        } else if (tokens.length == 4 && tokens[0].equals("WALL")) {
            String orientation = tokens[1];
            try {
                int row = Integer.parseInt(tokens[2]);
                int col = Integer.parseInt(tokens[3]);

                if (currentPlayer.getWallsRemaining() <= 0) {
                    System.out.println("You have no walls remaining.");
                    continue;
                }

                boolean success = board.claimWall(row, col, orientation, currentPlayer);
                if (success) return true;
                System.out.println("Invalid wall placement. Either overlaps or out of bounds.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid coordinates. Please enter numeric row and column.");
            }
        } else {
            System.out.println("Invalid command. Use 'MOVE <direction>' or 'WALL <H/V> <row> <col>'.");
        }
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
        if (input.equals("M")){
            return false;
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
