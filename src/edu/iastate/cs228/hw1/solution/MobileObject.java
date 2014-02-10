package edu.iastate.cs228.hw1.solution;

import java.util.ArrayList;

/**
 * Mobile Object that traverses the ground
 *
 * @author Nick Gerleman
 */
public class MobileObject {

    private static final int NORTH = 0;
    private static final int SOUTH = 1;
    private static final int EAST = 2;
    private static final int WEST = 3;
    private static final int NORTH_EAST = 4;
    private static final int SOUTH_EAST = 5;
    private static final int SOUTH_WEST = 6;
    private static final int NORTH_WEST = 7;

    /**
     * The ground which the MobileObjhect resides on
     */
    private final Ground ground;
    /**
     * The id of the mobileObject
     */
    private final int ID;
    /**
     * The current x coordinate
     */
    private int currentX;
    /**
     * The current y coordinate
     */
    private int currentY;
    /**
     * The speed of the MobileObject
     */
    private int speed;
    /**
     * The direction of the MobileObject
     * 0-7 correlates to N, S, E, W, NE, SE, SW, NW
     */
    private int direction;

    /**
     * Constructs a new MobileObject
     *
     * @param ID        the ID of the object
     * @param currentX  the x coordinate of the object
     * @param currentY  the y coordinate of the object
     * @param speed     the speed of the object
     * @param direction the direction of the object
     * @param ground    the Ground on which the object resides
     * @throws java.lang.IllegalArgumentException if the cell coordinates, speed, or direction is out of bounds
     */
    public MobileObject(int ID, int currentX, int currentY, int speed, int direction, Ground ground) {
        this.ID = ID;
        this.currentX = currentX;
        this.currentY = currentY;
        this.speed = speed;
        this.direction = direction;
        this.ground = ground;
        // If coordinate is out of bounds
        if (currentX < 0 || currentY < 0 || currentX > ground.getDimensionX() - 1 || currentY > ground.getDimensionY() - 1 || speed < 0 || direction < 0 || direction > 7) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Gets the x coordinate of the mobileObject
     */
    public int getCurrentX() {
        return currentX;
    }

    /**
     * Gets the y coordinate of the mobileObject
     */
    public int getCurrentY() {
        return currentY;
    }

    /**
     * Gets the speed of the MobileObject
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets the direction of the MobileObject
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Gets the ID of the MobileObject
     */
    public int getID() {
        return ID;
    }

    /**
     * Moves according to the speed
     */
    public void move() {
        for (int i = 0; i < speed; i++) {
            moveOnce();
        }
    }

    /**
     * Moves the MobileObject and activates relevant geoTasks for one move
     */
    private void moveOnce() {
        int potentialX = currentX;
        int potentialY = currentY;
        //Get potential new location
        switch (direction) {
            case NORTH:
                potentialY--;
                break;
            case SOUTH:
                potentialY++;
                break;
            case EAST:
                potentialX++;
                break;
            case WEST:
                potentialX--;
                break;
            case NORTH_EAST:
                potentialY--;
                potentialX++;
                break;
            case SOUTH_EAST:
                potentialY++;
                potentialX++;
                break;
            case SOUTH_WEST:
                potentialY++;
                potentialX--;
                break;
            case NORTH_WEST:
                potentialY--;
                potentialX--;
                break;
        }
        // Deal with need to reflect
        if (potentialX < 0 && potentialY < 0) {
            direction = SOUTH_EAST;
            return;
        }
        if (potentialX > ground.getDimensionX() - 1 && potentialY > ground.getDimensionY() - 1) {
            direction = NORTH_WEST;
            return;
        }
        if (potentialX < 0 && potentialY > ground.getDimensionY() - 1) {
            direction = NORTH_EAST;
            return;
        }
        if (potentialX > ground.getDimensionX() - 1 && potentialY < 0) {
            direction = SOUTH_WEST;
            return;
        }
        if (potentialX < 0) {
            switch (direction) {
                case WEST:
                    direction = EAST;
                    break;
                case NORTH_WEST:
                    direction = NORTH_EAST;
                    break;
                case SOUTH_WEST:
                    direction = SOUTH_EAST;
                    break;
            }
            return;
        }
        if (potentialY < 0) {
            switch (direction) {
                case NORTH:
                    direction = SOUTH;
                    break;
                case NORTH_WEST:
                    direction = SOUTH_WEST;
                    break;
                case NORTH_EAST:
                    direction = SOUTH_EAST;
                    break;
            }
            return;
        }
        if (potentialX > ground.getDimensionX() - 1) {
            switch (direction) {
                case EAST:
                    direction = WEST;
                    break;
                case SOUTH_EAST:
                    direction = SOUTH_WEST;
                    break;
                case NORTH_EAST:
                    direction = NORTH_WEST;
                    break;
            }
            return;
        }
        if (potentialY > ground.getDimensionY() - 1) {
            switch (direction) {
                case SOUTH:
                    direction = NORTH;
                    break;
                case SOUTH_WEST:
                    direction = NORTH_WEST;
                    break;
                case SOUTH_EAST:
                    direction = NORTH_EAST;
                    break;
            }
            return;
        }
        //Not reflecting
        ArrayList<Geotask> tasks = ground.getGeotask(currentX, currentY);
        if (tasks != null) {
            for (Geotask g : tasks) {
                g.moveOut(this);
            }
        }
        currentX = potentialX;
        currentY = potentialY;
        tasks = ground.getGeotask(currentX, currentY);
        if (tasks != null) {
            for (Geotask g : tasks) {
                g.moveIn(this);
            }
        }
    }
}
