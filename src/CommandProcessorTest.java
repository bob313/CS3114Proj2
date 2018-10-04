import student.TestCase;

/**
 * 
 * @author bob313 cdc97
 * @version sep 21 2018
 *
 */
public class CommandProcessorTest extends TestCase {

    /**
     * Tests the command processor
     */
    public void testConstructor() {
        CommandProcessor processor = new CommandProcessor("6",
            "P2SampleInput.txt");
        assertNotNull(processor.getHash("movie"));
        assertNotNull(processor.getHash("review"));
    }


    /**
     * tests to make sure ratings are proper when adding
     */
    public void testadd() {
        CommandProcessor processor = new CommandProcessor("6", "addrating.txt");
        assertEquals(processor.getHash("movie").getCount(), 0);
        assertEquals(processor.getHash("review").getCount(), 0);
    }
}
