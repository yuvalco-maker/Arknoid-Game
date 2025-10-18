//211602297 yuval cohen
package collision;
import objects.Point;

/**
 * The type Velocity.
 *
 * @author yuval cohen < yuvyuv29@gmail.com defines velocity wich determains  how an object can move The type Velocity.
 */
// Velocity specifies the change in position on the x and the y axes.
public class Velocity {
    private double horizontal;
    private double vertical;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the direction of X
     * @param dy the direction of Y
     */
// constructor
    public Velocity(double dx, double dy) {
        this.vertical = dy;
        this.horizontal = dx;
    }

    /**
     * Gets dx.
     * returns horizontal velocity
     *
     * @return the dx
     */
    public double getDx() {
        return this.horizontal;
    }

    /**
     * Gets dy.
     * returns the vertical velocity
     *
     * @return the dy
     */
    public double getDy() {
        return this.vertical;
    }

    /**
     * From angle and speed velocity.
     * calculates velocity from speed and angle using trigonometry
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Gets speed.
     *uses a formula to find the speed that was enters based on the dx and dy
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
    }


    /**
     * Gets speed from velocity.
     * calculates and returns the speed value based on the velocity
     *
     * @return the speed value
     */
    public  double getSpeedFromVelocity() {
        return Math.sqrt(this.getDx() * this.getDy() + this.getDx() * this.getDy());
    }

    /**
     * Apply to point.
     * change the original point to a new one based on the velocity
     *
     * @param p the point that we change
     * @return the changed point
     */
// Take a point with (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {

        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }
}