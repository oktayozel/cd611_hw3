package puzzles.games.dots_and_boxes_components;

// Board.java
public class DotsAndBoxesBoard {
    private int rows, cols;
    private DotsAndBoxesCell[][] boxes;
    private boolean lastMoveCompletedBox;

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
                System.out.print(boxes[r][c].hasTop() ? "──" : "  ");
            }
            System.out.println("○");

            // left line and box
            System.out.print("    ");
            for (int c = 0; c < cols; c++) {
                System.out.print(boxes[r][c].hasLeft() ? "│" : " ");
                DotsAndBoxesUser owner = boxes[r][c].getOwner();
                System.out.print(owner == null ? "  " : owner.getShortName());    
            }
            System.out.println(boxes[r][cols - 1].hasRight() ? "│" : " ");
        }

        // lowest line
        System.out.printf("%-3d ", rows);
        for (int c = 0; c < cols; c++) {
            System.out.print("○");
            System.out.print(boxes[rows - 1][c].hasBottom() ? "──" : "  ");
        }
        System.out.println("○");
    }


    public boolean claimEdge(int row, int col, String direction, DotsAndBoxesUser player) {
        lastMoveCompletedBox = false;

        if(direction.equals("H")) {
            if (row < 0 || row > rows || col < 0 || col >= cols) return false;
        } else if (direction.equals("V")) {
            if (row < 0 || row >= rows || col < 0 || col > cols) return false;
        } else {
            return false;
        }

        boolean valid = false;

        switch (direction) {
            case "H": // right line
                if (row < rows) {
                    if (!boxes[row][col].hasTop()) {
                        boxes[row][col].setTop(true);
                        valid = true;
                    }
                }
                if (row > 0) {
                    if (!boxes[row - 1][col].hasBottom()) {
                        boxes[row - 1][col].setBottom(true);
                        valid = true;
                    }       
                }
                break;

            case "V": // down line
                if (col < cols) {
                    if (!boxes[row][col].hasLeft()) {
                        boxes[row][col].setLeft(true);
                        valid = true;
                    }
                }
                if (col > 0) {
                    if (!boxes[row][col - 1].hasRight()) {
                        boxes[row][col - 1].setRight(true);
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

    public boolean isFull() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!boxes[r][c].isComplete()) return false;
            }
        }
        return true;
    }

    public boolean lastMoveCompletedBox() {
        return lastMoveCompletedBox;
    }
}
