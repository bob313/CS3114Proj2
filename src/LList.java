
/**
 * 
 * This provides definitions for all of the methods necessary for a list
 * 
 * implementation
 *
 * 
 * 
 * @author <ChristianCarminucci SamanthaWalmsley WilliamBao>
 * 
 * @param <E>
 * 
 *            The class that you want it to store
 * 
 * @version 10/27/2017
 * 
 */

public interface LList<E>

{

    /**
     * 
     * Gets the number of elements in the list
     *
     * 
     * 
     * @return the number of elements
     * 
     */

    public abstract int size();


    /**
     * 
     * Adds the object to the position in the list
     *
     * 
     * 
     * @param index
     * 
     *            where to add the object
     * 
     * @param obj
     * 
     *            the object to add
     * 
     * @throws IndexOutOfBoundsException
     * 
     *             if index is less than zero or greater than size
     * 
     * @throws IllegalArgumentException
     * 
     *             if obj is null
     * 
     */

    public abstract void add(int index, E obj);


    /**
     * 
     * Adds the object to the end of the list.
     *
     * 
     * 
     * @param obj
     * 
     *            the object to add
     * 
     * @throws IllegalArgumentException
     * 
     *             if obj is null
     * 
     */

    public abstract void add(E obj);


    /**
     * 
     * Checks if the array is empty
     *
     * 
     * 
     * @return if the array is empty
     * 
     */

    public abstract boolean isEmpty();


    /**
     * 
     * Removes the first instance of the given object from the list
     *
     * 
     * 
     * @param obj
     * 
     *            the object to remove
     * 
     * @return true if successful
     * 
     */

    public abstract boolean remove(E obj);


    /**
     * 
     * Gets the object at the given position
     *
     * 
     * 
     * @param index
     * 
     *            where the object is located
     * 
     * @return The object at the given position
     * 
     * @throws IndexOutOfBoundsException
     * 
     *             if there is not a node at the given index
     * 
     */

    public abstract E get(int index);


    /**
     * 
     * @param obj
     *            Object is the string to search for in the list
     * @return
     *         The node where the object is located
     */
    public abstract Node<E> getObject(E obj);


    /**
     * Finds the index of the object in the list
     * 
     * @param obj
     *            is the object to search for
     * @return the index of the found object
     */
    public abstract int getIndex(E obj);


    /**
     * 
     * Checks if the list contains the given object
     *
     * 
     * 
     * @param obj
     * 
     *            the object to check for
     * 
     * @return true if it contains the object
     * 
     * @throws IndexOutOfBoundsException
     * 
     *             if an element does not exist at the given index
     * 
     */

    public abstract boolean contains(E obj);


    /**
     * 
     * Gets the last time the given object is in the list
     *
     * 
     * 
     * @param obj
     * 
     *            the object to look for
     * 
     * @return the last position of it. -1 If it is not in the list
     * 
     */

    public abstract int lastIndexOf(E obj);


    /**
     * 
     * Returns a string representation of the list If a list contains A, B, and
     * 
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * 
     * 
     * @return a string representing the list
     * 
     */

    public abstract String toString();

}
