package graphics;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import collision.Counter;
import biuoop.Sleeper;
import controller.GameLevel;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSec;
    private int countFrom;
    private SpriteCollection sprites;
    private GUI gui;
    private boolean stop;
    private Sprite backRound;
    private Counter score;
    private GameLevel level;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     * @param gui          the gui
     * @param backRound    the back round
     * @param score        the score
     * @param level        the level
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen, GUI gui,
                              Sprite backRound, Counter score, GameLevel level) {
        this.numOfSec = numOfSeconds;
        this.countFrom = countFrom;
        this.sprites = gameScreen;
        this.gui = gui;
        this.stop = false;
        this.score = score;
        this.level = level;
    }

    /**
     * getGui.
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * doOneFrame.
     * @param d the draw surface
     * draws one frame
     */
    public void doOneFrame(DrawSurface d) {
        this.level.getLevel().getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        d.setColor(Color.WHITE.darker());
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(400, 15, this.score.fullvalueString(), 12);
        d.drawText(550, 15, this.level.getLevel().levelName(), 12);
    }

    /**
     * Count down.
     * creates an animation of the game screen witha countown
     */
    public void countDown() {
        Sleeper sleeper = new Sleeper();
        long millisecondsPerFrame = (long) (numOfSec / countFrom * 1000);
        int i = countFrom;
        while (!stop) {
            DrawSurface d = gui.getDrawSurface();
            long startTime = System.currentTimeMillis();
            String string = Integer.toString(i);
            if (i == 0) {
                string = "GO";
                stop = this.shouldStop();
            }
            this.doOneFrame(d);
            d.setColor(Color.black);
            d.drawText(400, 300, string, 70);
            this.getGui().show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            i--;
        }
    }

    /**
     * should stop.
     *
     * @return true
     */
    public boolean shouldStop() {
        return true;
    }
}
