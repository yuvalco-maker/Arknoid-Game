//211602297 yuval cohen
package objects;
import collision.Counter;
import collision.HitListener;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the initial value
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Hit event.
     *
     * @param beingHit the object being hit
     * @param hitter   the object that is the hitter
     * adds five points to the counter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getRemovable()) {
            this.currentScore.increase(5);
            beingHit.removeHitListener(this);
        }

    }
}