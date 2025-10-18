//211602297 yuval cohen
import levels.RunGame;

import java.util.ArrayList;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     * this class gets the input removes all  invalid inputs and if there are any inputs left it sends them to RunGame
     * else it uses the default RunGame
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ArrayList<String> corrected = new ArrayList<>();
        for (String arg : args) {
            String[] parts = arg.split(" ");
            if (arg.equals("1") || arg.equals("2") || arg.equals("3")) {
                corrected.add(arg);
            }
        }

        if (corrected.isEmpty()) {
            RunGame game = new RunGame();
            game.run();
        } else {
            RunGame game = new RunGame(corrected);
            game.run();
        }

    }


}