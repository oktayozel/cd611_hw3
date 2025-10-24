package puzzles.games.quoridor_components;

import puzzles.core.Cell;


/*
 * This class represents a cell in the Quoridor game.
 * it extends the abstract Cell class and includes properties for the edges of the cell 
 * It provides methods to maintain cell operations.
 */

public class QuoridorCell extends Cell{

    private boolean topWall; // has top wall
    private boolean bottomWall; // has bottom wall
    private boolean leftWall; // has left wall
    private boolean rightWall; // has right wall
    private boolean hasPlayer1;
    private boolean hasPlayer2;

    // constructor for the quoridor cell
    public QuoridorCell(int row, int col, String value) {
        super(row, col, new QuoridorPiece(value));
    }



    // getters and setters for the walls and players
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


    private String topWallOwner = "";   //check who created the wall
    private String leftWallOwner = "";  //check who created the wall

    // setters for the wall owners
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
