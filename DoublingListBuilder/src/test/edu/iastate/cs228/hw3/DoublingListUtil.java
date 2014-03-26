package test.edu.iastate.cs228.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import edu.iastate.cs228.hw3.DoublingList;
import edu.iastate.cs228.hw3.Node;

/**
 * Utility class with methods used to manually build a DoublingList
 * @author Brandon
 *
 */
public class DoublingListUtil {

	/**
	 * Method used to build a doubling list with the given elements.
	 * NOTE: It is possible to build invalid Node structures with this method
	 * since it doesn't pay attention to any compaction rules.
	 *
	 * If the given array doesn't have enough elements to fill the last Node,
	 * then the missing data will be replaced with nulls in the Node.
	 * 
	 *	Example:
	 *		buildList(new String[] {"A", "B", "C", "D"});
	 *	results in
	 *		[head] <-> [A] <-> [B | C] <-> [D|-|-|-] <-> [tail]
	 *
	 * 
	 * 	Example Usage:
	 * 		buildList(new String[] {"A", "B", null, "C", null, "D", "E"})
	 * 	results in:
	 * 		[head] <-> [A] <-> [B | -] <-> [C | - | D | E] <-> [tail]
	 * 
	 * @param eles
	 * 			an array of elements to put in the nodes. Null elements will be
	 * 		 	placed into the nodes as well as non-null values.
	 * @return
	 * 			A DoublingList containing the given elements, or an empty list if
	 * 			eles is null or empty
	 */
	public static <E> DoublingList<E> buildList(E[] eles) {
		if (eles == null || eles.length == 0) {
			return emptySetUp();
		}
		// Split the given elements into the arrays that will be placed in each node
		List<E[]> nodeDatas = split(eles);
		Node<E> head = new Node<E>(null);
		Node<E> tail = new Node<E>(null);
		int numNodes = nodeDatas.size();
		int size = calcSize(eles);
		
		// Actually do the building
		Node<E> last = buildListRec(nodeDatas, 0, head);
		
		// Since the recursion doesn't set up tail stuff, do it now
		last.setNext(tail);
		tail.setPrev(last);
		
		return new DoublingList<E>(head, tail, numNodes, size);
	}
	
	// Special case create for if we're given an empty or null array of elements
	private static <E> DoublingList<E> emptySetUp() {
		Node<E> head = new Node<E>(null);
		Node<E> tail = new Node<E>(null);
		head.setNext(tail);
		tail.setPrev(head);
		int numNodes = 0;
		int size = 0;
		return new DoublingList<E>(head, tail, numNodes, size);
	}
	
	/*
	 * Method used to split the list into arrays that will be placed
	 * in each node.
	 * 	Example:
	 * 		split(new String[] {"A", "B", null, "C", null, "D", "E"});
	 * 	results in:
	 * 		{[A], [B, null], [C, null, D, E]}
	 */
	private static <E> List<E[]> split(E[] eles) {
		List<E[]> nodeDatas = new ArrayList<E[]>();
		splitRec(nodeDatas, eles, 0, 1);
		return nodeDatas;
	}
	
	/*
	 * Recursive method that does all the splitting work
	 */
	private static <E> void splitRec(List<E[]> nodeDatas, E[] eles, int startIndex, int length) {
		// Nothing else to copy
		if (startIndex >= eles.length) {
			return;
		}
		int endIndex = startIndex + length;
		
		/* If the copyOfRange goes past the end of eles, then we get
		 * a bunch of nulls at the end of the copied array, which is
		 * what we want
		 */ 
		nodeDatas.add(Arrays.copyOfRange(eles, startIndex, endIndex));
		splitRec(nodeDatas, eles, endIndex, length * 2);
	}
	
	/*
	 * Counts all the non-null elements in eles.
	 */
	private static <E> int calcSize(E[] eles) {
		if (eles == null) return 0;
		int count = 0;
		for (E data : eles) {
			if (data != null) {
				++count;
			}
		}
		return count;
	}
	
	/*
	 * Creates nodes and links them together, thru the magic of recursion
	 */
	private static <E> Node<E> buildListRec(List<E[]> eles, int dataIndex, Node<E> prev) {
		// Nothing else to add
		if (dataIndex >= eles.size()) {
			return prev;
		}
		// Make something new to put the data in
		Node<E> newNode = new Node<E>(eles.get(dataIndex));
		
		// Set up references as necessary
		prev.setNext(newNode);
		newNode.setPrev(prev);
		return buildListRec(eles, ++dataIndex, newNode);
	}
	
	public static void main(String[] args) {
		DoublingList<String> list = DoublingListUtil.buildList(new String[] {"a", "b", "c", "d", "e"});
		ListIterator<String> iter = list.listIterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
	}
}
