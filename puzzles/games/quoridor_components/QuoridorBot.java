package puzzles.games.quoridor_components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import puzzles.core.*;
import puzzles.io.Animations;
public class QuoridorBot extends QuoridorUser implements ArtificialIntelligence {
    QuoridorBoard board;
    private final Random rng = new Random();

    public QuoridorBot(String username, String playerId) {
        super(username, playerId, true);
    }

    @Override
    public void evaluateBoard() {
        // Implementation for evaluating the Quoridor board state
    }

    @Override
    public void calculateNextMove() {
        // Implementation for calculating the next move
    }

    @Override
    public void makeMove() {
        List<String> dirs = new ArrayList<>(Arrays.asList("UP", "DOWN", "LEFT", "RIGHT"));
        Collections.shuffle(dirs, rng);

        for (String d : dirs) {
            if (board.movePlayer(this, d)) {
                Animations.displayAnimationWithSleep100("bot_thinking");
                System.out.println(getUsername() + " moves " + d);
                return;
            }
        }

        // If we get here, all moves were invalid (surrounded by walls/edges/blocked).
        System.out.println(getUsername() + " cannot move this turn.");
    }
    public void setBoard(QuoridorBoard board) {
        this.board = board;
    }
}