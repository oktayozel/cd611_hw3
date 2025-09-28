package puzzles.core;

/*
 * This abstract class manages the game state, including the move count.
 * It defines abstract methods for initializing the game, running the game loop, and checking for game completion.
 */
public abstract class GameManager{
    private int moveCount;
    // Constructor to initialize the game manager with a move count of zero.
    public GameManager(){
        this.moveCount = 0;
    }
    // Method to return the current move count.
    public int getMoveCount(){
        return moveCount;
    }

    public abstract void initGame(boolean gameFirstOpen);
    public abstract boolean runGame();
    public abstract boolean isGameEnd();

}