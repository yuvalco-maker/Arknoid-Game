//211602297 yuval cohen
package graphics;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private GUI gui;
    private AnimationRunner ar;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k   the k
     * @param gui the gui
     * @param ar  the ar
     */
    public PauseScreen(KeyboardSensor k, GUI gui, AnimationRunner ar) {
        this.gui = gui;
        this.keyboard = k;
        this.stop = false;
        this.ar = ar;
    }

    /**
     * doOneFrame.
     * @param d the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * shouldStop.
     * @return the value in this.stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * getGui.
     * @return gui
     */
    @Override
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Run.
     */
    public void run() {
        Animation decoratedPauseScreen = new KeyPressStoppableAnimation(
                this.keyboard, KeyboardSensor.SPACE_KEY, this, this.getGui());
        ar.run(decoratedPauseScreen);
    }
}