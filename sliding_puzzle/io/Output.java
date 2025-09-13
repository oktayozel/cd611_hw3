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

    public void printBoard(Cell[][] board){
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

    }

}