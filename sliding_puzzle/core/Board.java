package sliding_puzzle.core;

/*
 * This interface defines the structure and behavior of a board in the sliding puzzle game.
 * It includes methods for retrieving board dimensions, accessing cells, swapping cells, and checking adjacency.
 */
public interface Board{
    
    int getHeight();
    int getWidth();
    Cell[][] getBoard();
    Cell getCell(int row, int col); 
    boolean swapCells(Cell c1, Cell c2);
    boolean areAdjacent(Cell c1, Cell c2);

}