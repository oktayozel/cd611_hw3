package puzzles.app;

import puzzles.core.Board;
import puzzles.core.GameSelectionManager;
import puzzles.games.dots_and_boxes_components.DotsAndBoxesGameManager;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleBoard;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleGameManager;
import puzzles.io.Output;
/*
 * This is the entry point of the program.
 * It initializes the game manager and starts the game loop.
 */
public class Main{

    public static void main(String[] args){


        GameSelectionManager gameSelectionManager = new GameSelectionManager();
    
        gameSelectionManager.runSelectedGame();
   
    }
}