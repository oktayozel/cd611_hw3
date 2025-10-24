package puzzles.core;

import java.util.Scanner;
import puzzles.games.dots_and_boxes_components.DotsAndBoxesGameManager;
import puzzles.games.sliding_puzzle_components.SlidingPuzzleGameManager;
import puzzles.games.quoridor_components.QuoridorGameManager;
import puzzles.io.Input;
import puzzles.io.Output;


/*
 * This class manages the selection and execution of different games.
 * It initializes game managers for each supported game and handles user input to select and run a game.
 * It serves as a bigger manager for the subgames. It is a main menu
 */

public class GameSelectionManager {
    private DotsAndBoxesGameManager dotsAndBoxesGameManager;
    private SlidingPuzzleGameManager slidingPuzzleGameManager;
    private QuoridorGameManager quoridorGameManager;
    private Input input;
    private Output output;
    private Settings settings;

    public GameSelectionManager(){
        this.settings = new Settings();
        this.dotsAndBoxesGameManager = new DotsAndBoxesGameManager(settings);
        this.slidingPuzzleGameManager = new SlidingPuzzleGameManager(settings);
        this.quoridorGameManager = new QuoridorGameManager(settings);
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
    // returns the game manager object of Quoridor puzzle.
    public QuoridorGameManager getQuoridorGameManager(){
        return this.quoridorGameManager;
    }

    //Displays the games looking to the config file and allows for a number selection and then depending on the game selection calls the related game.
    public void runSelectedGame() {
        while(true){
            output.displayAnimation("opening");
            output.displaySupportedGames(settings.getSupportedGames());
            
            int _selected_game = input.readIntOrExit("Which game would you like to play? \nYou can simply type exit to finish the game  >>> ", 1, 3);
            boolean gameFirstOpen = true;
            // runs sliding puzzle
            if (_selected_game == 1) {
                while (true) {
                    slidingPuzzleGameManager.initGame(gameFirstOpen);
                    gameFirstOpen = false;
                    if (!slidingPuzzleGameManager.runGame()) {
                        break;
                    }
                }
            }
            // runs dots and boxes
            if (_selected_game == 2) {
                while (true) {
                    dotsAndBoxesGameManager.initGame(gameFirstOpen);
                    gameFirstOpen = false;
                    if (!dotsAndBoxesGameManager.runGame()) {
                        break;
                    }
                }
            }
            // runs quoridor
            if(_selected_game == 3){
                while( true){
                    quoridorGameManager.initGame(gameFirstOpen);
                    gameFirstOpen = false;
                    if (!quoridorGameManager.runGame()) {
                        break;
                    }
                }
            }

        }


    }

}
