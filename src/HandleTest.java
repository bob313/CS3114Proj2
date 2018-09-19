import student.TestCase;

/**
 * 
 * @author bob313 cdc97
 * @version sep 5 2018
 *
 */
public class HandleTest extends TestCase {

    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        // Nothing Here
    }

/**
 * tests the get methods for the handle class
 * also tests setting the deletion method
 */
    public void testHandles() {
        Handle test = new Handle(3, 5, "trial");
        assertEquals(test.getDeleted(), false);
        assertEquals(test.getLength(), 5);
        assertEquals(test.getMemPool(), 3);
        assertEquals(test.key(), "trial");
        test.setDeleted(true);
        assertEquals(test.getDeleted(), true);
    }

}
