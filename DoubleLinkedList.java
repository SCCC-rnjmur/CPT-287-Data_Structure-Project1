package project1;

import java.util.ListIterator;

@SuppressWarnings("rawtypes")
public class DoubleLinkedList<T> implements Iterable {
	// Node to store Objects
	public class Node {
		// Node Data fields
		public T data;
		public Node next, prev;
		public Node(T theData) {data = theData; } // Node Constructor
	}
	
	// Data fields
	private Node head, tail;
	private int numOfNodes;
	
	// Constructors
	public DoubleLinkedList() {}  // Default constructor
	
	public DoubleLinkedList(DoubleLinkedList<T> other) {
		/**
		 * Copy Constructor (deep copy)
		 *
		 * @param	DoubleLinkedList<T>	DoubleLinkedList to copy
		 */
		this.head = new Node(other.head.data);
		Node p = this.head, q = other.head.next;
		while (q != null) {
			p.next = new Node(q.data);
			p.next.prev = p;
			p = p.next;
			q = q.next;
		}
		this.tail = p;
		this.numOfNodes = other.numOfNodes;
	}
	
	// Methods
	private void sort() {
		if (numOfNodes == 0) { } // Do Nothing
		else {
			for (int i = 0; i < numOfNodes; i++) {
				
			}
		}
	}
	
	public void addFirst(T item) {
		/**
		 * Add {item} to the front of the linked list
		 *
		 * @param	T	object to add
		 */
		if (numOfNodes++ == 0) {
			head = tail = new Node(item);
		} else {
			head.prev = new Node(item);
			head.prev.next = head;
			head = head.prev;
		}
	}
	
	public void addLast(T item) {
		/**
		 * Add {item} to the end of the linked list
		 *
		 * @param	T	object to add
		 */
		if (numOfNodes++ == 0) {
			head = tail = new Node(item);
		} else {
			tail.next = new Node(item);
			tail.next.prev = tail;
			tail = tail.next;
		}
	}
	
	public T removeFirst() throws Exception {
		/**
		 * Removes and returns first {item} in linkedlist
		 * throws Exception if list is empty
		 *
		 * @return	T	object to return
		 */
		if (numOfNodes == 0) {
			throw new Exception("Attempt to remove item from empty list");
		}
		T returnVal = head.data;
		if (numOfNodes-- == 1) {
			head = tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		return returnVal;
	}
	
	public T removeLast() throws Exception {
		/**
		 * Removes and returns last {item} in linkedlist
		 * throws Exception if list is empty
		 *
		 * @return	T	object to return
		 */
		if (numOfNodes == 0) {
			throw new Exception("Attempt to remove item from empty list");
		}
		T returnVal = tail.data;
		if (numOfNodes-- == 1) {
			head = tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		return returnVal;
	}
	
	public int size() { return numOfNodes; } // return LinkedList size
	
	public boolean isEmpty() { return numOfNodes == 0; } //return isEmpty true or false

	@Override
	public ListIterator iterator() {
		/**
		 * Creates a ListIterator
		 *
		 * @return  ListIterator	returns ListIterator object
		 */
		return new ListIterator<T>() {
			// copy DLList head to current so starting point is
			// at the head
			Node current = head;

			@Override
			public boolean hasNext() {
				/**
				 * Check if there is still data to be read next
				 *
				 * @return  boolean	check if there is next data waiting to be read
				 */
				return current != null;
			}
			
			@Override
			public boolean hasPrevious() {
				/**
				 * Check if there is still data to be read previous
				 *
				 * @return  boolean	check if there is previous data waiting to be read
				 */
				return current != null;
			}

			@Override
			public T next() {
				/**
				 * returns data in current token then moves token next
				 *
				 * @return  T	returns object stored in current token
				 */
				T current_item = current.data;
				current = current.next;
				return (T) current_item;
			}
			
			@Override
			public T previous() {
				/**
				 * returns data in current token then moves token to previous
				 *
				 * @return  T	returns object stored in current token
				 */
				T current_item = current.data;
				current = current.prev;
				return (T) current_item;
			}
			
			@Override
			public void add(T item) {
				/**
				 * inserts item at current token location
				 *
				 * @param	T	object to insert
				 */
				if (numOfNodes++ == 0) {
					head = tail = new Node(item);
				} else {
					current.prev = new Node(item);
					current.prev.next = current;
					current = current.prev;
				}
			}

			@Override
			public int nextIndex() {
				// Not Used Stub
				return 0;
			}

			@Override
			public int previousIndex() {
				// Not Used Stub
				return 0;
			}

			@Override
			public void remove() {
				/**
				 * removes item at current token
				 *
				 */
				if (numOfNodes == 0) {
					// Do nothing
				} else if (numOfNodes-- == 1) {
					head = tail = null;
				} else {
					if (current.next == null) {
						current.prev.next = null;
						tail = current;
					} else if (current.prev == null) {
						current.next.prev = null;
						head = current;
					} else {
						current.prev = current.next;
						current.next.prev = current.prev;
					}
				}
			}

			@Override
			public void set(T item) {
				/**
				 * Set data in current token
				 *
				 * @param T	data to set in current token
				 */
				current.data = item;
			}
			
		};
	}

}
