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
    public boolean claimWall(int row, int col, String direction , QuoridorUser player){
        QuoridorCell cell1;
        QuoridorCell cell2;
        if( (row ==0 && direction.equals("H")) || (row == rowCount && direction.equals("H")) ){
            return false;
        }
        if(col == colCount){
            if(direction.equals("V")){
                cell1 = board[row][col];
                cell2 = board[row+1][col];  
                if (cell1.hasLeftWall() || cell2.hasLeftWall()){
                    return false;
                }
                cell1.setLeftWall(true);    
                cell2.setLeftWall(true);    

            }
            if(direction.equals("H")){
                return false;
            }

        }
        else{
            if(direction.equals("H")){
                cell1 = board[row][col];
                cell2 = board[row][col+1];  
                if (cell1.hasTopWall() || cell2.hasTopWall()){
                    return false;
                }
                cell1.setTopWall(true);
                cell2.setTopWall(true);
            }
            if(direction.equals("V")){
                cell1 = board[row][col];
                cell2 = board[row+1][col];  
                if (cell1.hasLeftWall() || cell2.hasLeftWall()){
                    return false;
                }
                cell1.setLeftWall(true);    
                cell2.setLeftWall(true);       

            }
        }
        return true;
    }

    public boolean movePlayer(QuoridorUser player, String direction) {
        if (player == null || direction == null) return false;
        direction = direction.trim().toUpperCase();

        int pr = -1, pc = -1;
        boolean isP1 = (player == player1);
        boolean isP2 = (player == player2);
        if (!isP1 && !isP2) return false; 

        outer:
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                if ((isP1 && board[r][c].hasPlayer1()) ||
                    (isP2 && board[r][c].hasPlayer2())) {
                    pr = r; pc = c;
                    break outer;
                }
            }
        }
        if (pr == -1) return false; 

        int tr = pr, tc = pc;
        switch (direction) {
            case "UP":
                tr = pr - 1;
                break;
            case "DOWN":
                tr = pr + 1;
                break;
            case "LEFT":
                tc = pc - 1;
                break;
            case "RIGHT":
                tc = pc + 1;
                break;
            default:
                return false; 
        }

        if (tr < 0 || tr >= rowCount || tc < 0 || tc >= colCount) return false;

        switch (direction) {
            case "UP":
                if (board[pr][pc].hasTopWall()) return false;
                break;
            case "DOWN":
                if (board[tr][tc].hasTopWall()) return false; 
                break;
            case "LEFT":
                if (board[pr][pc].hasLeftWall()) return false;
                break;
            case "RIGHT":
                if (board[tr][tc].hasLeftWall()) return false; 
                break;
        }

        if (board[tr][tc].hasPlayer1() || board[tr][tc].hasPlayer2()) {
            return false;
        }

        if (isP1) {
            board[pr][pc].setHasPlayer1(false);
            board[tr][tc].setHasPlayer1(true);
        } else {
            board[pr][pc].setHasPlayer2(false);
            board[tr][tc].setHasPlayer2(true);
        }
        return true;
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
