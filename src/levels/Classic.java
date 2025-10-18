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
 * The type Classic.
 */
public class Classic implements LevelInformation {
    private static final int SPEED = 7;
    private static final int RADIUS = 5;
    private static final int RIGHTEDGE = 30;
    private static final int LEFTEDGE = 770;
    //max number of blocks 70
    private static final int BLOCKSNUM = 70;
    private static final int STARTX = 729;
    private static final int WIDTH = 50;
    private static final int HIGHT = 25;
    private static final int LIMIT = 410;
    private static final int STARTY = 300;
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
        return 360;
    }

    /**
     * levelName.
     * @return a string containing the name of the level
     */
    @Override
    public String levelName() {
        return "level name: Classic";
    }

    /**
     * getBackground.
     * @return a sprite of the background
     */
    @Override
    public Sprite getBackground() {
        Sprite backRound = new Block(new Point(0, 0), 800, 600, Color.BLUE.darker().darker());
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
        double x = LEFTEDGE - WIDTH - 1;
        double  dist;
        double start = x;
        double y = STARTY;
        int height = HIGHT;
        int width = WIDTH;
        double limit = LIMIT;
        int j = rand.nextInt(colors.length);

       // Create the lines of blocks, forming a triangle
        for (int i = 0; i < BLOCKSNUM; i++) {
            if (y - height <= LIMITY) {
                break;
            }

            Point upperLeft = new Point(x, y);
            Block block = new Block(upperLeft, width, height, colors[j]);
            blocks.add(block);

            // While the next block will be in a valid range, move the x value to create it
            if (x - width >= limit) {
                x -= width;
            } else {
                // Else, we reset the x, get the next color, and move to the next height
                if (j + 1 < colors.length) {
                    j += 1;
                } else {
                    j = 0;
                }
                x = start;
                y -= height;
                limit -= width;

                // Checks if the new height will be in the screen
                if (limit < RIGHTEDGE + WIDTH) {
                    break;
                }
            }
        }
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
