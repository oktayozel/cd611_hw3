package puzzles.games.dots_and_boxes_components;



/*
 * this class represents the game board for the dots and boxes game.
 * it manages the grid of boxes handles edge claims checks for completed boxes
 * and displays the current state of the board. Basically holds everything related to the board.
 */
public class DotsAndBoxesBoard {
    private int rows, cols;
    private DotsAndBoxesCell[][] boxes;
    private boolean lastMoveCompletedBox;


    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";



    //  colors the line according the which player has picked the line.
    private String getColoredLine(boolean claimed, DotsAndBoxesUser owner, String direction) {
        if (!claimed) return direction.equals("H") ? "  " : " ";
        String line = direction.equals("H") ? "──" : "│";
        String color = (owner != null && "P1".equals(owner.getShortName())) ? ANSI_BLUE : ANSI_RED;
        return String.format("%s%s%s", color, line, ANSI_RESET);
    }
    // constructor for a rows x cols board 
    public DotsAndBoxesBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        boxes = new DotsAndBoxesCell[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                boxes[r][c] = new DotsAndBoxesCell(r, c);
            }
        }
    }
    // displays the board by denoting which user has picked which edge.
    public void display() {
        System.out.print("    ");
        for (int c = 0; c <= cols; c++) {
            System.out.printf("%-3d", c); //col num
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            // upper line
            System.out.printf("%-3d ", r); //row num
            for (int c = 0; c < cols; c++) {
                System.out.print("○");
                System.out.printf("%s", getColoredLine(boxes[r][c].hasTop(), boxes[r][c].getTopOwner(), "H"));
            }
            System.out.println("○");

            // left line and box
            System.out.print("    ");
            for (int c = 0; c < cols; c++) {
                System.out.printf("%s", getColoredLine(boxes[r][c].hasLeft(), boxes[r][c].getLeftOwner(), "V"));
                DotsAndBoxesUser owner = boxes[r][c].getOwner();
                System.out.print(owner == null ? "  " : owner.getShortName());    
            }
            System.out.printf("%s", getColoredLine(boxes[r][cols - 1].hasRight(), boxes[r][cols - 1].getRightOwner(), "V"));
            System.out.println();
        }

        // lowest line
        System.out.printf("%-3d ", rows);
        for (int c = 0; c < cols; c++) {
            System.out.print("○");
            System.out.printf("%s", getColoredLine(boxes[rows - 1][c].hasBottom(), boxes[rows - 1][c].getBottomOwner(), "H"));
        }
        System.out.println("○");
    }

    // sets the edge to the user and then checks if the game is over or not 
    public boolean claimEdge(int row, int col, String direction, DotsAndBoxesUser player) {
        lastMoveCompletedBox = false;
        
        //outside the board
        if(direction.equals("H")) {
            if (row < 0 || row > rows || col < 0 || col >= cols) return false;
        } else if (direction.equals("V")) {
            if (row < 0 || row >= rows || col < 0 || col > cols) return false;
        } else {
            return false;
        }

        boolean valid = false;

        switch (direction) {
            case "H": // horizontal right line
                if (row < rows) {
                    if (!boxes[row][col].hasTop()) {
                        boxes[row][col].setTop(player);;
                        valid = true;
                    }
                }
                if (row > 0) {
                    if (!boxes[row - 1][col].hasBottom()) {
                        boxes[row - 1][col].setBottom(player);
                        valid = true;
                    }       
                }
                break;

            case "V": // vertical down line
                if (col < cols) {
                    if (!boxes[row][col].hasLeft()) {
                        boxes[row][col].setLeft(player);
                        valid = true;
                    }
                }
                if (col > 0) {
                    if (!boxes[row][col - 1].hasRight()) {
                        boxes[row][col - 1].setRight(player);
                        valid = true;
                    }
                }
                break;
        }

        // check finish the cube
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (boxes[r][c].isComplete() && boxes[r][c].getOwner() == null) {
                    boxes[r][c].setOwner(player);
                    player.addPoint();
                    lastMoveCompletedBox = true;
                }
            }
        }

        return valid;
    }


    // method to check if board has been completed or not
    public boolean isFull() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!boxes[r][c].isComplete()) return false;
            }
        }
        return true;
    }


    // getter method to check if the game is over or not
    public boolean lastMoveCompletedBox() {
        return lastMoveCompletedBox;
    }
    // getter method for rows.
    public int getRows(){
        return this.rows;
    }
    //getter method for cols
    public int getCols(){
        return this.cols;
    }
}
