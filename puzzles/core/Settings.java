package puzzles.core;

import java.io.IOException;
import java.util.Properties;
import java.util.Arrays;
import java.util.List;
import java.io.InputStream;

/* This class handles the game settings loaded from a properties file. Basically holds all configuration related to the game. */
public class Settings {
    private Properties props = new Properties();
    // Constructor to load settings from config.properties file.
    public Settings(){

        try (InputStream in = Settings.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IOException("config.properties not found on classpath");
            }
            props.load(in);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    // getter methods to retrieve min board sizes for different games.
    public int getMinBoardSize(String gameName){
        return Integer.parseInt(props.getProperty(gameName + "-minBoardSize","2"));
    }
    // getter methods to retrieve  max board sizes for different games.
    public int getMaxBoardSize(String gameName){
        return Integer.parseInt(props.getProperty(gameName + "-maxBoardSize","10"));
    }
    // getter method to retrieve supported games from the properties file.
    public List<String> getSupportedGames(){
        String games = props.getProperty("supportedGames","");
        return Arrays.asList(games.split(","));
    }
}
