//211602297 yuval cohen
package levels;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.Counter;
import graphics.AnimationRunner;

import java.util.ArrayList;

/**
 * The type Run game.
 * this class handles the creation of the inputs that are needed for GameFlow to work correctly
 */
public class RunGame {
    private ArrayList<String> levelList;
    private GUI gui;
    private KeyboardSensor keyboard;
    private AnimationRunner animation;
    private Counter score;
    private ArrayList<LevelInformation> levels;

    /**
     * Instantiates a new Run game.
     * initiates the default level list
     */
    public RunGame() {
        this.gui = new GUI("arknoid", 800, 600);
        this.keyboard = this.gui.getKeyboardSensor();
        this.animation = new AnimationRunner();
        this.score = new Counter(0);
        this.levels = new ArrayList<LevelInformation>();

    }

    /**
     * Instantiates a new Run game.
     * creates a gui and a custom levle list based on input
     *
     * @param args the args
     */
    public RunGame(ArrayList<String> args) {
        this.gui = new GUI("arknoid", 800, 600);
        this.keyboard = this.gui.getKeyboardSensor();
        this.animation = new AnimationRunner();
        this.score = new Counter(0);
        this.levelList = new ArrayList<String>(args);
        this.levels = new ArrayList<LevelInformation>();
        for (String level : levelList) {
            if (level.equals("3")) {
                this.levels.add(new Classic());

            } else if (level.equals("1")) {
                this.levels.add(new Simple());
            } else if (level.equals("2")) {
                this.levels.add(new Easy());
            }
        }
    }

    /**
     * Gets levels.
     *
     * @return the levels
     */
    public ArrayList<LevelInformation> getLevels() {
        return this.levels;
    }

    /**
     * Gets level list.
     *
     * @return the level list
     */
    public ArrayList<String> getLevelList() {
        return this.levelList;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * Gets animation.
     *
     * @return the animation
     */
    public AnimationRunner getAnimation() {
        return this.animation;
    }

    /**
     * Run.
     * if we didn't get valid input we use run without a level list it will run the default level sequance.
     * or if we did get a valid input we run th e custom sequance
     */
    public void run() {
        if (this.getLevels().isEmpty()) {
            GameFlow game = new GameFlow(this.getAnimation(), this.getKeyboard(), this.getGui(), this.getScore());
            game.runLevels();
        } else {
            GameFlow game = new GameFlow(this.getAnimation(),
                    this.getKeyboard(), this.getGui(), this.getScore(), this.getLevels());
            game.runLevels();
        }

    }

}
