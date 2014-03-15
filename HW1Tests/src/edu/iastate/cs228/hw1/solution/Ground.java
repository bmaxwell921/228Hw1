package edu.iastate.cs228.hw1.solution;

import java.util.ArrayList;

/**
 * Class representing the ground where geotasks and mobile objects are placed
 *
 * @author Nick Gerleman
 */
public class Ground {

    /**
     * The x dimension
     */
    private int dimensionX;
    /**
     * The y dimension
     */
    private int dimensionY;
    /**
     * List of all Geotasks
     */
    private ArrayList<Geotask> geotasks;

    /**
     * Constructs a new Ground object
     *
     * @param dimensionX the size on the x axis
     * @param dimensionY the size on the y axis
     * @throws java.lang.IllegalArgumentException if either dimension is less than 1
     */
    public Ground(int dimensionX, int dimensionY) {
        if (dimensionX < 1 || dimensionY < 1) {
            throw new IllegalArgumentException("Illegal Dimensions");
        }
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        geotasks = new ArrayList<>();
    }

    /**
     * Adds a Geotask
     *
     * @param t the task to add
     */
    public void addGeotask(Geotask t) {
        geotasks.add(t);
    }

    /**
     * Gets the x dimension
     */
    public int getDimensionX() {
        return dimensionX;
    }

    /**
     * Gets the y dimesnsion
     */
    public int getDimensionY() {
        return dimensionY;
    }

    /**
     * Gets the Geotask belonging to a cell
     *
     * @param x the x coordinate of the Geotask
     * @param y the y coordinate of the Geotask
     * @return an ArrayList of geotasks if any is present, otherwise null
     */
    public ArrayList<Geotask> getGeotask(int x, int y) {
        ArrayList<Geotask> cell = new ArrayList<>();
        for (Geotask task : geotasks) {
            if (task.getX() == x && task.getY() == y) {
                cell.add(task);
            }
        }
        if (cell.isEmpty()) {
            return null;
        }
        return cell;
    }

    /**
     * Gets the Geotask belonging to a mobile object
     *
     * @param mo the MobileObject to get Geotasks belonging to
     * @return an ArrayList of geotasks if any is present, otherwise null
     */
    public ArrayList<Geotask> getGeotask(MobileObject mo) {
        return getGeotask(mo.getCurrentX(), mo.getCurrentY());
    }
}
