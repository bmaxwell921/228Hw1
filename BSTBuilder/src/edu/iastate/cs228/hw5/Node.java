package edu.iastate.cs228.hw5;

/**
 * 
 * @author 
 *
 */


/**
 * 
 * This class represents a tree node.  The class is public but in the BST class 
 * the root node will be protected. 
 *
 */

// also okay to use the following. 
// public class Node<E extends Comparable<? super E>>
public class Node<E> 
{
	private E data; 
	private Node<E> parent; 
	private Node<E> left; 
	private Node<E> right; 
	
	
	public Node(E dat)
	{
		// TODO
	}

	public Node(E dat, Node<E> left, Node<E> right)
	{
		// TODO
	}
	
	/**
	 * Write the value of the instance variable named data.
	 */
	public String toString()
	{
		// TODO
		
		return null; 
	}

	public E getData()
	{
		// TODO
		
		return null; 
	}
	
	public void setData(E dat)
	{
		// TODO
	}
	
	public Node<E> getParent()
	{
		// TODO
		
		return null; 
	}
	
	public void setParent(Node<E> parent)
	{
		// TODO 
	}
	
	public Node<E> getLeft()
	{
		// TODO
		
		return null; 
	}
	
	public void setLeft(Node<E> left)
	{
		// TODO
	}
	
	public Node<E> getRight()
	{
		// TODO
		
		return null; 
	}
	
	public void setRight(Node<E> right)
	{
		// TODO
	}
	
}
