package edu.iastate.cs228.hw1.solution;

/**
 * Geotask that monitors the current population of mobile units and alerts when
 * the number goes above or below a threshold
 *
 * @author Nick Gerleman
 */
public class PopulationMonitoringGeotask extends Geotask {

    /**
     * The population threshold to alert the user
     */
    private int alertThreshold = 0;

    /**
     * The current population
     */
    private int population = 0;

    /**
     * Constructs a new PopulationMonitoringGeotask
     *
     * @param x              the x coordinate
     * @param y              the y coordinate
     * @param alertThreshold the threshold of which to alert the user
     * @throws java.lang.IllegalArgumentException if x or y is less than 0
     */
    public PopulationMonitoringGeotask(int x, int y, int alertThreshold) {
        super(x, y);
        this.alertThreshold = alertThreshold;
    }

    @Override
    public void moveIn(MobileObject mo) {
        population++;
        if (population == alertThreshold) {
            System.out.println("too crowded");
        }
    }

    @Override
    public void moveOut(MobileObject mo) {
        population--;
        if (population == alertThreshold - 1) {
            System.out.println("no longer too crowded");
        }
    }
}