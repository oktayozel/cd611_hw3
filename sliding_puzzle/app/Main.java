package sliding_puzzle.app;

import sliding_puzzle.sliding_puzzle_components.SlidingPuzzleBoard;
import sliding_puzzle.core.Board;
import sliding_puzzle.io.Output;

public class Main{
    public static void main(String[] args){
        Board board = new SlidingPuzzleBoard(4,4);
        Output debugPrinter = new Output();
        debugPrinter.printBoard(board.getCells());
    }
}