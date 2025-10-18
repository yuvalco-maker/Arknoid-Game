//211602297 yuval cohen
package levels;

import objects.Block;
import collision.Velocity;

import java.util.ArrayList;
import java.util.List;

import graphics.Sprite;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return list of velocities
     */
// The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the width of the paddle
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return string containing the level name
     */
// the level name will be displayed at the top of the screen.
    String levelName();

    /**
     * Gets background.
     *
     * @return the background
     */
// Returns a sprite with the background of the level
    Sprite getBackground();

    /**
     * Blocks array list.
     *
     * @return the array list of the blocks in the level
     */
// The Blocks that make up this level, each block contains
    // its size, color and location.
    ArrayList<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     * @return the number of blocks that needs to be destroyed in order to win
     */
// Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}