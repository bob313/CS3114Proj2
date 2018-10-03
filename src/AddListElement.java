/**
 * Elelments for the list used in the add method
 * 
 * @author Christian William Bao
 * @version Oct 2 2018
 *
 */
public class AddListElement {
    /**
     * Index for list
     */
    public int index;
    /**
     * Innernode for list
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
