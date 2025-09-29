package puzzles.games.dots_and_boxes_components;

import puzzles.io.Input;
import puzzles.io.Output;

public class DotsAndBoxesGameManager {
    private boolean isRunning = true;
    private Input input;
    private Output output;
    private DotsAndBoxesPlayer player1;
    private DotsAndBoxesPlayer player2;
    private DotsAndBoxesBoard board;
    private DotsAndBoxesPlayer currentPlayer;
    private int rows;
    private int cols;

    public DotsAndBoxesGameManager() {
        this.input  = new Input();
        this.output = new Output(this.input, "dots_and_boxes");
    }

    public void initGame(boolean firstTime) {
        if (firstTime) {
            System.out.println("Welcome to Dots and Boxes!");
        } 
        else {
            System.out.println("\n Starting a new round...");
        }
        reset(); 
        isRunning = true; //ensure game reload every time
    }

    public void startDotsAndBoxes() {
        while (isRunning) {
            setup();
            play();
            showResult();
            promptNextAction();
        }
    }

    private void promptNextAction() {
        System.out.println("\nWhat would you like to do next?");
        System.out.println("1. Play again");
        System.out.println("2. Quit");

        int choice = input.readIntOrExit("Enter your choice (1 or 2):", 1,2);

        if (choice == 1) {
            reset();
        } else {
            isRunning = false;
            System.out.println("Thanks for playing! Goodbye! ");
        }
    }

    public boolean runGame() {
        while (isRunning) {
            setup();
            play();
            showResult();
            promptNextAction();
        }
        return askReplay(); // check play again
    }

    private boolean askReplay() {
        System.out.println("Would you like to play another round?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = input.readIntOrExit("Enter your choice (1 or 2):", 1,2);
        return choice == 1;
    }

    public void setup() {
        System.out.println("\n Setting up Dots and Boxes...");
        initializePlayers();
        initializeBoard();
    }

    private void initializePlayers() {
        System.out.print("Enter Player 1 name: ");
        String name1 = input.readLineOrExit();
        player1 = new DotsAndBoxesPlayer(name1);

        System.out.print("Enter Player 2 name: ");
        String name2 = input.readLineOrExit();
        player2 = new DotsAndBoxesPlayer(name2);
    }

    private void initializeBoard() {
        int rows = input.readIntOrExit("Enter number of rows: ", 0, 10);
        int cols = input.readIntOrExit("Enter number of columns: ", 0,10);
        this.rows = rows;
        this.cols = cols;
        board = new DotsAndBoxesBoard(rows, cols);
        currentPlayer = player1;
    }


    public void play() {
        while (!board.isFull()) {
            board.display();
            System.out.println(currentPlayer.getName() + "'s turn. Score: " + currentPlayer.getScore());

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
                switchPlayer();
            }
        }
    }

    public void showResult() {
        board.display();
        int score1 = player1.getScore();
        int score2 = player2.getScore();

        System.out.println("\nGame Over!");
        System.out.println(player1.getName() + ": " + score1 + " points");
        System.out.println(player2.getName() + ": " + score2 + " points");

        if (score1 > score2) {
            System.out.println(player1.getName() + " wins!");
        } else if (score2 > score1) {
            System.out.println(player2.getName() + " wins!");
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
        if (player1 != null) initializeBoard(); 
    }

    
}
