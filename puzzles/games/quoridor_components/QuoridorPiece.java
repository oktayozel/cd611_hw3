package puzzles.games.quoridor_components;

import puzzles.core.Piece;


/*
 * 
 * Represents a piece in the Quoridor game, implements the abstract Piece class.
 * 
 */
public class QuoridorPiece extends Piece {

    // is allowed method for the quoridor specific pieces only player pieces and empty space and null
    public static final String[] ALLOWED_VALUES = {"P1", "P2", " "};

    public QuoridorPiece(String value) {
        super(value);
    }


    // is allowed method fot he quoridor spesific peaces
    @Override
    public boolean isAllowed(String value) {
        if (value == null) {
            return true;
        }
        for (String allowedValue : ALLOWED_VALUES) {
            if (allowedValue.equals(value)) {
                return true;
            }
        }
        return false;
    }
}