package sliding_puzzle.core;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/*
 * This class manages the leaderboard for the sliding puzzle game.
 * It handles loading, saving, updating, and displaying the leaderboard.
 */
public class LeaderBoard {
    private Map<String, Integer> leaderBoard = new HashMap<>();
    private final String fileName = "data/leaderboard.txt";

    // Constructor to initialize the leaderboard.
    public LeaderBoard() {
    }
    // method to increment the game count for a user and save the updated leaderboard.
    public void incrementUser(String username) {
        leaderBoard.put(username, leaderBoard.getOrDefault(username, 0) + 1);
        saveLeaderBoard();
    }
    // method to load the leaderboard from a file.
    public void loadLeaderBoard() {
        leaderBoard.clear();
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String username = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    leaderBoard.put(username, score);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        
    }

    // method to save the leaderboard to a file.
    public void saveLeaderBoard() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> entry : leaderBoard.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // method to print the leaderboard to the console.
    public void printLeaderBoard(){
        saveLeaderBoard();
        loadLeaderBoard();
        System.out.println("Leaderboard:");
        System.out.println("Username |  Games Played");
        System.out.println("---------------------");
        
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(leaderBoard.entrySet());

        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.printf("%s\t\t%d\n", entry.getKey(), entry.getValue());
        }
    }

}