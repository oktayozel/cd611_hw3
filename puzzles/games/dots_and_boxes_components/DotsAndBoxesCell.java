package puzzles.games.dots_and_boxes_components;

import puzzles.core.Cell;

public class DotsAndBoxesCell extends Cell {
    private boolean top = false;
    private boolean bottom = false;
    private boolean left = false;
    private boolean right = false;

    public DotsAndBoxesCell(int rowIndex, int colIndex) {
        super(rowIndex, colIndex, null); 
    }

    public boolean isComplete() {
        return top && bottom && left && right;
    }

    public boolean hasTop() {
        return top;
    }

    public void setTop(boolean value) {
        this.top = value;
    }

    public boolean hasBottom() {
        return bottom;
    }

    public void setBottom(boolean value) {
        this.bottom = value;
    }

    public boolean hasLeft() {
        return left;
    }

    public void setLeft(boolean value) {
        this.left = value;
    }

    public boolean hasRight() {
        return right;
    }

    public void setRight(boolean value) {
        this.right = value;
    }

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