package puzzles.games.dots_and_boxes_components;

import puzzles.core.Piece;

public class DotsAndBoxesPiece extends Piece {

    public static final String[] ALLOWED_VALUES = {"P1", "P2", " "};

    public DotsAndBoxesPiece(String value) {
        super(value);
    }

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
