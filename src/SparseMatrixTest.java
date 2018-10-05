import student.TestCase;

/**
 * 
 * @author cdc97 bob313
 * @version oct 2 2018
 *
 */
public class SparseMatrixTest extends TestCase {

    private SparseMatrix matrix;


    /**
     * tests the listAdd
     */
    public void testListAdd() {
        matrix = new SparseMatrix();
        matrix.print();
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>10");
        matrix.print();
        assertEquals("10", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(0)).getInnerNode().getData());
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>7");
        matrix.print();
        assertEquals("7", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(0)).getInnerNode().getData());
        matrix.listAdd("Dr. Shaffer<SEP>Another Movie<SEP>8");
        matrix.print();
        assertEquals("8", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(1)).getInnerNode().getData());
        matrix.listAdd("Christian<SEP>Another Movie<SEP>4");
        matrix.print();
        assertEquals("4", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(1)).getInnerNode().bottom().getData());
        matrix.listAdd("Christian<SEP>Spirited Away<SEP>2");
        matrix.print();
        assertEquals("2", matrix.getReviewList().getObject(matrix
            .getReviewList().get(1)).getInnerNode().getData());
        matrix.listAdd("Bob<SEP>Movie 3<SEP>9");
        matrix.print();
        assertEquals("9", matrix.getReviewList().getObject(matrix
            .getReviewList().get(2)).getInnerNode().getData());
        assertEquals("9", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(2)).getInnerNode().getData());
        matrix.listAdd("Bob<SEP>Another Movie<SEP>5");
        matrix.print();
        assertEquals("5", matrix.getReviewList().getObject(matrix
            .getReviewList().get(2)).getInnerNode().getData());
        assertEquals("5", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(1)).getInnerNode().bottom().bottom().getData());
        matrix.listAdd("Christian<SEP>Movie 3<SEP>6");
        matrix.print();
        assertEquals("6", matrix.getReviewList().getObject(matrix
            .getReviewList().get(1)).getInnerNode().right().right().getData());
        assertEquals("6", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(2)).getInnerNode().getData());

        matrix.listAdd("Dr. Shaffer<SEP>This Project Sucks<SEP>8");
        matrix.print();
        assertEquals("8", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(3)).getInnerNode().getData());
        assertEquals("8", matrix.getReviewList().getObject(matrix
            .getReviewList().get(0)).getInnerNode().right().right().getData());

        matrix.listAdd("Dr. Shaffer<SEP>Movie 3<SEP>6");
        matrix.print();
        assertEquals("6", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(2)).getInnerNode().getData());
        assertEquals("6", matrix.getReviewList().getObject(matrix
            .getReviewList().get(0)).getInnerNode().right().right().getData());

        matrix.listAdd("Asshole<SEP>Spirited Away<SEP>2");
        matrix.print();
        assertEquals("2", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(0)).getInnerNode().bottom().bottom().getData());
        assertEquals("2", matrix.getReviewList().getObject(matrix
            .getReviewList().get(3)).getInnerNode().getData());

        matrix.listAdd("Bob<SEP>Spirited Away<SEP>5");
        matrix.print();
        assertEquals("5", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(0)).getInnerNode().bottom().bottom().getData());
        assertEquals("5", matrix.getReviewList().getObject(matrix
            .getReviewList().get(2)).getInnerNode().getData());
        matrix.list("reviewer", "Bob");
        // testing print

    }

        /**
     * tests the deleteMovie method
     */
    public void testdeleteMovie() {
        // set up
        System.out.println("Delete Begins");
        matrix = new SparseMatrix();
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>7");
        matrix.listAdd("Dr. Shaffer<SEP>Another Movie<SEP>8");
        matrix.listAdd("Christian<SEP>Spirited Away<SEP>2");
        matrix.listAdd("Christian<SEP>Another Movie<SEP>4");
        matrix.listAdd("Christian<SEP>Movie 3<SEP>6");
        matrix.listAdd("Dr. Shaffer<SEP>This Project Sucks<SEP>10");
        matrix.listAdd("Bob<SEP>Another Movie<SEP>5");
        matrix.listAdd("Bob<SEP>Movie 3<SEP>9");
        matrix.listAdd("Asshole<SEP>Spirited Away<SEP>2");
        matrix.print();

        // testing
        matrix.deleteMovie("Another Movie");
        matrix.print();
        assertFalse(matrix.getMovieList().contains("Another Movie"));
        assertEquals("Movie 3", matrix.getMovieList().get(1));
        assertEquals(matrix.getMovieList().getObject(matrix.getMovieList().get(
            1)).getInnerNode().getData(), matrix.getReviewList().getObject(
                matrix.getReviewList().get(1)).getInnerNode().right()
                .getData());

        matrix.deleteMovie("This Project Sucks");
        assertFalse(matrix.getMovieList().contains("This Project Sucks"));
        assertNull(matrix.getReviewList().getObject(matrix.getReviewList().get(
            0)).getInnerNode().right());

        matrix.deleteMovie("Spirited Away");
        assertEquals("Movie 3", matrix.getMovieList().get(0));
        assertEquals(matrix.getMovieList().getObject(matrix.getMovieList().get(
            0)).getInnerNode(), matrix.getReviewList().getObject(matrix
                .getReviewList().get(1)).getInnerNode());

        matrix.deleteReview("Dr. Shaffer");
    }


    /**
     * Tests the deleteReview method
     */
    public void testdeleteReview() {
        // set up
        System.out.println("Delete Begins");
        matrix = new SparseMatrix();
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>7");
        matrix.listAdd("Dr. Shaffer<SEP>Another Movie<SEP>8");
        matrix.listAdd("Christian<SEP>Spirited Away<SEP>2");
        matrix.listAdd("Christian<SEP>Another Movie<SEP>4");
        matrix.listAdd("Christian<SEP>Movie 3<SEP>6");
        matrix.listAdd("Dr. Shaffer<SEP>This Project Sucks<SEP>10");
        matrix.listAdd("Bob<SEP>Another Movie<SEP>5");
        matrix.listAdd("Bob<SEP>Movie 3<SEP>9");
        matrix.listAdd("Asshole<SEP>Spirited Away<SEP>2");
        matrix.print();

        // testing
        matrix.deleteReview("Christian");
        assertFalse(matrix.getReviewList().contains("Christian"));
        assertEquals("5", matrix.getMovieList().getObject(matrix
                .getMovieList().get(1)).getInnerNode().bottom().getData());
        matrix.deleteReview("Asshole");
        assertFalse(matrix.getReviewList().contains("Asshole"));
        assertNull(matrix.getMovieList().getObject(matrix.getMovieList().get(0))
            .getInnerNode().bottom());
        matrix.deleteReview("Dr. Shaffer");
        assertFalse(matrix.getReviewList().contains("Dr. Shaffer"));
        assertEquals("5", matrix.getMovieList().getObject(matrix
                .getMovieList().get(1)).getInnerNode().getData());
        
        matrix.deleteMovie("Spirited Away");
    }

    /**
     * tests the empty case
     */
    public void testEmpty() {
        matrix = new SparseMatrix();
        matrix.print();
        matrix.list("movie", "name");
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>10");
        matrix.deleteReview("Dr. Shaffer");
        matrix.print();
        assertNull(matrix.getMovieList().getObject(matrix.getMovieList()
            .get(0)).getInnerNode());
        assertTrue(matrix.getReviewList().isEmpty());
        matrix.listAdd("Christian<SEP>Spirited Away<SEP>2");
        matrix.deleteMovie("Spirited Away");
        matrix.print();
        assertNull(matrix.getReviewList().getObject(matrix.getReviewList()
            .get(0)).getInnerNode());
        assertTrue(matrix.getMovieList().isEmpty());
    }
    
    public void testSimilarity() {
        matrix = new SparseMatrix();
        matrix.print();
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>10");
        matrix.print();
        assertEquals("10", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(0)).getInnerNode().getData());
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>7");
        matrix.print();
        assertEquals("7", matrix.getMovieList().getObject(matrix.getMovieList()
            .get(0)).getInnerNode().getData());
        matrix.listAdd("Dr. Shaffer<SEP>Another Movie<SEP>8");
        matrix.print();
        matrix.listAdd("Christian<SEP>Spirited Away<SEP>2");
        matrix.print();
        assertEquals("2", matrix.getReviewList().getObject(matrix
            .getReviewList().get(1)).getInnerNode().getData());
        matrix.listAdd("Bob<SEP>Another Movie<SEP>5");
        matrix.similarScore("reviewer", "Christian");
        matrix.similarScore("movie", "Spirited Away");
    }
}
