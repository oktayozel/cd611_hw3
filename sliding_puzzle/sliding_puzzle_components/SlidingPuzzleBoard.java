package sliding_puzzle.sliding_puzzle_components;


import sliding_puzzle.core.Cell;
import sliding_puzzle.core.Board;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SlidingPuzzleBoard implements Board{
    private final int rowCount;
    private final int colCount;
    
    private final SlidingPuzzleCell[][] board;

    public SlidingPuzzleBoard(int rowCount, int colCount){
        this.rowCount = rowCount;
        this.colCount = colCount;

        this.board = initializeBoardRandomly();
        
    }

    private SlidingPuzzleCell[][] initializeBoardRandomly(){
        SlidingPuzzleCell[][] newBoard = new SlidingPuzzleCell[rowCount][colCount];
        int totalCellCount = rowCount*colCount;

        List<String> cellValues = new ArrayList<>();

        // Generate an arraylist of Strings in order.
        for (int i = 1; i <= totalCellCount; i++) {
            cellValues.add(Integer.toString(i));
        }

        // Randomly shuffle this arraylist
        Collections.shuffle(cellValues);

        // Create a Random object
        Random random = new Random();
        int blankCell = random.nextInt(totalCellCount); 

        // Select empty cell
        int valsIdx = 0;
        int k = 0;

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++, k++) {
                String v = (k == blankCell) ? null : cellValues.get(valsIdx++);
                newBoard[i][j] = new SlidingPuzzleCell(i, j, v);
            }
        }
        return newBoard;
    }

    @Override
    public int getHeight() { return rowCount; }

    @Override
    public int getWidth() { return colCount; }

    @Override
    public Cell[][] getCells() { 
        return board; 
    }


}