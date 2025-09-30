package puzzles.core;

import java.io.IOException;
import java.util.Properties;
import java.util.Arrays;
import java.util.List;
import java.io.InputStream;

public class Settings {
    private Properties props = new Properties();

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
    public int getMinBoardSize(String gameName){
        return Integer.parseInt(props.getProperty(gameName + "-minBoardSize","2"));
    }

    public int getMaxBoardSize(String gameName){
        return Integer.parseInt(props.getProperty(gameName + "-maxBoardSize","10"));
    }

    public List<String> getSupportedGames(){
        String games = props.getProperty("supportedGames","");
        return Arrays.asList(games.split(","));
    }
}
