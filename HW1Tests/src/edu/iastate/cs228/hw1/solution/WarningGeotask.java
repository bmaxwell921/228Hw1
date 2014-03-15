package edu.iastate.cs228.hw1.solution;

/**
 * Geotask that warns when a mobile object enters the area. alerts when it leaves
 *
 * @author Nick Gerleman
 */
public class WarningGeotask extends Geotask {

    /**
     * Constructs a new WarningGeotask
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @throws java.lang.IllegalArgumentException if x or y is less than 0
     */
    public WarningGeotask(int x, int y) {
        super(x, y);
    }

    @Override
    public void moveIn(MobileObject mo) {
        System.out.println("Warning: you are entering a place that is slippery");
    }

    @Override
    public void moveOut(MobileObject mo) {
        System.out.println("Bye - mobile object " + mo.getID());
    }

    @Override
    public void printType() {
        System.out.println("This is a WarningGeotask");
    }
}
