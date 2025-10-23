package puzzles.games.sliding_puzzle_components;

import puzzles.core.Cell;

/*
 * This class represents a cell in the sliding puzzle game.
 * It extends the abstract Cell class and provides constructors for initializing the cell with specific values.
 */

public class SlidingPuzzleCell extends Cell {
    // Constructors to initialize the cell with row, column, and optional value.
    public SlidingPuzzleCell(int row, int col, String value) {
        super(row, col, new SlidingPuzzlePiece(value));
    }
    // Constructor to initialize the cell with row and column, defaulting value to a space.
    public SlidingPuzzleCell(int row, int col) {
        super(row, col, new SlidingPuzzlePiece(" "));
    }
    // Constructor to initialize the cell with a single index for both row and column, defaulting value to a space.
    public SlidingPuzzleCell(int row) {
        super(row, row, new SlidingPuzzlePiece(" "));
    }




}





