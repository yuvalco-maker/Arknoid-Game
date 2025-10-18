
package collision;
import objects.Ball;
import objects.Point;
import objects.Rectangle;

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle i.e the rectangle that the ball collided with
     */
// Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball
     * @param hitter the ball that hit the object
     * changes the velocity based ont he hit then returns it
     * @return the velocity after changing it according to the hit point
     */
// Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);


    /**
     * Gets type.
     *
     * @return the type of the collideable
     */
    String getType();
}