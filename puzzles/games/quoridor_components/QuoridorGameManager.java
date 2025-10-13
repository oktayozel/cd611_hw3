package puzzles.games.sliding_puzzle_components;
import puzzles.core.GameManager;
import puzzles.core.LeaderBoard;
import puzzles.io.Input;
import puzzles.io.Output;
import puzzles.core.Settings;
import puzzles.games.dots_and_boxes_components.DotsAndBoxesUser;

public class QuoridorGameManager extends GameManager {
    
    private QuoridorBoard board;
    private Output output;
    private Input input;
    private LeaderBoard leaderBoard;
    private Settings settings;
    
    public QuoridorGameManager((Settings settings) {
        super();
        this.input  = new Input();
        this.output = new Output(this.input, "quoridor");
        this.leaderBoard = new LeaderBoard();
        this.settings = settings;
    }

     @Override
    public void initGame(boolean gameFirstOpen) {
        leaderBoard.loadLeaderBoard();

        initializePlayers(gameFirstOpen);
        initializeBoard();

    }

    // Method to prompt for player names and create player objects.
    @Override
    protected void initializePlayers(boolean gameFirstOpen) {

        if(gameFirstOpen == true){
            output.printWelcomeMessage();
            String name1 = input.readStringOrExit("Enter Player 1 name: ");
            player1 = new DotsAndBoxesUser(name1, "P1");
            String name2 = input.readStringOrExit("Enter Player 2 name: ");
            player2 = new DotsAndBoxesUser(name2, "P2");
        
        }
    }

    @Override
    protected void initializeBoard(){
        int rows = input.readIntOrExit(String.format("Enter number of rows: "), settings.getMinBoardSize("Quoridor"), settings.getMaxBoardSize("Quoridor"));
        int cols = input.readIntOrExit(String.format("Enter number of columns: "), settings.getMinBoardSize("Quoridor"), settings.getMaxBoardSize("Quoridor"));
        this.board = new QuoridorBoard(rows, cols, new QuoridorUser("Player 1"), new QuoridorUser("Player 2"));
    }

    




    
}
