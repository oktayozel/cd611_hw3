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

    // Every game has to have a initialize game function
    public abstract void initGame(boolean gameFirstOpen);

    // Every game has to have a initialize board function
    protected abstract void initializeBoard();

    // Every game has to have a initialize players function
    protected abstract void initializePlayers(boolean gameFirstOpen);

    // Every game has to have a run game function
    public abstract boolean runGame();

     // Every game has to have a function that checks if the game is finished or not.
    public abstract boolean isGameEnd();

    // Timer helper to be used in game managers. Should be initialized when the game is displayed
    public void startTimer() {
        startTime = System.currentTimeMillis();
        timerRunning = true;
    }

    //Timer helper to be used in game managers. Should be used to stop the timer when game starts and resumes.
    public void stopTimer() {
        if (timerRunning) {
            endTime = System.currentTimeMillis();
            timerRunning = false;
        }
    }

    // Timer helper to return the elapsed time to display. Returns in seconds.
    public int getElapsedTime() {
        if (timerRunning) {
            return (int)(System.currentTimeMillis() - startTime)/1000;
        } else {
            return (int)(endTime - startTime)/1000;
        }
    }
    
}