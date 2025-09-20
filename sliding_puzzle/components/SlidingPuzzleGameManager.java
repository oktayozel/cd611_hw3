package sliding_puzzle.components;
import java.util.Objects;
import sliding_puzzle.io.Output;
import sliding_puzzle.io.Input;

import sliding_puzzle.core.Board;
import sliding_puzzle.core.GameManager;
import sliding_puzzle.core.User;
import sliding_puzzle.core.LeaderBoard;

/*
 * This class manages the sliding puzzle game.
 * It handles game initialization, the main game loop, and checking for game completion.
 */

public class SlidingPuzzleGameManager extends GameManager {
    private SlidingPuzzleBoard board;
    private Output output;  
    private Input input;
    private User user;
    private int rows;
    private int cols;
    private String username;
    private LeaderBoard leaderBoard;

    // Constructor to initialize the game manager and its components.
    public SlidingPuzzleGameManager() {
        super();
        this.output = new Output();
        this.input  = new Input();
        this.leaderBoard = new LeaderBoard();
    }


    // Initialize the game by loading the leaderboard, getting user input, and setting up the board.
    @Override
    public void initGame(boolean gameFirstOpen) {

        leaderBoard.loadLeaderBoard();

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

    // Run the main game loop, handling user moves and checking for game completion.
    @Override
    public boolean runGame() {
        while(!isGameEnd()){
            output.displayNextScene(board.getBoard(),user.getMoveCount());
            SlidingPuzzleCell[] cellsToSwap = input.readMove(board);
            board.swapCells(cellsToSwap[0], cellsToSwap[1]);
            user.incrementMoveCount();
        }
        leaderBoard.incrementUser(user.getUsername());
        leaderBoard.saveLeaderBoard();
        output.displayCongratulations(user.getMoveCount(),leaderBoard);
        user.resetMoveCount();
        return input.inputNewGame();
    }

    // Check if the game has ended by verifying if the board is in the solved state.
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