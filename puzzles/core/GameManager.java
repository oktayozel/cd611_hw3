package puzzles.core;

/*
 * This abstract class manages the game state, including the move count.
 * It defines abstract methods for initializing the game, running the game loop, and checking for game completion.
 */
public abstract class GameManager{
    private int moveCount;
    private boolean timerRunning;
    private long startTime;
    private long endTime;
    // Constructor to initialize the game manager with a move count of zero.
    public GameManager(){
        this.moveCount = 0;
        this.timerRunning = false;

    }
    // Method to return the current move count.
    public int getMoveCount(){
        return moveCount;
    }

    public abstract void initGame(boolean gameFirstOpen);
    protected abstract void initializeBoard();
    
    protected abstract void initializePlayers(boolean gameFirstOpen);

    public abstract boolean runGame();
    public abstract boolean isGameEnd();


    public void startTimer() {
        startTime = System.currentTimeMillis();
        timerRunning = true;
    }

    public void stopTimer() {
        if (timerRunning) {
            endTime = System.currentTimeMillis();
            timerRunning = false;
        }
    }

    public int getElapsedTime() {
        if (timerRunning) {
            return (int)(System.currentTimeMillis() - startTime)/1000;
        } else {
            return (int)(endTime - startTime)/1000;
        }
    }
    
}