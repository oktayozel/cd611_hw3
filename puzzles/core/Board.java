package puzzles.core;

/*
 * This interface defines the structure and behavior of a board in the sliding puzzle game.
 * It includes methods for retrieving board dimensions, accessing cells, swapping cells, and checking adjacency.
 */
public abstract class Board {

    public abstract int getHeight();
    public abstract int getWidth();
    public abstract Cell[][] getBoard();
    public abstract Cell getCell(int row, int col); 
    public abstract void display();
}