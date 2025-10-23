package puzzles.core;


/*
 * This interface defines the structure and behavior of a board in the sliding puzzle game.
 * It includes methods for retrieving board dimensions, accessing cells, swapping cells, and checking adjacency.
 */
public abstract class Board {
    protected final Cell[][] board;

    public Board(Cell[][] board) {
        this.board = board;
    }

    public int getHeight(){
        return board.length;
    }
    public int getWidth(){
        return board[0].length;
    }

    public  Cell[][] getBoard() {
        return board;
    }
    public  Cell getCell(int row, int col) {
        return board[row][col];
    }
    public abstract void display();
}