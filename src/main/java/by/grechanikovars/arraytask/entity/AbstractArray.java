package by.grechanikovars.arraytask.entity;

/**
 * Abstract base class for array wrappers.
 */
public abstract class AbstractArray {

    /**
     * Returns the unique identifier of this array.
     *
     * @return array id
     */
    public abstract long getId();

    /**
     * Returns the number of elements in the array.
     *
     * @return size of the array
     */
    public abstract int size();
}