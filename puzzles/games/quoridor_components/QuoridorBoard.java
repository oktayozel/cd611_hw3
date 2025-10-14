package puzzles.games.quoridor_components;

import puzzles.core.Board;
import puzzles.core.Cell;
import puzzles.core.User;
import puzzles.games.quoridor_components.QuoridorUser;
public class QuoridorBoard implements Board{
    
    private final int rowCount;
    private final int colCount;
    private final QuoridorCell[][] board;
    private QuoridorUser player1;
    private QuoridorUser player2;

    public QuoridorBoard(int rowCount, int colCount,QuoridorUser player1, QuoridorUser player2) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.player1 = player1;
        this.player2 = player2;
        this.board = initializeBoard();

    }
    private QuoridorCell[][] initializeBoard(){
        QuoridorCell [][] board = new QuoridorCell[rowCount][colCount];
        for( int i = 0 ; i < rowCount; i++){
            for(int j = 0 ; j < colCount ; j++ ){
                board[i][j] = new QuoridorCell(i, j, "");
            }
        }
        board[0][colCount/2].setHasPlayer1(true);
        board[rowCount-1][colCount/2].setHasPlayer2(true);
        return board;
    }

    public void display(){
        final int cellWidth = 4; 
        final int cellHeight = 4;
        final int cellInnerWidth = cellWidth - 2;
        final int cellInnerHeight = cellHeight - 2;



        for(int i = 0 ; i < rowCount ; i++){
            
            // TOP BORDER
            for (int j = 0; j < colCount; j++) {
                System.out.print("#");
                if (board[i][j].hasTopWall()) {
                    for (int k = 0; k < cellInnerWidth; k++) System.out.print("#");
                } else {
                    for (int k = 0; k < cellInnerWidth; k++) System.out.print(" "); 
                }
            }
            System.out.printf("#\n"); // last corner

            //MIDDLE SEGMENT
            for (int r = 0; r < cellInnerHeight; r++) {
                for (int j = 0; j < colCount; j++) {
                    System.out.print(board[i][j].hasLeftWall() ? "#" : " ");

                    if (board[i][j].hasPlayer1()) {
                        System.out.print("11");
                    } else if (board[i][j].hasPlayer2()) {
                        System.out.print("22");
                    } else {
                        System.out.print("  ");
                    }
                   

                    if (j == colCount - 1) {
                        System.out.print(board[i][j].hasRightWall() ? "#" : " ");
                    }
                }
                System.out.println();
            }
            
        }
        // BOTTOM BORDER
        for (int j = 0; j < colCount; j++) {
            System.out.print("#");
            if (board[rowCount-1][j].hasBottomWall()) {
                for (int k = 0; k < cellInnerWidth; k++) System.out.print("#");
            } else {
                for (int k = 0; k < cellInnerWidth; k++) System.out.print(" ");
            }
        }
        System.out.print("#\n");
    } 

    public boolean isPlayer2inTheFirstRow() {
        for(int i = 0 ; i < colCount ; i++){
            if(board[0][i].hasPlayer2()){
                return true;
            }
        }
        return false;
    }

    public boolean isPlayer1inTheLastRow() {
        for(int i = 0 ; i < colCount ; i++){
            if(board[rowCount-1][i].hasPlayer1()){
                return true;
            }
        }
        return false;
    }


    @Override
    public Cell[][] getBoard(){
        return board;
    }

    @Override
    public Cell getCell(int row, int col){
        return board[row][col];
    }
    @Override
    public int getHeight() {
        return rowCount;
    }

    @Override
    public int getWidth() {
        return colCount;
    }







}
