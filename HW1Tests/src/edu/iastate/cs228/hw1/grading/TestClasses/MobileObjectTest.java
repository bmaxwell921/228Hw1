package edu.iastate.cs228.hw1.grading.TestClasses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs228.hw1.grading.Points;
import edu.iastate.cs228.hw1.grading.Total;

/**
 * Class to test MobileObject
 * @author Kyle Woolcock
 * @author brandon
 *
 */
@Total (value = 40)
public class MobileObjectTest {

	private edu.iastate.cs228.hw1.Ground ground;
	private edu.iastate.cs228.hw1.MobileObject test;
	
	@Before
	public void setup() {
		ground = new edu.iastate.cs228.hw1.Ground(20, 20);
	}
	
	@Test
	@Points (value = 3)
	public void NormalMoveConditionsGoingN()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 10, 10, 1, 0, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving N from %d", 9, 10), 
				9, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving N from %d", 10, 10), 
				10, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving N with no collision", 0), 
				0, test.getDirection());
	}
	
	@Test
	@Points (value = 2)
	public void NormalMoveConditionsGoingS()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 10, 10, 1, 1, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving S from %d", 11, 10), 
				11, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving S from %d", 10, 10), 
				10, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving S with no collisions.", 1), 
				1, test.getDirection());
	}
	
	@Test 
	@Points (value = 3)
	public void NormalMoveConditionsGoingE()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 10, 10, 1, 2, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving E from %d", 10, 10), 
				10, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving E from %d", 11, 10), 
				11, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving E with no collisions.", 2), 
				2, test.getDirection());
	}
	
	@Test 
	@Points (value = 2)
	public void NormalMoveConditionsGoingW()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 10, 10, 1, 3, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving W from %d", 10, 10), 
				10, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving W from %d", 9, 10), 
				9, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving W with no collisions.", 3), 
			3, test.getDirection());
	}
	
	@Test 
	@Points (value = 3)
	public void NormalMoveConditionsGoingNE()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 10, 10, 1, 4, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving NE from %d", 9, 10), 
				9, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving NE from %d", 11, 10), 
				11, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving NE with no collisions.", 4), 
				4, test.getDirection());
	}
	
	@Test 
	@Points (value = 2)
	public void NormalMoveConditionsGoingSE()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 10, 10, 1, 5, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving SE from %d", 11, 10), 
				11, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving SE from %d", 11, 10), 
				11, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving SE with no collisions.", 5), 
				5, test.getDirection());
	}
	
	@Test 
	@Points (value = 2)
	public void NormalMoveConditionsGoingSW()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 10, 10, 1, 6, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving SW from %d", 11, 10), 
				11, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving SW from %d", 9, 10), 
				9, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving SW with no collisions.", 6), 
				6, test.getDirection());
	}
	
	@Test 
	@Points (value = 3)
	public void NormalMoveConditionsGoingNW()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 10, 10, 1, 7, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving NW from %d", 9, 10), 
				9, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving NW from %d", 9, 10), 
				9, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving NW with no collisions.", 7), 
				7, test.getDirection());
	}
	
	@Test 
	@Points (value = 3)
	public void MoveNorthBoundaryGoingN()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 19, 0, 1, 0, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving N and colliding from %d", 0, 0), 
				0, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving N and colliding from %d", 19, 19), 
				19, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving N and colliding.", 1), 
				1, test.getDirection());
	}
	
	@Test 
	@Points (value = 2)
	public void MoveSouthBoundaryGoingS()  {
		test = new edu.iastate.cs228.hw1.MobileObject( 0, 0, 19, 1, 1, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving S and colliding from %d", 19, 19), 
				19, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving S and colliding from %d", 0, 0), 
				0, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving S and colliding.", 0), 
				0, test.getDirection());
	}
	
	@Test 
	@Points (value = 3)
	public void MoveEastBoundaryGoingE()  {
		test = new edu.iastate.cs228.hw1.MobileObject( 0, 19, 0, 1, 2, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving E and colliding from %d", 0, 0), 
				0, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving E and colliding from %d", 19, 19), 
				19, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving E and colliding.", 3), 
				3, test.getDirection());
	}
	
	@Test 
	@Points (value = 2)
	public void MoveWestBoundaryGoingW()  {
		test = new edu.iastate.cs228.hw1.MobileObject( 0, 0, 19, 1, 3, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving W and colliding from %d", 19, 19), 
				19, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving W and colliding from %d", 0, 0), 
				0, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving W and colliding.", 2), 
				2, test.getDirection());
	}
	
	@Test
	@Points (value = 3)
	public void MoveNorthEastBoundaryGoingNEAtCorner()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 19, 0, 1, 4, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving NE and colliding from %d", 0, 0), 
				0, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving NE and colliding from %d", 19, 19), 
				19, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving NE and colliding.", 6), 
				6, test.getDirection());
	}
	
	@Test 
	@Points (value = 2)
	public void MoveNorthWestBoundaryGoingNWAtCorner()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 0, 0, 1, 7, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving NW and colliding from %d", 0, 0), 
				0, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving NW and colliding from %d", 0, 0), 
				0, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving NW and colliding.", 5), 
				5, test.getDirection());
	}
	
	@Test 
	@Points (value = 3)
	public void MoveSouthWestBoundaryGoingSWAtCorner()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 0, 19, 1, 6, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving SW and colliding from %d", 19, 19), 
				19, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving SW and colliding from %d", 0, 0), 
				0, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving SW and colliding.", 4), 
				4, test.getDirection());
	}
	
	@Test 
	@Points (value = 2)
	public void MoveSouthEastBoundaryGoingSEAtCorner()  {
		test = new edu.iastate.cs228.hw1.MobileObject(0, 19, 19, 1, 5, ground);
		test.move();
		Assert.assertEquals(String.format("A MobileObject should have a Y value of %d after moving SE and colliding from %d", 19, 19), 
				19, test.getCurrentY());
		Assert.assertEquals(String.format("A MobileObject should have a X value of %d after moving SE and colliding from %d", 19, 19), 
				19, test.getCurrentX());
		Assert.assertEquals(String.format("A MobileObject should have a direction value of %d after moving SE And colliding.", 7), 
				7, test.getDirection());
	}
}
