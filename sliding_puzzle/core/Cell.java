package sliding_puzzle.core;

public class Cell{
    private final int rowIndex;
    private final int colIndex;
    private String value;

    public Cell(int rowIndex, int colIndex, String value){
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.value = value;
    }

    public int getRowIndex(){
        return rowIndex;
    }

    public int getColIndex(){
        return colIndex;
    }

    public String getValue() {
        return value; 
    }

    public void setValue(String value) {
        this.value = value;
    }

}   