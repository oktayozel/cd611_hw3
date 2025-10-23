package puzzles.games.quoridor_components;

import puzzles.core.Cell;


public class QuoridorCell extends Cell{

    private boolean topWall;
    private boolean bottomWall;
    private boolean leftWall;
    private boolean rightWall;
    private boolean hasPlayer1;
    private boolean hasPlayer2;


    public QuoridorCell(int row, int col, String value) {
        super(row, col, new QuoridorPiece(value));
    }
    public boolean hasTopWall() {
        return topWall;
    }
    public void setTopWall(boolean topWall) {
        this.topWall = topWall;
    }
    public boolean hasBottomWall() {
        return bottomWall;
    }
    public void setBottomWall(boolean bottomWall) {
        this.bottomWall = bottomWall;
    }
    public boolean hasLeftWall() {
        return leftWall;
    }
    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }
    public boolean hasRightWall() {
        return rightWall;
    }
    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }
    public boolean hasPlayer1() {
        return hasPlayer1;
    }
    public void setHasPlayer1(boolean hasPlayer1) {
        this.hasPlayer1 = hasPlayer1;   
    }
    public boolean hasPlayer2() {
        return hasPlayer2;
    }
    public void setHasPlayer2(boolean hasPlayer2) {
        this.hasPlayer2 = hasPlayer2;
    }

    private String topWallOwner = "";   //check who create the wall
    private String leftWallOwner = ""; 

    public void setTopWallOwner(String owner) {
        this.topWallOwner = owner;
    }
    public void setLeftWallOwner(String owner) {
        this.leftWallOwner = owner;
    }
    public String getTopWallOwner() {
        return topWallOwner;
    }
    public String getLeftWallOwner() {
        return leftWallOwner;
    }

}
