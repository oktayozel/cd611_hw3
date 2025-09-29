package puzzles.games.dots_and_boxes_components;

public class DotsAndBoxesPlayer {
    private String name;
    private int score;

    public DotsAndBoxesPlayer(String name) {
        this.name = name;
        this.score = 0;
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
}
