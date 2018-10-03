import student.TestCase;

public class SparseMatrixTest extends TestCase{

    private SparseMatrix matrix;
    
    public void testListAdd() {
        matrix = new SparseMatrix();
        matrix.print();
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>10");
        matrix.print();
        assertEquals("10", matrix.getMovieList().getObject(matrix.getMovieList().get(0)).getInnerNode().getData());
        matrix.listAdd("Dr. Shaffer<SEP>Spirited Away<SEP>7");
        matrix.print();
        assertEquals("7", matrix.getMovieList().getObject(matrix.getMovieList().get(0)).getInnerNode().getData());
        matrix.listAdd("Dr. Shaffer<SEP>Another Movie<SEP>8");
        matrix.print();
        assertEquals("8", matrix.getMovieList().getObject(matrix.getMovieList().get(1)).getInnerNode().getData());
        matrix.listAdd("Christian<SEP>Another Movie<SEP>4");
        matrix.print();
        assertEquals("4", matrix.getMovieList().getObject(matrix.getMovieList().get(1)).getInnerNode().bottom().getData());
        matrix.listAdd("Christian<SEP>Spirited Away<SEP>2");
        matrix.print();
        assertEquals("2", matrix.getReviewList().getObject(matrix.getReviewList().get(1)).getInnerNode().getData());
        matrix.listAdd("Bob<SEP>Movie 3<SEP>9");
        matrix.print();
        assertEquals("9", matrix.getReviewList().getObject(matrix.getReviewList().get(2)).getInnerNode().getData());
        assertEquals("9", matrix.getMovieList().getObject(matrix.getMovieList().get(2)).getInnerNode().getData());
        matrix.listAdd("Bob<SEP>Another Movie<SEP>5");
        matrix.print();
        assertEquals("5", matrix.getReviewList().getObject(matrix.getReviewList().get(2)).getInnerNode().getData());
        assertEquals("5", matrix.getMovieList().getObject(matrix.getMovieList().get(1)).getInnerNode().bottom().bottom().getData());
        matrix.listAdd("Christian<SEP>Movie 3<SEP>6");
        matrix.print();
        assertEquals("6", matrix.getReviewList().getObject(matrix.getReviewList().get(1)).getInnerNode().right().right().getData());
        assertEquals("6", matrix.getMovieList().getObject(matrix.getMovieList().get(2)).getInnerNode().getData());
        
        matrix.listAdd("Dr. Shaffer<SEP>This Project Sucks<SEP>8");
        matrix.print();
        assertEquals("8", matrix.getMovieList().getObject(matrix.getMovieList().get(3)).getInnerNode().getData());
        assertEquals("8", matrix.getReviewList().getObject(matrix.getReviewList().get(0)).getInnerNode().right().right().getData());
        
        matrix.listAdd("Dr. Shaffer<SEP>Movie 3<SEP>6");
        matrix.print();
        assertEquals("6", matrix.getMovieList().getObject(matrix.getMovieList().get(2)).getInnerNode().getData());
        assertEquals("6", matrix.getReviewList().getObject(matrix.getReviewList().get(0)).getInnerNode().right().right().getData());
        
        matrix.listAdd("Asshole<SEP>Spirited Away<SEP>2");
        matrix.print();
        assertEquals("2", matrix.getMovieList().getObject(matrix.getMovieList().get(0)).getInnerNode().bottom().bottom().getData());
        assertEquals("2", matrix.getReviewList().getObject(matrix.getReviewList().get(3)).getInnerNode().getData());
        
        matrix.listAdd("Bob<SEP>Spirited Away<SEP>5");
        matrix.print();
        assertEquals("5", matrix.getMovieList().getObject(matrix.getMovieList().get(0)).getInnerNode().bottom().bottom().getData());
        assertEquals("5", matrix.getReviewList().getObject(matrix.getReviewList().get(2)).getInnerNode().getData());
        
        //testing print
        
    }
}
