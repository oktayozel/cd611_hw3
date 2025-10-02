package puzzles.games.dots_and_boxes_components;

public class DotsAndBoxesBox {

    private boolean top = false;
    private boolean bottom = false;
    private boolean left = false;
    private boolean right = false;
    private DotsAndBoxesPlayer owner = null;

    public boolean isComplete() {
        return top && bottom && left && right;
    }

    public void setOwner(DotsAndBoxesPlayer player) {
        this.owner = player;
    }

    public DotsAndBoxesPlayer getOwner() {
        return owner;
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
    
}
