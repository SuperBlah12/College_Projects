package core;
/**
 * This is a dynamic list container.
 * @author Tyler Cole
 * @version 1.0
 * @since 2014-04-05
 */
//This code was written in a previous class. It's functionality has been changed here to meet
//the requirements of the assignemnt.
public class List
{
	Node first;
	Node last;
	int size;
	/**
	 * Class constructor.
	 */
	public List()
	{
		first = null;
		last = null;
		size = 0;
	}
	/**
	 * Returns the integer at the beginning of the list and then replaces the node with the 
	 * next one.
	 * @return 		the integer stored in the first node.
	 */	
	public int removeFirst()//removes first element.
	{
		int elem;
		if(size == 0)
			return -1;//If list is empty return -1.
		else
		{
			elem = first.getElem();
			first = first.getNext();
			size--;
		}
		return elem;
	}
	/**
	 * Returns the integer at the end of the list and then replaces the first node with the 
	 * next one. Because this is the last node, it is usually replaced by null.
	 * @return		the integer stored in the last node.
	 */	
	public int removeLast()//removes last element
	{
		int elem;
		if(size == 0)
			return -1;//if list is empty return -1
		else
		{
			elem = last.getElem();
			last = last.getPrev();
			size--;
		}
		return elem;
	}
	/**
	 * Returns the integer located at k, then adjusts the pointers to remove the node. If 
	 * there is no node at k, this returns null.
	 * @param k		the integer representing the location the node to be removed.
	 * @return 		the interger stored in the kth node. 
	 */	
	public int removeElement(int k)//remove element located at k
	{
		int elem;
		Node temp = first;
		if(size == 0 || k > size || 0 > k)
			return -1; //if list is empty of k is out of bounds, return -1.
		else
		{
			if(k == 0)//remove first
			{
				elem = first.getElem();
				first = first.getNext();
			}
			else if(k == (size-1))//remove last
			{
				elem = last.getElem();
				last = last.getPrev();
			}
			else//remove anything inbetween
			{
				for(int x = 0; x < k; x++)
					temp = temp.getNext();
				elem = temp.getElem();
				(temp.getPrev()).setNext(temp.getNext());
				(temp.getNext()).setPrev(temp.getPrev());
			}
			size--;
		}
		return elem;
	}
	/**
	 * Returns the integer at position k.
	 * If there is no node at position k, it returns null.
	 * @param k		the integer position of the node where the desired integer is stored.
	 * @return		the integer stored in the kth node.
	 */
	public int get(int k)//return elem found at position
	{
		Node temp = first;
		if (size == 0 || k >= size || 0 > k)
			return -1;//if list is empty, or k is out of bounds, return -1
		else if(k == 0)//return first
			return first.getElem();
		else if(k == (size-1))//return last
			return last.getElem();
		else//return anything inbetween
		{
			for(int x = 0; x < k; x++)
				temp = temp.getNext();
			return temp.getElem();
		}
	}
	/**
	 * Creates a new node and places it at position k with the value elem stored inside.
	 * If k does not exist, the integer is added to the end.
	 * @param k		the integer position where the new node will go.
	 * @param elem  the integer element to be stored inside the new node.
	 */
	public void add(int elem, int k)//add elem at position k
	{
		Node temp = new Node(elem);
		Node iter = first;
		if(k == 0) //add after first
		{
			temp.setNext(first.getNext());
			(first.getNext()).setPrev(temp);
			first.setNext(temp);
			temp.setPrev(first);
			size++;
		}
		else if(k == size)//add after last
		{
			temp.setPrev(last);
			last.setNext(temp);
			last = temp;
			size++;
		}
		else if(k > size || 0 > k)//if k is out of bounds, do nothing
		{}
		else //add elem at any other k
		{
			for(int x = 0; x < k; x++)
				iter = iter.getNext();
			temp.setNext(iter.getNext());
			(iter.getNext()).setPrev(temp);
			iter.setNext(temp);
			temp.setPrev(iter);
			size++;
		}
	}
	/**
	 * Creates a new node and places it at the end with the value elem stored inside.
	 * @param elem  the integer element to be stored inside the new node.
	 */
	public void add(int elem)//add elem to last of list
	{
		Node temp = new Node(elem);
		if(size == 0) //if list is empty perform special tasks
		{
			first = temp;
			last = temp;
		}
		else //all other cases
		{
			temp.setPrev(last);
			last.setNext(temp);
			last = temp;
		}
		size++;
	}
	/**
	 * Searches the list and returns the integer position of s. If s is not present in the list
	 * it returns -1.
	 * @param s		The integer value being searched for in the list.
	 * @return		the integer value of the position where s is located. Returns -1 if s is 
	 * not in the list
	 */
	public int contains (int s)//return position in list that contains s. if s is not found return -1 
	{
		Node temp = first;
		int y = 0;
		if(size == 0)//list is empty, return -1
			return -1;
		for(int x = 0; x < size; x++)//find s return position
		{
			int tester = temp.getElem();
			if(tester == s)
			{
				y = x;
				break;
			}
			temp = temp.getNext();
		}
		return y;
	}
	/**
	 * Checks if the list is empty, returns true if so.
	 * @return		the boolean describing if the list is empty.
	 */
	public boolean isEmpty()//return true if list is empty
	{
		if(size == 0)
			return true;
		else
			return false;
	}
	/**
	 *  Returns the integer length of the list.
	 *  @return		The integer value of the number of elements in the list.
	 */
	public int getSize()//return size of list
	{
		return size;
	}
	/**
	 * Returns the integer number of elements in the list.
	 */
	public String toString()//convert list into a string
	{
		String list = "Size=" + size + " Elements: ";
		int y = 0;
		Node temp = first;
		if(size == 0)
			list = list + "EMPTY";
		for(int x = 0; x < size; x++)
		{
			list = list + y + ". " + temp.getElem() + " ";
			temp = temp.getNext();
			y++;
		}
		return list;
	}
}