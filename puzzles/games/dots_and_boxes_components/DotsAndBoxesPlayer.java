package puzzles.games.dots_and_boxes_components;

public class DotsAndBoxesPlayer {
    private String name;
    private String id;
    private int score;

    public DotsAndBoxesPlayer(String name, String id) {
        this.name = name;
        this.score = 0;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addPoint() {
        score++;
    }

    public void resetScore() {
        score = 0;
    }

    @Override
    public String toString() {
        return name + " (" + score + " points)";
    }
    
    public String getShortName() { 
        return id; 
    }

}
