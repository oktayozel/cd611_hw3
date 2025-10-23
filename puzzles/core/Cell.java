package puzzles.core;

import puzzles.games.dots_and_boxes_components.DotsAndBoxesUser;

/*
 * This abstract class represents a cell in the sliding puzzle game.
 * It contains properties for the cell's row and column indices, as well as its value.
 * It provides methods to access and modify these properties.
 */
public abstract class Cell{
    private final int rowIndex;
    private final int colIndex;
    private DotsAndBoxesUser owner = null;
    private Piece piece;

    // Constructor to initialize the cell with row index, column index, and value.
    public Cell(int rowIndex, int colIndex, Piece piece){
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.piece = piece;
    }
    // Getter methods for row index, column index, and value.
    public int getRowIndex(){
        return rowIndex;
    }
    // Getter method for column index.
    public int getColIndex(){
        return colIndex;
    }

    // Getter method for cell value.
    public String getValue() {
        return piece.getValue();
    }

    // Setter method for cell value.
    public void setValue(String value) {
        this.piece.setValue(value);
    }
    // Override toString method to return the cell's value as a string and if null, return a space.
    @Override
    public String toString() {
        return piece == null || piece.getValue() == null ? " " : piece.toString();
    }
    // Sets the owner of the cell for dots and boxes type of games
    public void setOwner(DotsAndBoxesUser player) {
        this.owner = player;
        setValue(player.getShortName());
    }
    // helper function to get the owner of the cell
    public DotsAndBoxesUser getOwner() {
        return owner;
    }

}   