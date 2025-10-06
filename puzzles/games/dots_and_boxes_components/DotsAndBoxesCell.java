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
    // constructor for dots and boxes cell
    public DotsAndBoxesCell(int rowIndex, int colIndex) {
        super(rowIndex, colIndex, null); 
    }
    // checks if the cell has been surrounded or not
    public boolean isComplete() {
        return top.isClaimed() && bottom.isClaimed() && left.isClaimed() && right.isClaimed();
    }
    // checks if the top edge is claimed.
    public boolean hasTop() {
        return top.isClaimed();
    }
    // sets the top edge to the player
    public void setTop(DotsAndBoxesUser player) {
        top.claim(player);
    }
    // checks if the bottom edge is claimed.
    public boolean hasBottom() {
        return bottom.isClaimed();
    }
    // sets the bottom edge to the player
    public void setBottom(DotsAndBoxesUser player) {
        bottom.claim(player);
    }

    // checks if the left edge is claimed.
    public boolean hasLeft() {
        return left.isClaimed();
    }
    // sets the left edge to the player

    public void setLeft(DotsAndBoxesUser player) {
        left.claim(player);
    }

    // checks if the right edge is claimed.
    public boolean hasRight() {
        return right.isClaimed();
    }
    // sets the right edge to the player
    public void setRight(DotsAndBoxesUser player) {
        right.claim(player);
    }
    // gets the user who claimed the top edge
    public DotsAndBoxesUser getTopOwner() {
        return top.getOwner();
    }
    // gets the user who claimed the bottom edge

    public DotsAndBoxesUser getBottomOwner() {
        return bottom.getOwner();
    }
    // gets the user who claimed the left edge

    public DotsAndBoxesUser getLeftOwner() {
        return left.getOwner();
    }
    // gets the user who claimed the right edge

    public DotsAndBoxesUser getRightOwner() {
        return right.getOwner();
    }


    // a helper class in order to keep edge for the dots and boxes user.
    private static class Edge {
        private boolean claimed = false;
        private DotsAndBoxesUser owner = null;

        // checks if it is claimed or not
        public boolean isClaimed() {
            return claimed;
        }
        // helper function to claim an edge
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