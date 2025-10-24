package puzzles.games.quoridor_components;
import puzzles.core.User;


/*
 * 
 * represents a user in the quoridor game, extends the user class 
 */

public class QuoridorUser extends User {

    private String id;             //p1 and p2
    private int row, col;          //current position
    private int wallsRemaining;    //walls left

    // constructor for the quoridor user
    public QuoridorUser(String username, String id, boolean isThisABot) {
        super(username, isThisABot);
        this.id = id;
        this.wallsRemaining = 10;
        this.row = -1;
        this.col = -1;
    }

    // returns the short name of the user (id)
    public String getShortName() {
        return id;
    }
    // returns the current row position of the user, used in finish checks
    public int getRow() {
        return row;
    }
    // returns the current columns position of the user
    public int getCol() {
        return col;
    }

    // returns the number of walls remaining for the user
    public int getWallsRemaining() {
        return wallsRemaining;
    }
    // sets the current position of the user
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    // consumes one piece of wall
    public void useWall() {
        if (wallsRemaining > 0) {
            wallsRemaining--;
        }
    }

    // resets the user's position and walls remaining to initial state
    public void reset() {
        this.wallsRemaining = 10;
        this.row = -1;
        this.col = -1;
    }
    // overrides the tostring method to print the remaning walls as well as name and id of the user.
    @Override
    public String toString() {
        return getUsername() + " (" + getShortName() + ") at [" + row + "," + col + "] with " + wallsRemaining + " walls left" ;
    }
    // returns users id
    public String getId() {
        return this.id;
    }
}
