//211602297 yuval cohen
package collision;
import objects.Ball;
import objects.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * @param beingHit the object being hit
     * @param hitter   the object that is the hitter
     * will activate a  behavior for each object
     */
// This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}

