
package prakt1_listen_impl;

/**
 * @author dennis 
 *
 */
public interface IList<T> {

	
	/**
	 * Inserts the specified element at the specified position in this list
	 * Shifts the currently at that position (if any) and any other 
	 * elements after the position to the right (adds one to their indices).
	 * @param pos Position retrieved by find, or null if insert at beginning
	 * @param elem The element to be inserted
	 * @ensure find(elem).equals(pos)
	 * @throw IndexOutOfBoundsException 
	 *         if the pos is out of range (index < 0 || index > size())
	 */
	public void insert(int pos, T elem);

	/**
	 * Removes the element at the specified position in this list 
	 * Shifts any subsequent elements to the left. 
	 * @param pos the position retrieved by find()
	 * @ensure element at position pos is deleted
     * @throw IndexOutOfBoundsException 
     *         if the pos is out of range (index < 0 || index > size())
	 */
	public void delete(int pos);

	/**
	 * Returns the index of the first occurrence of the specified element
	 * in this list, or -1 if this list does not contain 
	 * @return the position of elem in this List,
	 * @ensure retrieve(find(elem)).equals(elem)
	 */
	public int find(T elem);

	/**
	 * Returns the element at the specified position in this list.
	 * @param pos The position 
	 * @return The element on Position pos
	 * @require pos >= 0 && pos < length
	 * @ensure find(retrieve(pos)).equals(pos)
	 */
	public T retrieve(int pos);

	/**
	 * Appends all of the otherList to the end of this list
	 * @param other The second List
	 * @return ListInterface that consists of both
	 * @require otherList != null
	 * @require otherList instance of IList<T>
	 */
	public void concat(IList<T> otherList);

	/**
	 * @return the number of elements in this list
	 */
	public int size();
	
	/**
	 * Resets the time counter
	 */
	public void resetCounter();
	
	/**
	 * @return Time counter of the work time of this list (nanoseconds)
	 */
	public long getCounter();

}
