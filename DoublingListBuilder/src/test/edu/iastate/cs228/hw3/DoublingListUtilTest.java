package test.edu.iastate.cs228.hw3;


import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

import edu.iastate.cs228.hw3.DoublingList;
import edu.iastate.cs228.hw3.Node;

/**
 * Tests for the DoublingListUtil class.
 * @author Brandon
 *
 */
public class DoublingListUtilTest {
	// Names to use with reflection
	private static final String NUM_NODES_NAME = "numNodes";
	private static final String SIZE_NAME = "size";
	
	@Test
	public void testNull() {
		DoublingList<String> test = DoublingListUtil.buildList(null);
		testList(test, null, 0, 0);
		hasAllElements(test, null);
	}
	
	@Test
	public void testEmptyArray() {
		DoublingList<String> test = DoublingListUtil.buildList(new String[] {});
		testList(test, new String[] {}, 0, 0);
	}
	
	@Test
	public void testSingleEle() {
		String[] data = {"A"};
		DoublingList<String> test = DoublingListUtil.buildList(data);
		testList(test, data, 1, 1);
	}
	
	@Test
	public void testMultiNodeFull() {
		String[] data = {"A", "B", "C", "D", "E", "F", "G"};
		DoublingList<String> test = DoublingListUtil.buildList(data);
		testList(test, data, 3, 7);
	}
	
	@Test
	public void testMultiNodeNotFull() {
		String[] data = {"A", "B", "C", "D", "E"};
		DoublingList<String> test = DoublingListUtil.buildList(data);
		testList(test, data, 3, 5);
	}
	
	@Test
	public void testSingleNullValue() {
		String[] data = {"A", null, "B"};
		DoublingList<String> test = DoublingListUtil.buildList(data);
		testList(test, data, 2, 2);
	}
	
	@Test
	public void testManyNullValues() {
		String[] data = {null, "B", "C", "D", null, null, "G"};
		DoublingList<String> test = DoublingListUtil.buildList(data);
		testList(test, data, 3, 4);
	}
	
	@Test
	public void testAllNullValues() {
		String[] data = {null, null, null, null, null, null, null};
		DoublingList<String> test = DoublingListUtil.buildList(data);
		testList(test, data, 3, 0);
	}
	
	// Method that checks that numNodes, size, node references, and data are created properly
	private static <E> void testList(DoublingList<E> list, E[] data, int numNodes, int size) {
		Assert.assertEquals("Lists should be created with the proper number of nodes", numNodes, getInstanceField(list, NUM_NODES_NAME));
		Assert.assertEquals("Lists shoudl be created with the proper size", size, getInstanceField(list, SIZE_NAME));
		Assert.assertFalse("Lists shouldn't have cycles", hasCycle(list));
		Assert.assertTrue("Node references should be set up correctly", hasProperPointers(list));
		Assert.assertTrue("All the data should be in the list", hasAllElements(list, data));
	}
	
	/*
	 *  Method used to get instance field values from objects.
	 *  NOTE: It's usually a bad idea to rely on Reflection...but I wanted everything to be
	 *  completely independent of your implementaiton. Plus Reflection is fun.
	 */
	private static Object getInstanceField(Object object, String fieldName) {
		try {
			// Use reflection to grab the values
			Field field = object.getClass().getDeclaredField(fieldName);
			
			/*
			 *  Since they're private we need to make it accessible, otherwise
			 *  we'd get the IllegalAccessException
			 */		
			field.setAccessible(true);
			return field.get(object);
		} catch (Exception e) {
			/*
			 *  Typically I dislike seeing catch (Exception), but in this case, if any exception is
			 *  thrown, this is how we handle it
			 */
			System.out.println(String.format("Unable to retrieve instance field: %s", fieldName));
			return null;
		}
	}
	
	// Floyd's Cycle-finding Alg
	private static <E> boolean hasCycle(DoublingList<E> list) {
		/*
		 *  We use two references: fast will jump 2 nodes at a time
		 *  and slow with go 1 at a time. If there is a cycle, then 
		 *  at some point fast and slow will be the same. If either
		 *  gets to the end of the list, then there isn't a list 
		 */
		Node<E> fast = list.getHeadNode();
		Node<E> slow = list.getHeadNode();
		
		while (true) {
			slow = slow.getNext();
			if (fast.getNext() == list.getTailNode()) {
				return false;
			} 
			fast = fast.getNext().getNext();
			if (slow == list.getTailNode() || fast == list.getTailNode()) {
				return false;
			}
			
			if (slow == fast) {
				return true;
			}
		}
	}
	
	// Checks that the node references were set up properly
	private static <E> boolean hasProperPointers(DoublingList<E> list) {
		Node<E> prev = list.getHeadNode();
		Node<E> cur = list.getHeadNode().getNext();
		
		// Go thru the nodes, making sure that the nodes'
		// next and prev references are correct
		while (cur != null) {
			if (prev.getNext() != cur) {
				return false;
			}
			if (cur.getPrev() != prev) {
				return false;
			}
			prev = cur;
			cur = cur.getNext();
		}
		
		// Since we break out of the loop before checking tail, do it here
		if (prev != list.getTailNode()) {
			return false;
		}
		if (prev.getNext() != null) {
			return false;
		}
		return true;
	}
	
	// Checks that all the data was properly set up
	private static <E> boolean hasAllElements(DoublingList<E> list, E[] eles) {
		int curEle = 0;
		Node<E> curNode = list.getHeadNode().getNext();
		
		while (curNode != list.getTailNode()) {
			// Safety check for loop
			if (curNode.getData() == null) {
				return false;
			}
			
			for (E data : curNode.getData()) {
				/*
				 *  You're not required to supply all the data in the last node,
				 *  it'll be filled in with null, so it's possible to have extra elements
				 *  past the length of the given 'eles' array. If that happens, we just
				 *  use null
				 *  
				 *  Ternary operator format:
				 *  		(boolean-expression) ? if-true : if-false;
				 */
				E propData = (curEle < eles.length) ? eles[curEle++] : null;
				// Equality check for nulls and non nulls
				if (propData == data || propData != null && propData.equals(data)) {
					continue;
				}
				return false;
			}
			
			curNode = curNode.getNext();
		}
		
		return true;
	}
}
