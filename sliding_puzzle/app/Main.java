package sliding_puzzle.app;

import sliding_puzzle.sliding_puzzle_components.SlidingPuzzleBoard;
import sliding_puzzle.core.Board;
import sliding_puzzle.io.Output;
import sliding_puzzle.sliding_puzzle_components.SlidingPuzzleGameManager;

public class Main{
    public static void main(String[] args){
        SlidingPuzzleGameManager gameManager = new SlidingPuzzleGameManager();
        gameManager.initGame();
        gameManager.runGame();
    }
}