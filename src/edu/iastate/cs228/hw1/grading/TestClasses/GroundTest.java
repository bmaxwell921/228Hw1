package edu.iastate.cs228.hw1.grading.TestClasses;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs228.hw1.grading.Points;
import edu.iastate.cs228.hw1.grading.Total;

/**
 * Class used to test the Ground object
 * 
 * @author Kyle Woolcock
 * @author Brandon
 * 
 */
@Total(value = 10)
public class GroundTest {

	private edu.iastate.cs228.hw1.Ground ground;
	private edu.iastate.cs228.hw1.CounterGeotask countGeo;
	private edu.iastate.cs228.hw1.WarningGeotask warnGeo2;
	private List<edu.iastate.cs228.hw1.Geotask> arr;

	@Before
	public void setup() {

		countGeo = new edu.iastate.cs228.hw1.CounterGeotask(5, 5);
		warnGeo2 = new edu.iastate.cs228.hw1.WarningGeotask(5, 5);
		arr = new ArrayList<edu.iastate.cs228.hw1.Geotask>();
	}

	@Test
	@Points(value = 1)
	public void testGetGeotaskEmpty() {
		ground = new edu.iastate.cs228.hw1.Ground(20, 20);
		List<edu.iastate.cs228.hw1.Geotask> result = ground.getGeotask(5, 5);
		// Allow null here since the Professor's solution returns null
		boolean success = (result == null) || (result != null)
				&& result.isEmpty();
		Assert.assertTrue(
				"GetGeotasks should return correctly if there are no geotasks at a location",
				success);
	}

	@Test
	@Points(value = 2)
	public void AddGeotaskAndRetrieve() {
		ground = new edu.iastate.cs228.hw1.Ground(20, 20);
		ground.addGeotask(countGeo);
		arr.add(countGeo);
		Assert.assertArrayEquals(
				String.format("GetGeotasks should return an ArrayList of one element when there is a Geotask "
						+ "at the given location"), arr.toArray(), ground
						.getGeotask(5, 5).toArray());
	}

	@Test
	@Points(value = 2)
	public void AddGeotaskAndRetrieveTwoItems() {
		ground = new edu.iastate.cs228.hw1.Ground(20, 20);
		ground.addGeotask(countGeo);
		ground.addGeotask(warnGeo2);
		arr.add(countGeo);
		arr.add(warnGeo2);
		Assert.assertArrayEquals(
				String.format("GetGeotasks should return an ArrayList of two elements when there are two Geotasks "
						+ "at the given location"), arr.toArray(), ground
						.getGeotask(5, 5).toArray());
	}

	@Test
	@Points(value = 1)
	public void testNoGeotaskAtMobileLocation() {
		ground = new edu.iastate.cs228.hw1.Ground(20, 20);
		List<edu.iastate.cs228.hw1.Geotask> result = ground
				.getGeotask(new edu.iastate.cs228.hw1.MobileObject(0, 5, 5, 1,
						0, ground));
		// Allow null here since the Professor's solution returns null
		boolean success = (result == null) || (result != null)
				&& result.isEmpty();
		Assert.assertTrue(
				"GetGeotasks should return correctly if there are no geotasks at a location",
				success);
	}

	@Test
	@Points(value = 2)
	public void testSingleGeotaskAtMobileLocation() {
		ground = new edu.iastate.cs228.hw1.Ground(20, 20);
		ground.addGeotask(countGeo);
		arr.add(countGeo);
		Assert.assertArrayEquals(
				String.format("GetGeotasks should return an ArrayList of one element when there is a Geotask "
						+ "at the given MobileObject's location"),
				arr.toArray(),
				ground.getGeotask(
						new edu.iastate.cs228.hw1.MobileObject(0, 5, 5, 1, 0,
								ground)).toArray());
	}

	@Test
	@Points(value = 2)
	public void AddGeotaskAndRetrieveFromMobile() {
		ground = new edu.iastate.cs228.hw1.Ground(20, 20);
		ground.addGeotask(countGeo);
		ground.addGeotask(warnGeo2);
		arr.add(countGeo);
		arr.add(warnGeo2);
		Assert.assertArrayEquals(
				String.format("GetGeotasks should return an ArrayList of two elements when there are two Geotasks "
						+ "at the given MobileObject's location"),
				arr.toArray(),
				ground.getGeotask(
						new edu.iastate.cs228.hw1.MobileObject(0, 5, 5, 1, 0,
								ground)).toArray());
	}

}
