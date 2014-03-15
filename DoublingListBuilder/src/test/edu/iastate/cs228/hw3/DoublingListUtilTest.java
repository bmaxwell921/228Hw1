package test.edu.iastate.cs228.hw3;


import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw3.DoublingList;


public class DoublingListUtilTest {
	
	@Test
	public void testNull() {
		DoublingList<String> test = DoublingListUtil.buildList(null);
		testList(test, 0, 0);
		Assert.assertEquals("Testing with null should return an empty list", test.getHeadNode().getNext(), 
				test.getTailNode());
		Assert.assertEquals("Testing with null should return an empty list", test.getTailNode().getPrev(), 
				test.getHeadNode());
	}
	
	private void testList(DoublingList<String> list, int numNodes, int size) {
		Assert.assertEquals("Testing with null should return an empty list", numNodes, list.getNumNodes());
		Assert.assertEquals("Testing with null should return an empty list", size, list.getSize());
	}
}
