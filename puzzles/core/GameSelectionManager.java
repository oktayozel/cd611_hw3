package puzzles.core;

import java.util.Scanner;
import puzzles.games.dots_and_boxes_components.DotsAndBoxesGameManager;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleGameManager;
import puzzles.io.Input;
public class GameSelectionManager {
    private DotsAndBoxesGameManager dotsAndBoxesGameManager;
    private SlidingPuzzleGameManager slidingPuzzleGameManager;
    private Input input;
    public GameSelectionManager(){
        Scanner scanner = new Scanner(System.in);                // Scanner
        //this.input = new Input(scanner);
        this.dotsAndBoxesGameManager = new DotsAndBoxesGameManager(scanner);
        this.slidingPuzzleGameManager = new SlidingPuzzleGameManager();
        this.input = new Input();
    }
    public DotsAndBoxesGameManager getDotsAndBoxesGameManager(){
        return this.dotsAndBoxesGameManager;
    }
    public SlidingPuzzleGameManager getSlidingPuzzleGameManager(){
        return this.slidingPuzzleGameManager;
    }
    public String readGameSelection(){
        return input.readGameSelection();
    }

    public void runSelectedGame(){

        String _selected_game = this.readGameSelection();

        
        if( _selected_game.equals("sliding_puzzle")  ){

            boolean gameFirstOpen = true;
            while(true){
                slidingPuzzleGameManager.initGame(gameFirstOpen);
                gameFirstOpen = false;
                if(slidingPuzzleGameManager.runGame() == false){
                    break;
                }
            }
        }

        if( _selected_game.equals("dots_and_boxes")  ){
            boolean gameFirstOpen = true;
            while (true) {
                dotsAndBoxesGameManager.initGame(gameFirstOpen);
                gameFirstOpen = false;
                if (!dotsAndBoxesGameManager.runGame()) {
                    break;
                }
            }

        }


    }

}
