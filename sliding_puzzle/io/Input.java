package sliding_puzzle.io;
import java.util.Scanner;
import sliding_puzzle.components.SlidingPuzzleBoard;
import sliding_puzzle.components.SlidingPuzzleCell;
import sliding_puzzle.io.Output;

public class Input{

    public Scanner scanner;


    public Input() {
        scanner = new Scanner(System.in);
    }
    public String inputUsername(){
        Output.clearScreen();
        System.out.print("Enter a username >>> ");
        return scanner.nextLine();
    }
    

    public int[] inputPuzzleSize(boolean gameFirstOpen, String username){
        Output.clearScreen();
        if(gameFirstOpen){
        System.out.printf("Alright %s, let's customize your puzzle\n", username);
        }
        else{
            System.out.printf("Good to see you again %s. Let's customize your new puzzle\n", username);
        }
        System.out.print("Prompt your puzzle size.(example 3 4 creates a 3 row 4 column puzzle for you.)\n  >>> ");

        int rows = 0;
        int cols = 0;
        while( true){
            rows = scanner.nextInt();
            cols = scanner.nextInt();

            if (rows < SlidingPuzzleBoard.MIN_ROWS || cols < SlidingPuzzleBoard.MIN_COLS) {
                System.out.println("Invalid input!");
                System.out.printf("The dimensions of the puzzle must be at least %d x %d.%n",
                    SlidingPuzzleBoard.MIN_ROWS, SlidingPuzzleBoard.MIN_COLS);
                System.out.print(">>> ");
                continue;
            }

            if (rows > SlidingPuzzleBoard.MAX_ROWS || cols > SlidingPuzzleBoard.MAX_COLS) {
                System.out.println("Invalid input!");
                System.out.printf("The puzzle size must not exceed %d rows and %d columns.%n",
                    SlidingPuzzleBoard.MAX_ROWS, SlidingPuzzleBoard.MAX_COLS);
                System.out.print(">>> ");
                continue;
            }
            break;
        }



        return new int[] { rows, cols };
    }

    public SlidingPuzzleCell[] readMove(SlidingPuzzleBoard board){
        int move;
        int maxValue = (board.getHeight()) * (board.getWidth())-1;

        while(true){
            System.out.print(">>> ");

            move = scanner.nextInt();

            if (move < 1 || move > maxValue) {
                System.out.printf("❌ Invalid input! Please try again.");
                System.out.printf("Values must be between 1 and %d (both inclusive)\n", maxValue);
                continue;
            } 

            SlidingPuzzleCell cell = null;
            SlidingPuzzleCell emptyCell = null;
            String target = Integer.toString(move);

            for( int i = 0 ; i < board.getHeight() ; i++ ){
                for( int j = 0 ; j < board.getWidth() ; j++ ){
                    SlidingPuzzleCell temp = board.getCell(i,j);
                    String value = temp.getValue();
                    if (value == null) emptyCell = temp;
                    else if (value.equals(target)) cell = temp;    
                }
            }
            if (!board.areAdjacent(cell, emptyCell)) {
                System.out.println(" That move is not possible since there is no neighbouring empty space. Make a valid move.");
                continue;
            }
            return new SlidingPuzzleCell[]{cell,emptyCell};
        }
    }

    public boolean inputNewGame(){
        scanner.nextLine();
        System.out.print("To play a new game type y/Y, to exit press any key >>> ");
        String newGame = scanner.nextLine();
        System.out.printf("newGame value = %s", newGame);
        if(newGame.equals("y") || newGame.equals("Y")){
            return true;
        }
        else{
            return false;
        }
    }
    public static void getAnyKey(){
        new java.util.Scanner(System.in).nextLine();
    }

}