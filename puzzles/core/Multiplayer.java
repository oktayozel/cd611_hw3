package puzzles.core;



/*
 * Interface for multiplayer games. A multiplayer game manager must implement this interface
 */

public interface Multiplayer {
    
    public static final int playerCount = 2;

    public void switchPlayer();
    
}
