package puzzles.games.dots_and_boxes_components;

import java.util.Scanner;
import puzzles.io.InputCheck;

// import puzzles.core.Board;

public class DotsAndBoxesStart {
    private Scanner scanner;
    private DotsAndBoxesPlayer player1;
    private DotsAndBoxesPlayer player2;
    private DotsAndBoxesBoard board;
    private DotsAndBoxesPlayer currentPlayer;

    public DotsAndBoxesStart(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setup() {
        System.out.println("\n Setting up Dots and Boxes...");
        initializePlayers();
        initializeBoard();
    }

    private void initializePlayers() {
        System.out.print("Enter Player 1 name: ");
        String name1 = InputCheck.readLineOrExit(scanner);
        player1 = new DotsAndBoxesPlayer(name1);

        System.out.print("Enter Player 2 name: ");
        String name2 = InputCheck.readLineOrExit(scanner);
        player2 = new DotsAndBoxesPlayer(name2);
    }

    private void initializeBoard() {
        //System.out.print("Enter number of rows: ");
        int rows = InputCheck.readIntOrExit(scanner, "Enter number of rows: ");
        //System.out.print("Enter number of columns: ");
        int cols = InputCheck.readIntOrExit(scanner, "Enter number of columns: ");
        board = new DotsAndBoxesBoard(rows, cols);
        currentPlayer = player1;
    }


    public void play() {
        while (!board.isFull()) {
            board.display();
            System.out.println(currentPlayer.getName() + "'s turn. Score: " + currentPlayer.getScore());

            boolean validMove = false;
            while (!validMove) {
                //System.out.print("Enter row: ");
                int row = InputCheck.readIntOrExit(scanner, "Enter row: ");

                //System.out.print("Enter column: ");
                int col = InputCheck.readIntOrExit(scanner, "Enter column: ");

                System.out.print("Enter direction (H for horizontal right line, V for vertical down line): ");
                String dir = InputCheck.readDirectionOrExit(scanner);

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
