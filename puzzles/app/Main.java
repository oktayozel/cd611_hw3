package puzzles.app;

import puzzles.core.Board;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleBoard;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleGameManager;
import puzzles.io.Output;
/*
 * This is the entry point of the program.
 * It initializes the game manager and starts the game loop.
 */
public class Main{
    public static void main(String[] args){
        SlidingPuzzleGameManager gameManager = new SlidingPuzzleGameManager();
        boolean gameFirstOpen = true;
        while(true){
            gameManager.initGame(gameFirstOpen);
            gameFirstOpen = false;
            if(gameManager.runGame() == false){
                break;
            }
        }
    }
}