//211602297 yuval cohen
package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The type Rectangle.
 */
public class Rectangle {

    private Point lowerLeft;
    private Point lowerRight;

    private Point upperRight;

    private Point upperLeft;
    private double width;
    private double height;


    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        this.upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());


    }

    /**
     * Gets left.
     *
     * @return the left side of the rectangle
     */
    public Line getLeft() {
        return new Line(this.upperLeft, this.lowerLeft);
    }

    /**
     * Gets right.
     *
     * @return the right side of the rectangle
     */
    public Line getRight() {
        return new Line(this.upperRight, this.lowerRight);
    }

    /**
     * Gets up.
     *
     * @return the upper side
     */
    public Line getUp() {
        return new Line(this.upperRight, this.upperLeft);
    }

    /**
     * Gets down.
     *
     * @return the lower side
     */
    public Line getDown() {
        return new Line(this.lowerLeft, this.lowerRight);
    }

    /**
     * Belongs to boolean.
     * checks if a point belongs to any side of the rectangle
     *
     * @param point the point
     * @return the boolean
     */
    public boolean belongsTo(Point point) {
        if (this.getUp().belongsTo(point)) {
            return true;
        }
        if (this.getDown().belongsTo(point)) {
            return true;
        }
        if (this.getLeft().belongsTo(point)) {
            return true;
        }
        if (this.getRight().belongsTo(point)) {
            return true;
        }
        return false;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right point
     */
    public Point getUpperRight() {
        return upperRight;
    }


    /**
     * Intersection points java . util . list.
     * creates a list of all unique intersection points with the rectangle
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        //create a line for each side
        Line left = new Line(this.upperLeft, this.lowerLeft);
        Line right = new Line(this.upperRight, this.lowerRight);
        Line up = new Line(this.upperRight, this.upperLeft);
        Line down = new Line(this.lowerLeft, this.lowerRight);
        //if there is an intersection between a line and aside we add it to the list
        Point leftInter = left.intersectionWith(line);
        Point upInter = up.intersectionWith(line);
        Point downInter = down.intersectionWith(line);
        Point rightInter = right.intersectionWith(line);
        List<Point> intersections = Arrays.asList(leftInter, upInter, rightInter, downInter);
        List<Point> uniqueIntersections = new ArrayList<>();
        //ceate a list the contains only the unique elements
        for (Point p : intersections) {
            boolean contains = false;
            if (p != null) {
                for (int i = 0; i < uniqueIntersections.size(); i++) {
                    if (p.equals(uniqueIntersections.get(i))) {
                        contains = true;
                    }

                }
                if (!contains) {
                    uniqueIntersections.add(p);
                }

            }
        }
        return uniqueIntersections;

    }

    /**
     * Gets closest point.
     * goes over a list of points and returns the closest point to the start of the line
     *
     * @param trajectory the trajectory
     * @return the closest point
     */
    public Point getClosestPoint(Line trajectory) {
        List<Point> points = intersectionPoints(trajectory);
        if (points.size() == 0) {
            return null;
        }
        Point closestPoint = null;
        double minDistance = Double.POSITIVE_INFINITY;
        Point start = trajectory.start();
        //for each point we check if the distance is shorter the min fistance if it is we update the min distance and
        //the min point
        for (Point p : points) {
            double distance = start.distance(p);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = p;
            }
        }
        //return the min point

        return closestPoint;
    }


    /**
     * Gets width.
     *
     * @return the width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * Gets upper left.
     *
     * @return the upper left
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Contains boolean.
     *
     * @param point the point
     * @return the boolean
     */
    boolean contains(Point point) {

        if (this.lowerLeft.getX() <= point.getX() && point.getX() <= this.lowerRight.getX()) {
            if (this.lowerRight.getY() <= point.getY() && point.getY() <= this.upperRight.getY()) {
                return true;
            }
        }
        return false;
    }
}
