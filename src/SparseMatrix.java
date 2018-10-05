/**
 * 
 * @author bob313 cdc97
 * @version oct 2 2018
 *
 */
public class SparseMatrix {

    private LinkedList<String> movieList;
    private LinkedList<String> reviewList;


    /**
     * sparse matrix
     */
    public SparseMatrix() {
        movieList = new LinkedList<String>();
        reviewList = new LinkedList<String>();
    }


    /**
     * 
     * @param command
     *            is the command string to add
     */
    public void listAdd(String command) {
        String name = command.replaceFirst("add", "");
        name = formatString(name);
        String[] key = name.split("<SEP>");
        key[0] = key[0].trim();
        key[1] = key[1].trim();
        key[2] = key[2].trim();
        if (!reviewList.contains(key[0])) {
            reviewList.add(key[0]);
            highReviewIndex++;
            reviewList.getObject(key[0]).setIndex(highReviewIndex);
        }
        if (!movieList.contains(key[1])) {
            movieList.add(key[1]);
            highMovieIndex++;
            movieList.getObject(key[1]).setIndex(highMovieIndex);
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
                    inner.setLeft(reviewRowIndexes.get(reviewRowIndexes.size()
                        - 1).innerNode);
                }
            }
            // sets up a linked list containing indices for a movie column
            InnerNode<String> currNodeMovie = movieInner;
            LinkedList<AddListElement> movieColumnIndexes = new LinkedList<>();
            while (currNodeMovie != null) {
                for (int i = 0; i < reviewList.size(); i++) {
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
                            movieColumnIndexes.get(i).innerNode.top().setBottom(
                                inner);
                            inner.setTop(movieColumnIndexes.get(i).innerNode
                                .top());
                        }
                        movieColumnIndexes.get(i).innerNode.setTop(inner);
                        inner.setBottom(movieColumnIndexes.get(i).innerNode);
                        addedToColumn = true;
                        break;
                    }
                }
                // case where new element is last in column
                if (!addedToColumn) {
                    movieColumnIndexes.get(movieColumnIndexes.size()
                        - 1).innerNode.setBottom(inner);
                    inner.setTop(movieColumnIndexes.get(movieColumnIndexes
                        .size() - 1).innerNode);
                }
            }
        }
    }


    /**
     * Deletes a review row from the sparse matrix
     * 
     * @param rowName
     *            the row to be deleted
     */
    public void deleteReview(String rowName) {
        InnerNode<String> currNode = null;
        if (reviewList.getObject(rowName).getInnerNode() != null) {
            currNode = reviewList.getObject(rowName).getInnerNode();
        }
        currNode = reviewList.getObject(rowName).getInnerNode();
        while (currNode != null) {
            if (currNode.top() != null) {
                currNode.top().setBottom(currNode.bottom());
                if (currNode.bottom() != null) {

                    currNode.bottom().setTop(currNode.top());
                }
            }
            else {
                Node<String> movieListElement = getMoveListElement(currNode);
                movieListElement.setInnerNode(currNode.bottom());
                if (currNode.bottom() != null) {
                    currNode.bottom().setTop(null);
                }
            }
            currNode = currNode.right();

        }
        reviewList.getObject(rowName).setInnerNode(null);
        reviewList.remove(rowName);
    }


    /**
     * Deletes a movie column from the sparse Matrix
     * 
     * @param movieName
     *            the movie to be deleted
     */
    public void deleteMovie(String movieName) {
        InnerNode<String> currNode = null;
        if (movieList.getObject(movieName).getInnerNode() != null) {
            currNode = movieList.getObject(movieName).getInnerNode();
        }
        while (currNode != null) {
            if (currNode.left() != null) {
                currNode.left().setRight(currNode.right());
                if (currNode.right() != null) {
                    currNode.right().setLeft(currNode.left());
                }
            }
            else {
                Node<String> reviewListElement = getReviewListElement(currNode);
                reviewListElement.setInnerNode(currNode.right());
                if (currNode.right() != null) {
                    currNode.right().setLeft(null);
                }

            }
            currNode = currNode.bottom();

        }
        movieList.getObject(movieName).setInnerNode(null);
        movieList.remove(movieName);

    }


    /**
     * Gets the element from the movie list that a given node is part of.
     * 
     * @param innerNode
     *            the given inner node
     * @return the element of movieList that points to the inner node
     */
    private Node<String> getMoveListElement(InnerNode<String> innerNode) {
        for (int i = 0; i < movieList.size(); i++) {
            if (columnContains(innerNode, movieList.getObject(movieList.get(i))
                .getInnerNode())) {
                return movieList.getObject(movieList.get(i));
            }
        }
        return null;
    }


    /**
     * Gets the element from the review list that a given node is part of.
     * 
     * @param innerNode
     *            the given inner node
     * @return the element of reviewList that points to the inner node
     */
    private Node<String> getReviewListElement(InnerNode<String> innerNode) {
        for (int i = 0; i < reviewList.size(); i++) {
            if (rowContains(innerNode, reviewList.getObject(reviewList.get(i))
                .getInnerNode())) {
                return reviewList.getObject(reviewList.get(i));
            }
        }
        return null;
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
            if (curr == reviewNode) {
                return true;
            }
            // System.out.println(curr.getData());
            curr = curr.bottom();
        }
        // System.out.println("new col");
        return false;
    }


    /**
     * Checks if an inner node is contained in an inner node row
     * 
     * @param movieNode
     *            the inner node to check for
     * @param reviewNodeStart
     *            the start of the row to check
     * @return true if its in the column, false otherwise
     */
    public boolean rowContains(
        InnerNode<String> movieNode,
        InnerNode<String> reviewNodeStart) {
        InnerNode<String> curr = reviewNodeStart;
        while (curr != null) {
            if (curr == movieNode) {
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
     * 
     * @return
     *         true if the matrix is empty
     */
    private boolean emptiness() {
        int i = 0;
        while (i < movieList.size()) {
            if (movieList.getObject(movieList.get(i)).getInnerNode() != null) {
                return false;
            }
            i++;
        }
        i = 0;
        while (i < reviewList.size()) {
            if (reviewList.getObject(reviewList.get(i))
                .getInnerNode() != null) {
                return false;
            }
            i++;
        }
        return true;
    }


    /**
     * Prints the contents of the sparse matrix
     */
    public void print() {
        if (emptiness()) {
            System.out.println("There are no ratings in the database");
        }

        for (int i = 0; i < reviewList.size(); i++) {
            if (reviewList.get(i) != null) {
                System.out.println(reviewList.get(i) + ": " + reviewList
                    .getObject(reviewList.get(i)).getIndex());
            }
        }
        for (int i = 0; i < movieList.size(); i++) {
            StringBuilder builder = new StringBuilder();
            if (movieList.get(i) != null) {
                builder.append(movieList.get(i));
                builder.append(":");
                InnerNode<String> curr = movieList.getObject(movieList.get(i))
                    .getInnerNode();
                while (curr != null) {
                    for (int j = 0; j < reviewList.size(); j++) {
                        if (rowContains(curr, reviewList.getObject(reviewList
                            .get(j)).getInnerNode())) {
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
                    if (this.rowContains(inner, reviewList.getObject(reviewList
                        .get(i)).getInnerNode())) {
                        System.out.println(reviewList.get(i) + ": " + inner
                            .getData());
                        inner = inner.bottom();
                    }
                    i++;
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
                    if (this.columnContains(inner, movieList.getObject(movieList
                        .get(i)).getInnerNode())) {
                        System.out.println(movieList.get(i) + ": " + inner
                            .getData());
                        inner = inner.right();
                    }
                    else {
                        i++;
                    }
                }
            }
        }
    }


    /**
     * 
     * @param listType
     *            is which list to search through movie or reviewer
     *
     * @param name
     *            is the name of the movie or reviewer to compare against
     */
    public void similarScore(String listType, String name) {
        LinkedList<String> temp = reviewList;
        int index = 0;
        if (listType.equals("movie")) {
            temp = movieList;
        }
        if (temp.getObject(name).getInnerNode() != null) {
            double[] similar = similarity(listType, name);
            double min = 10;
            for (int i = 0; i < similar.length; i++) {
                if (similar[i] < min) {
                    min = similar[i];
                    index = i;
                }
            }
            if (min == 10) {
                System.out.println("There is no " + listType + " similar to |"
                    + name + "|");
            }
            else {
                System.out.println("The " + listType + " |" + name
                    + "| is similar to |" + temp.get(index) + "| with score "
                    + min);
            }
        }
        else {
            System.out.println("There is no " + listType + " similar to |"
                + name + "|");
        }
    }


    /**
     * returns an array with similarity values
     * 
     * @param listType
     *            is which list to search through movie or reviewer
     * @param name
     *            is the name of the movie or reviewer to compare against
     * @return
     *         the similarity array
     */
    public double[] similarity(String listType, String name) {
        LinkedList<String> temp = reviewList;
        if (listType.equals("movie")) {
            temp = movieList;
        }
        double[] similar = new double[temp.size()];
        int orig = temp.getIndex(name);
        for (int i = 0; i < similar.length; i++) {
            if (temp.get(i).equals(name)) {
                similar[i] = 10;
            }
            else {
                if (listType.equals("movie")) {
                    similar[i] = movieDiff(orig, i);
                }
                else {
                    similar[i] = reviewDiff(orig, i);
                }
            }
        }
        return similar;
    }


    /**
     * 
     * @param orig
     *            is the original movie
     * @param compare
     *            is the movie to compare to
     * @return
     *         the similarity score between two movies
     */
    private double movieDiff(int orig, int compare) {
        int i = 0;
        double sum = 0;
        double count = 0;
        InnerNode<String> origCounter = movieList.getObject(movieList.get(orig))
            .getInnerNode();
        InnerNode<String> compCounter = movieList.getObject(movieList.get(
            compare)).getInnerNode();
        while (compCounter != null && origCounter != null) {
            if (rowContains(origCounter, reviewList.getObject(reviewList.get(i))
                .getInnerNode()) && rowContains(compCounter, reviewList
                    .getObject(reviewList.get(i)).getInnerNode())) {
                sum += Math.abs(Integer.valueOf(origCounter.getData()) - Integer
                    .valueOf(compCounter.getData()));
                origCounter = origCounter.bottom();
                compCounter = compCounter.bottom();
                count++;
            }
            else if (rowContains(compCounter, reviewList.getObject(reviewList
                .get(i)).getInnerNode())) {
                compCounter = compCounter.bottom();
            }
            else if (rowContains(origCounter, reviewList.getObject(reviewList
                .get(i)).getInnerNode())) {
                origCounter = origCounter.bottom();
            }
            i++;
        }
        if (count == 0) {
            return 10;
        }
        return (double)Math.round((sum / count) * 100) / 100;
    }


    /**
     * 
     * @param orig
     *            is the original movie
     * @param compare
     *            is the movie to compare to
     * @return
     *         the similarity score between two movies
     */
    private double reviewDiff(int orig, int compare) {
        int i = 0;
        double sum = 0;
        double count = 0;
        InnerNode<String> origCounter = reviewList.getObject(reviewList.get(
            orig)).getInnerNode();
        InnerNode<String> compCounter = reviewList.getObject(reviewList.get(
            compare)).getInnerNode();
        while (compCounter != null && origCounter != null) {
            if (columnContains(origCounter, movieList.getObject(movieList.get(
                i)).getInnerNode()) && columnContains(compCounter, movieList
                    .getObject(movieList.get(i)).getInnerNode())) {
                count++;
                sum += Math.abs(Integer.valueOf(origCounter.getData()) - Integer
                    .valueOf(compCounter.getData()));
                origCounter = origCounter.right();
                compCounter = compCounter.right();
            }
            else if (columnContains(compCounter, movieList.getObject(movieList
                .get(i)).getInnerNode())) {
                compCounter = compCounter.right();
            }
            else if (columnContains(origCounter, movieList.getObject(movieList
                .get(i)).getInnerNode())) {
                origCounter = origCounter.right();
            }
            i++;
        }
        if (count == 0) {
            return 10;
        }
        return (double)Math.round((sum / count) * 100) / 100;
    }


    /**
     * list getter method for testing
     * 
     * @return
     *         the movieList
     */
    public LinkedList<String> getMovieList() {
        return movieList;
    }


    /**
     * list getter method for testing
     * 
     * @return
     *         the reviewList
     */
    public LinkedList<String> getReviewList() {
        return reviewList;
    }
}
