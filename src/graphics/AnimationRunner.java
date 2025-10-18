//211602297 yuval cohen
package graphics;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import biuoop.GUI;
import collision.Counter;
import controller.GameLevel;
import controller.GameEnvironment;


/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private GameLevel runner;
    private GUI gui;
    private int framesPerSecond = 60;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter score;


    /**
     * Run.
     * runs one loop in 60 fps until it gets a single to stop
     * @param animation the animation
     */
    public void run(Animation animation) {
        this.gui = animation.getGui();
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
         while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    }