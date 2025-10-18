//211602297 yuval cohen
package collision;


/**
 * The Counter class.
 */
public class Counter {
    private int value;

    /**
     * Creates a new Counter with the specified starting value.
     *
     * @param startingVal the starting value of the counter
     */
    public Counter(int startingVal) {
        this.value = startingVal;
    }

    /**
     * Increases the counter by the specified number.
     *
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Sets the value of the counter to the specified value.
     *
     * @param set the value to set the counter to
     */
    public void setValue(int set) {
        this.value = set;
    }

    /**
     * Returns the string representation of the counter's value.
     *
     * @return the string representation of the counter's value
     */
    public String getValueString() {
        return String.valueOf(this.getValue());
    }

    /**
     * fullValueString string.
     * returnce the value with the prefix Score:
     * @return the string
     */
    public String fullvalueString() {
        return "Score: " + this.getValueString();
    }
}

