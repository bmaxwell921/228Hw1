package edu.iastate.cs228.hw1.solution;

/**
 * Geotask that tracks the number of concurrent visitors
 *
 * @author Nick Gerleman
 */
public class CounterGeotask extends Geotask {

    /**
     * The number of visitors
     */
    private int numberOfVisitors = 0;

    /**
     * Constructs a CounterGeotask
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @throws java.lang.IllegalArgumentException if x or y is less than 0
     */
    public CounterGeotask(int x, int y) {
        super(x, y);
    }

    @Override
    public void moveIn(MobileObject mo) {
        numberOfVisitors++;
    }

    @Override
    public void moveOut(MobileObject mo) {
    }

    @Override
    public void printType() {
        System.out.println("This is a CounterGeotask");
    }

    /**
     * Gets the number of visitors
     */
    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }
}
