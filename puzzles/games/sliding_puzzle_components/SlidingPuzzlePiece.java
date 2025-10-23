package puzzles.games.sliding_puzzle_components;

import puzzles.core.Piece;

public class SlidingPuzzlePiece extends Piece {

    public static final String[] ALLOWED_VALUES = new String[1000];

    public SlidingPuzzlePiece(String value) {
        super(value);
        for(int i = 0 ; i < 1000 ; i++) {
            ALLOWED_VALUES[i] = String.valueOf(i);
        }
        ALLOWED_VALUES[999] = " ";
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