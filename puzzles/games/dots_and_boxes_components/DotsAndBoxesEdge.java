package puzzles.games.dots_and_boxes_components;

public class DotsAndBoxesEdge {
    private boolean claimed = false;
    private DotsAndBoxesPlayer owner = null;

    public boolean isClaimed() {
        return claimed;
    }

    public void claim(DotsAndBoxesPlayer player) {
        if (!claimed) {
            claimed = true;
            owner = player;
        }
    }

    public DotsAndBoxesPlayer getOwner() {
        return owner;
    }

    public String getOwnerName() {
        return owner == null ? null : owner.getName();
    }

    @Override
    public String toString() {
        return claimed ? (owner == null ? "X" : owner.getName().substring(0, 1)) : " ";
    }
}
