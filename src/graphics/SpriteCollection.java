//211602297 yuval cohen
package graphics;
import biuoop.DrawSurface;
import java.util.List;

import java.util.ArrayList;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private  ArrayList<Sprite> sprites = new ArrayList<>();

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {

    }

    /**
     * Add sprite.
     * adds a sprite to the array
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }


    /**
     * Notify all time passed.
     * use the time passed on every sprite
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(sprites);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all on.
     * Draws all sprites on the draw surface
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);

        }
        }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Sets sprites.
     *
     * @param copy the copy
     */
    public void setSprites(ArrayList<Sprite> copy) {
        this.sprites = copy;
    }



}
