package puzzles.games.dots_and_boxes_components;

import java.util.Scanner;

public class DotsAndBoxesGameManager {
    private Scanner scanner;
    private boolean isRunning = true;
    private DotsAndBoxesStart game;

    public void initGame(boolean firstTime) {
        if (firstTime) {
            System.out.println("Welcome to Dots and Boxes!");
        } 
        else {
            System.out.println("\n Starting a new round...");
        }
        game = new DotsAndBoxesStart(scanner);
        game.reset(); 
        isRunning = true; //ensure game reload every time
    }

    public DotsAndBoxesGameManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startDotsAndBoxes(DotsAndBoxesStart game) {
        while (isRunning) {
            game.setup();
            game.play();
            game.showResult();
            promptNextAction(game);
        }
    }

    private void promptNextAction(DotsAndBoxesStart currentGame) {
        System.out.println("\nWhat would you like to do next?");
        System.out.println("1. Play again");
        System.out.println("2. Quit");

        int choice = -1;
        while (choice < 1 || choice > 2) {
            System.out.print("Enter your choice (1 or 2): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }

        if (choice == 1) {
            game.reset();
        } else {
            isRunning = false;
            System.out.println("Thanks for playing! Goodbye! ");
        }

    }

    public boolean runGame() {
        while (isRunning) {
            game.setup();
            game.play();
            game.showResult();
            promptNextAction(game);
        }
        return askReplay(); // check play again
    }

    private boolean askReplay() {
        System.out.println("Would you like to play another round?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = -1;
        while (choice < 1 || choice > 2) {
            System.out.print("Enter your choice (1 or 2): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }
        return choice == 1;
    }

    
}
