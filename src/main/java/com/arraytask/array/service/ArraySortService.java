package com.arraytask.array.service;

import com.arraytask.array.entity.IntArray;
import com.arraytask.array.exception.ArrayException;

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
