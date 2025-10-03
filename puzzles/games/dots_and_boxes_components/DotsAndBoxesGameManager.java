package puzzles.games.dots_and_boxes_components;


import puzzles.core.GameManager;
import puzzles.core.Settings;
import puzzles.core.User;
import puzzles.io.Input;
import puzzles.io.Output;
import puzzles.core.LeaderBoard;


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
        while (!board.isFull()) {
            board.display();
            if (currentPlayer == player1){
                System.out.println("Player1 " + currentPlayer.getUsername() + "'s turn. Score: " + currentPlayer.getScore());
            }
            else{
                System.out.println("Player2 " + currentPlayer.getUsername() + "'s turn. Score: " + currentPlayer.getScore());
            }
            boolean validMove = false;
            while (!validMove) {
                int row = input.readIntOrExit( "Enter row: ",0,this.rows );
                int col = input.readIntOrExit( "Enter column: ",0,this.cols);
                
                String dir = input.readDirectionOrExit("Enter direction (H for horizontal right line, V for vertical down line): ");

                validMove = board.claimEdge(row, col, dir, currentPlayer);
                if (!validMove) {
                    System.out.println("Invalid move. Try again.");
                }
            }
            if (!board.lastMoveCompletedBox()) {
                System.out.println(" ");
                switchPlayer();
            }
        }

        showResult();
        
        return input.inputYesOrExit("Would you like to play another round? type y/Y to do so? to exit type exit ");
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

    public void showResult() {
        board.display();
        int score1 = player1.getScore();
        int score2 = player2.getScore();

        System.out.println("\nGame Over!");
        System.out.println(player1.getUsername() + ": " + score1 + " points");
        System.out.println(player2.getUsername() + ": " + score2 + " points");

        if (score1 > score2) {
            System.out.println(player1.getUsername() + " wins!");
        } else if (score2 > score1) {
            System.out.println(player2.getUsername() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
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
