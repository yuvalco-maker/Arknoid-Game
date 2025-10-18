//211602297 yuval cohen
package levels;

import collision.Velocity;
import graphics.Sprite;
import objects.Block;
import objects.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * the type Simple.
 */
public class Simple implements LevelInformation {
    private static final int SPEED = 6;
    private static final int RADIUS = 5;
    private static final int RIGHTEDGE = 770;
    private static final int LEFTEDGE = 30;
    //max number of blocks 70
    private static final int BLOCKSNUM = 70;
    private static final int STARTX = 729;
    private static final int WIDTH = 70;
    private static final int HIGHT = 25;
    private static final int LIMIT = 410;
    private static final int STARTY = 400;
    private static final int LIMITY = 15;
    private Color[] colors = {Color.red, Color.orange, Color.yellow, Color.green,
            Color.blue, Color.pink, Color.magenta};
    private Color[] colorsForBall = {Color.red, Color.orange, Color.yellow, Color.green,
            Color.pink, Color.magenta};

    /**
     * number of balls.
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * initialBallsVelocities.
     * @return an arrayed list of each balls velocity
     */
    @Override
    public ArrayList<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<Velocity>();
        Velocity velo1 = new Velocity(5, 0);
        Velocity velo2 = new Velocity(5, 45);
        Velocity velo3 = new Velocity(5, -45);
        velocities.add(velo1);
        velocities.add(velo2);
        velocities.add(velo3);
        return velocities;
    }


    /**
     * paddleSpeed.
     * @return the speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        return SPEED;
    }

    /**
     * paddleWidth.
     * @return the width of the paddle
     */
    @Override
    public int paddleWidth() {
        return 200;
    }

    /**
     * levelName.
     * @return a string containing the name of the level
     */
    @Override
    public String levelName() {
        return "level name: Simple";
    }

    /**
     * getBackground.
     * @return a sprite of the background
     */
    @Override
    public Sprite getBackground() {
        Sprite backRound = new Block(new Point(0, 0), 800, 600, Color.DARK_GRAY);
        return backRound;
    }

    /**
     * blocks.
     * @return an arrayList of blocks in a pattern
     */
    @Override
    public ArrayList<Block> blocks() {
        Random rand = new Random();
        ArrayList<Block> blocks = new ArrayList<Block>();
        //creates blocks that will cover the screen edges and adds them to the game
        Block[] edges = new Block[]{
                new Block(new Point(0, 0), 30, 600, Color.gray, false),
                new Block(new Point(0, 0), 800, 30, Color.gray, false),
                new Block(new Point(770, 0), 30, 600, Color.gray, false),

        };
        for (Block b : edges) {
            blocks.add(b);
        }
        Block block = new Block(new Point(400, 200), WIDTH, HIGHT, Color.RED);
        blocks.add(block);

        //create a block that removes balls
        Block death = new Block(
                new Point(0, 600), 800, 30, Color.gray, false, true);
        blocks.add(death);
        return blocks;
    }

    /**
     * number of blocks to removes.
     * @return the number of removable blocks
     */
    @Override
    public int numberOfBlocksToRemove() {
        //5 is the number of edges
        return blocks().size() - 4;
    }
}
