//211602297 yuval cohen
package objects;

import biuoop.DrawSurface;
import collision.CollisionInfo;
import collision.HitListener;
import collision.HitNotifier;
import collision.Velocity;
import controller.GameEnvironment;
import controller.GameLevel;
import graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Ball.
 * creats a ball defined by a center point, color,radius,and velocity
 *
 * @author yuval cohen < yuvyuv29@gmail.com The type Ball.
 */
public class Ball implements Sprite, HitListener, HitNotifier {
    private List<HitListener> hitListeners = new ArrayList<>();
    private String type = "ball";
    private Point center;
    private int radius;
    private java.awt.Color color;
    private double velocityY;
    private double velocityX;
    private GameEnvironment environment;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;
    /**
     * The Width.
     */
    static final int WIDTH = 800;


    /**
     * Instantiates a new Ball.
     * creates a new ball with an input of point radius color and velocity
     *
     * @param center the center
     * @param r      the radius
     * @param color  the color of the ball
     * @param dx     the dx value
     * @param dy     the dy value
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color, double dx, double dy) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.velocityX = dx;
        this.velocityY = dy;
        this.addHitListener(this);

    }

    /**
     * Instantiates a new Ball.
     *
     * @param center           the center
     * @param r                the radius
     * @param color            the color of the ball
     * @param speed            the speed of the ball
     * @param angle            the angle of the ball
     * @param environmentInput the environment input
     */
    public Ball(Point center, int r, java.awt.Color color, int speed, int angle, GameEnvironment environmentInput) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.environment = environmentInput;
        Velocity veloInput = Velocity.fromAngleAndSpeed(speed, angle);
        this.velocityX = veloInput.getDx();
        this.velocityY = veloInput.getDy();
        this.addHitListener(this);

    }

    /**
     * Instantiates a new Ball.
     *
     * @param center           the center
     * @param r                the r
     * @param color            the color
     * @param environmentInput the environment input
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environmentInput) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.environment = environmentInput;
        this.addHitListener(this);
    }

    /**
     * Instantiates a new Ball.
     * createsa new ball with the input:center point radius and color
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.addHitListener(this);

    }

    /**
     * Instantiates a new Ball.
     * another constructor the receives the x and y values of center the radius color and velocity
     *
     * @param x     the x value of center
     * @param y     the y value of center
     * @param r     the radius
     * @param color the color
     * @param dx    the dx velocity
     * @param dy    the dy veloctry
     */
// constractor in case we get ints or doubles
    public Ball(double x, double y, int r, java.awt.Color color, double dx, double dy) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocityX = dx;
        this.velocityY = dy;
        this.addHitListener(this);

    }

    /**
     * Instantiates a new Ball.
     * another constructor the receives the x and y values of center the radius and color
     *
     * @param x     the x value of center
     * @param y     the y value of center
     * @param r     the radius
     * @param color the color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.addHitListener(this);

    }


    /**
     * Gets x.
     * returns the x value of the center
     *
     * @return the x value
     */
// accessors
    public int getX() {
        return (int) this.center.getX();

    }

    /**
     * Gets y.
     *
     * @return the y value of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     * geets the radius of the ball
     *
     * @return the size
     */
    public int getSize() {
        return this.radius;

    }


    /**
     * Gets color.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * Draw on.
     * this method draws the ball in a given gui
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    @Override
    public void drawOn(DrawSurface surface) {
        DrawSurface d = surface;
        //sets the color of the ball and draws it
        d.setColor(this.color);
        d.fillCircle((int) this.center.getX(), (int) this.center.getY(), radius);

    }


    /**
     * Sets velocity.
     *
     * @param v the velocity that will be added to the ball
     */
    public void setVelocity(Velocity v) {
        this.velocityX = v.getDx();
        this.velocityY = v.getDy();
    }

    /**
     * Sets x.
     * changes the x value of the ball
     *
     * @param x the x
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * Sets y.
     * changes the y value of the ball
     *
     * @param y the y
     */
    public void setY(double y) {
        this.center.setY(y);
    }

    /**
     * Sets velocity.
     * sets the velocity using explicit values
     *
     * @param dx the horizontal change
     * @param dy the vertical change
     */
    public void setVelocity(double dx, double dy) {
        this.velocityX = dx;
        this.velocityY = dy;

    }

    /**
     * Gets horizontal.
     *
     * @return the horizontal change of the velocity
     */
    public double getHorizontal() {
        return this.velocityX;
    }

    /**
     * Gets vertical.
     *
     * @return the vertical change of the velocity
     */
    public double getVertical() {
        return this.velocityY;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        Velocity velo = new Velocity(this.getHorizontal(), this.getVertical());
        return velo;
    }

    /**
     * Flips velocity.
     */
    public void flipVelocity() {
        Velocity velo = new Velocity(-this.getHorizontal(), -this.getVertical());
        this.setVelocity(velo.getDx(), velo.getDy());

    }

    /**
     * Move one step.
     * moves the ball by using method to change its center based on the velocity
     */
    public void moveOneStep() {
        Line trajectory = this.calculateTrajectory();
        CollisionInfo hit = this.environment.getClosestCollision(trajectory);
        if (hit == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Velocity veloNew = hit.collisionObject().hit(this, hit.collisionPoint(), this.getVelocity());
            this.center = this.almostHit(hit.collisionPoint(), this.getVelocity());
            this.setVelocity(veloNew);


        }

    }

    /**
     * Almost hit point.
     *
     * @param spot the spot
     * @param velo the velo create a point the is almost the hit point based on the
     * velocity the point will be the point the where the  radius hits the rectangle
     * @return the new point
     */
    public Point almostHit(Point spot, Velocity velo) {
        double hitX = spot.getX();
        double hitY = spot.getY();
        if (velo.getDx() < 0) {
            hitX = hitX + this.radius;
        }
        if (velo.getDx() > 0) {
            hitX = hitX - this.radius;
        }
        if (velo.getDy() < 0) {
            hitY = hitY + this.radius;
        }
        if (velo.getDy() > 0) {
            hitY = hitY - this.radius;
        }
        return new Point(hitX, hitY);
    }

    @Override
    /**
     * timePassed.
     *
     * moves the ball using move one step
     */
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public String getType() {
        return this.type;
    }


    /**
     * Calculate trajectory line.
     *
     * @return the line
     */
// this.center = this.getVelocity().applyToPoint(this.center);
    public Line calculateTrajectory() {
        Point endPoint = getVelocity().applyToPoint(center);
        return new Line(center.getX(), center.getY(), endPoint.getX(), endPoint.getY());
    }

    /**
     * Gets center.
     *
     * @return the center of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Add to game.
     * adds the rectangle to the environment
     *
     * @param g the game environment
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("lost a ball");

    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);

    }

    /**
     * Remove from game.
     * removes the ball from the game by deleting its sprite
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}


