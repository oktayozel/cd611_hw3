package puzzles.games.quoridor_components;

import puzzles.core.Board;
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
        return board;
    }

    public void display(){
        final int cellWidth = 4; 
        final int cellHeight = 4;
        final int cellInnerWidth = cellWidth - 2;
        final int cellInnerHeight = cellHeight - 2;

        for(int i = 0 ; i <= rowCount ; i++){
            
            // TOP BORDER
            for(int j = 0 ; j <= colCount ; j++){
                System.out.printf("#");
                if( board[i][j].hasTopWall()){
                    for(int k = 0 ; k < cellInnerWidth ; k++){System.out.printf("#");}
                }
            }
            System.out.printf("#"); // last corner

            //MIDDLE SEGMENT
            for(int temp_i = 0 ; temp_i < cellInnerHeight ; temp_i++){
                for(int j = 0 ; j < colCount ; j++){
                    if ( board[i][j].hasLeftWall() ){
                        System.out.printf("#");
                    } else {
                        System.out.printf(" ");
                    }

                    // print cell has player 
                    if(board[i][j].hasPlayer1() && temp_i == cellInnerHeight/2){
                        System.out.printf("11");
                    } else if ( board[i][j].hasPlayer2() && temp_i == cellInnerHeight/2){
                        System.out.printf("22");
                    } else {
                        System.out.printf("  ");
                    }

                    // right wall of the last cell

                    if(j == colCount - 1 && board[i][j].hasRightWall()){
                        System.out.printf("#");
                    } else if ( j == colCount - 1 ){
                        System.out.printf(" ");
                    }
                }
            }
            
            // TOP BORDER
            for(int j = 0 ; j <= colCount ; j++){
                System.out.printf("#");
                if( board[i][j].hasTopWall()){
                    for(int k = 0 ; k < cellInnerWidth ; k++){System.out.printf("#");}
                }
            }
            System.out.printf("#"); // last corner

        }



        
    } 










    
    public int getRowCount() {
        return rowCount;
    }
    public int getColCount() {
        return colCount;
    }






}
