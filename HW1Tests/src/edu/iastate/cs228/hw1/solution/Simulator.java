package edu.iastate.cs228.hw1.solution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Simulates the interaction between objects
 *
 * @author Nick Gerleman
 */
public class Simulator {
    public static void main(String[] args) {
        int dimX = -1;
        int dimY = -1;
        int numObjects = -1;
        int duration = -1;
        ArrayList<Geotask> geotasks = new ArrayList<>();
        File configFile = new File("Simulation.Configuration");
        Scanner configScanner;
        // Try to open file
        try {
            configScanner = new Scanner(configFile);
        } catch (IOException e) {
            System.out.println("Unable to read configuration file");
            return;
        }
        while (configScanner.hasNext()) {
            Scanner lineScanner = new Scanner(configScanner.nextLine());
            lineScanner.useDelimiter("\\: \\s*|\\, \\s*");
            try {
                switch (lineScanner.next()) {
                    case "dimensionX":
                        dimX = lineScanner.nextInt();
                        break;
                    case "dimensionY":
                        dimY = lineScanner.nextInt();
                        break;
                    case "numberOfMobileObjects":
                        numObjects = lineScanner.nextInt();
                        break;
                    case "durationOfSimulationTime":
                        duration = lineScanner.nextInt();
                        break;
                    case "WarningGeotask":
                        geotasks.add(new WarningGeotask(lineScanner.nextInt(), lineScanner.nextInt()));
                        break;
                    case "CounterGeotask":
                        geotasks.add(new CounterGeotask(lineScanner.nextInt(), lineScanner.nextInt()));
                        break;
                    case "PopulationMonitoringGeotask":
                        geotasks.add(new PopulationMonitoringGeotask(lineScanner.nextInt(), lineScanner.nextInt(), lineScanner.nextInt()));
                        break;
                }
            } catch (NoSuchElementException | IllegalArgumentException e) {
                System.out.println("Invalid configuration File");
                return;
            }
        }
        // Check for invalid config
        if (dimX < 1 || dimY < 1 || numObjects < 0 || duration < 0) {
            System.out.println("Invalid Configuration File");
            return;
        }
        // Create simulation
        Ground ground = new Ground(dimX, dimY);
        MobileObject[] mobileObjects = new MobileObject[numObjects];
        for (Geotask task : geotasks) {
            ground.addGeotask(task);
        }
        //Initialize mobile objects
        for (int i = 0; i < numObjects; i++) {
            mobileObjects[i] = new MobileObject(i, i % dimX, i % dimY, 1, i % 8, ground);
        }
        // Move in for all mobile objects
        for (MobileObject mo : mobileObjects) {
            ArrayList<Geotask> tasks = ground.getGeotask(mo);
            if (tasks != null) {
                for (Geotask task : tasks) {
                    task.moveIn(mo);
                }
            }
        }
        // Run the simulation
        for (int i = 0; i < duration; i++) {
            for (MobileObject mo : mobileObjects) {
                mo.move();
                System.out.println(mo.getID() + ": (" + mo.getCurrentX() + ", " + mo.getCurrentY() + ")");
            }
        }
    }
}
