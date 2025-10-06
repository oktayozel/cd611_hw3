package puzzles.core;

import java.io.IOException;
import java.util.Properties;
import java.util.Arrays;
import java.util.List;
import java.io.InputStream;

public class Settings {
    private Properties props = new Properties();
    // constructor for the properties file.
    public Settings(){

        try (InputStream in = Settings.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IOException();
            }
            props.load(in);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    // gets the minimum board size for the given game name
    public int getMinBoardSize(String gameName){
        return Integer.parseInt(props.getProperty(gameName + "-minBoardSize","2"));
    }

    // gets the maximum board size for the given game name
    public int getMaxBoardSize(String gameName){
        return Integer.parseInt(props.getProperty(gameName + "-maxBoardSize","10"));
    }
    // returns available sub games in order to show in the main screen.
    public List<String> getSupportedGames(){
        String games = props.getProperty("supportedGames","");
        return Arrays.asList(games.split(","));
    }
}
