package puzzles.games.sliding_puzzle_components;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
 * This class implements the Board interface for a sliding puzzle game.
 * It initializes a board with numbered cells and one empty cell, and provides methods to manipulate the board.
 */

import puzzles.core.Board;
import puzzles.core.Cell;
import puzzles.core.User;
import puzzles.io.Output;

public class SlidingPuzzleBoard implements Board{
    private final int rowCount;
    private final int colCount;
    private Output output;
    private User user;
    
    private final SlidingPuzzleCell[][] board;
    private Random rnd = new Random();

    // Constructor to initialize the board with given dimensions and shuffle it.
    public SlidingPuzzleBoard(int rowCount, int colCount,Output output, User user) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.output = output;
        this.user = user;

        this.board = initializeBoard();
        shuffleBoard();

        
    }

    // Initialize the board with numbered cells and one empty cell.
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
    // Shuffle the board by making random valid moves from the solved state ensuring solvability.
    private void shuffleBoard() {

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
    // Method to print the entire board along with the move count.
    @Override
    public void display(){
        int rowCount = board.length;
        int colCount = board[0].length;
        
        int spacing = (int) Math.log10(rowCount * colCount) + 1;

        output.printLineHelper(colCount,spacing);
        for(int i = 0 ; i < rowCount ; i++){
            System.out.printf("\n|");
            for( int j = 0 ; j < colCount ; j++){
                output.printCellValue(board[i][j],spacing);
                System.out.printf("|");
            }
            output.printLineHelper(colCount,spacing);
        }
        System.out.printf("                                    move count = %d",user.getMoveCount());
        System.out.printf("\n\n");
    }

    // Get the cell at the specified row and column.
    public SlidingPuzzleCell getCell(int row, int col) {
        return board[row][col];
    }
    // Swap the values of two adjacent cells.
    public boolean swapCells(Cell cell1, Cell cell2) {
        if (!areAdjacent(cell1, cell2)) {
            return false;
        }
        String tempValue = cell1.getValue();
        cell1.setValue(cell2.getValue());
        cell2.setValue(tempValue);
        return true;
    }
    // Check if two cells are adjacent (horizontally or vertically).
    public boolean areAdjacent(Cell cell1, Cell cell2){
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