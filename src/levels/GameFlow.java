//211602297 yuval cohen
package levels;

import biuoop.GUI;
import collision.Counter;
import graphics.AnimationRunner;
import biuoop.KeyboardSensor;
import controller.GameLevel;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboard;
    private AnimationRunner animation;
    private GUI gui;
    private ArrayList<LevelInformation> levels;
    private Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar     the animation runner
     * @param ks     the keyboard sensor
     * @param gui    the gui
     * @param score  the score
     * @param levels the levels
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, Counter score, ArrayList<LevelInformation> levels) {
        this.gui = gui;
        this.keyboard = ks;
        this.animation = ar;
        this.levels = new ArrayList<LevelInformation>(levels);
        this.score = score;

    }

    /**
     * Instantiates a new Game flow.
     *
     * @param ar     the animation runner
     * @param ks     the keyboard sensor
     * @param gui    the gui
     * @param score  the score
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, Counter score) {
        this.gui = gui;
        this.keyboard = ks;
        this.animation = ar;
        this.levels = new ArrayList<LevelInformation>();
        this.score = score;
        levels.add(new Simple());
        levels.add(new Easy());
        levels.add(new Classic());

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
     * Get score counter.
     *
     * @return the counter
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard sensor
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
     * Gets levels.
     *
     * @return the levels
     */
    public ArrayList<LevelInformation> getLevels() {
        return this.levels;
    }


    /**
     * Run levels.
     * runs each level in a for loop adding 100 points to score when winning and initiates endScreen when losing
     * or completing all levels
     */
    public void runLevels() {
        boolean flag = true;
        List<LevelInformation> levels = new ArrayList<>(this.getLevels());

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animation, this.keyboard, this.gui, this.score);
            level.initialize();

            // Ass6Game the level until there are no more blocks or balls
            while (level.getBlocks().getValue() > 0 && level.getBalls().getValue() > 0) {
                level.run();
            }

            // Check if there are no more balls
            if (level.getBalls().getValue() == 0) {
                EndScreen lost = new EndScreen(this.getAnimation(), this.getKeyboard(), this.getGui(),
                        this.getScore().getValue(), level.getBalls().getValue(), level.getBlocks().getValue());
                lost.run();
                flag = false;
                this.getGui().close();


                break;  // Exit the loop and end the game
            }
            this.getScore().increase(100);

        }
        if (flag) {
            EndScreen win = new EndScreen(this.getAnimation(), this.getKeyboard(), this.getGui(),
                    this.getScore().getValue(), 1, 0);
            win.run();
            this.getGui().close();
        }

    }

}

