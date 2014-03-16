package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * A doubly linked list that stores data in Nodes with varying size arrays as
 * the backing store.
 * Important Note: Your index-based methods remove(int pos), add(int pos, E
 * item) and listIterator(int pos) must not traverse every element in order to
 * find the node and offset for a given index pos (see spec for more details)
 */

public class DoublingList<E> extends AbstractSequentialList<E> {

	/**
	 * Node to keep track of the head (beginning of the list)
	 */
	private Node<E> head;

	/**
	 * Node to keep track of the tail (end of the list)
	 */
	private Node<E> tail;
	
	/**
	 * The number of actual nodes in the list
	 */
	private int numNodes;
	
	/**
	 * A count of the number of data elements in the list
	 */
	private int size;
	

	/**
	 * Constructs an empty DoublingList
	 */
	public DoublingList() {
	}
	
	/**
	 * A constructor to be called by the tests when it's necessary to manually create
	 * the internal structure of the list. 
	 * 
	 * NOTE: In real life you would never have this constructor. It is simply used so
	 * we can test your remove methods without relying on the add methods working properly
	 * 
	 * @param head
	 * @param tail
	 * @param numNodes
	 * @param size
	 */
	public DoublingList(Node<E> head, Node<E> tail, int numNodes, int size) {
		this.head = head;
		this.tail = tail;
		this.numNodes = numNodes;
		this.size = size;
		
		/*
		 * TODO any additional initialization code you need.
		 * It is not necessarily the case that you need anything here. 
		 */
	}
	
	/**
	 * Returns the head node of the list.
	 * 
	 *  NOTE: Again, in real life you would never have this method. It is just used 
	 *  in the tests so we don't need to rely on your get method.
	 * @return
	 * 		the head node of the list
	 */
	public Node<E> getHeadNode() {
		return head;
	}
	
	/**
	 * Returns the tail node of the list.
	 * 
	 *  NOTE: Again, in real life you would never have this method. It is just used 
	 *  in the tests so we don't need to rely on your get method.
	 * @return
	 * 		the tail node of the list
	 */
	public Node<E> getTailNode() {
		return tail;
	}
	
	/**
	 * Removes the element with the given logical position, following the rules
	 * for removing an element.
	 */
	@Override
	public E remove(int pos) {
		// TODO
		return null;
	}

	/**
	 * Adds the given item to have the given logical position. Adds a new Node
	 * if necessary. Follows the rules stated by leftward and rightward shift.
	 */
	@Override
	public void add(int pos, E item) {
		// TODO
	}

	/**
	 * Adds the given item to the end of the list. Creates a new Node if
	 * Necessary. Return true if the add was successful, false otherwise.
	 * 
	 * @throws NullPointerException 
	 * 				If the item is null.
	 */
	@Override
	public boolean add(E item) {
		// TODO
		return false;
	}

	/**
	 * Returns a ListIterator for this DoublingList at the given position (I.E.
	 * a call to next should return the element with the logical index equal to
	 * the index given)
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO
		return null;
	}

	/**
	 * Returns a ListIterator for this DoublingList starting from the beginning
	 */
	@Override
	public ListIterator<E> listIterator() {
		// TODO
		return new DoublingListIterator();
	}

	/**
	 * Returns an Iterator for this DoublingList starting from the beginning
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO
		return null;
	}

	/**
	 * Returns the size of the list. It is acceptable to use the size instance
	 * variable and update it during add / remove so you can just return that
	 * variable here.
	 */
	@Override
	public int size() {
		// TODO
		return 0;
	}

	/**
	 * ListIterator class. Please reference the ListIterator API to see how
	 * methods handle errors (no next element, null arguments, etc.)
	 * 
	 * API: http://docs.oracle.com/javase/6/docs/api/java/util/ListIterator.html
	 */
	private class DoublingListIterator implements ListIterator<E> {
		int index = 0;
		/**
		 * Adds the given element to the DoublingList following the rules of
		 * add(). DO NOT call the add method you wrote for DoublingList above!
		 * This one needs to run in AMORTIZED O(1) (constant time).
		 */
		@Override
		public void add(E arg0) {
			// TODO
			// DO NOT call DoublingList add methods here!
		}

		/**
		 * Returns true if this list iterator has more elements when traversing
		 * the list in the forward direction. (In other words, returns true if
		 * next would return an element rather than throwing an exception.)
		 */

		@Override
		public boolean hasNext() {
			// TODO
			return false;
		}

		/**
		 * Returns true if this list iterator has more elements when traversing
		 * the list in the reverse direction. (In other words, returns true if
		 * previous would return an element rather than throwing an exception.)
		 */

		@Override
		public boolean hasPrevious() {
			// TODO
			return false;
		}

		/**
		 * Returns the next element in the list. This method may be called
		 * repeatedly to iterate through the list, or intermixed with calls to
		 * previous to go back and forth. (Note that alternating calls to next
		 * and previous will return the same element repeatedly.)
		 */
		@Override
		public E next() {
			++index;
			return null;
		}

		/**
		 * Returns the index of the element that would be returned by a
		 * subsequent call to next. (Returns list size if the list iterator is
		 * at the end of the list.)
		 */
		@Override
		public int nextIndex() {
			return index;
		}

		/**
		 * 
		 * Returns the previous element in the list. This method may be called
		 * repeatedly to iterate through the list backwards, or intermixed with
		 * calls to next to go back and forth. (Note that alternating calls to
		 * next and previous will return the same element repeatedly.)
		 */

		@Override
		public E previous() {
			// TODO
			return null;
		}

		/**
		 * Returns the index of the element that would be returned by a
		 * subsequent call to previous. (Returns -1 if the list iterator is at
		 * the beginning of the list.)
		 */

		@Override
		public int previousIndex() {
			// TODO
			return 0;
		}

		/**
		 * Removes from the list the last element that was returned by next or
		 * previous (optional operation). This call can only be made once per
		 * call to next or previous. It can be made only if ListIterator.add has
		 * not been called after the last call to next or previous. DO NOT call
		 * the remove method you wrote for DoublingList above! This one should
		 * run in AMORTIZED O(1) (constant time)
		 */
		@Override
		public void remove() {
			// TODO
			// DO NOT call DoublingList remove methods here
		}

		/**
		 * Replaces the last element returned by next or previous with the
		 * specified element (optional operation). This call can be made only if
		 * neither ListIterator.remove nor ListIterator.add have been called
		 * after the last call to next or previous.
		 */
		@Override
		public void set(E arg0) {
			// TODO
		}
	}

	/**
	 * Iterator to be used for traversing a DoublingList. This iterator is
	 * optional if you fully implement the ListIterator but is easier and
	 * partial point will be awarded if the one is correct and your ListIterator
	 * is wrong.

	 * API: http://docs.oracle.com/javase/6/docs/api/java/util/Iterator.html
	 */
	private class DoublingIterator implements Iterator<E> {
		/**
		 * Returns true if the iteration has more elements. (In other words,
		 * returns true if next would return an element rather than throwing an
		 * exception.)
		 */
		@Override
		public boolean hasNext() {
			// TODO
			return false;
		}

		/**
		 * Returns the next element in the iteration.
		 */
		@Override
		public E next() {
			// TODO
			return null;
		}

		/**
		 * You do not need to implement this method
		 */

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * NodeInfo class that you may find useful to use. Again, feel free to add
	 * methods / constructors / variables that you find useful in here.
	 */
	private class NodeInfo {
		public Node<E> node;
		public int offset;

		public NodeInfo(Node<E> node, int offset) {
			this.node = node;
			this.offset = offset;
		}
	}

	/**
	 * Returns a string representation of this list showing the internal
	 * structure of the nodes.
	 */

	public String toStringInternal() {
		return toStringInternal(null);
	}

	/**
	 * Returns a string representation of this list showing the internal
	 * structure of the nodes and the position of the iterator.
	 * @param iter
	 *            an iterator for this list
	 */

	public String toStringInternal(ListIterator<E> iter) {
		int count = 0;
		int position = -1;
		if (iter != null) {
			position = iter.nextIndex();
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');

		Node<E> current = head.getNext();
		while (current != tail) {
			sb.append('(');
			E data = current.getData()[0];
			if (data == null) {
				sb.append("-");
			} else {
				if (position == count) {
					sb.append("| ");
					position = -1;
				}
				sb.append(data.toString());
				++count;
			}

			for (int i = 1; i < current.getData().length; ++i) {
				sb.append(", ");
				data = current.getData()[i];
				if (data == null) {
					sb.append("-");
				} else {
					if (position == count) {
						sb.append("| ");
						position = -1;
					}

					sb.append(data.toString());
					++count;

					// iterator at end
					if (position == size() && count == size()) {
						sb.append(" |");
						position = -1;
					}
				}
			}
			sb.append(')');
			current = current.getNext();
			if (current != tail)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();

	}

}
