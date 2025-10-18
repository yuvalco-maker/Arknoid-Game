//211602297 yuval cohen
package graphics;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the boolean for stopping the animation
     */
    boolean shouldStop();

    /**
     * Gets gui.
     *
     * @return the gui
     */
    GUI getGui();
}