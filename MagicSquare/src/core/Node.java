package core;
/** 
 *  The Node object is the building block of a dynamic container. 
 *  <p>
 *  It has pointers to the next and previous nodes. 
 *  @author Tyler Cole
 *  @version 1.0
 *  @since 2014-03-09
 */
//This code was written in a previous class. It's functionality has been changed here to meet
//the requirements of the assignemnt.
public class Node
{
	Node next;
	Node prev;
	private int elem;
	
	/** 
	 *  This is the default constructor. It defaults all values to null.
	 */
	public Node()
	{
		next = null;
		prev = null;
		elem = 0;
	}
	
	/** 
	 *  This stores a specified int value in the created node, but also leaves it with empty 
	 *  pointers to be assigned later.
	 *  @param elem		The integer to be stored in the Node
	 */
	public Node(int elem)
	{
		next = null;
		prev = null;
		this.elem = elem;
	}
	
	/** 
	 *  This stores a specified int value in the node. It also assigns the next and previous 
	 *  nodes.
	 *  @param elem		The integer to be stored in the Node.
	 *  @param next		The Node that comes next in the container.
	 *  @param prev		The Node that comes prevoiusly in the container.
	 */
	public Node(int elem, Node prev, Node next)
	{
		this.next = next;
		this.prev = prev;
		this.elem = elem;
	}
	
	/** 
	 *  This will return the Node pointed to in 'next'.
	 *  @return 		The next Node in the container.
	 */
	public Node getNext()
	{
		return next;
	}
	
	/** 
	 *  This will return the Node pointed to in 'prev'.
	 *  @return 		The previous Node in the container.
	 */
	public Node getPrev()
	{
		return prev;
	}
	
	/** 
	 *  This will change the Node pointed to in 'next'.
	 *  @param next		The new Node to come after in the array.
	 */
	public void setNext(Node next)
	{
		this.next = next;
	}
	
	/** 
	 *  This will change the Node pointed to in 'prev'.
	 *  @param prev		The new Node to come before in the array.
	 */
	public void setPrev(Node prev)
	{
		this.prev = prev;
	}
	
	/** 
	 *  This will return the element stored inside the Node.
	 *  @return 		The integer stored in the Node.
	 */
	public int getElem()
	{
		return elem;
	}
	
	/** 
	 *  This will change the value stored in the Node.
	 *  @param elem		The new integer to be stored in the Node.
	 */
	public void setElem(int elem)
	{
		this.elem = elem;
	}
}