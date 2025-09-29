package puzzles.games.dots_and_boxes_components;

// Board.java
public class DotsAndBoxesBoard {
    private int rows, cols;
    private DotsAndBoxesBox[][] boxes;
    private boolean lastMoveCompletedBox;

    public DotsAndBoxesBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        boxes = new DotsAndBoxesBox[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                boxes[r][c] = new DotsAndBoxesBox();
            }
        }
    }

    public void display() {
    // 顯示欄編號
    System.out.print("    ");
    for (int c = 0; c <= cols; c++) {
        System.out.printf("%-3d", c);
    }
    System.out.println();

    for (int r = 0; r < rows; r++) {
        // 第一層：上邊線
        System.out.printf("%-3d ", r);
        for (int c = 0; c < cols; c++) {
            System.out.print("○");
            System.out.print(boxes[r][c].hasTop() ? "──" : "  ");
        }
        System.out.println("○");

        // 第二層：左邊線與格子內容
        System.out.print("    ");
        for (int c = 0; c < cols; c++) {
            System.out.print(boxes[r][c].hasLeft() ? "│" : " ");
            String owner = boxes[r][c].getOwnerName();
            System.out.print(owner == null ? "  " : owner.substring(0, Math.min(2, owner.length())));
        }
        System.out.println(boxes[r][cols - 1].hasRight() ? "│" : " ");
    }

    // 最底邊線
    System.out.printf("%-3d ", rows);
    for (int c = 0; c < cols; c++) {
        System.out.print("○");
        System.out.print(boxes[rows - 1][c].hasBottom() ? "──" : "  ");
    }
    System.out.println("○");
}


    public boolean claimEdge(int row, int col, String direction, DotsAndBoxesPlayer player) {
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
                if (boxes[r][c].isComplete() && boxes[r][c].getOwnerName() == null) {
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
