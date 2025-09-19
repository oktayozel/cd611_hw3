package sliding_puzzle.core;

public class User{
    private String username;
    private int moveCount;
    public User(String username){
        this.username = username;
        this.moveCount = 0;
    }
    public void incrementMoveCount(){
        this.moveCount += 1;
    }
    public void resetMoveCount(){
       this.moveCount = 0;
    }
    public int getMoveCount(){
       return this.moveCount;
    }
    public String getUsername(){
        return username;
    }
}