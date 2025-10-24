package puzzles.core;


/*
 * This abstract class defines the structure and behavior of a board in the sliding puzzle game.
 * It includes methods for retrieving board dimensions, accessing cells, swapping cells, and checking adjacency.
 */
public abstract class Board {
    protected final Cell[][] board;

    // default constructor
    public Board(Cell[][] board) {
        this.board = board;
    }
    // default get height method for the board
    public int getHeight(){
        return board.length;
    }
    // default get width method for the board
    public int getWidth(){
        return board[0].length;
    }
    // returns the entire board
    public  Cell[][] getBoard() {
        return board;
    }
    
    // returns a specific cell at the given row and column
    public  Cell getCell(int row, int col) {
        return board[row][col];
    }
    public abstract void display();
}