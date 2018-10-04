
/**
 * This represents a node in a singly linked list. This node stores data
 * along with having a pointer to the next node in the list
 * 
 * @author bob bao bob313 Christian Carminucci cdc97
 * @version Oct 2 2018
 * 
 * @param <D>
 *            data type
 */
public class Node<D> {

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
     * 
     * @param inner
     *            sets what the inner node is
     */
    public void setInnerNode(InnerNode<D> inner) {
        innerNext = inner;
    }


    /**
     * 
     * @return the innerNode
     */
    public InnerNode<D> getInnerNode() {
        return innerNext;
    }


    /**
     * Gets the data in the node
     *
     * @return the data in the node
     */
    public D getData() {
        return data;
    }


    /**
     * 
     * @param d
     *            is what to set the data as
     */
    public void setData(D d) {
        data = d;
    }
}
