package puzzles.games.quoridor_components;
import java.util.ArrayDeque;
import java.util.Queue;
import puzzles.core.Board;
import puzzles.core.Cell;

/*
 * this class represents the game board for the quoridor game.
 * it manages the grid of boxes handles edge claims checks for completed boxes
 * and displays the current state of the board. Basically holds everything related to the board.
 */
public class QuoridorBoard extends Board {
    private final int rowCount;
    private final int colCount;
    private final QuoridorCell[][] board;
    private QuoridorUser player1;
    private QuoridorUser player2;

    // Constructor
    public QuoridorBoard(int rowCount, int colCount, QuoridorUser player1, QuoridorUser player2) {
        super(new QuoridorCell[rowCount][colCount]);
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.player1 = player1;
        this.player2 = player2;
        this.board = initializeBoard();
    }
    // initializes the board by creating QuoridorCell objects for each position
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
    // moves the player in the specified direction if the move is valid
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

    // claims a wall on the board if the move is valid
    public boolean claimWall(int row, int col, String direction, QuoridorUser player) {
        if (player.getWallsRemaining() <= 0) return false;
        if (direction == null) return false;// guard

        direction = direction.toUpperCase();
        QuoridorCell cell1, cell2;

        if (direction.equals("H")) {
            if (row <= 0 || row >= rowCount || col < 0 || col >= colCount - 1)
                return false;

            cell1 = board[row][col];
            cell2 = board[row][col + 1];

            if (cell1.hasTopWall() || cell2.hasTopWall())
                return false;

            cell1.setTopWall(true);
            cell2.setTopWall(true);
            cell1.setTopWallOwner(player.getId());
            cell2.setTopWallOwner(player.getId());
            
            boolean ok = (calculateShortestPathLength(player1) != -1) &&
             (calculateShortestPathLength(player2) != -1);

            if (!ok) {
                //  Revert
                cell1.setTopWall(false);
                cell2.setTopWall(false);
                cell1.setTopWallOwner("");
                cell2.setTopWallOwner("");
                return false;
            }
        }

        else if (direction.equals("V")) {
            if (row < 0 || row >= rowCount - 1 || col <= 0 || col >= colCount)
                return false;

            cell1 = board[row][col];
            cell2 = board[row + 1][col];

            if (cell1.hasLeftWall() || cell2.hasLeftWall())
                return false;

            cell1.setLeftWall(true);
            cell2.setLeftWall(true);
            cell1.setLeftWallOwner(player.getId());
            cell2.setLeftWallOwner(player.getId());

            boolean ok = (calculateShortestPathLength(player1) != -1) &&
             (calculateShortestPathLength(player2) != -1);

            if (!ok) {
                //  Revert 
                cell1.setLeftWall(false);
                cell2.setLeftWall(false);
                cell1.setLeftWallOwner("");
                cell2.setLeftWallOwner("");
                return false;
            }
        }

        else {
            return false;
        }

        player.useWall();
        return true;
    }

    // checks if the player has reached their goal row
    public boolean hasPlayerReachedGoal(QuoridorUser player) {
        return (player == player1 && player.getRow() == rowCount - 1) || (player == player2 && player.getRow() == 0);
    }
    // displays the current state of the board
    public void display() {
        String RESET = "\u001B[0m";
        String PURPLE = "\u001B[35m";
        String YELLOW = "\u001B[33m";
        String RED = "\u001B[31m";
        String BLUE = "\u001B[34m";
 
        String emptySpace = "   ";
        String borderCorner = PURPLE + "+" + RESET;
        String borderEdge = YELLOW + "───" + RESET;  
        String borderEdgePlain = "───";

        // prints player 1 situation
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(BLUE + "Player1: " + RESET + player1 + " Shortest path to target: " + calculateShortestPathLength(player1) + "\n");
        System.out.println("-------------------------------------------------------------------------------------------------");

        // prints column headers
        System.out.print("  ");
        for (int j = 0; j <= colCount; j++) {
            System.out.printf("%4d", j);
        }
        System.out.println();
        // prints rows
        for (int i = 0; i < rowCount; i++) {
            //row i
            System.out.printf("%3d ", i);
            boolean isPlainRow = (i == 0 || i == rowCount);
            System.out.print(isPlainRow ? borderCorner : borderCorner);

            for (int j = 0; j < colCount; j++) {
            String edge;
            if (board[i][j].hasTopWall()) {
                String owner = board[i][j].getTopWallOwner();
                if ("P1".equals(owner)) {
                    edge = BLUE + "###" + RESET;
                } else if ("P2".equals(owner)) {
                    edge = RED + "###" + RESET;
                } else {
                    edge = "###";
                }
            } else {
                edge = isPlainRow ? borderEdgePlain : borderEdge;
            }
            System.out.print(edge + borderCorner);

            }

            System.out.println();
            System.out.print("    "); 

            for (int j = 0; j < colCount; j++) {

                boolean isPlainCol = (j == 0);
                String leftSymbol;
                if (board[i][j].hasLeftWall()) {
                String owner = board[i][j].getLeftWallOwner();
                if ("P1".equals(owner)) {
                    leftSymbol = isPlainCol ? "#" : BLUE + "#" + RESET;
                } else if ("P2".equals(owner)) {
                    leftSymbol = isPlainCol ? "#" : RED + "#" + RESET;
                } else {
                    leftSymbol = isPlainCol ? "#" : YELLOW + "#" + RESET;
                }
            } else {
                leftSymbol = isPlainCol ? "│" : YELLOW + "│" + RESET;
            }

                System.out.print(leftSymbol);

                if (board[i][j].hasPlayer1()) {
                    System.out.print(BLUE + "P1 " + RESET);
                } else if (board[i][j].hasPlayer2()) {
                    System.out.print(RED + "P2 " + RESET);
                } else {
                    System.out.print(emptySpace);
                }
            }
            
            System.out.println("│");

        }  
        // prints bottom border

        System.out.printf("%3d ", rowCount);
        System.out.print(borderCorner);
        for (int j = 0; j < colCount; j++) {
            System.out.print(borderEdgePlain + borderCorner);
        }
        System.out.println();
        // prints player 2 situation
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(RED + "\nPlayer2: "+ RESET + player2 + " Shortest path to target: " + calculateShortestPathLength(player2));
        System.out.println("-------------------------------------------------------------------------------------------------");


    }

    // calculates the shortest path length for the given player if 
    // such a path does not exist returns -1
    // uses bfs to do that
    public int calculateShortestPathLength(QuoridorUser player){
        final int goalRow = (player == player1) ? (rowCount-1) : 0;

        final int start_row = player.getRow();
        final int start_col = player.getCol();


        boolean[][] visited = new boolean[rowCount][colCount];
        int[][] dist = new int[rowCount][colCount];

        Queue<int[]> q = new ArrayDeque<>();

        visited[start_row][start_col] = true;
        dist[start_row][start_col] = 0;
        q.add(new int[]{start_row, start_col});

        final int[] row_direction = {-1,1,0,0};
        final int[] col_direction = {0,0,-1,1};
        // bfs loop
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            if(r == goalRow) {
                return dist[r][c];
            }
            for (int k = 0 ; k < 4 ; k++){
                int next_row = r + row_direction[k];
                int next_col = c + col_direction[k];
                if (!canStepHelper(r, c, next_row, next_col)) continue;
                if (visited[next_row][next_col]) continue;
                visited[next_row][next_col] = true;
                dist[next_row][next_col] = dist[r][c] + 1;
                q.add(new int[]{next_row, next_col});
            }

        }
        return -1;
    }

    // checks if the player can move from (r, c) to (next_row, next_col)
    public boolean canStepHelper(int r, int c, int next_row, int next_col) {
        if(next_row < 0 || next_row >= rowCount || next_col < 0 || next_col >= colCount) {
            return false;
        }
        if(Math.abs(next_row - r) + Math.abs(next_col - c) != 1) {
            return false;
        }
        QuoridorCell currentCell = board[r][c];
        QuoridorCell nextCell = board[next_row][next_col];


        int dr = next_row - r, dc = next_col - c;
        // check if we can move up
        if (dr == -1 && dc == 0) { 
            if (currentCell.hasTopWall()){
                return false;
            }
        } 
        // check if we can move down
        else if (dr == 1 && dc == 0) {
            if (nextCell.hasTopWall()){
                return false;
            }
        } 
        // check if we can move left
        else if (dr == 0 && dc == -1) {
            if (currentCell.hasLeftWall()){
                return false;
            }
        } 
        // check if we can move right
        else if (dr == 0 && dc == 1) {  
            if (nextCell.hasLeftWall()){
                return false;
            }
        }

        if(nextCell.hasPlayer1() || nextCell.hasPlayer2()) {
            return false;
        }

        return true;
    }


    // getters for board dimensions and cells
    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
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

    // helpers for the bot operations
    public QuoridorUser getOpponent(QuoridorUser me) {
        return (me == player1) ? player2 : player1;
    }

    public int[] getPlayerPosition(QuoridorUser player) {
        return new int[]{ player.getRow(), player.getCol() };
    }

    public int getRemainingWalls(QuoridorUser player) {
        return player.getWallsRemaining();
    }

}