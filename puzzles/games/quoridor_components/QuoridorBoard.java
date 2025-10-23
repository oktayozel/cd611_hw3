package puzzles.games.quoridor_components;

import puzzles.core.Board;
import puzzles.core.Cell;

public class QuoridorBoard implements Board {
    private final int rowCount;
    private final int colCount;
    private final QuoridorCell[][] board;
    private QuoridorUser player1;
    private QuoridorUser player2;

    public QuoridorBoard(int rowCount, int colCount, QuoridorUser player1, QuoridorUser player2) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.player1 = player1;
        this.player2 = player2;
        this.board = initializeBoard();
    }

    private QuoridorCell[][] initializeBoard() {
        QuoridorCell[][] board = new QuoridorCell[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                board[i][j] = new QuoridorCell(i, j, "");
            }
        }
        int mid = colCount / 2;
        board[0][mid].setHasPlayer1(true);
        player1.setPosition(0, mid);
        board[rowCount - 1][mid].setHasPlayer2(true);
        player2.setPosition(rowCount - 1, mid);
        return board;
    }

    public boolean movePlayer(QuoridorUser player, String direction) {
        direction = direction.trim().toUpperCase();
        int pr = player.getRow();
        int pc = player.getCol();
        int tr = pr, tc = pc;

        switch (direction) {
            case "UP":    tr = pr - 1; break;
            case "DOWN":  tr = pr + 1; break;
            case "LEFT":  tc = pc - 1; break;
            case "RIGHT": tc = pc + 1; break;
            default: return false;
        }

        if (tr < 0 || tr >= rowCount || tc < 0 || tc >= colCount) return false;

        if (direction.equals("UP") && board[pr][pc].hasTopWall()) return false;
        if (direction.equals("DOWN") && board[tr][tc].hasTopWall()) return false;
        if (direction.equals("LEFT") && board[pr][pc].hasLeftWall()) return false;
        if (direction.equals("RIGHT") && board[tr][tc].hasLeftWall()) return false;

        if (board[tr][tc].hasPlayer1() || board[tr][tc].hasPlayer2()) return false;

        if (player == player1) {
            board[pr][pc].setHasPlayer1(false);
            board[tr][tc].setHasPlayer1(true);
        } else {
            board[pr][pc].setHasPlayer2(false);
            board[tr][tc].setHasPlayer2(true);
        }

        player.setPosition(tr, tc);
        return true;
    }

    public boolean claimWall(int row, int col, String direction, QuoridorUser player) {
        if (player.getWallsRemaining() <= 0) return false;

        QuoridorCell cell1, cell2;
        direction = direction.toUpperCase();

        if (direction.equals("H")) {
            if (row < 0 || row >= rowCount || col < 0 || col >= colCount - 1) 
                return false;
            cell1 = board[row][col];
            cell2 = board[row][col + 1];

            if (cell1.hasTopWall() || cell2.hasTopWall()) 
                return false;
            cell1.setTopWall(true);
            cell2.setTopWall(true);
        } 

        else if (direction.equals("V")) {
            if (row < 0 || row >= rowCount - 1 || col < 0 || col >= colCount)
                return false;
            cell1 = board[row][col];
            cell2 = board[row + 1][col];

            if (cell1.hasLeftWall() || cell2.hasLeftWall()) 
                return false;
            cell1.setLeftWall(true);
            cell2.setLeftWall(true);
        } 
        
        else {
            return false;
        }

        player.useWall();
        return true;
    }

    public boolean hasPlayerReachedGoal(QuoridorUser player) {
        return (player == player1 && player.getRow() == rowCount - 1) || (player == player2 && player.getRow() == 0);
    }

    public void display() {
    String horizontalWall = "###";  
    String verticalWall = "#";     
    String emptySpace = "   ";
    String borderCorner = "+";
    String borderEdge = "───";  

    System.out.print("  ");
    for (int j = 0; j < colCount; j++) {
        System.out.printf("%4d", j);
    }
    System.out.println();

    for (int i = 0; i < rowCount; i++) {
        //row i
        System.out.printf("%3d ", i);
        System.out.print(borderCorner);
        for (int j = 0; j < colCount; j++) {
            if (board[i][j].hasTopWall()) {
                System.out.print(horizontalWall + borderCorner);
            } else {
                System.out.print(borderEdge + borderCorner);
            }
        }
        System.out.println();

        System.out.print("    "); 
        for (int j = 0; j < colCount; j++) {
            System.out.print(board[i][j].hasLeftWall() ? verticalWall : "│");

            if (board[i][j].hasPlayer1()) {
                System.out.print("P1 ");
            } else if (board[i][j].hasPlayer2()) {
                System.out.print("P2 ");
            } else {
                System.out.print(emptySpace);
            }
        }
        System.out.println("│");
    }

    System.out.printf("%3d ", rowCount);
    System.out.print(borderCorner);
    for (int j = 0; j < colCount; j++) {
        System.out.print(borderEdge + borderCorner);
    }
    System.out.println();

    System.out.println("Player 1: " + player1);
    System.out.println("Player 2: " + player2);
}

    @Override
    public Cell[][] getBoard() {
        return board;
    }

    @Override
    public Cell getCell(int row, int col) {
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