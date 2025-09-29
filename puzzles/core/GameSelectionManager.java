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
    public GameSelectionManager(){
        this.dotsAndBoxesGameManager = new DotsAndBoxesGameManager();
        this.slidingPuzzleGameManager = new SlidingPuzzleGameManager();
        this.input = new Input();
    }
    public DotsAndBoxesGameManager getDotsAndBoxesGameManager(){
        return this.dotsAndBoxesGameManager;
    }
    public SlidingPuzzleGameManager getSlidingPuzzleGameManager(){
        return this.slidingPuzzleGameManager;
    }


    public void runSelectedGame() {

        int _selected_game = input.readIntOrExit(
            "Which game would you like to play? (\n [1] for sliding_puzzle \n [2] for dots_and_boxes) \n >>> ",
            1, 2
        );

        // ✅ 決定遊戲名稱字串
        String gameName = (_selected_game == 1) ? "sliding_puzzle" : "dots_and_boxes";

        // ✅ 建立 Output 並傳入遊戲名稱（這裡是關鍵改動）
        Output output = new Output(input, gameName);
        output.clearScreen(); // ← 顯示對應遊戲的 ASCII Logo

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
