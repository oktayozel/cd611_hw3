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

        boolean tryMove = rng.nextBoolean(); //true->move false->create wall
        
        if (tryMove) {
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
        }

        else{
            for (int attempt = 0; attempt < 20; attempt++) {
                String direction = rng.nextBoolean() ? "H" : "V";
                int row = rng.nextInt(board.getRowCount());
                int col = rng.nextInt(board.getColCount());

                if (board.claimWall(row, col, direction, this)) {
                    Animations.displayAnimationWithSleep("bot_thinking", 200);
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("            " + getUsername() + " places a " + direction + " wall at (" + row + "," + col + ")");
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Animations.displayAnimationWithSleep("bot_executing", 100);
                    return;
                }
            }
        }

        // If we get here, all moves were invalid (surrounded by walls/edges/blocked).
        System.out.println(getUsername() + " cannot move this turn.");

    }
    public void setBoard(QuoridorBoard board) {
        this.board = board;
    }
    
}