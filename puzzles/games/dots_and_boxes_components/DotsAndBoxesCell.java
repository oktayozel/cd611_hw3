package puzzles.games.dots_and_boxes_components;

import puzzles.core.Cell;

/*
 * This class represents a cell in the Dots and Boxes game.
 * it extends the abstract Cell class and includes properties for the edges of the cell 
 * It provides methods to maintain cell operations.
 */
public class DotsAndBoxesCell extends Cell {
    private Edge top = new Edge();
    private Edge bottom = new Edge();
    private Edge left = new Edge();
    private Edge right = new Edge();

    public DotsAndBoxesCell(int rowIndex, int colIndex) {
        super(rowIndex, colIndex, null); 
    }

    //check finish the box
    public boolean isComplete() {
        return top.isClaimed() && bottom.isClaimed() && left.isClaimed() && right.isClaimed();
    }
    // check if the there is top line
    public boolean hasTop() {
        return top.isClaimed();
    }
    // set the top line
    public void setTop(DotsAndBoxesUser player) {
        top.claim(player);
    }
    // check if the there is bottom line
    public boolean hasBottom() {
        return bottom.isClaimed();
    }
    // set the bottom line
    public void setBottom(DotsAndBoxesUser player) {
        bottom.claim(player);
    }
    // check if the there is left line
    public boolean hasLeft() {
        return left.isClaimed();
    }
    // set the left line
    public void setLeft(DotsAndBoxesUser player) {
        left.claim(player);
    }
    // check if the there is right line
    public boolean hasRight() {
        return right.isClaimed();
    }
    // set the right line
    public void setRight(DotsAndBoxesUser player) {
        right.claim(player);
    }

    // returns the owner of the top edge of the cell.
    public DotsAndBoxesUser getTopOwner() {
        return top.getOwner();
    }
    //returns the owner of the bottom edge of the cell.
    public DotsAndBoxesUser getBottomOwner() {
        return bottom.getOwner();
    }
    //returns the owner of the left edge of the cell.
    public DotsAndBoxesUser getLeftOwner() {
        return left.getOwner();
    }
    //returns the owner of the right edge of the cell.
    public DotsAndBoxesUser getRightOwner() {
        return right.getOwner();
    }
    // Inner class to represent an edge of the cell.
    private static class Edge {
        private boolean claimed = false;
        private DotsAndBoxesUser owner = null;

        public boolean isClaimed() {
            return claimed;
        }

        public void claim(DotsAndBoxesUser player) {
            if (!claimed) {
                claimed = true;
                owner = player;
            }
        }

        public DotsAndBoxesUser getOwner() {
            return owner;
        }

        public String getOwnerName() {
            return owner == null ? null : owner.getUsername();
        }

        @Override
        public String toString() {
            return claimed ? (owner == null ? "X" : owner.getUsername().substring(0, 1)) : " ";
        }
    }
}