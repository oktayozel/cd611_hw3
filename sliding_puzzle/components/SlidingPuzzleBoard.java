package sliding_puzzle.components;


import sliding_puzzle.core.Cell;
import sliding_puzzle.core.Board;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SlidingPuzzleBoard implements Board{
    private final int rowCount;
    private final int colCount;
    public static final int MIN_ROWS = 2;
    public static final int MIN_COLS = 2;
    public static final int MAX_ROWS = 10;
    public static final int MAX_COLS = 10;
    
    private final SlidingPuzzleCell[][] board;
    private Random rnd = new Random();

    public SlidingPuzzleBoard(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;


        this.board = initializeBoard();
        shuffleBoard();
        
    }


    private SlidingPuzzleCell[][] initializeBoard(){
        SlidingPuzzleCell[][] newBoard = new SlidingPuzzleCell[rowCount][colCount];
        int totalCellCount = rowCount*colCount;

        List<String> cellValues = new ArrayList<>();

        // Generate an arraylist of Strings in order.
        for (int i = 1; i < totalCellCount; i++) {
            cellValues.add(Integer.toString(i));
        }

        // Create a Random object
        int valsIdx = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                String v = ( (i == rowCount-1) && (j == colCount-1)) ? null : cellValues.get(valsIdx++);
                newBoard[i][j] = new SlidingPuzzleCell(i, j, v);
            }
        }
        return newBoard;
    }
    private void shuffleBoard() {
        Random rnd = new Random();

        int randomBackIterationCount = rnd.nextInt(1000) + 100;

        SlidingPuzzleCell emptyCell = board[rowCount - 1][colCount - 1];
        for (int i = 0; i < randomBackIterationCount; i++) {

            int emptyCellRow = emptyCell.getRowIndex(); // row, not col
            int emptyCellCol = emptyCell.getColIndex(); // col, not row

            int[][] directions = { {0,1}, {1,0}, {0,-1}, {-1,0} };
            List<int[]> viableDirections = new ArrayList<>(4);

            for (int j = 0; j < directions.length; j++) {
                int nr = emptyCellRow + directions[j][0];
                int nc = emptyCellCol + directions[j][1];
                if (nr >= 0 && nr < rowCount && nc >= 0 && nc < colCount) {
                    viableDirections.add(directions[j]);
                }
            }

            int[] step = viableDirections.get(rnd.nextInt(viableDirections.size()));
            int nr = emptyCellRow + step[0];
            int nc = emptyCellCol + step[1];

            SlidingPuzzleCell neighbor = getCell(nr, nc);
            swapCells(emptyCell, neighbor);

            emptyCell = neighbor;
        }
    }

    public SlidingPuzzleCell getCell(int row, int col) {
        return board[row][col];
    }

    public boolean swapCells(SlidingPuzzleCell cell1, SlidingPuzzleCell cell2) {
        if (!areAdjacent(cell1, cell2)) {
            return false;
        }
        String tempValue = cell1.getValue();
        cell1.setValue(cell2.getValue());
        cell2.setValue(tempValue);
        return true;
    }

    public boolean areAdjacent(SlidingPuzzleCell cell1, SlidingPuzzleCell cell2){
        int rowDiff = Math.abs(cell1.getRowIndex() - cell2.getRowIndex());
        int colDiff = Math.abs(cell1.getColIndex() - cell2.getColIndex());
        return (rowDiff + colDiff) == 1;
    }

    @Override
    public int getHeight() { return rowCount; }

    @Override
    public int getWidth() { return colCount; }
    @Override
    public Cell[][] getBoard() { 
        return board; 
    }


}