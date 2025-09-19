package sliding_puzzle.components;
import java.util.Objects;
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
    private String username;


    public SlidingPuzzleGameManager() {
        super();
        this.output = new Output();
        this.input  = new Input();
        
    }
    @Override
    public void initGame(boolean gameFirstOpen) {
        if(gameFirstOpen == true){
            output.printWelcomeMessage();
            username = input.inputUsername();
        }
        int[] dimensions = input.inputPuzzleSize(gameFirstOpen,username);

        this.rows = dimensions[0];
        this.cols = dimensions[1];
        if(gameFirstOpen == true){
            this.user = new User(username);
        }
        while(true){
            this.board = new SlidingPuzzleBoard(rows, cols);
            if(isGameEnd() == false){
                break;
            }
        }
    }

    @Override
    public boolean runGame() {
        while(!isGameEnd()){
            output.displayNextScene(board.getBoard(),user.getMoveCount());
            SlidingPuzzleCell[] cellsToSwap = input.readMove(board);
            board.swapCells(cellsToSwap[0], cellsToSwap[1]);
            user.incrementMoveCount();
        }
        output.displayCongratulations(user.getMoveCount());
        return input.inputNewGame();
    }

    @Override
    public boolean isGameEnd() {
        int k = 1;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                String expected = (i == board.getHeight() - 1 && j == board.getWidth() - 1) ? null   : Integer.toString(k++);
                String actual = ((SlidingPuzzleCell) board.getBoard()[i][j]).getValue();
                if (!Objects.equals(actual, expected)) {
                    return false;
                }
            }
        }
        return true; 
    }
}