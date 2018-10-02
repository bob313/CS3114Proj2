/**
 * Elelments for the list used in the add method
 * @author Christian
 *
 */
public class AddListElement {
    public int index;
    public InnerNode<String> innerNode;
    
    public AddListElement(int ind, InnerNode<String> node) {
        index = ind;
        innerNode = node;
    }

}
