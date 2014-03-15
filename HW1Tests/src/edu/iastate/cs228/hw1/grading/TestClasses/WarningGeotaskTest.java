package edu.iastate.cs228.hw1.grading.TestClasses;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw1.grading.Points;
import edu.iastate.cs228.hw1.grading.Total;

@Total(value = 10)
public class WarningGeotaskTest extends GeotaskTest {

	// Constructor values
	private static final int VALID_X = 2;
	private static final int VALID_Y = 1;

	// Ground values
	private static final int GROUND_DIM_X = 5;
	private static final int GROUND_DIM_Y = 5;

	@Override
	protected edu.iastate.cs228.hw1.Geotask getValidInstance() {
		return new edu.iastate.cs228.hw1.WarningGeotask(VALID_X, VALID_Y);
	}

	@Test
	@Points(value = 1)
	public void testWarningGeotaskConstructor() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();

		Assert.assertEquals(
				String.format(
						"WarningGeotaskTest constructed with an x value of %d should be valid.",
						VALID_X), VALID_X, test.getX());
		Assert.assertEquals(
				String.format(
						"WarningGeotaskTest constructed with a y value of %d should be valid.",
						VALID_Y), VALID_Y, test.getY());
	}

	@Test
	@Points(value = 4)
	public void testMoveIn() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.MobileObject mo = new edu.iastate.cs228.hw1.MobileObject(
				GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
				getValidGround(GROUND_DIM_X, GROUND_DIM_Y));

		// Check that they print some kind of output
		super.redirectStdOut();
		test.moveIn(mo);
		super.restoreStdOut();

		String[] keywords = { "warning", "entering", "place", "slippery", };
		String output = super.getOutput();

		for (String keyword : keywords) {
			if (output.contains(keyword)) {
				return;
			}
		}

		Assert.fail(String
				.format("A WarningGeotaskTest should output a message similar to "
						+ "'Warning: you are entering a place that is slippery.' when a MobileObject moves in."));
	}

	@Test
	@Points(value = 4)
	public void testMoveOut() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.MobileObject mo = new edu.iastate.cs228.hw1.MobileObject(
				GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
				getValidGround(GROUND_DIM_X, GROUND_DIM_Y));

		// Check that they print some kind of output
		super.redirectStdOut();
		test.moveIn(mo);
		// Remove other output
		super.output.reset();

		test.moveOut(mo);
		super.restoreStdOut();

		String[] keywords = { "bye", "mobile", "object",
				"" + (GeotaskTest.MOBILE_OBJ_ID - 1), };
		String output = super.getOutput();

		for (String keyword : keywords) {
			if (output.contains(keyword)) {
				return;
			}
		}

		Assert.fail(String
				.format("A WarningGeotaskTest should output a message similar to "
						+ "'Bye - mobile object %d' when a MobileObject moves in.",
						GeotaskTest.MOBILE_OBJ_ID - 1));
	}

	@Test
	@Points(value = 1)
	public void testPrintType() {
		super.redirectStdOut();
		getValidInstance().printType();
		super.restoreStdOut();

		String output = super.getOutput();

		String[] keywords = { "Warning", "Geotask" };

		for (String keyword : keywords) {
			if (output.contains(keyword.toLowerCase())) {
				return;
			}
		}

		Assert.fail(String
				.format("WarningGeotaskTest's printType should be similar to "
						+ "'This is a WarningGeotaskTest'"));
	}
}
