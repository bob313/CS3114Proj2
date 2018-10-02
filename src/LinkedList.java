

/**
 * This is a basic implementation of a linked list
 *
 * @author William Bao (bob313) Christian Carminucci (cdc97)
 * @version 11/9/2017
 * 
 * @param <E>
 *            This is the type of object that this class will store
 */

@SuppressWarnings("unused")
public class LinkedList<E> implements LList<E> {

    /**
     * This represents a node in a singly linked list. This node stores data
     * along with having a pointer to the next node in the list
     * 
     * @author bob bao bob313 Christian Carminucci cdc97
     *
     * @param <D> data type
     */
    public static class Node<D> {

        // The data element stored in the node.
        private D data;

        // The next node in the sequence.
        private Node<D> next;
        
        private InnerNode<D> innerNext;

        /**
         * Creates a new node with the given data
         *
         * @param d
         *            the data to put inside the node
         */
        public Node(D d) {
            data = d;
        }


        /**
         * Sets the node after this node
         *
         * @param n
         *            the node after this one
         */
        public void setNext(Node<D> n) {
            next = n;
        }


        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node<D> next() {
            return next;
        }
        
        /**
         * Sets the inner nodes
         * @param inner
         */
        public void setInnerNode(InnerNode<D> inner) {
            innerNext = inner;
        }

        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public D getData() {
            return data;
        }
    }

    private Node<E> head;

    // the size of the linked list
    private int size;


    /**
     * Creates a new LinkedList object
     */
    public LinkedList() {
        head = null;
        size = 0;

    }
    
    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Adds the object to the position in the list
     *
     * @precondition obj cannot be null
     * @param index
     *            where to add the object
     * @param obj
     *            the object to add
     * @throws IndexOutOfBoundsException
     *             if index is less than zero or greater than size
     * @throws IllegalArgumentException
     *             if obj is null
     */
    @Override
    public void add(int index, E obj) {
        // check if the object is null
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }

        // check if the index is out of bounds
        if ((index < 0) || (index > size())) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node<E> current = head;

        // empty stack case
        if (isEmpty()) {
            head = new Node<E>(obj);
        }
        // 1st node to remove
        else if (index == 0) {
            Node<E> newNode = new Node<E>(obj);
            newNode.setNext(current);
            head = newNode;
        }
        // all other cases
        else {
            int currentIndex = 0;
            while (currentIndex <= index) {
                // while(currentIndex != index) {
                if (currentIndex == index - 1) {
                    Node<E> nextNext = current.next;
                    Node<E> newNode = new Node<E>(obj);
                    current.setNext(newNode);
                    newNode.setNext(nextNext);
                }
                current = current.next();
                currentIndex++;
            }
        }
        size++;
    }


    /**
     * Adds the object to the end of the list.
     *
     * @precondition obj cannot be null
     * @param obj
     *            the object to add
     * @throws IllegalArgumentException
     *             if obj is null
     */
    @Override
    public void add(E obj) {
        // check if the object is null
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }

        Node<E> current = head;

        // empty stack case
        if (isEmpty()) {
            head = new Node<E>(obj);
        }

        // other cases
        else {
            while (current.next != null) {
                current = current.next;
            }
            current.setNext(new Node<E>(obj));
        }
        size++;
    }


    /**
     * Checks if the array is empty
     *
     * @return true if the array is empty
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Removes the first instance of the given object from the list
     *
     * @param obj
     *            the object to remove
     * @return true if successful
     */
    @Override
    public boolean remove(E obj) {
        Node<E> current = head;

        // account for matching head
        if (!isEmpty() && (obj.equals(current.data))) {
            head = head.next;
            return true;
        }

        // account for 2+ size
        if (!isEmpty()) {
            while (current.next != null) {
                if ((obj.equals(current.next.data))) {
                    if (current.next.next != null) {
                        current.setNext(current.next.next);
                    }
                    else {
                        current.next = null;
                    }
                    return true;
                }
                current = current.next;
            }
        }
        // this accounts for the isEmpty case or the object does not exist
        return false;
    }


    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if no node at the given index
     */
    @Override
    public E get(int index) {
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException("Index exceeds the size.");
        }
        Node<E> current = head;
        int currentIndex = 0;
        E data = null;
        if (index == 0) {
            return head.data;
        }
        while (currentIndex != index) {
            current = current.next;
            if (current == null) {
                throw new IndexOutOfBoundsException("Index exceeds the size.");
            }
            currentIndex++;
            if (currentIndex == index) {
                data = current.data;
            }
        }

//
// // check if the data was null...
// if (data == null) {
// // ... if so throw an exception
// throw new IndexOutOfBoundsException("Index exceeds the size.");
// }
        return data;
    }
    
    /**
     * 
     * @param obj
     *             Object is the string to search for in the list
     * @return
     */
    public InnerNode<E> getObject(E obj){
        Node<E> current = head;
        if (head == null) {
            return null;
        }
        while (current != null) {
            if (current.getData().equals(obj)) {
                return current.innerNext;
            }
            current = current.next;
        }
        return null;
    }


    /**
     * Checks if the list contains the given object
     *
     * @param obj
     *            the object to check for
     * @return true if it contains the object
     */
    @Override
    public boolean contains(E obj) {
        Node<E> current = head;
        while (current != null) {
            if (obj.equals(current.data)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }


    /**
     * Gets the last time the given object is in the list
     *
     * @param obj
     *            the object to look for
     * @return the last position of it. -1 If it is not in the list
     */
    @Override
    public int lastIndexOf(E obj) {
        int lastIndex = -1;
        Node<E> current = head;
        int currentIndex = 0;
        while (current != null) {
            if (obj.equals(current.data)) {
                lastIndex = currentIndex;
            }
            currentIndex++;
            current = current.next;

        }
        return lastIndex;
    }


    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        String result = "{";

        Node<E> current = head;
        while (current != null) {
            result += "" + current.data;
            current = current.next;
            if (current != null) {
                result += ", ";
            }
        }
        result += "}";
        return result;
    }


    /**
     * Returns an array representation of the list If a list contains A, B, and
     * C, the following should be returned {A, B, C}, If a list
     * contains A, B, C, and C the following should be returned {A, B, C, C}
     *
     * @return an array representing the list
     */
    public Object[] toArray() {

        Object[] array = new Object[this.size()];

        Node<E> current = head;
        int count = 0;
        while (current != null) {
            array[count] = current.getData();
            current = current.next;
            count++;
        }
        return array;
    }


    /**
     * Returns true if both lists have the exact same contents
     * in the exact same order
     *
     * @return a boolean of whether two lists have the same contents,
     *         item per item and in the same order
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (this.getClass() == obj.getClass()) {
            @SuppressWarnings("unchecked")
            LinkedList<E> other = ((LinkedList<E>)obj);
            if (other.size() == this.size()) {
                Node<E> current = head;
                Node<E> otherCurrent = other.head;
                while (current != null) {
                    if (!current.getData().equals(otherCurrent.getData())) {
                        return false;
                    }
                    current = current.next();
                    otherCurrent = otherCurrent.next();
                }
                return true;
            }
        }

        return false;
    }

}
