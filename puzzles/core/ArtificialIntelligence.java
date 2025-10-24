package puzzles.core;

/*
 * Interface for Artificial Intelligence implementations in puzzle games.
 * An intelligent bot opponent should be able to evaluate the game, decide for the next move and execute it.
 */
public interface ArtificialIntelligence {

    public void evaluateBoard();
    public void calculateNextMove();
    public void makeMove();

} 
