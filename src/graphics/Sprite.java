//211602297 yuval cohen
package graphics;
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     * draws on the draw surface
     * @param d the draw surface
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
// notify the sprite that time has passed
    void timePassed();
    /**
     * Gets type.
     *
     * @return the type of the sprite
     */
    String getType();
}