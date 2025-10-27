package puzzles.core;

import java.io.*;
import java.util.*;

/*
 * This is a leaderboard class which holds the data and stats of the so far played games.
 * Saves the data to data/leaderboard.txt which mocks a database basically and allows 
 * for the retrieval of the data for the upcoming runs.
 */


public class LeaderBoard {
    // holds the hashmap after loading from the file. a username and a stats object.
    private Map<String, Stats> leaderboard = new HashMap<>();
    private final String fileName = "data/leaderboard.txt";

    // constructor
    public LeaderBoard() { 
        loadLeaderBoard();
    }



    // return the stats that is related to that username. if a record does not exist creates an empty record in the hashmap and saves to the db.
    private Stats get(String user) {
        Stats s = leaderboard.get(user);
        if (s == null) {
            s = new Stats(user);
            leaderboard.put(user, s);
        }
        return s;
    }
    // increments the total games played by 1
    public void incrementTotal(String user) {
        Stats s = get(user);
        s.total++;
        saveLeaderBoard();
    }

    // increments sliding puzzles played by 1 and total puzzles played .
    public void incrementSlidingPuzzlesPlayed(String user) {
        Stats s = get(user);
        s.total++;
        s.slidingPlayed++;
        saveLeaderBoard();
    }
    // increments sliding puzzles played by 1 and total puzzles played .
    public void incrementDotsAndBoxesPlayed(String user) {
        Stats s = get(user);
        s.total++;
        s.dnbPlayed++;
        saveLeaderBoard();
    }

    // increments quoridor played by 1 and total puzzles played
    public void incrementQuoridorPlayed(String user) {
        Stats s = get(user);
        s.total++;
        s.quoridorPlayed++;
        saveLeaderBoard();
    }

    public void recordQuoridorResult(String user, boolean win) {
        Stats s = get(user);
        s.total++;
        s.quoridorPlayed++;
        if (win) s.quoridorWins++;
        else s.quoridorLoses++;
        saveLeaderBoard();
    }

    // saves the game as a win or loss for multiplayer games.
    public void recordDotsAndBoxesResult(String user, boolean win) {
        Stats s = get(user);
        s.total++;
        s.dnbPlayed++;
        if (win) s.dnbWins++;
        else s.dnbLoses++;
        saveLeaderBoard();
    }

    // loads the leaderboard from the mock database.
    public void loadLeaderBoard() {
        leaderboard.clear();
        File f = new File(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length != 9) continue;
                Stats s = new Stats(p[0].trim());
                s.total        = Integer.parseInt(p[1].trim());
                s.dnbPlayed    = Integer.parseInt(p[2].trim());
                s.slidingPlayed= Integer.parseInt(p[3].trim());
                s.dnbWins      = Integer.parseInt(p[4].trim());
                s.dnbLoses     = Integer.parseInt(p[5].trim());
                s.quoridorPlayed  = Integer.parseInt(p[6].trim());
                s.quoridorWins    = Integer.parseInt(p[7].trim());
                s.quoridorLoses   = Integer.parseInt(p[8].trim());
                leaderboard.put(s.username, s);
            }
        } catch (IOException ignored) { }
    }
    // writes the leaderboard to the mock database.
    public void saveLeaderBoard() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Stats s : leaderboard.values()) {
                bw.write(
                    s.username + "," +
                    s.total + "," +
                    s.dnbPlayed + "," +
                    s.slidingPlayed + "," +
                    s.dnbWins + "," +
                    s.dnbLoses + "," +
                    s.quoridorPlayed + "," +
                    s.quoridorWins + "," +
                    s.quoridorLoses
                );
                bw.newLine();
            }
        } catch (IOException ignored) { }
    }

    // displays the leaderboard values ordere by the total number of games played in descending order.
    public void printLeaderBoard() {
        List<Stats> list = new ArrayList<>(leaderboard.values());

        Collections.sort(list, new Comparator<Stats>() {
            @Override public int compare(Stats a, Stats b) {
                int cmp = Integer.compare(b.total, a.total);
                if (cmp != 0) return cmp;

                int aWins = a.dnbWins + a.quoridorWins;
                int bWins = b.dnbWins + b.quoridorWins;
                cmp = Integer.compare(bWins, aWins);
                if (cmp != 0) return cmp;

                return a.username.compareToIgnoreCase(b.username); // deterministic tie-breaker
            }
        });

        System.out.println("Leaderboard:");
        System.out.println("Username   Total   D&B   SlidingP   D&B_Win   D&B_Loss   Quoridor   Q_Win   Q_Loss");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (Stats s : list) {
            System.out.printf("%-8s     %d       %d         %d       %d        %d       %d        %d      %d\n", s.username, s.total, s.dnbPlayed, s.slidingPlayed, s.dnbWins, s.dnbLoses, s.quoridorPlayed, s.quoridorWins, s.quoridorLoses);
        }
    }

    // a helper class in order to hold the statistics.
    private static class Stats {
        String username;
        int total;
        int dnbPlayed;
        int slidingPlayed;
        int dnbWins;
        int dnbLoses;
        int quoridorPlayed;
        int quoridorWins;
        int quoridorLoses;

        Stats(String username) { 
            this.username = username;
        }
    }


}
