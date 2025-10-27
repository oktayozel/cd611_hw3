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

    // determines which edge the bot aims for
    private boolean aimBottom() {
        String id = (getId() == null) ? "" : getId().toUpperCase();
        if (id.contains("1")) return true;   // P1 goes DOWN
        if (id.contains("2")) return false;  // P2 goes UP
        return false; // default is up for the bot
    }

    // cached, minimal “evaluation” state
    private String forwardDir = "DOWN";
    private int opponentRow = -1, opponentCol = -1;
    private String wallDir = "H"; // horizontal walls slows down the progression of the opponent
    private boolean tryMove = true;

    // function to evaluate the current state
    @Override
    public void evaluateBoard() {
        forwardDir = aimBottom() ? "DOWN" : "UP";
        wallDir = "H";
        QuoridorUser opp = board.getOpponent(this);
        int[] pos = board.getPlayerPosition(opp); 
        opponentRow = pos[0];
        opponentCol = pos[1];
    }

    //  pick what to do move:wall = 2:1 ratio meaning gives 2 moves for every wall placed,
    @Override
    public void calculateNextMove() {
        tryMove = (rng.nextInt(3) < 2); // 2/3 probability to move 1/3 probability to place a wall
        if (board.getRemainingWalls(this) <= 0) {
            tryMove = true;
        }
    }

    // execute: move toward finish else place a wall just ahead of opponent
    @Override
    public void makeMove() {
        evaluateBoard();
        calculateNextMove();

        if (tryMove) {
            String backward = aimBottom() ? "UP" : "DOWN";
            List<String> dirs = new ArrayList<>(Arrays.asList(forwardDir, "LEFT", "RIGHT", backward));

            for (String d : dirs) {
                if (board.movePlayer(this, d)) {
                    puzzles.io.Animations.displayAnimationWithSleep("bot_thinking", 200);
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("            " + getUsername() + " moves " + d);
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                    puzzles.io.Animations.displayAnimationWithSleep("bot_executing", 100);
                    return;
                }
            }
            // if no move worked, fall through to wall attempt
        }

        // Place a wall in front of the opponent
        int rows = board.getRowCount(), cols = board.getColCount();
        boolean oppAimsBottom = !aimBottom();
        int aheadRow = opponentRow + (oppAimsBottom ? +1 : -1);
        aheadRow = Math.max(0, Math.min(rows - 1, aheadRow));
        int aheadCol = Math.max(0, Math.min(cols - 1, opponentCol));

        if (board.claimWall(aheadRow, aheadCol, wallDir, this)) {
            puzzles.io.Animations.displayAnimationWithSleep("bot_thinking", 200);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("            " + getUsername() + " places a " + wallDir + " wall at (" + aheadRow + "," + aheadCol + ")");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            puzzles.io.Animations.displayAnimationWithSleep("bot_executing", 100);
            return;
        }

        // checks for the neigbors to see if the move is illegal
        for (int i = 0; i < 8; i++) {
            int r = Math.max(0, Math.min(rows - 1, aheadRow + rng.nextInt(3) - 1));
            int c = Math.max(0, Math.min(cols - 1, aheadCol + rng.nextInt(3) - 1));
            String d = (i % 2 == 0) ? "H" : "V";
            if (board.claimWall(r, c, d, this)) {
                puzzles.io.Animations.displayAnimationWithSleep("bot_thinking", 200);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("            " + getUsername() + " places a " + d + " wall at (" + r + "," + c + ")");
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                puzzles.io.Animations.displayAnimationWithSleep("bot_executing", 100);
                return;
            }
        }

        // bot is stuck
        System.out.println(getUsername() + " cannot move this turn.");
    }

    // gets the current board in order to be able to make decisions about the board
    public void setBoard(QuoridorBoard board) {
        this.board = board;
    }
    
}