package sliding_puzzle.app;

import sliding_puzzle.components.SlidingPuzzleBoard;
import sliding_puzzle.core.Board;
import sliding_puzzle.io.Output;
import sliding_puzzle.components.SlidingPuzzleGameManager;
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