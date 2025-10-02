package puzzles.games.dots_and_boxes_components;

public class DotsAndBoxesEdge {
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
