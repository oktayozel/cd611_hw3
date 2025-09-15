package sliding_puzzle.sliding_puzzle_components;

import sliding_puzzle.io.Output;
import sliding_puzzle.io.Input;

import sliding_puzzle.core.Board;
import sliding_puzzle.core.GameManager;
import sliding_puzzle.core.User;


public class SlidingPuzzleGameManager extends GameManager {
    private SlidingPuzzleBoard board;
    private Output output;  
    private Input input;
    private User user;
    private int rows;
    private int cols;


    public SlidingPuzzleGameManager() {
        super();
        this.output = new Output();
        this.input  = new Input();

    }

    public void initGame() {
        output.printWelcomeMessage();

        String username  = input.inputUsername();
        int[] dimensions = input.inputPuzzleSize(username);

        this.rows = dimensions[0];
        this.cols = dimensions[1];
        this.user = new User(username);

        this.board = new SlidingPuzzleBoard(rows, cols);
    }
    
    public void runGame() {
        while(!isGameOver()){
            output.displayNextScene(board.getBoard());
            int[] inputMove = input.readMoves();
            int row1 = inputMove[0];    int col1 = inputMove[1];    int row2 = inputMove[2];    int col2 = inputMove[3];
            board.swapCells(board.getCell(row1, col1), board.getCell(row2, col2));
        }
    }

    @Override
    public boolean isGameOver() {
        return false; // Placeholder
    }
}