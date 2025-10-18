//21602297 yuval cohen
package objects;
import collision.HitListener;
import controller.GameLevel;
import collision.Counter;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game           the game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    /**
     * Hit event.
     *
     * @param beingHit the object being hit
     * @param hitter   the object that is the hitter
     * removes the ball from the game
     */
    public void hitEvent(Block beingHit, Ball hitter) {
            hitter.removeFromGame(this.game);
            hitter.removeHitListener(this);
            this.remainingBalls.decrease(1);
    }
}