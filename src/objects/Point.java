//211602297 yuval cohen
package objects;

/**
 * *@author yuval cohen < yuvyuv29@gmail.com
 * this class defines a point and supports operation on point or two points
 * The type Point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x value of the point
     * @param y the y value of the point
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }
    // create a threshold to compare doubles
    private final double threshold = 0.001;

    /**
     * Distance double.
     * calculates the distance between two points using the standard formula
     *
     * @param other the other point
     * @return the double value of the distance
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double distance;
        distance = Math.sqrt(Math.pow((this.getX() - other.getX()), 2) + Math.pow((this.getY() - other.getY()), 2));
        return distance;


    }

    /**
     * Invert y point.
     * inverts the y value of a point
     * @return the point
     */
    public Point invertY() {
        return new Point(this.x, -this.y);
    }


    /**
     * Equals boolean.
     * checks if two points are actually the same point
     *
     * @param other the other point
     * @return the boolean true if they are the same false if they are not
     */
// equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        if (Math.abs(this.getX() - other.getX()) <= threshold && this.getX() - other.getX() >= -threshold) {
            if (Math.abs(this.getY() - other.getY()) <= threshold && this.getY() - other.getY() >= -threshold) {
                return true;
            }
        }


        return false;
    }

    /**
     * Gets x.
     * provides the x value of the point
     *
     * @return the x value of the point
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Sets x value.
     *
     * @param x the x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y value of an exiting point.
     *
     * @param y the y value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets y.
     * provides the y value of the point
     *
     * @return the y value of the point
     */
    public double getY() {
        return this.y;
    }
}