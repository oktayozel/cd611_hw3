package puzzles.games.quoridor_components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import puzzles.core.*;
import puzzles.io.Animations;


/*
 * 
 * represents a bot player in the quoridor game, extends the quoridor user class
 * bot has all the rights that a user has but it is not saved into the leaderboard.
 */
public class QuoridorBot extends QuoridorUser implements ArtificialIntelligence {
    QuoridorBoard board;
    private final Random rng = new Random();

    public QuoridorBot(String username, String playerId) {
        super(username, playerId, true);
    }

    // evaluates the board situation
    @Override
    public void evaluateBoard() {
        // Implementation for evaluating the Quoridor board state
    }
    // calculates the next move depending on the heuristic
    @Override
    public void calculateNextMove() {
        // Implementation for calculating the next move
    }

    // executes the move on the board
    @Override
    public void makeMove() {
        List<String> dirs = new ArrayList<>(Arrays.asList("UP", "DOWN", "LEFT", "RIGHT"));
        Collections.shuffle(dirs, rng);

        for (String d : dirs) {
            if (board.movePlayer(this, d)) {
                Animations.displayAnimationWithSleep("bot_thinking",200);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("            " + getUsername() + " moves " + d);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Animations.displayAnimationWithSleep("bot_executing",100);
                return;
            }
        }

        // If we get here, all moves were invalid (surrounded by walls/edges/blocked).
        System.out.println(getUsername() + " cannot move this turn.");
    }
    // gets the current board in order to be able to make decisions about the board
    public void setBoard(QuoridorBoard board) {
        this.board = board;
    }
}