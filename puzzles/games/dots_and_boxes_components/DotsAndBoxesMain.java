package puzzles.games.dots_and_boxes_components;

import java.util.Scanner;

// import puzzles.core.GameManager;

public class DotsAndBoxesMain {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DotsAndBoxesGameManager gameManager = new DotsAndBoxesGameManager(scanner);

        System.out.println("Hi! Welcome to Dots and Boxes!");
        DotsAndBoxesStart game = new DotsAndBoxesStart(scanner);
        gameManager.startDotsAndBoxes(game);
    }
}
