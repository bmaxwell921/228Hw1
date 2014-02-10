package edu.iastate.cs228.hw1.grading.TestClasses;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw1.grading.Points;
import edu.iastate.cs228.hw1.grading.Total;

@Total(value = 10)
public class PopulationMonitoringGeotaskTest extends GeotaskTest {

	// Constructor values
	private static final int VALID_X = 2;
	private static final int VALID_Y = 1;
	private static final int THRESHOLD = 3;

	// Ground values
	private static final int GROUND_DIM_X = 5;
	private static final int GROUND_DIM_Y = 5;

	@Override
	protected edu.iastate.cs228.hw1.Geotask getValidInstance() {
		return new edu.iastate.cs228.hw1.PopulationMonitoringGeotask(VALID_X,
				VALID_Y, THRESHOLD);
	}

	@Test
	@Points(value = 1)
	public void testPopMonitorGeotaskConstructor() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();

		Assert.assertEquals(
				String.format(
						"PopulationMonitoringGeotask constructed with an x value of %d should be valid.",
						VALID_X), VALID_X, test.getX());
		Assert.assertEquals(
				String.format(
						"PopulationMonitoringGeotask constructed with a y value of %d should be valid.",
						VALID_Y), VALID_Y, test.getY());
	}

	@Test
	@Points(value = 2)
	public void testMoveInUnderThresh() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.MobileObject mo = new edu.iastate.cs228.hw1.MobileObject(
				GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
				getValidGround(GROUND_DIM_X, GROUND_DIM_Y));

		super.redirectStdOut();
		test.moveIn(mo);

		// Check to make sure that 'too crowded' wasn't printed out

		String[] invalidOut = { "too", "crowded" };

		String studentOutput = super.getOutput();

		for (String invalid : invalidOut) {
			if (studentOutput.contains(invalid)) {
				Assert.fail(String
						.format("A PopulationMonitoringGeotask should not output if "
								+ "the number of MobileObjects moving in is under the threshold"));
			}
		}
	}

	@Test
	@Points(value = 2)
	public void testMoveInOverThresh() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.MobileObject mo = new edu.iastate.cs228.hw1.MobileObject(
				GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
				getValidGround(GROUND_DIM_X, GROUND_DIM_Y));
		super.redirectStdOut();
		// CurrentNum = 1
		test.moveIn(mo);
		// CurrentNum = 2
		test.moveIn(mo);

		// Make sure to get rid of any output from before
		super.output.reset();

		// This should output since current = 3 == thresh
		test.moveIn(mo);
		super.restoreStdOut();

		String[] keywords = { "too", "crowded" };
		String output = super.getOutput();

		for (String keyword : keywords) {
			if (output.contains(keyword)) {
				return;
			}
		}

		Assert.fail(String
				.format("PopulationMonitoringGeotasks should output after their threshold is reached."));
	}

	@Test
	@Points(value = 2)
	public void testMoveOutUnderThresh() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.MobileObject mo = new edu.iastate.cs228.hw1.MobileObject(
				GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
				getValidGround(GROUND_DIM_X, GROUND_DIM_Y));

		super.redirectStdOut();
		test.moveIn(mo);

		// Get rid of any output
		super.output.reset();

		test.moveOut(mo);
		super.restoreStdOut();

		String output = super.getOutput();

		String[] invalidOut = { "no", "longer", "too", "crowded" };

		for (String invalid : invalidOut) {
			if (output.contains(invalid)) {
				Assert.fail(String
						.format("A PopulationMonitoringGeotask should not output if "
								+ "the number of MobileObjects moving out didn't take it under the threshold"));
			}
		}
	}

	@Test
	@Points(value = 2)
	public void testMoveOutOverThresh() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.MobileObject mo = new edu.iastate.cs228.hw1.MobileObject(
				GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
				getValidGround(GROUND_DIM_X, GROUND_DIM_Y));
		super.redirectStdOut();
		// CurrentNum = 1
		test.moveIn(mo);
		// CurrentNum = 2
		test.moveIn(mo);
		// CurrentNum = 3
		test.moveIn(mo);

		// Make sure to get rid of any output from before
		super.output.reset();

		test.moveOut(mo);
		super.restoreStdOut();

		String output = super.getOutput();
		String[] keywords = { "no", "longer", "too", "crowded" };

		for (String keyword : keywords) {
			if (output.contains(keyword)) {
				return;
			}
		}

		Assert.fail(String
				.format("A PopulationMonitoringGeotask should output a string similar to "
						+ "'No longer too crowded' when it goes under the threshold"));
	}

	@Test
	@Points(value = 1)
	public void testPrintType() {
		super.redirectStdOut();
		getValidInstance().printType();
		super.restoreStdOut();

		String output = super.getOutput();

		String[] keywords = { "Population", "Monitoring", "Geotask" };

		for (String keyword : keywords) {
			if (output.contains(keyword.toLowerCase())) {
				return;
			}
		}

		Assert.fail(String
				.format("PopulationMonitoringGeotask's printType should be similar to "
						+ "'This is a PopulationMonitoringGeotask'"));
	}

}
