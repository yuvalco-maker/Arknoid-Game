//211602297 yuval cohen
package controller;

import java.util.ArrayList;
import java.util.List;

import collision.Collidable;
import collision.CollisionInfo;
import objects.Line;
import objects.Point;


/**
 * The type Game environment.
 * crates a game environment
 */
public class GameEnvironment {
    private  ArrayList<Collidable> collidables = new ArrayList<>();

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {

    }

    /**
     * Add collidable.
     * adds an object to the environment
     *
     * @param c the object
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Gets collidables.
     *
     * @return the list collidables
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Gets closest collision.
     * createa collision info of a collision of a line with a collectable
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collectables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (!collide(trajectory)) {
            return null;
        }
        Collidable collisionObject = null;
        Point closestCollisionPoint = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Collidable c : collidables) {
            //if the list isnt empty
            if (c.getCollisionRectangle().intersectionPoints(trajectory).size() > 0) {
                // go over all the hit points save the closest distance and the point and in the end create
                // a collision info that belongs to the closest hit point
                if (c.getCollisionRectangle().getClosestPoint(trajectory).distance(trajectory.start()) < minDistance) {
                    minDistance = c.getCollisionRectangle().getClosestPoint(trajectory).distance(trajectory.start());
                    closestCollisionPoint = c.getCollisionRectangle().getClosestPoint(trajectory);
                    collisionObject = c;
                }
            }

        }

        CollisionInfo closest = new CollisionInfo(closestCollisionPoint, collisionObject);
        return closest;
    }

    /**
     * Collide boolean.
     * checks if there is a collision and returns true if it is false if it is not
     *
     * @param trajectory the trajectory
     * @return the boolean
     */
    public boolean collide(Line trajectory) {
        List<Collidable> copy = new ArrayList<>(collidables);
        for (Collidable c : copy) {
            if (c.getCollisionRectangle().intersectionPoints(trajectory).size() != 0) {
                return true;
            }

        }
        return false;
    }

    /**
     * Sets collidables.
     * the function set a new list of collideables to be the environments collidables
     * @param copy the copy
     */
    public void setCollidables(ArrayList<Collidable> copy) {
        this.collidables = copy;

    }


}
