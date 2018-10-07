/**
 * Elelments for the list used in the add method
 * 
 * @author Christian William Bao
 * @version Oct 2 2018
 *
 */
public class AddListElement {
    /**
     * The index of the node in its list
     */
    public int index;
    /**
     * The InnerNode that the node points to in the matrix
     */
    public InnerNode<String> innerNode;


    /**
     * 
     * @param ind
     *            is the index
     * @param node
     *            is the innderNode
     */
    public AddListElement(int ind, InnerNode<String> node) {
        index = ind;
        innerNode = node;
    }

}
