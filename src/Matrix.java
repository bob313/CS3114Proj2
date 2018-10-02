
public class Matrix {

    private LinkedList<String> movieList;
    private LinkedList<String> reviewList;
    

    public Matrix() {
        
    }
    
    public void listAdd(String Command) {
        String name = Command.replaceFirst("add", "");
        name = formatString(name);
        String[] key = name.split("<SEP>");
        key[0] = key[0].trim();
        key[1] = key[1].trim();
        key[2] = key[2].trim();
        if (!reviewList.contains(key[0])) {
            reviewList.add(key[0]);
        }
        if (!movieList.contains(key[1])) {
            movieList.add(key[1]);
        }
        Node<String> row = reviewList.getObject(key[0]);
        Node<String> col = movieList.getObject(key[1]);
        InnerNode<String> rInner = row.getInnerNode();
        InnerNode<String> cInner = col.getInnerNode();
        InnerNode<String> inner = findIntersect(key[2], col);
        // tests for when inner node already exists
        if (inner != null) {
            inner.setData(key[2]);
        }
        else {
            inner = new InnerNode<String>(key[2]);
            // if for the first row
            if (reviewList.get(0).equals(key[0])) {
                if (movieList.get(0).equals(key[1])) {
                    inner.setRight(row.getInnerNode());
                    inner.setBottom(col.getInnerNode());
                    row.setInnerNode(inner);
                    col.setInnerNode(inner);
                }
                else if (cInner == null) {

                }
            }
            // if for the first column
            else if (movieList.get(0).equals(key[1])) {

            }
            // else just add the inner node in the matrix
            else {

            }
        }

    }


    /**
     * finds the inner node if it exists
     * 
     * @param rating
     *            is the rating to find
     * @param col
     *            is the column "list" to iterate through
     * @return the innerNode found
     */
    public InnerNode<String> findIntersect(String rating, Node<String> col) {
        int index = reviewList.getIndex(rating);
        InnerNode<String> temp = col.getInnerNode();
        for (int i = 0; i <= index; i++) {
            if (temp == null) {
                return null;
            }
            else if (temp.getData().equals(rating)) {
                return temp;
            }
            temp = temp.bottom();
        }
        return null;
    }


    /**
     * Formats input strings to remove excess spaces and command words (i.e.
     * update, add, delete)
     * 
     * @param nameString
     *            unformatted string
     * @return formatted string
     */
    private String formatString(String nameString) {
        StringBuilder newString = new StringBuilder();
        char[] chars = nameString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isWhitespace(chars[i])) {
                while (i < chars.length && !Character.isWhitespace(chars[i])) {
                    newString.append(chars[i]);
                    i++;
                }
                newString.append(" ");
            }
        }
        newString.deleteCharAt(newString.length() - 1);
        return newString.toString();
    }
}
