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
            "P2sampleInput.txt");
        assertNotNull(processor.getHash("movie"));
        assertNotNull(processor.getHash("review"));
    }
    // herro
}
