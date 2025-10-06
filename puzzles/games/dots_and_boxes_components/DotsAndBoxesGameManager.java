package puzzles.games.dots_and_boxes_components;


import puzzles.core.GameManager;
import puzzles.core.LeaderBoard;
import puzzles.core.Settings;
import puzzles.io.Input;
import puzzles.io.Output;


public class DotsAndBoxesGameManager extends GameManager{
    private Input input;
    private Settings settings;
    private LeaderBoard leaderBoard ;
    private Output output;
    private DotsAndBoxesUser player1;
    private DotsAndBoxesUser player2;
    private DotsAndBoxesBoard board;
    private DotsAndBoxesUser currentPlayer;
    private int rows;
    private int cols;

    public DotsAndBoxesGameManager(Settings settings) {
        this.input  = new Input();
        this.output = new Output(this.input, "dots_and_boxes");
        this.settings = settings;
        this.leaderBoard = new LeaderBoard();

    }

    @Override
    public void initGame(boolean gameFirstOpen) {
        leaderBoard.loadLeaderBoard();
        reset(); 
        initializePlayers(gameFirstOpen);
        initializeBoard();
    }


    @Override
    public boolean runGame() {
        super.startTimer();
        while (!board.isFull()) {
            output.displayNextScene(board ,currentPlayer,(currentPlayer == player1) ? "player1" : "player2");

            input.readDotsAndBoxesMove(board, currentPlayer);

            if (!board.lastMoveCompletedBox()) {
                System.out.println(" ");
                switchPlayer();
            }

        }
        super.stopTimer();
        int elapsedTime = super.getElapsedTime();
        String result = output.displayCongratulations(board, player1, player2, elapsedTime );;
        if(result.equals("player1") ){
            leaderBoard.recordDotsAndBoxesResult(player1.getUsername(),true);
            leaderBoard.recordDotsAndBoxesResult(player2.getUsername(),false);
        }
        else if(result.equals("player2") ){
            leaderBoard.recordDotsAndBoxesResult(player1.getUsername(),false);
            leaderBoard.recordDotsAndBoxesResult(player2.getUsername(),true);
        }
        output.displayLeaderboard(leaderBoard);
        return input.inputYesOrExit("\n\n\n\nTo play a new game type y/Y, to exit press any key >>> \n To go back to main menu type m/M to \n any other input will end the game.");
    }

     @Override
    public boolean isGameEnd() {
        return board.isFull(); 
    }

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
    protected void initializeBoard() {
        int rows = input.readIntOrExit("Enter number of rows: ", settings.getMinBoardSize("DotsAndBoxes"), settings.getMaxBoardSize("DotsAndBoxes"));
        int cols = input.readIntOrExit("Enter number of columns: ", settings.getMinBoardSize("DotsAndBoxes"), settings.getMaxBoardSize("DotsAndBoxes"));
        System.out.println("-------------------------------------------------------------------------------- ");
        System.out.println("Hint: Each time you enter a point, you will be asked to choose H or V. ");
        System.out.println("H places a horizontal line to the right of that point. ");
        System.out.println("V places a vertical line below that point. ");
        this.rows = rows;
        this.cols = cols;
        board = new DotsAndBoxesBoard(rows, cols);
        currentPlayer = player1;
    }



    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
    

    public void reset() {
        if (player1 != null) player1.resetScore();
        if (player2 != null) player2.resetScore();
        board = null;
        currentPlayer = null;
        rows = 0;
        cols = 0;
    }

}
