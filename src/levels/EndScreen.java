//211602297 yuval cohen
package levels;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import graphics.Animation;
import graphics.AnimationRunner;
import graphics.KeyPressStoppableAnimation;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private AnimationRunner ar;
    private GUI gui;
    private KeyboardSensor ks;
    private int levelNum;
    private int blockNum;
    private int score;
    private int ballsNum;

    /**
     * Instantiates a new End screen.
     *
     * @param ar        the ar
     * @param ks        the ks
     * @param gui       the gui
     * @param score     the score
     * @param ballsNum  the balls num
     * @param blocksNum the blocks num
     */
    public EndScreen(
            AnimationRunner ar, KeyboardSensor ks, GUI gui, int score, int ballsNum, int blocksNum) {

        this.gui = gui;
        this.ks = ks;
        this.ar = ar;
        this.blockNum = blocksNum;
        this.ballsNum = ballsNum;
        this.score = score;
    }
@Override
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Gets keyboard sensor.
     *
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.ks;
    }

    /**
     * Gets animation runner.
     *
     * @return the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return this.ar;
    }

    /**
     * Gets block number.
     *
     * @return the block number
     */
    public int getBlockNumber() {
        return this.blockNum;
    }

    /**
     * Gets balls number.
     *
     * @return the balls number
     */
    public int getBallsNumber() {
        return this.ballsNum;
    }


    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * doOneFrame.
     * @param d the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        if (this.getBlockNumber() == 0) {
            d.drawText(100, 300, "YOU WIN! Your score is " + Integer.toString(this.getScore()), 40);

        } else {
            d.drawText(100, 300, "GAME OVER. Your score is " + Integer.toString(this.getScore()), 40);
        }


    }

    /**
     * shouldStop.
     * signles when the program should stop and closes the gui to avoid bugs where the animation doesnt stop
     */
    @Override
    public boolean shouldStop() {
        return this.getKeyboardSensor().isPressed(KeyboardSensor.SPACE_KEY);
    }

    /**
     * Run.
     *
     */
    public void run() {
        Animation decoratedAnimation = new KeyPressStoppableAnimation(
                ks, KeyboardSensor.SPACE_KEY, this, getGui());
        ar.run(decoratedAnimation);
    }

}

