package sliding_puzzle.io;

import sliding_puzzle.core.Cell;

public class Output{

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

    public void printWelcomeMessage(){
        clearScreen();
        System.out.println("Welcome to the Sliding Puzzle Game!");
        System.out.println("You can swap adjacent cells to solve the puzzle. Press any key to start...");
        Input.getAnyKey();
    }

    public void printPromptInput(){
        System.out.println("\nWhich cell do you want to move?");
    }
    public void displayNextScene(Cell[][] board, int moveCount){
        clearScreen();
        printBoard(board,moveCount);
        printPromptInput();
    }
    public void displayCongratulations(int moveCount){
        clearScreen();
        System.out.printf("!!!!!               Congratulations             !!!!!\n\n You solved the puzzle in %d moves!\n\n", moveCount);             
    }
    public static void clearScreen(){
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

}