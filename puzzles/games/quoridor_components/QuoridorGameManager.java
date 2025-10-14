package puzzles.games.quoridor_components;
import java.util.Objects;

import puzzles.core.Board;
import puzzles.core.GameManager;
import puzzles.core.LeaderBoard;
import puzzles.io.Input;
import puzzles.io.Output;
import puzzles.core.Settings;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleCell;

public class QuoridorGameManager extends GameManager {
    
    private QuoridorBoard board;
    private Output output;
    private Input input;
    private LeaderBoard leaderBoard;
    private Settings settings;
    private QuoridorUser player1;
    private QuoridorUser player2;
    private QuoridorUser currentPlayer;

    
    public QuoridorGameManager(Settings settings) {
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
            player1 = new QuoridorUser(name1, "P1");
            String name2 = input.readStringOrExit("Enter Player 2 name: ");
            player2 = new QuoridorUser(name2, "P2");
        
        }
    }

    @Override
    protected void initializeBoard(){
        int rows = input.readIntOrExit(String.format("Enter number of rows: "), settings.getMinBoardSize("Quoridor"), settings.getMaxBoardSize("Quoridor"));
        int cols = input.readIntOrExit(String.format("Enter number of columns: "), settings.getMinBoardSize("Quoridor"), settings.getMaxBoardSize("Quoridor"));
        this.board = new QuoridorBoard(rows, cols, player1, player2);
    }

    

    @Override
    public boolean runGame() {
        super.startTimer();
        while (!isGameEnd()) {
            output.displayNextScene(board ,currentPlayer,(currentPlayer == player1) ? "player1" : "player2");

        }

        super.stopTimer();
        int elapsedTime = super.getElapsedTime();

        // TODO: display results and update leaderboard
        // TODO: implement congratulations message call in here


        output.displayLeaderboard(leaderBoard);
        return input.inputYesOrExit("\n\n\n\nTo play a new game type y/Y, to exit press any key >>> \n To go back to main menu type m/M to \n any other input will end the game.");
    }

    @Override
    public boolean isGameEnd() {
        return board.isPlayer2inTheFirstRow() || board.isPlayer1inTheLastRow();
    }

    public QuoridorBoard getBoard() {
        return board;
    }
    public QuoridorUser getPlayer1() {
        return player1;
    }
    public QuoridorUser getPlayer2() {
        return player2;
    }


    
}


//  Andrew:
//
//GameManager
//User
//  I will find one other nice funcitonality and implement so that we can get extra points
//
//
//
//
//Oktay:
//Board
//Cell
//LeaderBoard -> we need to add Quoridor related statistics
//




