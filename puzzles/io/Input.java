package puzzles.io;
import java.util.Scanner;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleBoard;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleCell;

/*
 * This class handles user input for the sliding puzzle game.
 * It provides methods to input username, puzzle size, moves, and new game prompts.
 */
public class Input{

    public Scanner scanner;

    // Constructor to initialize the scanner for user input.
    public Input() {
        scanner = new Scanner(System.in);
    }
    // Method to input the username from the user.
    public String inputUsername(){
        Output.clearScreen();
        System.out.print("Enter a username >>> ");
        return InputCheck.readLineOrExit(scanner);
    }
    
    // Method to input the puzzle size (rows and columns) from the user.
    public int[] inputPuzzleSize(boolean gameFirstOpen, String username){
        Output.clearScreen();
        if(gameFirstOpen){
            System.out.printf("Alright %s, let's customize your puzzle\n", username);
        } else {
            System.out.printf("Good to see you again %s. Let's customize your new puzzle\n", username);
        }

        int rows = 0;
        int cols = 0;

        while (true) {
            rows = InputCheck.readIntOrExit(scanner, "Enter number of rows >>> ");
            cols = InputCheck.readIntOrExit(scanner, "Enter number of columns >>> ");

            if (rows < SlidingPuzzleBoard.MIN_ROWS || cols < SlidingPuzzleBoard.MIN_COLS) {
                System.out.println("Invalid input!");
                System.out.printf("The dimensions of the puzzle must be at least %d x %d.%n", SlidingPuzzleBoard.MIN_ROWS, SlidingPuzzleBoard.MIN_COLS);
                continue;
            }

            if (rows > SlidingPuzzleBoard.MAX_ROWS || cols > SlidingPuzzleBoard.MAX_COLS) {
                System.out.println("Invalid input!");
                System.out.printf("The puzzle size must not exceed %d rows and %d columns.%n", SlidingPuzzleBoard.MAX_ROWS, SlidingPuzzleBoard.MAX_COLS);
                continue;
            }

            break;
        }

        return new int[] { rows, cols };

    }


    // Method to read the user's move input and return the selected cell and the empty cell.
    public SlidingPuzzleCell[] readMove(SlidingPuzzleBoard board){
        // int move;
        int maxValue = (board.getHeight()) * (board.getWidth())-1;

        while(true){
            int move = InputCheck.readIntOrExit(scanner, String.format("Enter cell to move (1 to %d) >>> ", maxValue));

            if (move < 1 || move > maxValue) {
                System.out.printf("Invalid input! Values must be between 1 and %d (inclusive)\n", maxValue);
                continue;
            }

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
    public boolean inputNewGame(){
        //scanner.nextLine();
        System.out.print("To play a new game type y/Y, to exit press any key >>> ");
        //String newGame = scanner.nextLine();
        String newGame = InputCheck.readLineOrExit(scanner);
        if(newGame.equals("y") || newGame.equals("Y")){
            return true;
        }
        else{
            return false;
        }
    }

    // Method to wait for the user to press any key.
    public static void getAnyKey(){
        new java.util.Scanner(System.in).nextLine();
    }


    // ------------------------------------------------------------------------------------------------------------------------------------------
    // GameSelectionManager methods 
    // ------------------------------------------------------------------------------------------------------------------------------------------

    public String readGameSelection(){
        //TODO : implement error handling 
        System.out.print("Which game would you like to play? (Type 'sliding_puzzle' or 'dots_and_boxes') >>> ");
        //return scanner.nextLine();
        return InputCheck.readLineOrExit(scanner);
    }


}