package puzzles.games.quoridor_components;
import puzzles.core.User;



public class QuoridorUser extends User {
    private String id;             //p1 and p2
    private int row, col;          //current position
    private int wallsRemaining;    //walls left

    public QuoridorUser(String username, String id, boolean isThisABot) {
        super(username, isThisABot);
        this.id = id;
        this.wallsRemaining = 10;
        this.row = -1;
        this.col = -1;
    }

    public String getShortName() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getWallsRemaining() {
        return wallsRemaining;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void useWall() {
        if (wallsRemaining > 0) {
            wallsRemaining--;
        }
    }

    public void reset() {
        this.wallsRemaining = 10;
        this.row = -1;
        this.col = -1;
    }

    @Override
    public String toString() {
        return getUsername() + " (" + getShortName() + ") at [" + row + "," + col + "] with " + wallsRemaining + " walls left" ;
    }

    public String getId() {
        return this.id;
    }
}
