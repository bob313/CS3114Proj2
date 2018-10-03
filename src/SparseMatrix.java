/**
 * SParse matrix
 * 
 * @author Christian cdc97 William Bao bob313
 * @version Oct 2 2018
 */
public class SparseMatrix {

    private LinkedList<String> movieList;
    private LinkedList<String> reviewList;


    /**
     * 
     */
    public SparseMatrix() {
        movieList = new LinkedList<String>();
        reviewList = new LinkedList<String>();
    }


    /**
     * 
     * @param Command
     *            command string to add
     */
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
        InnerNode<String> reviewInner = reviewList.getObject(key[0])
            .getInnerNode();
        int movieIndex = movieList.getIndex(key[1]);
        InnerNode<String> movieInner = movieList.getObject(key[1])
            .getInnerNode();
        // empty matrix case
        if (reviewInner == null && movieInner == null) {
            InnerNode<String> inner = new InnerNode<String>(key[2]);
            reviewList.getObject(key[0]).setInnerNode(inner);
            movieList.getObject(key[1]).setInnerNode(inner);
            return;
        }
        // replica case
        boolean replica = checkForReplica(reviewInner, movieInner, key[2]);
        if (!replica) {
            // sets up a linked list containing indices for a review row
            InnerNode<String> currNodeReview = reviewInner;
            LinkedList<AddListElement> reviewRowIndexes = new LinkedList<>();
            while (currNodeReview != null) {
                for (int i = 0; i < movieList.size(); i++) {
                    if (columnContains(currNodeReview, movieList.getObject(
                        movieList.get(i)).getInnerNode())) {
                        reviewRowIndexes.add(new AddListElement(i,
                            currNodeReview));
                    }
                }
                currNodeReview = currNodeReview.right();
            }
            InnerNode<String> inner = new InnerNode<String>(key[2]); // new node
            // adding new node to correct column
            if (reviewRowIndexes.isEmpty()) {
                reviewList.getObject(key[0]).setInnerNode(inner);
            }
            else {
                boolean addedToRow = false;
                for (int i = 0; i < reviewRowIndexes.size(); i++) {
                    if (reviewRowIndexes.get(i).index > movieIndex) {
                        // case where new element is first in row
                        if (reviewRowIndexes.get(i).innerNode.left() == null) {
                            reviewList.getObject(key[0]).setInnerNode(inner);
                        }
                        else {
                            reviewRowIndexes.get(i).innerNode.left().setRight(
                                inner);
                            inner.setLeft(reviewRowIndexes.get(i).innerNode
                                .left());
                        }

                        reviewRowIndexes.get(i).innerNode.setLeft(inner);
                        inner.setRight(reviewRowIndexes.get(i).innerNode);
                        addedToRow = true;
                        break;
                    }
                }
                // case where new element is last in row
                if (!addedToRow) {
                    reviewRowIndexes.get(reviewRowIndexes.size() - 1).innerNode
                        .setRight(inner);
                }
            }
            // sets up a linked list containing indices for a movie column
            InnerNode<String> currNodeMovie = movieInner;
            LinkedList<AddListElement> movieColumnIndexes = new LinkedList<>();
            while (currNodeMovie != null) {
                for (int i = 0; i < movieList.size(); i++) {
                    if (rowContains(currNodeMovie, reviewList.getObject(
                        reviewList.get(i)).getInnerNode())) {
                        movieColumnIndexes.add(new AddListElement(i,
                            currNodeMovie));
                    }
                }
                currNodeMovie = currNodeMovie.bottom();
            }
            // adding new node to correct row
            if (movieColumnIndexes.isEmpty()) {
                movieList.getObject(key[1]).setInnerNode(inner);
            }
            else {
                boolean addedToColumn = false;
                for (int i = 0; i < movieColumnIndexes.size(); i++) {
                    if (movieColumnIndexes.get(i).index > reviewIndex) {
                        // case where new element is first in column
                        if (movieColumnIndexes.get(i).innerNode.top() == null) {
                            movieList.getObject(key[1]).setInnerNode(inner);
                        }
                        else {
                            reviewRowIndexes.get(i).innerNode.top().setBottom(
                                inner);
                            inner.setTop(reviewRowIndexes.get(i).innerNode
                                .top());
                        }

                        movieColumnIndexes.get(i).innerNode.setTop(inner);
                        inner.setBottom(reviewRowIndexes.get(i).innerNode);
                        break;
                    }

                }
                // case where new element is last in column
                if (!addedToColumn) {
                    movieColumnIndexes.get(movieColumnIndexes.size()
                        - 1).innerNode.setBottom(inner);
                }
            }
        }
    }


    /**
     * Checks if an inner node is contained in an inner node column
     * 
     * @param reviewNode
     *            the inner node to check for
     * @param movieNodeStart
     *            the start of the column to check
     * @return true if its in the column, false otherwise
     */
    public boolean columnContains(
        InnerNode<String> reviewNode,
        InnerNode<String> movieNodeStart) {
        InnerNode<String> curr = movieNodeStart;
        while (curr != null) {
            if (curr.equals(reviewNode)) {
                return true;
            }
            curr = curr.bottom();
        }
        return false;
    }


    /**
     * Checks if an inner node is contained in an inner node row
     * 
     * @param reviewNode
     *            the inner node to check for
     * @param movieNodeStart
     *            the start of the row to check
     * @return true if its in the column, false otherwise
     */
    public boolean rowContains(
        InnerNode<String> movieNode,
        InnerNode<String> reviewNodeStart) {
        InnerNode<String> curr = reviewNodeStart;
        while (curr != null) {
            if (curr.equals(movieNode)) {
                return true;
            }
            curr = curr.right();
        }
        return false;
    }


    /**
     * Checks if a node already exists in the matrix then updates the score.
     * 
     * @param review
     *            the first inner node of the review row
     * @param movie
     *            the first inner node of the movie column
     * @param score
     *            the new score
     * @return true if one existed, false if it did not.
     */
    private boolean checkForReplica(
        InnerNode<String> review,
        InnerNode<String> movie,
        String score) {
        InnerNode<String> curr = review;
        while (curr != null) {
            InnerNode<String> curr1 = movie;
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

        for (int i = 0; i < reviewList.size(); i++) {
            System.out.println(reviewList.get(i) + ": " + i);
        }
        for (int i = 0; i < movieList.size(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(movieList.get(i));
            builder.append(":");
            InnerNode<String> curr = movieList.getObject(movieList.get(i))
                .getInnerNode();
            while (curr != null) {
                for (int j = 0; j < reviewList.size(); j++) {
                    if (rowContains(curr, reviewList.getObject(reviewList.get(
                        j)).getInnerNode())) {
                        builder.append(" " + j + ":");
                        builder.append(curr.getData());
                        break;
                    }
                }
                curr = curr.bottom();
            }

            System.out.println(builder.toString());
        }

    }


    /**
     * 
     * @param list
     *            is which linked list to scan through
     * @param name
     *            is the name of the object to search for
     */
    public void list(String list, String name) {
        LinkedList<String> temp = reviewList;
        if (list.equals("movie")) {
            temp = movieList;
            if (!temp.contains(name)) {
                System.out.println("Cannot list, " + list + " |" + name
                    + "| not found in the database.");
            }
            else {
                InnerNode<String> inner = temp.getObject(name).getInnerNode();
                System.out.println("Ratings for " + list + " |" + name + "|:");
                int i = 0;
                while (inner != null) {
                    if (this.rowContains(reviewList.getObject(reviewList.get(i))
                        .getInnerNode(), inner)) {
                        System.out.println(reviewList.get(i) + ": " + inner
                            .getData());
                    }
                    i++;
                    inner = inner.bottom();
                }
            }
        }
        else {
            temp = reviewList;
            if (!temp.contains(name)) {
                System.out.println("Cannot list, " + list + " |" + name
                    + "| not found in the database.");
            }
            else {
                InnerNode<String> inner = temp.getObject(name).getInnerNode();
                System.out.println("Ratings for " + list + " |" + name + "|:");
                int i = 0;
                while (inner != null) {
                    if (this.columnContains(reviewList.getObject(reviewList.get(
                        i)).getInnerNode(), inner)) {
                        System.out.println(reviewList.get(i) + ": " + inner
                            .getData());
                    }
                    i++;
                    inner = inner.right();
                }
            }
        }
    }


    /**
     * list getter method for testing
     * 
     * @return the movielist
     */
    public LinkedList<String> getMovieList() {
        return movieList;
    }


    /**
     * list getter method for testing
     * 
     * @return the reviewList
     */
    public LinkedList<String> getReviewList() {
        return reviewList;
    }
}
