//211602297 yuval cohen
package objects;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import graphics.Sprite;
import collision.Collidable;
import controller.GameLevel;
import collision.Velocity;


/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private String type = "paddle";
    private biuoop.GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private double speed;
    private Block block;

    /**
     * Instantiates a new Paddle.
     *
     * @param gui   the gui
     * @param speed the speed
     * @param block the block
     */
    public Paddle(GUI gui, double speed, Block block) {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.speed = speed;
        this.block = block;
    }

    /**
     * Gets block.
     *
     * @return the block
     */
    public Block getBlock() {
        return this.block;
    }

    /**
     * Update pos.
     * creates a new block in a new position
     *
     * @param x the x value of the new position
     */
    public void updatePos(double x) {
        Point updateCorner =
                new Point(this.block.getShape().getUpperLeft().getX() + x, this.block.getShape().getUpperLeft().getY());
        this.block = new Block(updateCorner, this.block.getWidth(), this.block.getHeight(), this.block.getColor());
    }


    /**
     * Move left.
     * if the key a or the left key was pressed move the block to the left by speed value
     */
    public void moveLeft() {
        if (this.getBlock().getShape().getUpperLeft().getX() - this.speed >= 28) {
            if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                this.updatePos(-this.speed);

            }

        }
    }


    /**
     * Move right.
     * if the key a or the left key was pressed move the block to the right by speed value
     */
    public void moveRight() {
        if (this.getBlock().getShape().getUpperRight().getX() + this.speed <= 770) {
            if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                this.updatePos(this.speed);

            }

        }
    }

    // Sprite
    @Override
    /**
     * timePassed.
     * moves the paddle if input was entered
     */
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }


    @Override
    /**
     * drawOn.
     * @param d the draw surface
     * draws the ball
     */
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);

    }

    // Collidable
    @Override
    /**
     * getCollisionRectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.block.getShape();

    }
    /**
     * getCollisionRectangle.
     * @param dx the dx of the velocity
     * @param angle the angle the new angle
     * the method cahnges tghe dy of a given velocity based on a new angle using trigonometry
     * @return teh new velocity
     */
    private static double dyFromAngle(double dx, double angle) {
        return (dx * Math.tan(angle));

    }

    @Override
    /**
     * hit.
     * @param collisionPoint the point of collision with the paddle
     * @param currentVelocity the velocity of the ball at the time of collision
     * the method changes the ball's velocity based on where it collided with the paddle
     * there is no case for the lower side because in the real game the ball cant hit it
     * because it has to hit the lower edge first and thus losing the ball
     * @return the new velocity
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double section = this.block.getShape().getWidth() * 0.2;
        double angle = Math.toDegrees(Math.atan2(currentVelocity.getDy(), currentVelocity.getDx()));
        // divide the upper side of the paddle into five sections
        Line part1 = new Line(this.block.getUpperLeft(),
                new Point(this.block.getUpperLeft().getX() + section, this.block.getUpperLeft().getY()));
        Line part2 = new Line(part1.end(), new Point(part1.end().getX() + section, part1.end().getY()));
        Line part3 = new Line(part2.end(), new Point(part2.end().getX() + section, part2.end().getY()));
        Line part4 = new Line(part3.end(), new Point(part3.end().getX() + section, part3.end().getY()));
        Line part5 = new Line(part4.end(), this.block.getShape().getUpperRight());
        //if the collision point belong to one of the five or to one of the sides left or right act accordingly
        if (this.block.getShape().getLeft().belongsTo(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }

        if (part1.belongsTo(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());

        }
        if (part2.belongsTo(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());

        }
        if (part3.belongsTo(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());

        }
        if (part4.belongsTo(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());

        }
        if (part5.belongsTo(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());

        }
        if (this.block.getShape().getRight().belongsTo(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        // default return statement to avoid a bug where the ball gets a null velocity and crashes the game
        return currentVelocity;
    }


    /**
     * Add to game.
     * adds the paddle to the collectable and sprite collections
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     *  getType.
     * @return the type of the paddle
     */
    public String getType() {
        return this.type;
    }
}
