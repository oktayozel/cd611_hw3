package puzzles.games.quoridor_components;
import puzzles.core.User;



public class QuoridorUser extends User {
    private String id;
    // constructor to initialize username and id (like 1, 2)
    public QuoridorUser(String username, String id) {
        super(username);
        this.id = id;
    }
    // helper to get P1 or P2
    public String getShortName() {
        return id;
    }
    
}