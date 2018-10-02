
public class SparseMatrix {

    private LinkedList<String> movieList;
    private LinkedList<String> reviewList;
    

    public SparseMatrix() {
        
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
        int reviewIndex = reviewList.getIndex(key[0]);
        InnerNode<String> reviewInner = reviewList.getObject(key[0]).getInnerNode();
        int movieIndex = movieList.getIndex(key[1]);
        InnerNode<String> movieInner = movieList.getObject(key[1]).getInnerNode();
        //empty matrix case
        if (reviewInner == null && movieInner == null) {
            InnerNode<String> inner = new InnerNode<String>(key[2]);
            reviewList.getObject(key[0]).setInnerNode(inner);
            movieList.getObject(key[1]).setInnerNode(inner);
            return;
        }
        //replica case
        boolean replica = checkForReplica(reviewInner, movieInner, key[2]);
        //sets up a linked list containing indices for a review row
        InnerNode<String> currNodeReview = reviewInner;
        LinkedList<AddListElement> reviewRowIndexes = new LinkedList<>();
        while (!replica && currNodeReview != null) {
            for (int i = 0; i < movieList.size(); i++) {
                if (columnContains(currNodeReview, movieList.getObject(movieList.get(i)).getInnerNode())) {
                    reviewRowIndexes.add(new AddListElement(i, currNodeReview));
                }
            }
            currNodeReview = currNodeReview.right();
        }
        InnerNode<String> inner = new InnerNode<String>(key[2]); //new node
        //adding new node to correct column
        if (reviewRowIndexes.isEmpty()) {
            reviewList.getObject(key[0]).setInnerNode(inner);
        }
        for (int i = 0; i < reviewRowIndexes.size(); i++) {
            if (reviewRowIndexes.get(i).index > movieIndex) {
                reviewRowIndexes.get(i).innerNode.setLeft(inner);
                inner.setRight(reviewRowIndexes.get(i).innerNode);
                reviewRowIndexes.get(i-1).innerNode.setRight(inner);
                inner.setLeft(reviewRowIndexes.get(i-1).innerNode);
            }
        }
      //sets up a linked list containing indices for a movie column
        InnerNode<String> currNodeMovie = movieInner;
        LinkedList<AddListElement> movieColumnIndexes = new LinkedList<>();
        while (!replica && currNodeMovie != null) {
            for (int i = 0; i < movieList.size(); i++) {
                if (rowContains(currNodeMovie, reviewList.getObject(reviewList.get(i)).getInnerNode())) {
                    movieColumnIndexes.add(new AddListElement(i, currNodeMovie));
                }
            }
            currNodeMovie = currNodeMovie.bottom();
        }
        //adding new node to correct row
        if (movieColumnIndexes.isEmpty()) {
            movieList.getObject(key[1]).setInnerNode(inner);
        }
        for (int i = 0; i < movieColumnIndexes.size(); i++) {
            if (movieColumnIndexes.get(i).index > reviewIndex) {
                movieColumnIndexes.get(i).innerNode.setTop(inner);
                inner.setBottom(reviewRowIndexes.get(i).innerNode);
                reviewRowIndexes.get(i-1).innerNode.setBottom(inner);
                inner.setTop(reviewRowIndexes.get(i-1).innerNode);
            }
        }
    }

    /**
     * Checks if an inner node is contained in an inner node column
     * @param reviewNode the inner node to check for
     * @param movieNodeStart the start of the column to check
     * @return true if its in the column, false otherwise
     */
    public boolean columnContains(InnerNode<String> reviewNode, InnerNode<String> movieNodeStart) {
        InnerNode<String> curr = movieNodeStart;
        while(curr != null) {
            if (curr.equals(reviewNode)) {
                return true;
            }
            curr = curr.bottom();
        }
        return false;
    }
    
    /**
     * Checks if an inner node is contained in an inner node row
     * @param reviewNode the inner node to check for
     * @param movieNodeStart the start of the row to check
     * @return true if its in the column, false otherwise
     */
    public boolean rowContains(InnerNode<String> movieNode, InnerNode<String> reviewNodeStart) {
        InnerNode<String> curr = reviewNodeStart;
        while(curr != null) {
            if (curr.equals(movieNode)) {
                return true;
            }
            curr = curr.right();
        }
        return false;
    }

    /**
     * Checks if a node already exists in the matrix then updates the score.
     * @param review the first inner node of the review row
     * @param movie the first inner node of the movie column
     * @param score the new score
     * @return true if one existed, false if it did not.
     */
   private boolean checkForReplica(InnerNode<String> review, InnerNode<String> movie, String score) {
       InnerNode<String> curr = review;
       while (curr != null) {
           InnerNode<String >curr1 = movie;
           while (curr1 != null) {
               if (curr.equals(curr1)) {
                   curr.setData(score);
                   return true;
               }
               curr1 = curr1.bottom();
           }
           curr = curr.right();
       }
       return false;
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
    
    /**
     * Prints the contents of the sparse matrix
     */
    public void print() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < reviewList.size(); i++) {
            System.out.println(reviewList.get(i) + ": " + i);
        }
        for (int i = 0; i < movieList.size(); i++) {
            builder.append(movieList.get(i));
            builder.append(":");
            for (int j = 0; j < reviewList.size(); j++) {
                builder.append(" ");
                InnerNode<String> firstNode = movieList.getObject(movieList.get(i)).getInnerNode();
                builder.append(getReviewScore(firstNode, j));
            }
        }
        System.out.println(builder.toString());
    }


    /**
     * Finds the review score at a specific position in a column
     * @param firstNode the first node in the column
     * @param index how far down the desired node is
     * @return the value of the desired node
     */
    private String getReviewScore(InnerNode<String> firstNode, int index) {
        InnerNode<String> currNode = firstNode;
        for (int i = 0; i < index; i++) {
            currNode = currNode.bottom();
        }
        return currNode.toString();
    }
}
