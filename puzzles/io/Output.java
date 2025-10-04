package puzzles.io;

import java.util.List;
import puzzles.core.Cell;
import puzzles.core.LeaderBoard;
import puzzles.games.dots_and_boxes_components.DotsAndBoxesBoard;
import puzzles.games.dots_and_boxes_components.DotsAndBoxesUser;

/*
 * This class handles output for the sliding puzzle game.
 * It provides methods to print the board, welcome message, prompts, and leaderboard.
 */
public class Output{
    private Input input;
    private String gameName;

    public Output(Input input) {
        this.input = input;
    }

    public Output(Input input, String gameName) {
        this.input =  input;
        this.gameName = gameName.toLowerCase();
    }

    public void displayAnimation(){
        String[] animation = {
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "              ,---------------------------,",
        "              |  /---------------------\\  |",
        "              | |                       | |",
        "              | |     Welcome to        | |",
        "              | |      CS-611           | |",
        "              | |       Games           | |",
        "              | |                       | |",
        "              |  \\_____________________/  |",
        "              |___________________________|",
        "            ,---\\_____     []     _______/------,",
        "          /         /______________\\           /|",
        "        /___________________________________ /  | ___",
        "        |                                   |   |    )",
        "        |  _ _ _                 [-------]  |   |   (",
        "        |  o o o                 [-------]  |  /    _)_",
        "        |__________________________________ |/     /  /",
        "    /-------------------------------------/|      ( )/",
        "  /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /",
        "/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /",
        "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ",    
        "                                             ", 
        };
        for(String line : animation){
            System.out.println(line);
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

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
        if (gameName.equals("sliding_puzzle")) {
            System.out.println("Welcome to the Sliding Puzzle Game!");
            System.out.println("Swap adjacent cells to order them.");
            input.readLineOrExit(" Press enter to start...");
        }
        if (gameName.equals("dots_and_boxes")) {
            System.out.println("Welcome to the Dots and Boxes Game!");
            System.out.println("Be the one who puts the last piece to conquer as much boxes as you can. Enjoy! ");
            input.readLineOrExit(" Press enter to start...");
        }
    }

    
    // Method to display the next scene after a move, showing the updated board and move count.
    public void displayNextScene(Cell[][] board, int moveCount){
        clearScreen();
        printBoard(board,moveCount);
    }


    public void displayNextScene(DotsAndBoxesBoard board, DotsAndBoxesUser currentPlayer  , String turn){
        clearScreen();
        board.display();
        if (turn.equals("player1")){
                System.out.println("Player1 " + currentPlayer.getUsername() + "'s turn. Score: " + currentPlayer.getScore());
        }
        else{
            System.out.println("Player2 " + currentPlayer.getUsername() + "'s turn. Score: " + currentPlayer.getScore());
        }
    }

    // Method to display the congratulations message upon solving the puzzle and show the leaderboard.
    public void displayCongratulations(int moveCount){
        clearScreen();
        System.out.printf("!!!!!               Congratulations             !!!!!\n\n You solved the puzzle in %d moves!\n\n", moveCount); 
        for(int i = 0 ; i < 10 ; i++){
            System.out.printf("\n");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String displayCongratulations(DotsAndBoxesBoard board, DotsAndBoxesUser player1, DotsAndBoxesUser player2){
        board.display();
        int score1 = player1.getScore();
        int score2 = player2.getScore();

        System.out.println("\nGame is Over!");
        System.out.println(player1.getUsername() + ": " + score1 + " points");
        System.out.println(player2.getUsername() + ": " + score2 + " points");

        if (score1 > score2) {
            System.out.println(player1.getUsername() + " wins!");
            return "player1";
        } else if (score2 > score1) {
            System.out.println(player2.getUsername() + " wins!");
            return "player2";
        } else {
            System.out.println("It's a tie!");
            return "draw";
        }

    }

    public void displayNEmptyLines(int N){
        for(int i = 0 ; i < N ; i++){
            System.out.printf("\n");
        }
    }

    // Static method to clear the console screen by printing new lines and a decorative header.
    public void clearScreen(){

        displayNEmptyLines(100);
        System.out.printf("----------------------------------------Adjust your window size till it you don't see the this line------------------------------------------------");
        displayNEmptyLines(10);


        if (gameName.equals("sliding_puzzle")) {
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
        }

        else if(gameName.equals("dots_and_boxes")) {
            System.out.printf("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
            System.out.printf("X                                                                                                     X\n");
            System.out.printf("X   XXXXX                                             X     XXXXXXX                                     X\n");
            System.out.printf("X   XX   X                                            X     XX    XX                                    X\n");
            System.out.printf("X   XX    X          X                                X     XX    XX                                    X\n");
            System.out.printf("X   XX     X  XXX  XXXXX XXXXX      XXXX   X XXX    XXX     XXXXXXXXX    XXX   X    X   XXXXX   XXXXX   X\n");
            System.out.printf("X   XX     X X   X   X   X         X   x   XX  XX XX  X     XXX     XX  X   X   X  X   X     X  X       X\n");
            System.out.printf("X   XX     X X   X   X   XXXXX     X   X   X    X X   X     XX      XX  X   X    XX    XXXXXXX  XXXXX   X\n");
            System.out.printf("X   XX    X  X   X   X       X     X   XX  X    X X   X     XX     XX   X   X   X  X   X            X   X\n");
            System.out.printf("X   XXXXXX    XXX    X   XXXXX      XXX XX X    X  XXXX     XXXXXXXX     XXX   X    X   XXXXX   XXXXX   X\n");
            System.out.printf("X                                                                                                     X\n");
            System.out.printf("X                                                                                                     X\n");
            System.out.printf("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");    
        }
        
        displayNEmptyLines(20);

    }
    // Method to print the leaderboard by loading it and waiting for user input to continue.
    public void displayLeaderboard(LeaderBoard leaderBoard){
        clearScreen();
        leaderBoard.printLeaderBoard();
        input.readLineOrExit("Press enter to continue...");
    }
    public void displaySupportedGames(List<String> games) {
        System.out.println("Available games:");
        for (int i = 0; i < games.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + games.get(i));
        }
    }

}