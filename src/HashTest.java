import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test the hash function (you should throw this away for your project)
 *
 * @author CS3114 staff
 * @version August, 2018
 */
public class HashTest extends TestCase {
    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        // Nothing Here
    }


    /**
     * tests add method
     */
    public void testadd() {
        Hash myHash = new Hash(4);
        Handle first = new Handle(4, 4, "first");
        Handle second = new Handle(5, 4, "second");
        Handle third = new Handle(6, 4, "third");
        Handle fourth = new Handle(4, 4, "fourth");
        Handle last = new Handle(7, 4, "last");
        first.setRecord("first<SEP>fir<SEP>1");
        second.setRecord("second<SEP>sec<SEP>2");
        third.setRecord("third<SEP>thi<SEP>3");
        fourth.setRecord("fourth<SEP>fou<SEP>4");
        last.setRecord("last<SEP>las<SEP>8");
        myHash.add("first", first);
        assertEquals(myHash.add("first", first), false);
        assertEquals(myHash.getHandle(2).key(), "first");
        myHash.add("second", second);
        assertEquals(myHash.getHashtable().length, 4);
        assertEquals(myHash.getHandle(1).key(), "second");
        myHash.add("third", third);
        myHash.print();
        assertEquals(myHash.getHandle(0).key(), "third");
        myHash.add("fourth", fourth);
        assertEquals(myHash.getHandle(3).key(), "fourth");
        assertEquals(myHash.getHashtable().length, 8);
        myHash.add("last", last);
        myHash.print();
        assertEquals(myHash.getHashtable().length, 16);
        assertEquals(myHash.getHandle(12).key(), "last");
    }


    /**
     * more tests
     */
    public void testadd2() {
        Hash myHash = new Hash(10);
        Handle first = new Handle(4, 4, "Death Note");
        Handle second = new Handle(4, 4, "Death Note");
        first.setRecord("first<SEP>fir<SEP>1");
        second.setRecord("second<SEP>sec<SEP>2");
        myHash.add("Death Note", first);
        assertEquals(myHash.add("Death Note", second), false);
        myHash.print();
    }
    
    /**
     * test search
     */
//    public void testsearch() {
//        Hash myHash = new Hash(6);
//        Handle first = new Handle(4, 4, "Spirited Away");
//        Handle second = new Handle(5, 4, "Batman v Superman");
//        Handle third = new Handle(6, 4, "third");
//        Handle fourth = new Handle(4, 4, "fourth");
//    }


    /**
     * tests remove method
     */
    public void testremove() {
        Hash myHash = new Hash(4);
        Handle first = new Handle(4, 4, "first");
        Handle second = new Handle(5, 4, "second");
        Handle third = new Handle(6, 4, "third");
        Handle fourth = new Handle(4, 4, "fourth");
        first.setRecord("first<SEP>fir<SEP>1");
        second.setRecord("second<SEP>sec<SEP>2");
        third.setRecord("third<SEP>thi<SEP>3");
        fourth.setRecord("fourth<SEP>fou<SEP>4");
        myHash.add("first", first);
        myHash.add("second", second);
        myHash.add("third", third);
        myHash.add("fourth", fourth);
        myHash.remove("first");
        myHash.remove("second");
        assertEquals(myHash.searchHandle("fourth"), fourth);
        assertNull(myHash.searchHandle("first"));
        myHash.print();
    }


    /**
     * more removal tests
     */
    public void testremove2() {
        Hash myHash = new Hash(4);
        Handle first = new Handle(4, 4, "first");
        Handle second = new Handle(5, 4, "second");
        Handle third = new Handle(6, 4, "third");
        Handle fourth = new Handle(4, 4, "fourth");
        assertEquals(myHash.remove("first"), false);
        first.setRecord("first<SEP>fir<SEP>1");
        second.setRecord("second<SEP>sec<SEP>2");
        third.setRecord("third<SEP>thi<SEP>3");
        fourth.setRecord("fourth<SEP>fou<SEP>4");
        myHash.print();
        myHash.add("first", first);
        myHash.add("second", second);
        myHash.remove("second");
        myHash.add("second", second);
        myHash.print();
        myHash.add("third", third);
        myHash.add("fourth", fourth);
        myHash.print();
        myHash.remove("first");
        myHash.remove("second");
        myHash.print();
        myHash.remove("third");
        myHash.remove("fourth");
        myHash.print();
        assertEquals(myHash.remove("second"), false);
        assertEquals(myHash.remove("fifth"), false);
    }


    /**
     * tests the searchHandle method
     */
    public void testsearchHandle() {
        Hash myHash = new Hash(4);
        Handle first = new Handle(4, 4, "first");
        Handle fourth = new Handle(4, 4, "fourth");
        first.setRecord("first<SEP>fir<SEP>1");
        fourth.setRecord("fourth<SEP>fou<SEP>4");
        myHash.add("first", first);
        myHash.add("fourth", fourth);
        assertEquals(myHash.searchHandle("first"), first);
        assertEquals(myHash.searchHandle("fourth"), fourth);
        myHash.remove("fourth");
        assertNull(myHash.searchHandle("fourth"));
        assertNull(myHash.searchHandle("second"));
        assertFalse(myHash.search("fourth"));
    }


    /**
     * tests the print method
     */
    public void testprint() {
        Hash myHash = new Hash(4);
        Handle first = new Handle(4, 4, "first");
        Handle second = new Handle(5, 4, "second");
        Handle third = new Handle(6, 4, "third");
        Handle last = new Handle(7, 4, "last");
        first.setRecord("first<SEP>fir<SEP>1");
        second.setRecord("second<SEP>sec<SEP>2");
        third.setRecord("third<SEP>thi<SEP>3");
        last.setRecord("last<SEP>las<SEP>8");
        myHash.print();
        myHash.add("first", first);
        assertEquals(myHash.getHandle(2).key(), "first");
        myHash.add("second", second);
        assertEquals(myHash.getHashtable().length, 4);
        assertEquals(myHash.getHandle(1).key(), "second");
        myHash.add("third", third);
        myHash.print();
        assertEquals(myHash.getHandle(0).key(), "third");
        myHash.add("last", last);
        myHash.remove("first");
        myHash.remove("second");
        myHash.remove("third");
        myHash.remove("last");
        myHash.print();
    }


    /**
     * Test the hash function
     */
    public void testh() {

        Hash myHash = new Hash(10);
        assertEquals(myHash.h("aaaabbbb", 101), 75);
        assertEquals(myHash.h("aaaabbb", 101), 1640219587 % 101);
    }
}
