package puzzles.games.sliding_puzzle_components;
import java.util.Objects;
import puzzles.core.GameManager;
import puzzles.core.LeaderBoard;
import puzzles.core.User;
import puzzles.io.Input;
import puzzles.io.Output;
import puzzles.core.Settings;

/*
 * This class manages the sliding puzzle game.
 * It handles game initialization, the main game loop, and checking for game completion.
 */

public class SlidingPuzzleGameManager extends GameManager {
    private SlidingPuzzleBoard board;
    private Output output;  
    private Input input;
    private User user;
    private String username;
    private LeaderBoard leaderBoard;
    private Settings settings;

    // Constructor to initialize the game manager and its components.
    public SlidingPuzzleGameManager(Settings settings) {
        super();
        this.input  = new Input();
        this.output = new Output(this.input, "sliding_puzzle");
        this.leaderBoard = new LeaderBoard();
        this.settings = settings;
    }


    // Initialize the game by loading the leaderboard, getting user input, and setting up the board.
    @Override
    public void initGame(boolean gameFirstOpen) {

        leaderBoard.loadLeaderBoard();

        if(gameFirstOpen == true){
            output.printWelcomeMessage();
            username = input.readStringOrExit("Enter a username >>> ");

        }
        int rows = input.readIntOrExit(String.format("Hey %s Enter number of rows: ", username), settings.getMinBoardSize("SlidingPuzzle"), settings.getMaxBoardSize("SlidingPuzzle"));
        int cols = input.readIntOrExit(String.format("Hey %s Enter number of columns: ", username), settings.getMinBoardSize("SlidingPuzzle"), settings.getMaxBoardSize("SlidingPuzzle"));


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
            SlidingPuzzleCell[] cellsToSwap = input.readSlidingPuzzleMove(board);
            board.swapCells(cellsToSwap[0], cellsToSwap[1]);
            user.incrementMoveCount();
        }
        leaderBoard.incrementTotal(user.getUsername());
        leaderBoard.saveLeaderBoard();
        output.displayCongratulations(user.getMoveCount(),leaderBoard);
        user.resetMoveCount();
        return input.inputYesOrExit("To play a new game type y/Y, to exit press any key >>> ");
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