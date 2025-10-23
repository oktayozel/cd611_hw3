package puzzles.core;

public abstract class Piece {
    private String value;

    public Piece(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (isAllowed(value)) {
            this.value = value;
        }
    }

    public String toString() {
        return value == null ? "null" : value;
    }
    public abstract boolean isAllowed(String value);
}
