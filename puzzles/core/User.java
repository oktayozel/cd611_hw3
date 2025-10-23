package puzzles.core;
/*
 * This class represents a user in the sliding puzzle game.
 * It contains properties for the username and move count, along with methods to manage these properties.
 */
public class User{
    private String username;
    private String email;
    private int age;
    private String name;
    private String surname;
    private int moveCount;
    private int score;
    private boolean isThisABot;

    // Constructor to initialize the user with a username and zero move count.
    public User(String username, boolean isThisABot){
        this.username = username;
        this.isThisABot = isThisABot;
        this.moveCount = 0;
    }
    // Method to increment the move count by one.
    public void incrementMoveCount(){
        this.moveCount += 1;
    }
    // Method to reset the move count to zero.
    public void resetMoveCount(){
       this.moveCount = 0;
    }
    // Getter method for move count.
    public int getMoveCount(){
       return this.moveCount;
    }
    // Getter method for username.
    public String getUsername(){
        return username;
    }
    // getter method for the score
    public int getScore() {
        return score;
    }
    //getter method for increasing the score by 1.
    public void addPoint() {
        score++;
    }
    // sets the score equal to 0
    public void resetScore() {
        score = 0;
    }


}