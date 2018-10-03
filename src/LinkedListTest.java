
import student.TestCase;
import java.util.Arrays;

/**
 * 
 * Tests the equals and toString methods of a linked list.
 * 
 * @author William Bao (bob313) Christian Carminucci (cdc97)
 * 
 * 
 * @version 10/27/2017
 *
 */
public class LinkedListTest extends TestCase {

    private LinkedList<String> emptyListA;
    private LinkedList<String> emptyListB;
    private LinkedList<String> smallListA;
    private LinkedList<String> smallListB;
    // private LinkedList<String> bigListA;
    private LinkedList<String> bigListB;
    // private String nullObject;


    /**
     * Initializes 2 empty lists, 2 lists with a small number of items, and 2
     * lists with a large number of items
     */
    public void setUp() {
        emptyListA = new LinkedList<String>();
        emptyListB = new LinkedList<String>();

        smallListA = new LinkedList<String>();
        smallListB = new LinkedList<String>();

        smallListA.add("soccer");
        smallListA.add("swimming");
        smallListA.add("gymnastics");

        smallListB.add("soccer");
        smallListB.add("swimming");
        smallListB.add("gymnastics");

        // bigListA = new LinkedList<String>();

        for (int i = 0; i < 100; i++) {
            // bigListA.add("sport" + i);
        }

        bigListB = new LinkedList<String>();
        for (int i = 0; i < 100; i++) {
            bigListB.add("sport" + i);
        }

        // to be explicit
        // nullObject = null;
    }


    /**
     * tests the size method
     */
    public void size() {
        assertEquals(smallListA.size(), 4);
        assertEquals(emptyListA.size(), 0);
    }


    /**
     * tests the add with index method
     */
    public void testaddindex() {
        emptyListA.add(0, "zero");
        smallListA.add(1, "cake");
        smallListA.add(3, "pie");
        assertEquals(emptyListA.size(), 1);
        assertEquals(smallListA.get(1), "cake");
        assertEquals(smallListA.get(3), "pie");
    }


    /**
     * tests the add method when index is out of bound
     */
    public void testaddindexoutofbound() {
        Exception thrown = null;
        try {
            emptyListA.add(-1, "hi");
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof IndexOutOfBoundsException);

        Exception thrown2 = null;
        try {
            emptyListA.add(11, "hi");
        }
        catch (Exception exception) {
            thrown2 = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown2);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown2 instanceof IndexOutOfBoundsException);
    }


    /**
     * tests the add method with index when add is null
     */
    public void testaddindexnull() {
        String hi = null;
        Exception thrown = null;
        try {
            emptyListA.add(1, hi);
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /**
     * tests the add only object method
     */
    public void testadd() {
        emptyListA.add("apple");
        assertEquals(emptyListA.size(), 1);
        emptyListA.add("banana");
        assertEquals(emptyListA.toString(), "{apple, banana}");
    }


    /**
     * tests add null object only method
     */
    public void testaddnull() {
        String hi = null;
        Exception thrown = null;
        try {
            emptyListA.add(hi);
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /**
     * tests isempty method
     */
    public void testisEmpty() {
        assertTrue(emptyListA.isEmpty());
        assertFalse(smallListA.isEmpty());
    }


// /**
// * tests remove method
// */
// public void testremove() {
// assertTrue(smallListA.remove("soccer"));
// assertEquals(smallListA.size(), 2);
// assertFalse(smallListA.remove("aple"));
// assertEquals(smallListA.size(), 2);
// smallListA.add("hi");
// smallListA.add("die");
// smallListA.remove("hi");
// assertEquals(smallListA.size(), 3);
// assertTrue(smallListA.remove("gymnastics"));
// assertEquals(smallListA.size(), 2);
// assertTrue(smallListA.remove("die"));
// assertTrue(smallListA.remove("swimming"));
// assertFalse(smallListA.remove("ha"));
// }

    /**
     * tests the get method
     */
    public void testget() {
        Exception thrown = null;
        try {
            smallListA.get(-1);
        }
        catch (Exception exception) {
            thrown = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown instanceof IndexOutOfBoundsException);

        Exception thrown2 = null;
        try {
            emptyListA.get(2);
        }
        catch (Exception exception) {
            thrown2 = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown2);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown2 instanceof IndexOutOfBoundsException);

        Exception thrown3 = null;
        try {
            smallListA.get(10);
        }
        catch (Exception exception) {
            thrown3 = exception;
        }

        // checks whether an Exception was actually thrown
        assertNotNull(thrown3);

        // checks whether the right type of Exception was thrown
        assertTrue(thrown3 instanceof IndexOutOfBoundsException);

        assertEquals(smallListA.get(1), "swimming");
        assertEquals(smallListA.get(0), "soccer");
        assertEquals(smallListA.get(2), "gymnastics");
        smallListA.add(1, "pi");
        smallListA.add("pi");
        assertEquals(smallListA.get(1), "pi");
        assertEquals(smallListA.get(4), "pi");

        smallListA.add(0, "A");
        assertEquals("A", smallListA.get(0));

    }


    /**
     * tests the contains method
     */
    public void testcontains() {
        assertTrue(smallListA.contains("gymnastics"));
        assertFalse(smallListA.contains("cake"));
    }


    /**
     * tests the last index method
     */
    public void testlastindexof() {
        assertEquals(smallListA.lastIndexOf("soccer"), 0);
        assertEquals(smallListA.lastIndexOf("cake"), -1);
        smallListA.add("mango");
        assertEquals(smallListA.lastIndexOf("mango"), 3);
    }


    /**
     * tests tostring method
     */
    public void testtostring() {
        emptyListA.add("1");
        emptyListA.add("2");
        emptyListA.add("3");
        emptyListA.remove("3");
        emptyListA.remove("1");
        emptyListA.remove("2");
        assertEquals("{}", emptyListA.toString());
        assertEquals("{soccer, swimming, gymnastics}", smallListA.toString());
    }


// /**
// * Tests the equals method on a list with a small number of items in it
// */
// public void testEqualsSmallList() {
// assertEquals(smallListA, smallListA);
// assertEquals(smallListA, smallListB);
// assertFalse(smallListA.equals(nullObject));
// assertFalse(smallListA.equals("soccer"));
// assertFalse(smallListA.equals(bigListA));
// assertFalse(smallListA.equals(emptyListA));
// smallListB.add("jump roping");
// assertFalse(smallListA.equals(smallListB));
//
// // Make smallListA and smallListB differ in
// // content, but have the same size
// smallListA.add("rope jumping");
// assertFalse(smallListA.equals(smallListB));
//
// // Replace the last element in smallListA
// // to make smallListA and smallListB equal again
// smallListA.remove("rope jumping");
// smallListA.add("jump roping");
// assertEquals(smallListA, smallListB);
// }

    /**
     * Tests the toString method on an empty list
     */
    public void testToArrayEmpty() {

        Object[] emptyArray = {};
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyArray));
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyListB.toArray()));
        assertFalse(Arrays.equals(emptyListA.toArray(), smallListB.toArray()));
        Object[] oneItemArray = { "one thing" };
        emptyListA.add("one thing");
        assertTrue(Arrays.equals(emptyListA.toArray(), oneItemArray));

    }


    /**
     * Tests the toString method on a list with items in it
     */
    public void testToArrayContents() {

        Object[] origArray = { "soccer", "swimming", "gymnastics" };
        assertTrue(Arrays.equals(smallListA.toArray(), origArray));
        assertTrue(Arrays.equals(emptyListA.toArray(), emptyListB.toArray()));
        assertFalse(Arrays.equals(smallListA.toArray(), bigListB.toArray()));

    }

}
