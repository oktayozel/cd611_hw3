package puzzles.core;

import java.util.Scanner;
import puzzles.games.dots_and_boxes_components.DotsAndBoxesGameManager;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleGameManager;
import puzzles.io.Input;
import puzzles.io.Output;

public class GameSelectionManager {
    private DotsAndBoxesGameManager dotsAndBoxesGameManager;
    private SlidingPuzzleGameManager slidingPuzzleGameManager;
    private Input input;
    private Output output;
    private Settings settings;
    public GameSelectionManager(){
        this.settings = new Settings();
        this.dotsAndBoxesGameManager = new DotsAndBoxesGameManager(settings);
        this.slidingPuzzleGameManager = new SlidingPuzzleGameManager(settings);
        this.input = new Input();
        this.output = new Output(this.input);
    }
    // returns the game manager object of Dots and Boxes puzzle.
    public DotsAndBoxesGameManager getDotsAndBoxesGameManager(){
        return this.dotsAndBoxesGameManager;
    }
    // returns the game manager object of Sliding puzzle.
    public SlidingPuzzleGameManager getSlidingPuzzleGameManager(){
        return this.slidingPuzzleGameManager;
    }

    //Displays the games looking to the config file and allows for a number selection and then depending on the game selection calls the related game.
    public void runSelectedGame() {
        while(true){
            output.displayAnimation();
            output.displaySupportedGames(settings.getSupportedGames());
            int _selected_game = input.readIntOrExit("Which game would you like to play? \nYou can simply type exit to finish the game  >>> ", 1, 2);
            
            if (_selected_game == 1) {
                boolean gameFirstOpen = true;
                while (true) {
                    slidingPuzzleGameManager.initGame(gameFirstOpen);
                    gameFirstOpen = false;
                    if (!slidingPuzzleGameManager.runGame()) {
                        break;
                    }
                }
            }

            if (_selected_game == 2) {
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

}
