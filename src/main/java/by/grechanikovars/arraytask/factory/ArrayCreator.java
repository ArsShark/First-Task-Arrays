package by.grechanikovars.arraytask.factory;

import by.grechanikovars.arraytask.entity.IntArray;

/**
 * Factory Method interface for creating IntArray instances.
 */
public interface ArrayCreator {

    /**
     * @param elements source elements
     * @return new IntArray instance
     */
    IntArray create(int[] elements);
}
