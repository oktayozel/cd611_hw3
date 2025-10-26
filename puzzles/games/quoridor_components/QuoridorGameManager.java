package puzzles.games.quoridor_components;

import puzzles.core.GameManager;
import puzzles.core.LeaderBoard;
import puzzles.core.Multiplayer;
import puzzles.core.Settings;
import puzzles.io.Input;
import puzzles.io.Output;

public class QuoridorGameManager extends GameManager implements Multiplayer{

    private QuoridorBoard board;
    private Output output;
    private Input input;
    private LeaderBoard leaderBoard;
    private Settings settings;
    private QuoridorUser player1;
    private QuoridorUser player2;
    private QuoridorUser currentPlayer;     //track turns
    private boolean playAgainstBot;

    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";

    public QuoridorGameManager(Settings settings) {
        super();
        this.input = new Input();
        this.output = new Output(this.input, "quoridor");
        this.leaderBoard = new LeaderBoard();
        this.settings = settings;
    }


    //init the game(board players setting)
    @Override
    public void initGame(boolean gameFirstOpen) {
        leaderBoard.loadLeaderBoard();
        output.printWelcomeMessage();
        playAgainstBot = input.inputYesOrExit("Do you want to play against our AI Bot? (y/n): ");
        initializePlayers(gameFirstOpen);
        initializeBoard();
    }

    //player names
    @Override
    protected void initializePlayers(boolean gameFirstOpen) {
        String name1 = input.readStringOrExit("Enter" + BLUE + " Player 1 " + RESET + "name:");
        player1 = new QuoridorUser(name1, "P1", false);

        if (!playAgainstBot) {
            String name2 = input.readStringOrExit("Enter" + RED + " Player 2 " + RESET + "name:");
            player2 = new QuoridorUser(name2, "P2", false);
        } else {
            System.out.println("Playing against our AI Bot ALFRED.");
            player2 = new QuoridorBot("AI Bot: Alfred", "P2");
        }
        
    }

    //create board(dimensions)
    @Override
    protected void initializeBoard() {
        int rows = input.readIntOrExit("Enter number of" + YELLOW + " rows: " + RESET, settings.getMinBoardSize("Quoridor"), settings.getMaxBoardSize("Quoridor"));
        int cols = input.readIntOrExit("Enter number of" + YELLOW + " columns: " + RESET, settings.getMinBoardSize("Quoridor"), settings.getMaxBoardSize("Quoridor"));
        this.board = new QuoridorBoard(rows, cols, player1, player2);
        if(playAgainstBot && player2 instanceof QuoridorBot ){
            ((QuoridorBot)player2).setBoard(this.board);
        }
    }

    //turns, check moves, check win
    @Override
    public boolean runGame() {
        super.startTimer();
        currentPlayer = player1;

        while (!isGameEnd()) {
            output.displayNextScene(board, currentPlayer,
                    currentPlayer == player1 ? "player1" : "player2");

            if (playAgainstBot && currentPlayer instanceof QuoridorBot) {
                ((QuoridorBot) currentPlayer).makeMove();
            } else {
                boolean validMove = false;
                while (!validMove) {
                    validMove = input.readQuoridorMove(board, currentPlayer);
                }
            }
            switchPlayer();
        }

        super.stopTimer();
        int elapsedTime = super.getElapsedTime();
        
        //check winner
        QuoridorUser winner = board.hasPlayerReachedGoal(player1) ? player1 : player2;
        QuoridorUser loser = (winner == player1) ? player2 : player1;

        //display
        output.displayVictoryMessage(winner.getUsername(), elapsedTime);

        //update leaderboard (except bot)
        if (!(player1 instanceof QuoridorBot) && !(player2 instanceof QuoridorBot)) {
            leaderBoard.recordQuoridorResult(winner.getUsername(), true);
            leaderBoard.recordQuoridorResult(loser.getUsername(), false);
            leaderBoard.saveLeaderBoard();
            output.displayLeaderboard(leaderBoard);
        } 
        else {
            System.out.println("Game played against AI. Leaderboard will not updated.");
        }

        return input.inputYesOrExit("\nPlay again? (y/Y), Main menu (m/M), or any other key to exit >>> ");
    }



    @Override
    public boolean isGameEnd() {
        return board.hasPlayerReachedGoal(player1) || board.hasPlayerReachedGoal(player2);
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
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