package by.grechanikovars.arraytask.service.impl;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.service.ArraySortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implements two classic O(n²) sorting algorithms: Bubble Sort and Selection Sort.
 * Both sort the array in ascending order and modify the IntArray in place.
 */
public class ArraySortServiceImpl implements ArraySortService {

    private static final Logger logger = LogManager.getLogger(ArraySortServiceImpl.class);

    @Override
    public void bubbleSort(IntArray array) throws ArrayException {
        if (array == null) {
            throw new ArrayException("Array must not be null");
        }
        int[] elements = array.getElements();
        int n = elements.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
        array.setElements(elements);
        logger.info("Bubble sort result: {}", array);
    }

    @Override
    public void selectionSort(IntArray array) throws ArrayException {
        if (array == null) {
            throw new ArrayException("Array must not be null");
        }
        int[] elements = array.getElements();
        int n = elements.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (elements[j] < elements[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = elements[minIndex];
            elements[minIndex] = elements[i];
            elements[i] = temp;
        }
        array.setElements(elements);
        logger.info("Selection sort result: {}", array);
    }
}
