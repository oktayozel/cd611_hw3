package puzzles.games.sliding_puzzle_components;
import puzzles.core.GameManager;
import puzzles.core.LeaderBoard;
import puzzles.io.Input;
import puzzles.io.Output;
import puzzles.core.Settings;

public class QuoridorGameManager extends GameManager {
    
    private QuoridorBoard board;
    private Output output;
    private Input input;
    private LeaderBoard leaderBoard;
    private Settings settings;
    
    public QuoridorGameManager((Settings settings) {
        super();
        this.input  = new Input();
        this.output = new Output(this.input, "quoridor");
        this.leaderBoard = new LeaderBoard();
        this.settings = new Settings();
    }





    
}
