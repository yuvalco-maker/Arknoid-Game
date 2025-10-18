//211602297 yuval cohen
package controller;

import graphics.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import objects.BlockRemover;

import collision.Counter;
import graphics.SpriteCollection;
import collision.Collidable;
import graphics.Sprite;
import objects.ScoreTrackingListener;
import objects.Block;
import objects.Paddle;
import objects.Point;
import objects.Ball;
import objects.BallRemover;
import graphics.AnimationRunner;
import graphics.PauseScreen;
import biuoop.KeyboardSensor;
import graphics.CountdownAnimation;
import levels.LevelInformation;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private static final int SPEED = 6;
    private static final int RADIUS = 5;
    private static final int RIGHTEDGE = 30;
    private static final int LEFTEDGE = 770;
    //max number of blocks 70
    private static final int BLOCKSNUM = 70;
    private static final int STARTX = 729;
    private static final int WIDTH = 50;
    private static final int HIGHT = 25;
    private static final int LIMIT = 410;
    private static final int STARTY = 335;
    private static final int LIMITY = 15;
    private static final int NUMOFBALLS = 3;
    private Counter balls;
    private Counter blocks;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private BlockRemover remover;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTracking;
    private AnimationRunner runner;
    private boolean running = true;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    // a list of colors for blocks
    private Color[] colors = {Color.red, Color.orange, Color.yellow, Color.green,
            Color.blue, Color.pink, Color.magenta};
    //  a list of colors for balls
    private Color[] colorsForBall = {Color.red, Color.orange, Color.yellow, Color.green,
            Color.pink, Color.magenta};


    /**
     * Instantiates a new Game level.
     *
     * @param level     the level
     * @param animation the animation
     * @param keyboard  the keyboard
     * @param gui       the gui
     * @param score     the score
     */
    public GameLevel(LevelInformation level, AnimationRunner animation,
                     KeyboardSensor keyboard, GUI gui, Counter score) {
        this.level = level;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.blocks = new Counter(this.level.numberOfBlocksToRemove());
        this.balls = new Counter(NUMOFBALLS);
        this.ballRemover = new BallRemover(this, this.balls);
        this.score = score;
        this.scoreTracking = new ScoreTrackingListener(score);
        this.runner = animation;
        this.keyboard = keyboard;
        this.blocks = new Counter(this.level.numberOfBlocksToRemove());


    }

    /**
     * getGui.
     * @return this gui
     */

    public GUI getGui() {
        return gui;
    }

    /**
     * Add collidable.
     * adds the object to the game environment
     *
     * @param c the collectable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     * adds sprite to the game environment
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize.
     * creates all the objects that are needed for the game
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        //creates the paddle and adds it to the game environment
        Block base = new Block(new Point(400 - this.level.paddleWidth() / 2,
                580), level.paddleWidth(), 15, Color.cyan, false);
        Paddle pad = new Paddle(gui, level.paddleSpeed(), base);
        pad.addToGame(this);
        Random rand = new Random();
        int ballX = 425;
        int ballY = 500;
        //creates two balls  and adds them to the game
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Point center = new Point(ballX, 500);
            Ball ball = new Ball(center, RADIUS, colorsForBall[rand.nextInt(colorsForBall.length)],
                    (int) level.initialBallVelocities().get(i).getDy(),
                    (int) level.initialBallVelocities().get(i).getDx(),
                    this.environment);
            ball.addToGame(this);
        }
        this.remover = new BlockRemover(this, this.blocks);
        ArrayList<Block> blocks = level.blocks();
        for (Block b : blocks) {
            b.addToGame(this);
        }
        ArrayList<Collidable> copy = new ArrayList<>(this.getEnvironment().getCollidables());
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i).getType().equals("block")) {
                Block block = (Block) copy.get(i);
                block.addHitListener(this.remover);
                if (block.getRemovable()) {
                    block.addHitListener(this.scoreTracking);
                }
                if (block.getDeathRegion()) {
                    block.addHitListener(ballRemover);
                }
            }
        }

    }


    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            PauseScreen pause = new PauseScreen(this.keyboard, this.getGui(), this.runner);
            pause.run();
        }
        this.level.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        d.setColor(Color.WHITE.darker());
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(400, 15, this.score.fullvalueString(), 12);
        d.drawText(550, 15, this.level.levelName(), 12);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * Ass6Game.
     * runs the game
     */
// Ass6Game the game -- start the animation loop.
    public void run() {
        CountdownAnimation countdownAnimation = new CountdownAnimation(
                2, 3, this.getSprites(), this.getGui(), this.level.getBackground(),
                this.getScore(), this);
        countdownAnimation.countDown();
        // countdown before turn starts.
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.running = true;
        this.runner.run(this);
    }

    public boolean shouldStop() {
        if (this.balls.getValue() == 0 || this.blocks.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(Counter score) {
        this.score = score;
    }

    /**
     * Gets blocks.
     *
     * @return the blocks
     */
    public Counter getBlocks() {
        return this.blocks;
    }

    /**
     * Gets balls.
     *
     * @return the balls
     */
    public Counter getBalls() {
        return this.balls;
    }

    /**
     * Gets environment.
     * create a copy of the array of sprites to iterate over while removing the intended collidable then sets it as
     * the new collidable collection
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Gets sprites.
     *
     * @return the list of sprites
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        ArrayList<Collidable> copy = this.environment.getCollidables();
        for (int i = 0; i < copy.size(); i++) {
            if (c.getType().equals(copy.get(i).getType())) {
                if (c.getType().equals("block")) {
                    if (((Block) c).getShape().getUpperLeft().equals(((Block) copy.get(i)).getShape().getUpperLeft())) {
                        copy.remove(i);
                        this.environment.setCollidables(copy);
                        return;

                    }
                } else if (c.getType().equals("ball")) {
                    if (((Ball) c).getCenter().equals(((Ball) copy.get(i)).getCenter())) {
                        copy.remove(i);
                        this.environment.setCollidables(copy);
                        return;
                    }
                }
            }
        }


    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public LevelInformation getLevel() {
        return this.level;
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite          create a copy of the array of sprites to itirate over while removing the intendeqd sprite then sets it as the new          sprite collection
     */
    public void removeSprite(Sprite s) {
        ArrayList<Sprite> copy = this.sprites.getSprites();
        for (int i = 0; i < copy.size(); i++) {
            if (s.getType().equals(copy.get(i).getType())) {
                if (s.getType().equals("block")) {
                    if (((Block) s).getShape().getUpperLeft().equals(((Block) copy.get(i)).getShape().getUpperLeft())) {
                        copy.remove(i);
                        this.sprites.setSprites(copy);
                        return;

                    }
                } else if (s.getType().equals("ball")) {
                    if (((Ball) s).getCenter().equals(((Ball) copy.get(i)).getCenter())) {
                        copy.remove(i);
                        this.sprites.setSprites(copy);
                        return;
                    }
                }
            }
        }


    }


}


