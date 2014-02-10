package edu.iastate.cs228.hw1.grading.TestClasses;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw1.grading.Points;
import edu.iastate.cs228.hw1.grading.Total;

@Total(value = 10)
public class CounterGeotaskTest extends GeotaskTest {

	// Valid x and y values
	private static final int VALID_X = 2;
	private static final int VALID_Y = 1;

	private static final int GROUND_DIM_X = 5;
	private static final int GROUND_DIM_Y = 5;

	// Common initialization
	@Override
	protected edu.iastate.cs228.hw1.Geotask getValidInstance() {
		return new edu.iastate.cs228.hw1.CounterGeotask(VALID_X, VALID_Y);
	}

	@Test
	@Points(value = 1)
	public void testCounterGeotastConstructor() {
		// Test that the x and y values are set properly
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();

		Assert.assertEquals(
				String.format(
						"CounterGeotask constructed with an x value of %d should be valid.",
						VALID_X), VALID_X, test.getX());
		Assert.assertEquals(
				String.format(
						"CounterGeotask constructed with a y value of %d should be valid.",
						VALID_Y), VALID_Y, test.getY());
	}

	@Test
	@Points(value = 2)
	public void testSingleMoveIn() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.MobileObject mo = new edu.iastate.cs228.hw1.MobileObject(
				GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
				getValidGround(GROUND_DIM_X, GROUND_DIM_Y));

		test.moveIn(mo);

		final int CORRECT_COUNT = 1;

		Assert.assertEquals(
				String.format(
						"After moving a single Mobile Object in, a CounterGeotask should have %d visitors",
						CORRECT_COUNT), CORRECT_COUNT,
				((edu.iastate.cs228.hw1.CounterGeotask) test)
						.getNumberOfVisitors());
	}

	@Test
	@Points(value = 2)
	public void testMultiMoveIn() {
		// Adds 3 of the same MobileObject to the Geotask and checks that the
		// number of Visitors is valid
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.Ground ground = getValidGround(GROUND_DIM_X,
				GROUND_DIM_Y);

		// Just three MobileObjects
		edu.iastate.cs228.hw1.MobileObject[] mos = {
				new edu.iastate.cs228.hw1.MobileObject(
						GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
						ground),
				new edu.iastate.cs228.hw1.MobileObject(
						GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
						ground),
				new edu.iastate.cs228.hw1.MobileObject(
						GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
						ground) };

		int CORRECT_COUNT = 1;

		int calls = 1;
		for (edu.iastate.cs228.hw1.MobileObject mo : mos) {
			test.moveIn(mo);
			Assert.assertEquals(
					String.format(
							"After calling moveIn %d times, getNumberOfVisitors should be: %d",
							calls, CORRECT_COUNT), CORRECT_COUNT,
					((edu.iastate.cs228.hw1.CounterGeotask) test)
							.getNumberOfVisitors());
			++calls;
			++CORRECT_COUNT;
		}
	}

	@Test
	@Points(value = 2)
	public void testSingleMoveOut() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.MobileObject mo = new edu.iastate.cs228.hw1.MobileObject(
				GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
				getValidGround(GROUND_DIM_X, GROUND_DIM_Y));

		test.moveIn(mo);
		test.moveOut(mo);

		final int CORRECT_COUNT = 1;

		Assert.assertEquals(
				String.format("After one MobileObject moves out of a CounterGeotask it's number of visitors shouldn't change."),
				CORRECT_COUNT, ((edu.iastate.cs228.hw1.CounterGeotask) test)
						.getNumberOfVisitors());
	}

	@Test
	@Points(value = 2)
	public void testMultiMoveOut() {
		edu.iastate.cs228.hw1.Geotask test = getValidInstance();
		edu.iastate.cs228.hw1.Ground ground = getValidGround(GROUND_DIM_X,
				GROUND_DIM_Y);

		// Just three MobileObjects
		edu.iastate.cs228.hw1.MobileObject[] mos = {
				new edu.iastate.cs228.hw1.MobileObject(
						GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
						ground),
				new edu.iastate.cs228.hw1.MobileObject(
						GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
						ground),
				new edu.iastate.cs228.hw1.MobileObject(
						GeotaskTest.MOBILE_OBJ_ID++, VALID_X, VALID_Y, 1, 1,
						ground) };

		// Add them all
		for (edu.iastate.cs228.hw1.MobileObject mo : mos) {
			test.moveIn(mo);
		}

		int calls = 1;

		final int CORRECT_COUNT = 3;

		// Move them out
		for (edu.iastate.cs228.hw1.MobileObject mo : mos) {
			test.moveOut(mo);
			Assert.assertEquals(String.format(
					"After calling moveOut %d times on a CounterGeotask with 3 visitors, "
							+ "getNumberOfVisitors should be unchanged",
					calls++), CORRECT_COUNT,
					((edu.iastate.cs228.hw1.CounterGeotask) test)
							.getNumberOfVisitors());
		}
	}

	@Test
	@Points(value = 1)
	public void testPrintType() {
		super.redirectStdOut();
		getValidInstance().printType();
		super.restoreStdOut();

		String output = super.getOutput();

		String[] keywords = { "Counter", "Geotask" };

		for (String keyword : keywords) {
			if (output.contains(keyword.toLowerCase())) {
				return;
			}
		}

		Assert.fail(String
				.format("CounterGeotask's printType should be similar to 'This is a CounterGeotask'"));
	}
}
