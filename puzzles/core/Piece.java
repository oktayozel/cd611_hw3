package puzzles.core;


/*
 * Abstract class representing a game piece.
 */
public abstract class Piece {
    private String value;

    // constructor for the game piece
    public Piece(String value) {
        this.value = value;
    }

    // returns the value of the game piece
    public String getValue() {
        return value;
    }

    // sets the value of the game piece depending on the respective isAllowed function in the specific subclass
    public void setValue(String value) {
        if (isAllowed(value)) {
            this.value = value;
        }
    }

    public String toString() {
        return value == null ? "null" : value;
    }
    //  checks if the value is allowed for the specific game piece in the spesific game
    public abstract boolean isAllowed(String value);
}
