//211602297 yuval cohen

package objects;
import java.util.List;


/**
 * The type Line.
 * defines the  line by the start and end points
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    private final double threshold = 0.001;

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x value of the start point
     * @param y1 the y value of the start point
     * @param x2 the x value of the end point
     * @param y2 the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }

    /**
     * Length double.
     * finds the distance of the line
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        return start.distance(end);
    }

    /**
     * Middle point.
     * finds the middle point of a line by dividing the X rang eor Y range by two
     *
     * @return the point
     */
// Returns the middle point of the line
    public Point middle() {
        double midX, midY;
        // Check for vertical line
        if (this.start.getX() == this.end.getX()) {
            midX = this.start.getX();
            midY = (this.start.getY() + this.end.getY()) / 2;
            // for horizontal lines
        } else if (this.start.getY() == this.end.getY()) {
            midX = (this.start.getX() + this.end.getX()) / 2;
            midY = this.start.getY();
        } else {
            // for regular lines we find the middle X by dividing the difference between the max X and the min X by 2
            if (this.start.getX() < this.end.getX()) {
                midX = this.start.getX() + ((this.end.getX() - this.start.getX()) / 2);
            } else {
                midX = this.end.getX() + ((this.start.getX() - this.end.getX()) / 2);
            }
            // get the Y point by calculating the line equation with the meeting X
            midY = (this.getSlope() * midX) + this.getConstant();
        }
        return new Point(midX, midY);
    }

    /**
     * Gets slope.
     * finds the slope of the line
     *
     * @return the slope
     */
    public double getSlope() {
        if (this.start.getY() == this.end.getY()) {
            return 0;
        }
        double endY = this.start.getY();
        double startY = this.end.getY();
        if (this.startRange() == this.start.getX()) {
            startY = this.start.getY();
            endY = this.end.getY();

        }
        //in case the line is parallel to the x axis
        if (this.startRange() == this.endRange()) {
            return 0;
        }
        double slope = (endY - startY) / (this.endRange() - this.startRange());
        return slope;
    }

    /**
     * Gets constant.
     *
     * @return the constant
     */
    public double getConstant() {
        double constant = this.start.getY() - (this.getSlope() * this.start.getX());
        return constant;

    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
// Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     * returns the end point of the line
     *
     * @return the end point
     */
// Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * Parallels boolean.
     * checks if two lines are parallels that never meet using slopes and the constant in their equation
     *
     * @param other the other line that we compare
     * @return the true if parallel
     */
    public boolean parallels(Line other) {
        // a case where both lines are vertical and are the same
        if (this.start.getX() == this.end.getX() && other.start.getX()
                == other.end.getX() && this.start.getX() == other.start.getX()) {
            return false;
        }
        // a case where one line is vertical and the other is horizontal
        if (this.start.getX() == this.end.getX() && other.start.getY() == other.end.getY()) {
            return false;
        }
        // a case where both lines are horizontal and are the same
        if (this.start.getY() == this.end.getY() && other.start.getX() == other.end.getX()) {
            return false;
        }

        if (this.getConstant() != other.getConstant()) {
            //checks if the two lines have the same slope
            if (this.getSlope() == other.getSlope()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Start range double.
     * returns the lower lowest x value in the line
     *
     * @return the found value
     */
    public double startRange() {

        if (this.start.getX() <= this.end.getX()) {
            return this.start.getX();
        }
        return this.end.getX();

    }

    /**
     * Start range y double.
     * find the minimum value of Y
     *
     * @return the value
     */
    public double startRangeY() {

        if (this.start.getY() <= this.end.getY()) {
            return this.start.getY();
        }
        return this.end.getY();

    }

    /**
     * End range double.
     * returns the highest x value in the line
     *
     * @return the value
     */
    public double endRange() {
        if (this.startRange() == this.start.getX()) {
            return this.end.getX();
        }
        return this.start.getX();
    }

    /**
     * End range y double.
     * returns the maximum value of Y
     *
     * @return the value
     */
    public double endRangeY() {
        if (this.startRangeY() == this.start.getY()) {
            return this.end.getY();
        }
        return this.start.getY();
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
// Returns the intersection point if the lines intersect so it can be drawn,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        // first check if there can be an intersection point using the parallels method
        if (this.parallels(other)) {
            return null;
        }
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return null;
        }
        if (this.start.equals(other.end) && this.end.equals(other.start)) {
            return null;
        }

        //if this is vertical and other is horizontal
        if (this.start.getX() == this.end.getX() && other.start.getY() == other.end.getY()) {
            Point inter = new Point(this.start.getX(), other.start.getY());
            if (this.belongsTo(inter) && other.belongsTo(inter)) {
                return inter;
            }
        }
        if (this.start.getY() == this.end.getY() && other.start.getX() == other.end.getX()) {
            Point inter = new Point(other.start.getX(), this.start.getY());
            if (this.belongsTo(inter) && other.belongsTo(inter)) {
                return inter;
            }
        }
        // if both lines are parallel to the Y axis
        // checks if one line start of end on the other line start/end
        if (this.start().getX() == this.end().getX() && other.start().getX() == other.end().getX()) {
            if (this.startRangeY() <= other.startRangeY() && other.startRangeY() <= this.endRangeY()) {
                return new Point(this.start().getX(), other.startRangeY());
            } else if (this.startRangeY() <= other.endRangeY() && other.endRangeY() <= this.endRangeY()) {
                return new Point(this.start().getX(), other.endRangeY());
            } else if (other.startRangeY() <= this.startRangeY() && this.startRangeY() <= other.endRangeY()) {
                return new Point(other.start().getX(), this.startRangeY());
            } else if (other.startRangeY() <= this.endRangeY() && this.endRangeY() <= other.endRangeY()) {
                return new Point(other.start().getX(), this.endRangeY());
            }
        }
        // if this line is parallel to the Y axis
        if (this.start().getX() == this.end().getX()) {
            Point speculate = this.getIntersectionYParallel(other);
            // if the point belongs to the two lines then we return it
            if (this.belongsTo(speculate) && other.belongsTo(speculate)) {
                return speculate;
            }
        }
        // if the other line is parallel to the Y axis
        if (other.start().getX() == other.end().getX()) {
            Point speculate = other.getIntersectionYParallel(this);
            // if the point belongs to the two lines then we return it
            if (this.belongsTo(speculate) && other.belongsTo(speculate)) {
                return speculate;
            }
        }
        // if both lines are parallel to the X axis
        // checks if one line start of end on the other line start/end
        if (this.start().getY() == this.end().getY() && other.start().getY() == other.end().getY()) {
            if (this.startRange() <= other.startRange() && other.startRange() <= this.endRange()) {
                return new Point(other.startRange(), this.start().getY());
            } else if (this.startRange() <= other.endRange() && other.endRange() <= this.endRange()) {
                return new Point(other.endRange(), this.start().getY());
            } else if (other.startRange() <= this.startRange() && this.startRange() <= other.endRange()) {
                return new Point(this.startRange(), this.start().getY());
            } else if (other.startRange() <= this.endRange() && this.endRange() <= other.endRange()) {
                return new Point(this.endRange(), this.start().getY());
            }
        }
        // if this line is parallel to the X axis
        if (this.start.getY() == this.end.getY()) {
            Point speculate = this.getIntersectionXParallel(other);
            if (this.belongsTo(speculate.invertY()) && other.belongsTo(speculate)) {
                return speculate;
            }
        }
        // if the other line is parallel to the X axis
        if (other.start.getY() == other.end.getY()) {
            Point speculate = other.getIntersectionXParallel(other);
            if (this.belongsTo(speculate) && other.belongsTo(speculate)) {
                return speculate;
            }
        }
        // if both lines are not parallel to any axis
        Point speculate = this.getIntersection(other);
        if (this.belongsTo(speculate) && other.belongsTo(speculate)) {
            return speculate;
        }
        // if one line end where the other starts or vice versa
        if (other.start.equals(this.start) || other.start.equals(this.end)) {
            return other.start;
        }
        if (other.end.equals(this.start) || other.end.equals(this.end)) {
            return other.start;
        }
        // if one line is parallel to the X axes
        if (this.start.getX() == this.end.getX()
                && other.start.getX() == other.end.getX() && this.start.getX() == other.start.getX()) {
            if (this.start.equals(other.start()) || this.start.equals(other.end())) {
                return this.start();
            }
            // if one line end where the other starts or vice versa when one line is horizontal
            if (this.end.equals(other.start()) || this.end.equals(other.end())) {
                return this.end();
            }
            if (other.end.equals(this.start) || other.end.equals(this.end)) {
                return other.start;
            }

        }

        // if there is no point that belongs to both lines
        return null;
    }


    /**
     * Is intersecting boolean.
     * checks if two lines intersect
     *
     * @param other the other line
     * @return the true if they are
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        double meetingX = (other.getConstant() - this.getConstant()) - ((this.getSlope() - other.getSlope()) / 2);
// if there was an intersection point then they are intersecting
        if (intersectionWith(other) != null) {
            return true;
        }
        return false;
    }


    /**
     * Equals boolean.
     * checks if two lines are one and of the same
     *
     * @param other the other line
     * @return the true if they are false if they are not
     */
// equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        // checks if we have two vertical lines that are
        if (this.startRange() == this.endRange() && other.startRange() == other.endRange()) {
            if (this.startRange() == other.startRange()) {
                //we check to see if there is an overlap in the Y values
                if ((this.startRangeY() >= other.startRangeY() && this.endRangeY() <= other.endRangeY())
                        || (other.startRangeY() >= this.startRangeY() && other.endRangeY() <= this.endRangeY())) {


                    return true;
                }
            }
        }
        // if the lines have the same equation we check for an overlap of the X values
        if (((this.getSlope() == other.getSlope())) && this.getConstant() == other.getConstant()) {
            if (this.startRange() <= other.start.getX() && other.start.getX() <= this.endRange()) {
                return true;
            } else if (this.startRange() <= other.end.getX() && other.end.getX() <= this.endRange()) {
                return true;
            }
        }


        return false;
    }

    /**
     * Closest intersection to start of line point.
     * the method checks when the line first hits the rectangle
     *
     * @param rect the rectangle
     * @return the point where the line hits the rectangle first
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.size() == 0) {
            return null;
        }
        //goes over the array and compares each distance from the
        // start of the line and return the point with min distance
        double minDis = this.start.distance(intersectionPoints.get(0));
        Point closestP = intersectionPoints.get(0);
        for (int i = 0; i < intersectionPoints.size(); i++) {
            if (this.start.distance(intersectionPoints.get(i)) < minDis) {
                minDis = this.start.distance(intersectionPoints.get(i));
                closestP = intersectionPoints.get(i);
            }
        }
        return closestP;

    }


    /**
     * * Belongs to boolean.
     * checks if a point is a part of the line
     * you should note that it will work correctly if the point belongs the line if it wa infinite ,
     * it checks whether or not it is within the range of the two points
     *
     * @param point the point
     * @return the boolean true if it belongs flase if it does not
     */
    boolean belongsTo(Point point) {
        // first 2 ifs check if the point is the same as the ends of the line
        if (Math.abs(point.getX() - this.start().getX()) <= threshold
                && Math.abs(point.getY() - this.start().getY()) <= threshold) {
            return true;

        } else if (Math.abs(point.getX() - this.end().getX()) <= threshold
                && Math.abs(point.getY() - this.end().getY()) <= threshold) {
            return true;

        } else if (this.startRange() <= point.getX() && point.getX() <= this.endRange()
                && this.startRangeY() <= point.getY() && point.getY() <= this.endRangeY()) {
            return true;
        }
        return false;
    }


    /**
     * Gets intersection.
     * calculates and returns the intersection point of to non-vertical or horizontal line
     * @param other the  line other
     * @return the intersection
     */
    public Point getIntersection(Line other) {
        // calculates X and Y using line equation
        double meetingX = ((other.getConstant() - this.getConstant()) / (this.getSlope() - other.getSlope()));
        double meetingY = (this.getSlope() * meetingX) + this.getConstant();
        return new Point(meetingX, meetingY);
    }

    /**
     * Gets intersection x parallel.
     * calculates the meeting point if one of the lines if vertical
     * @param other the other
     * @return the intersection x parallel
     */
//if this is parallel to X axes
    public Point getIntersectionXParallel(Line other) {
        double meetingY = this.start.getY();
        double constant = other.getConstant();
        double mid = meetingY - other.getConstant();
        double meetingX = (mid / other.getSlope());
        return new Point(meetingX, meetingY);
    }

    /**
     * Gets parallel const x.
     * gets the constant of a vertical line
     * @return the parallel const x
     */
    public double getParallelConstX() {
        return this.start.getY();

    }

    /**
     * Gets parallel const y.
     *
     * @return the parallel const y
     */
    public double getParallelConstY() {
        return this.start.getX();

    }

    /**
     * Gets intersection y parallel.
     * gets the constant of a horizontal line
     * @param other the other
     * @return the intersection y parallel
     */
//if this is parallel to X axes
    public Point getIntersectionYParallel(Line other) {
        double meetingX = this.start.getX();
        double meetingY = other.getSlope() * meetingX + other.getConstant();
        return new Point(meetingX, meetingY);
    }
}




