//211602297 yuval cohen
package collision;
import objects.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point that the collision occurs at.
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Collision object collidable, the object that the point belongs to.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return collisionObject;
    }
}
