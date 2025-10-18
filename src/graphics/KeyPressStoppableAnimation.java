//211602297 yuval cohen
package graphics;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.GUI;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Boolean isAlreadyPressed = true;
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isKeyPressed;
    private boolean isAnimationEnded;
    private GUI gui;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param keyboardSensor the keyboard sensor
     * @param key            the key
     * @param animation      the animation
     * @param gui            the gui
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation, GUI gui) {
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.animation = animation;
        this.isKeyPressed = false;
        this.isAnimationEnded = false;
        this.gui = gui;
    }
    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.keyboardSensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        this.animation.doOneFrame(d);
        if (keyboardSensor.isPressed(key) && !this.isAlreadyPressed) {
            isKeyPressed = true;
        }
    }
    /**
     * Should stop boolean.
     *
     * @return the boolean for stopping the animation
     */

    @Override
    public boolean shouldStop() {
        if (animation.shouldStop() || (isKeyPressed && !isAnimationEnded)) {
            isAnimationEnded = true;
            return true;
        }
        return false;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */

    @Override
    public GUI getGui() {
        return this.gui;
    }
}
