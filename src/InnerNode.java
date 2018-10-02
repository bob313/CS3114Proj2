
/**
 * This represents a node in a singly linked list. This node stores data
 * along with having a pointer to the next node in the list
 * 
 * @author bob bao bob313 Christian Carminucci cdc97
 *
 * @param <D>
 *            data type
 */
public class InnerNode<D> {

    // The data element stored in the node.
    private D data;

    // The next nodes in the sequence.
    private InnerNode<D> right;
    private InnerNode<D> left;
    private InnerNode<D> top;
    private InnerNode<D> bottom;


    /**
     * Creates a new node with the given data
     *
     * @param d
     *            the data to put inside the node
     */
    public InnerNode(D d) {
        data = d;
    }


    /**
     * Sets the node after this node
     *
     * @param n
     *            the node after this one
     */
    public void setRight(InnerNode<D> n) {
        right = n;
    }


    /**
     * Gets the next node
     *
     * @return the next node
     */
    public InnerNode<D> right() {
        return right;
    }


    /**
     * Sets the node after this node
     *
     * @param n
     *            the node after this one
     */
    public void setLeft(InnerNode<D> n) {
        left = n;
    }


    /**
     * Gets the next node
     *
     * @return the next node
     */
    public InnerNode<D> left() {
        return left;
    }


    /**
     * Sets the node after this node
     *
     * @param n
     *            the node after this one
     */
    public void setTop(InnerNode<D> n) {
        top = n;
    }


    /**
     * Gets the next node
     *
     * @return the next node
     */
    public InnerNode<D> top() {
        return top;
    }


    /**
     * Sets the node after this node
     *
     * @param n
     *            the node after this one
     */
    public void setBottom(InnerNode<D> n) {
        bottom = n;
    }


    /**
     * Gets the next node
     *
     * @return the next node
     */
    public InnerNode<D> bottom() {
        return bottom;
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
     * updates the data value
     * 
     * @param d
     *            is the new d to set value to
     */
    public void setData(D d) {
        data = d;
    }
}
