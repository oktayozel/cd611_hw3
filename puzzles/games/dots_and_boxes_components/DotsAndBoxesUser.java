package puzzles.games.dots_and_boxes_components;

import puzzles.core.User;

public class DotsAndBoxesUser extends User {
    private String id;

    public DotsAndBoxesUser(String username, String id) {
        super(username);
        this.id = id;
    }

    public String getShortName() {
        return id;
    }

    @Override
    public String toString() {
        return getUsername() + " (" + getScore() + " points)";
    }

}