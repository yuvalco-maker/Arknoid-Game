package objects;

import collision.HitListener;
import controller.GameLevel;
import collision.Counter;


/**
 * The type Block remover.
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter removedBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.

    /**
     *  hitEvent.
     * @param beingHit the object being hit
     * @param hitter   the object that is the hitter
     * removes the block from the game
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getRemovable()) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.removedBlocks.decrease(1);
        }
    }
}