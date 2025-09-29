package puzzles.io;

import java.util.Scanner;
import puzzles.core.Cell;
import puzzles.core.LeaderBoard;

/*
 * This class handles output for the sliding puzzle game.
 * It provides methods to print the board, welcome message, prompts, and leaderboard.
 */
public class Output{
    private Input input;

    public Output(Input input) {
        this.input =  input;
    }

    // Helper method to print a line separator based on column count and spacing.
    private void printLineHelper(int colCount, int spacing){
        System.out.print("\n ");
        for(int i = 0 ; i < colCount  ; i++){
            for(int temp = 0 ; temp < spacing ; temp++){
                System.out.print("-");
            }
            System.out.print(" ");

        }
        System.out.print(" ");
    }
    // Helper method to print the value of a cell with appropriate spacing.
    public void printCellValue(Cell c,int spacing){
        String toPrint = c.getValue();
        
        System.out.printf("%s",toPrint == null ? " " : toPrint);

        int spaceLength;
        if(toPrint == null){
            spaceLength = spacing-1;
        }
        else{
            spaceLength = spacing - toPrint.length();
        }

        while(spaceLength-- > 0 ){
            System.out.printf(" ");
        }
    }
    // Method to print the entire board along with the move count.
    public void printBoard(Cell[][] board, int moveCount){
        int rowCount = board.length;
        int colCount = board[0].length;
        
        int spacing = (int) Math.log10(rowCount * colCount) + 1;

        printLineHelper(colCount,spacing);
        for(int i = 0 ; i < rowCount ; i++){
            System.out.printf("\n|");
            for( int j = 0 ; j < colCount ; j++){
                printCellValue(board[i][j],spacing);
                System.out.printf("|");
            }
            printLineHelper(colCount,spacing);
        }
        System.out.printf("                                    move count = %d",moveCount);
        System.out.printf("\n\n");
    }
    // Method to print the welcome message at the start of the game.
    public void printWelcomeMessage(){
        clearScreen();
        System.out.println("Welcome to the Sliding Puzzle Game!");
        System.out.println("You can swap adjacent cells to solve the puzzle.");
        input.readLineOrExit(" Press enter to start...");
    }
    // Method to print the prompt asking which cell to move.
    public void printPromptInput(){
        System.out.println("\nWhich cell do you want to move?(example : 2)");
    }
    // Method to display the next scene after a move, showing the updated board and move count.
    public void displayNextScene(Cell[][] board, int moveCount){
        clearScreen();
        printBoard(board,moveCount);
        printPromptInput();
    }
    // Method to display the congratulations message upon solving the puzzle and show the leaderboard.
    public void displayCongratulations(int moveCount, LeaderBoard leaderBoard){
        clearScreen();
        System.out.printf("!!!!!               Congratulations             !!!!!\n\n You solved the puzzle in %d moves!\n\n", moveCount); 
        for(int i = 0 ; i < 10 ; i++){
            System.out.printf("\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printLeaderBoard(leaderBoard);
        
    }
    // Static method to clear the console screen by printing new lines and a decorative header.
    public static void clearScreen( ){
        for(int i = 0 ; i < 100 ; i++){
            System.out.printf("\n");
        }
        System.out.printf("----------------------------------------Adjust your window size till it you don't see the this line------------------------------------------------");
        for(int i = 0 ; i < 10 ; i++){
            System.out.printf("\n");
        }

        System.out.printf("############################################################################################################################################\n");
        System.out.printf("#                                                                                                                                          #\n");
        System.out.printf("#                                                                                                                                          #\n");
        System.out.printf("#     ######    #        #####       ####    #####  #    #    ######         ######    #     #   #######   #######   #         #######     #\n"); 
        System.out.printf("#     #         #          #         #    #    #    ##   #   #               #     #   #     #        #          #   #         #           #\n"); 
        System.out.printf("#     #         #          #         #    #    #    # #  #   #               #     #   #     #       #          #    #         #           #\n"); 
        System.out.printf("#     #####     #          #         #    #    #    #  # #   #   ###         ######    #     #      #          #     #         #####       #\n"); 
        System.out.printf("#          #    #          #         #    #    #    #   ##   #     #         #         #     #     #          #      #         #           #\n"); 
        System.out.printf("#          #    #          #         #    #    #    #    #   #     #         #         #     #    #          #       #         #           #\n"); 
        System.out.printf("#     ######    #######   ###        ####     ###   #    #    #####          #          #####    #######    #######  ######    #######     #\n"); 
        System.out.printf("#                                                                                                                                          #\n");
        System.out.printf("#                                                                                                                                          #\n");
        System.out.printf("############################################################################################################################################\n");
        for(int i = 0 ; i < 20 ; i++){
            System.out.printf("\n");
        }
    }
    // Method to print the leaderboard by loading it and waiting for user input to continue.
    public void printLeaderBoard(LeaderBoard leaderBoard){
        clearScreen();
        leaderBoard.printLeaderBoard();
        input.readLineOrExit("Press enter to continue...");
    }

}