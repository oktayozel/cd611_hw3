package puzzles.games.dots_and_boxes_components;

import puzzles.core.User;

/*
 * Represents a user/player in the Dots and Boxes game.
 * Extends from the abstract User class.
 */

public class DotsAndBoxesUser extends User {
    private String id;
    // constructor to initialize username and id (like 1, 2)
    public DotsAndBoxesUser(String username, String id, boolean isThisABot) {
        super(username, isThisABot);
        this.id = id;
    }
    // helper to get P1 or P2
    public String getShortName() {
        return id;
    }
    
    @Override
    public String toString() {
        return getUsername() + " (" + getScore() + " points)";
    }

}