package sliding_puzzle.core;


import java.util.HashMap;
import java.util.Map;

public abstract class LeaderBoard {
    private Map<String,Integer> LeaderBoard = new HashMap<>();

    public LeaderBoard() {
    }
    public abstract void loadLeaderBoard();
    public abstract void saveLeaderBoard();

}