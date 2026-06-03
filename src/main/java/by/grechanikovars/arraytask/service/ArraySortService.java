package by.grechanikovars.arraytask.service;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;

/**
 * Service interface for sorting an IntArray in ascending order.
 */
public interface ArraySortService {

    /**
     * @param array array to sort (modified in place)
     * @throws ArrayException if array is null
     */
    void bubbleSort(IntArray array) throws ArrayException;

    /**
     * @param array array to sort (modified in place)
     * @throws ArrayException if array is null
     */
    void selectionSort(IntArray array) throws ArrayException;
}
