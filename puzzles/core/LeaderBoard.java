package puzzles.core;

import java.io.*;
import java.util.*;

/*
 * 
 * This class manages the common leaderboard for the games.
 */
public class LeaderBoard {
    // holds the hashmap after loading from the file. a username and a stats object.
    private Map<String, Stats> leaderboard = new HashMap<>();
    private final String fileName = "data/leaderboard.txt";
    
    // constructor to load the leaderboard from the file.
    public LeaderBoard() { 
        loadLeaderBoard();
    }
    // helper to get the stats object for a user creating it if it doesn't exist
    private Stats get(String user) {
        Stats s = leaderboard.get(user);
        if (s == null) {
            s = new Stats(user);
            leaderboard.put(user, s);
        }
        return s;
    }
    // increments the total games played for a user and saves the leaderboard.
    public void incrementTotal(String user) {
        Stats s = get(user);
        s.total++;
        saveLeaderBoard();
    }
    // increments the sliding puzzles played for a user and saves the leaderboard.
    public void incrementSlidingPuzzlesPlayed(String user) {
        Stats s = get(user);
        s.total++;
        s.slidingPlayed++;
        saveLeaderBoard();
    }
    // increments the dots and boxes played for a user and saves the leaderboard.
    public void incrementDotsAndBoxesPlayed(String user) {
        Stats s = get(user);
        s.total++;
        s.dnbPlayed++;
        saveLeaderBoard();
    }
    // records the result of a dots and boxes game for a user, updating wins or losses accordingly and saves the leaderboard.
    public void recordDotsAndBoxesResult(String user, boolean win) {
        Stats s = get(user);
        s.total++;
        s.dnbPlayed++;
        if (win) s.dnbWins++;
        else s.dnbLoses++;
        saveLeaderBoard();
    }

    // helper to load the leaderboard from the file into the hashmap
    public void loadLeaderBoard() {
        leaderboard.clear();
        File f = new File(fileName);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length != 6) continue;
                Stats s = new Stats(p[0].trim());
                s.total        = Integer.parseInt(p[1].trim());
                s.dnbPlayed    = Integer.parseInt(p[2].trim());
                s.slidingPlayed= Integer.parseInt(p[3].trim());
                s.dnbWins      = Integer.parseInt(p[4].trim());
                s.dnbLoses     = Integer.parseInt(p[5].trim());
                leaderboard.put(s.username, s);
            }
        } catch (IOException ignored) { }
    }
    // helper to save the current hashmap into the file
    public void saveLeaderBoard() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Stats s : leaderboard.values()) {
                bw.write(
                    s.username + "," +
                    s.total + "," +
                    s.dnbPlayed + "," +
                    s.slidingPlayed + "," +
                    s.dnbWins + "," +
                    s.dnbLoses
                );
                bw.newLine();
            }
        } catch (IOException ignored) { }
    }

    // prints out the leaderboard to the console sorted by total games played in descending order
    public void printLeaderBoard() {
        List<Stats> list = new ArrayList<>(leaderboard.values());
        Collections.sort(list, new Comparator<Stats>() {
            @Override public int compare(Stats a, Stats b) {
                if (b.total != a.total) return b.total - a.total; // desc by total
                return a.username.compareToIgnoreCase(b.username);
            }
        });

        System.out.println("Leaderboard:");
        System.out.println("Username   Total   D&B   SlidingP   D&B_Win   D&B_Loss");
        System.out.println("--------------------------------------------------------------");
        for (Stats s : list) {
            System.out.printf("%-8s     %d       %d         %d       %d        %d \n", s.username, s.total, s.dnbPlayed, s.slidingPlayed, s.dnbWins, s.dnbLoses);
        }
    }

    // small class to hold stats for a user
    private static class Stats {
        String username;
        int total;
        int dnbPlayed;
        int slidingPlayed;
        int dnbWins;
        int dnbLoses;

        Stats(String username) { 
            this.username = username;
        }
    }


}
