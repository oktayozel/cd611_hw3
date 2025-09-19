package sliding_puzzle.core;


public abstract class GameManager{
    public int moveCount;

    public GameManager(){
        this.moveCount = 0;
    }

    public int getMoveCount(){
        return moveCount;
    }

    public abstract void initGame(boolean gameFirstOpen);
    public abstract boolean runGame();
    public abstract boolean isGameEnd();

}