
/**
 * 
 * @author bob313 cdc97
 * @version sep 4 2018
 */
public class Handle {
    private int memPool;
    private int length;
    private String key;
    private boolean tombstone;

    private String record; // delete


    /**
     * 
     * @param mem
     *            is the mem pool record location
     * @param size
     *            is the size of the record
     * @param name
     *            is the name of the record
     */
    public Handle(int mem, int size, String name) {
        memPool = mem;
        length = size;
        key = name;
        this.tombstone = false;
    }


    /**
     * 
     * @return the key
     */
    public String key() {
        return key;
    }


    /**
     * sets the deletion state of the handle,
     * if it's true it means it's been deleted
     * 
     * @param state
     *            is the deletion state
     */
    public void setDeleted(boolean state) {
        this.tombstone = state;
    }


    /**
     * 
     * @return the state of deletion
     */
    public boolean getDeleted() {
        return this.tombstone;
    }


    /**
     * @return the memory pool offset location
     */
    public int getMemPool() {
        return memPool;
    }


    /**
     * @return the length of the record
     */
    public int getLength() {
        return length;
    }


    /**
     * DELETE
     * 
     * @return the record
     */
    public String getRecord() {
        return record;
    }


    /**
     * delete
     * 
     * @param record
     *            is record to set
     */
    public void setRecord(String record) {
        this.record = record;
    }
}
