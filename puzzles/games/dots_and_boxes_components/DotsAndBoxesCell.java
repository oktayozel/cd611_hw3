package puzzles.games.dots_and_boxes_components;

import puzzles.core.Cell;

public class DotsAndBoxesCell extends Cell {
    private Edge top = new Edge();
    private Edge bottom = new Edge();
    private Edge left = new Edge();
    private Edge right = new Edge();

    public DotsAndBoxesCell(int rowIndex, int colIndex) {
        super(rowIndex, colIndex, null); 
    }

    public boolean isComplete() {
        return top.isClaimed() && bottom.isClaimed() && left.isClaimed() && right.isClaimed();
    }

    public boolean hasTop() {
        return top.isClaimed();
    }

    public void setTop(DotsAndBoxesUser player) {
        top.claim(player);
    }

    public boolean hasBottom() {
        return bottom.isClaimed();
    }

    public void setBottom(DotsAndBoxesUser player) {
        bottom.claim(player);
    }

    public boolean hasLeft() {
        return left.isClaimed();
    }

    public void setLeft(DotsAndBoxesUser player) {
        left.claim(player);
    }

    public boolean hasRight() {
        return right.isClaimed();
    }

    public void setRight(DotsAndBoxesUser player) {
        right.claim(player);
    }

    public DotsAndBoxesUser getTopOwner() {
        return top.getOwner();
    }

    public DotsAndBoxesUser getBottomOwner() {
        return bottom.getOwner();
    }

    public DotsAndBoxesUser getLeftOwner() {
        return left.getOwner();
    }

    public DotsAndBoxesUser getRightOwner() {
        return right.getOwner();
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