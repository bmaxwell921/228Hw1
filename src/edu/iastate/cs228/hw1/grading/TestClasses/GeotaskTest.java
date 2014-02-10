package edu.iastate.cs228.hw1.grading.TestClasses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.After;
import edu.iastate.cs228.hw1.grading.Total;
import edu.iastate.cs228.hw1.grading.Util;

/**
 * Super class for Geotask tests that handles redirection of Standard output
 * @author Brandon
 *
 */
public abstract class GeotaskTest {
	
	// Name of the instance field for the x value
	protected static final String X_FIELD = "x";
	
	// Name of the instance field for the y value
	protected static final String Y_FIELD = "y";
	
	protected static int MOBILE_OBJ_ID = 0;
	
	// Reference to Util used to redirect output
	private Util util;
	
	// Where standard output is redirected to
	protected ByteArrayOutputStream output;
	
	public GeotaskTest() {
		util = Util.getInstance();
	}
	
	/**
	 * Gets a valid instance of the ground with the given dimensions
	 * @param dimX
	 * @param dimY
	 * @return
	 */
	protected edu.iastate.cs228.hw1.Ground getValidGround(int dimX, int dimY) {
		return new edu.iastate.cs228.hw1.Ground(dimX, dimY);
	}
	
	/**
	 * Returns a valid instance of a Geotask to test
	 * @return
	 */
	protected abstract edu.iastate.cs228.hw1.Geotask getValidInstance();
	
	/**
	 * Used to redirect standard output as needed.
	 * Not a @Before because we don't always want to redirect
	 */
	protected void redirectStdOut() {
		output = util.redirectStdOut();
	}
	
	/**
	 * Used to restore standard output to normal.
	 * Not an @After so it can be called as needed
	 */
	protected void restoreStdOut() {
		util.restoreStdOut();	
	}
	
	/**
	 * Gets all available output from output
	 * @return
	 */
	protected String getOutput() {
		try {
			output.flush();
		} catch (IOException e) {
			// Do nothing, shouldn't ever happen
		}
		return output.toString().toLowerCase();
	}
	
	@After
	public void tearDown() {
		// Make sure standard output is back
		restoreStdOut();
		output = null;
	}
}
