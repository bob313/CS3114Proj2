import student.TestCase;

public class SparseMatrixTest extends TestCase{

    private SparseMatrix matrix;
    
    public void testListAdd() {
        matrix = new SparseMatrix();
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>10");
        assertEquals("10", matrix.getMovieList().getObject(matrix.getMovieList().get(0)).getInnerNode().getData());
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>7");
        assertEquals("7", matrix.getMovieList().getObject(matrix.getMovieList().get(0)).getInnerNode().getData());
        matrix.listAdd("Dr. Shaffer<SEP>Another Movie<SEP>8");
        assertEquals("8", matrix.getMovieList().getObject(matrix.getMovieList().get(1)).getInnerNode().getData());
        matrix.listAdd("Christian<SEP>Another Movie<SEP>4");
        assertEquals("4", matrix.getMovieList().getObject(matrix.getMovieList().get(1)).getInnerNode().bottom().getData());
        matrix.listAdd("Christian<SEP>Spirited Away<SEP>2");
        assertEquals("2", matrix.getReviewList().getObject(matrix.getReviewList().get(1)).getInnerNode().getData());
        matrix.listAdd("Bob<SEP>Movie 3<SEP>9");
        assertEquals("9", matrix.getReviewList().getObject(matrix.getReviewList().get(2)).getInnerNode().getData());
        assertEquals("9", matrix.getMovieList().getObject(matrix.getMovieList().get(2)).getInnerNode().getData());
        matrix.listAdd("Bob<SEP>Another Movie<SEP>5");
        assertEquals("5", matrix.getReviewList().getObject(matrix.getReviewList().get(2)).getInnerNode().getData());
        assertEquals("5", matrix.getMovieList().getObject(matrix.getMovieList().get(1)).getInnerNode().bottom().bottom().getData());
        matrix.listAdd("Christian<SEP>Movie 3<SEP>6");
        assertEquals("6", matrix.getReviewList().getObject(matrix.getReviewList().get(1)).getInnerNode().right().right().getData());
        assertEquals("6", matrix.getMovieList().getObject(matrix.getMovieList().get(2)).getInnerNode().getData());
        
        
        //testing print
        matrix.print();
    }
}
