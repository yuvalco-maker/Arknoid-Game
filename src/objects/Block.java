//211602297 yuval cohen
package objects;

import biuoop.DrawSurface;
import collision.Collidable;
import collision.HitListener;
import collision.HitNotifier;
import graphics.Sprite;
import collision.Velocity;

import java.awt.Color;

import controller.GameLevel;

import java.util.List;
import java.util.ArrayList;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitListener, HitNotifier {
    private List<HitListener> hitListeners =  new ArrayList<>();
    private Boolean removable = true;
    private String type = "block";
    private Rectangle shape;
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;
    private Boolean deathRegion = false;

    /**
     * Instantiates a new Block.
     *
     * @param origin the origin
     * @param width  the width
     * @param height the height
     */
    public Block(Point origin, double width, double height) {
        this.upperLeft = origin;
        this.width = width;
        this.height = height;
        this.shape = new Rectangle(upperLeft, width, height);
        this.addHitListener(this);


    }

    /**
     * Instantiates a new Block.
     *
     * @param origin the origin
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Block(Point origin, double width, double height, java.awt.Color color) {
        this.upperLeft = origin;
        this.width = width;
        this.height = height;
        this.shape = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.addHitListener(this);
        this.removable = true;


    }

    /**
     * Instantiates a new Block that cant be deleted.
     *
     * @param origin    the origin
     * @param width     the width
     * @param height    the height
     * @param color     the color
     * @param removable indicates if the block cant be removed
     */
    public Block(Point origin, double width, double height, java.awt.Color color, Boolean removable) {
        this.upperLeft = origin;
        this.width = width;
        this.height = height;
        this.shape = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.addHitListener(this);
        this.removable = removable;


    }

    /**
     * Instantiates a new Block that kills the pla.
     *
     * @param origin      the origin
     * @param width       the width
     * @param height      the height
     * @param color       the color
     * @param removable   indicates if the block cant be removed
     * @param deathRegion indicates if the block can delete the ball
     */
    public Block(
            Point origin, double width, double height, java.awt.Color color, Boolean removable, Boolean deathRegion) {
        this.upperLeft = origin;
        this.width = width;
        this.height = height;
        this.shape = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.addHitListener(this);
        this.deathRegion = deathRegion;
        this.removable = removable;


    }


    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }


    /**
     * Gets upper left.
     *
     * @return the upper left side of the block
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    @Override
    /**
     * getCollisionRectangle.
     * returns the blocks rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    @Override
    /**
     * hit
     * @param collisionPoint the collisionPoint with the shape
     * @param currentVelocity  the currentVelocity of the ball
     * returns a new velocity based on the collision point in relation to the rectangle
     * returns the blocks rectangle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity current = currentVelocity;
        Line left = this.shape.getLeft();
        Line right = this.shape.getRight();
        Line up = this.shape.getUp();
        Line down = this.shape.getDown();
        //if the point belonged to the left side
        if (left.belongsTo(collisionPoint)) {
            current = new Velocity(-current.getDx(), current.getDy());
            this.notifyHit(hitter);
        }
        //if the collision point belongs to the right side
        if (right.belongsTo(collisionPoint)) {
            current = new Velocity(-current.getDx(), current.getDy());
            this.notifyHit(hitter);
        }
        //if the collision point belongs to the upper side
        if (up.belongsTo(collisionPoint)) {
            current = new Velocity(current.getDx(), -current.getDy());
            this.notifyHit(hitter);
        }
        //if the collision point belongs to the lower side
        if (down.belongsTo(collisionPoint)) {
            current = new Velocity(current.getDx(), -current.getDy());
            this.notifyHit(hitter);
        }
        return current;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    @Override
    /**
     * draw on.
     * @param surface the draw surface
     * draws the ball on the surface
     */
    public void drawOn(DrawSurface surface) {
        DrawSurface d = surface;
        //sets the color of the rectangle and draws it
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(), (int) this.width, (int) this.height);
        d.setColor(this.color);
        d.fillRectangle((int) this.getUpperLeft().getX(),
                (int) this.getUpperLeft().getY(), (int) this.width, (int) this.height);
    }

    /**
     * Gets shape.
     *
     * @return the shape
     */
    public Rectangle getShape() {

        return this.shape;
    }

    @Override
/**
 * timePassed.
 *informs the object that time has passed
 */
    public void timePassed() {

    }

    /**
     * Add to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);

    }

    /**
     * Gets type.
     *
     * @return the type of the object
     */
    public String getType() {
        return this.type;
    }

    /**
     * Remove from game.
     * removes the block from the game and the sprite collection
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        if (this.removable) {
            game.removeCollidable(this);
            game.removeSprite(this);
        }

    }

    /**
     * addHitListener.
     * @param hl the hl
     * adds the hl as a listener
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * RemoveHitListener.
     * @param hl the hl
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

    }

    /**
     * notifyHit.
     * @param hitter the ball that did the hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Gets removable.
     *
     * @return the removable
     */
    public Boolean getRemovable() {
        return this.removable;
    }

    /**
     * Get DeathRegion.
     *
     * @return the boolean for the deathRegion
     */
    public Boolean getDeathRegion() {
        return this.deathRegion;
    }

}
