package sliding_puzzle.core;


public abstract class GameManager{
    public int moveCount;

    public GameManager(){
        this.moveCount = 0;
    }

    public int getMoveCount(){
        return moveCount;
    }

    public abstract boolean isGameOver();


}