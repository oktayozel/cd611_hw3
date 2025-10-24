package puzzles.io;


/*
 * 
 * Animation sequences for the game.
 * seperated from the output class in order to keep the code organized and maintainable.
 */
public class Animations {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_ORANGE = "\u001B[38;5;208m";

    private static final String[] openingAnimation = {
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    ANSI_BLUE + "              ,---------------------------," + ANSI_RESET,
    ANSI_BLUE + "              |  /---------------------\\  |" + ANSI_RESET,
    ANSI_BLUE + "              | |                       | |" + ANSI_RESET,
    ANSI_BLUE + "              | |   " + ANSI_GREEN + "   Welcome to       " + ANSI_BLUE + "| |" + ANSI_RESET,
    ANSI_BLUE + "              | |    " + ANSI_YELLOW + "    CS-611         " + ANSI_BLUE + "| |" + ANSI_RESET,
    ANSI_BLUE + "              | |     " + ANSI_CYAN + "   Games          " + ANSI_BLUE + "| |" + ANSI_RESET,
    ANSI_BLUE + "              | |                       | |" + ANSI_RESET,
    ANSI_BLUE + "              |  \\_____________________/  |" + ANSI_RESET,
    ANSI_BLUE + "              |___________________________|" + ANSI_RESET,
    ANSI_CYAN + "            ,---\\_____     []     _______/------," + ANSI_RESET,
    ANSI_CYAN + "          /         /______________\\           /|" + ANSI_RESET,
    ANSI_CYAN + "        /___________________________________ /  | ___" + ANSI_RESET,
    ANSI_CYAN + "        |                                   |   |    )" + ANSI_RESET,
    ANSI_CYAN + "        |  _ _ _                 [-------]  |   |   (" + ANSI_RESET,
    ANSI_CYAN + "        |  o o o                 [-------]  |  /    _)_"+ ANSI_RESET,
    ANSI_CYAN + "        |__________________________________ |/     /  /" + ANSI_RESET,
    ANSI_CYAN + "    /-------------------------------------/|      ( )/" + ANSI_RESET,
    ANSI_CYAN + "  /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /" + ANSI_RESET,
    ANSI_CYAN + "/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /" + ANSI_RESET,
    ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET,
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",
    };


    private static final String[] closingAnimation = {
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    ANSI_BLUE + "              ,---------------------------," + ANSI_RESET,
    ANSI_BLUE + "              |  /---------------------\\  |" + ANSI_RESET,
    ANSI_BLUE + "              | |                       | |" + ANSI_RESET,
    ANSI_BLUE + "              | |   " + ANSI_GREEN + "   See              " + ANSI_BLUE + "| |" + ANSI_RESET,
    ANSI_BLUE + "              | |    " + ANSI_YELLOW + "     you           " + ANSI_BLUE + "| |" + ANSI_RESET,
    ANSI_BLUE + "              | |     " + ANSI_CYAN + "   later!         " + ANSI_BLUE + "| |" + ANSI_RESET,
    ANSI_BLUE + "              | |                       | |" + ANSI_RESET,
    ANSI_BLUE + "              |  \\_____________________/  |" + ANSI_RESET,
    ANSI_BLUE + "              |___________________________|" + ANSI_RESET,
    ANSI_CYAN + "            ,---\\_____     []     _______/------," + ANSI_RESET,
    ANSI_CYAN + "          /         /______________\\           /|" + ANSI_RESET,
    ANSI_CYAN + "        /___________________________________ /  | ___" + ANSI_RESET,
    ANSI_CYAN + "        |                                   |   |    )" + ANSI_RESET,
    ANSI_CYAN + "        |  _ _ _                 [-------]  |   |   (" + ANSI_RESET,
    ANSI_CYAN + "        |  o o o                 [-------]  |  /    _)_"+ ANSI_RESET,
    ANSI_CYAN + "        |__________________________________ |/     /  /" + ANSI_RESET,
    ANSI_CYAN + "    /-------------------------------------/|      ( )/" + ANSI_RESET,
    ANSI_CYAN + "  /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /" + ANSI_RESET,
    ANSI_CYAN + "/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /" + ANSI_RESET,
    ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET,
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",    
    "                                             ",
    };
    private static final String[] botThinkingAnimation = {
    "#                                            ",
    "##    T                                      ",
    "###                                          ",
    "####     H                                   ",
    "#####                                        ",
    "######                                       ",
    "#######      I                               ",
    "########                                     ",
    "#########      N                             ",
    "##########                                   ",
    "###########                                  ",
    "############      K                          ",
    "#############                                ",
    "##############                               ",
    "###############       I                      ",
    "################                             ",
    "#################                            ",
    "##################                           ",
    "###################       N                  ",
    "####################                         ",
    "#####################                        ",
    "######################                       ",
    "#######################       G    .....     ",
    "########################                     ",
    "#########################                    ",
    };

    

private static final String[] botExecutingAnimation = {
    "                              #########################",
    "                   E           ########################",
    "                                #######################",
    "                     X           ######################",
    "                                  #####################",
    "                       E           ####################",
    "                                    ###################",
    "                         C           ##################",
    "                                      #################",
    "                           U           ################",
    "                                        ###############",
    "                             T           ##############",
    "                                          #############",
    "                                           ############",
    "                                 I          ###########",
    "                                             ##########",
    "                                              #########",
    "                                   N           ########",
    "                                                #######",
    "                                                 ######",
    "                                      G....       #####",
    "                                                   ####",
    "                                                    ###",
    "                                                     ##",
    "                                                      #",
};

    // constructor
    public Animations(){ 

    }
    // prints the input string with a delay between each line, delay is taken as an input via sleepDuration
    public static void displayAnimationWithSleep(String animationType, int sleepDuration){
        String[] animation = null;
        if (animationType.equals("opening")) {
            animation = openingAnimation;
        }
        if (animationType.equals("closing")) {
            animation = closingAnimation;
        }
        if (animationType.equals("bot_thinking")) {
            animation = botThinkingAnimation;
        }
        if (animationType.equals("bot_executing")) {
            animation = botExecutingAnimation;
        }

        for(String line : animation){
            System.out.println(line);
            try {
                Thread.sleep(sleepDuration); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
