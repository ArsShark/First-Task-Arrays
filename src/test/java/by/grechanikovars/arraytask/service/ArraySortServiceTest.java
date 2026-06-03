package by.grechanikovars.arraytask.service;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.service.impl.ArraySortServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArraySortServiceTest {

    private static final int[] UNSORTED_ELEMENTS = {5, 3, 8, 1, 9, 2};
    private static final int[] EXPECTED_SORTED = {1, 2, 3, 5, 8, 9};
    private static final int[] SINGLE_ELEMENT = {42};
    private static final int[] ALREADY_SORTED = {1, 2, 3, 4, 5};
    private static final int[] NEGATIVE_ELEMENTS = {-1, -5, -3, -2};
    private static final int[] EXPECTED_NEGATIVE_SORTED = {-5, -3, -2, -1};

    private ArraySortService service;

    @BeforeEach
    void setUp() {
        service = new ArraySortServiceImpl();
    }

    @Test
    void testBubbleSortProducesAscendingOrder() throws ArrayException {
        // given
        IntArray array = new IntArray(UNSORTED_ELEMENTS);
        // when
        service.bubbleSort(array);
        // then
        assertArrayEquals(EXPECTED_SORTED, array.getElements());
    }

    @Test
    void testBubbleSortSingleElementArrayUnchanged() throws ArrayException {

        IntArray array = new IntArray(SINGLE_ELEMENT);

        service.bubbleSort(array);

        assertArrayEquals(SINGLE_ELEMENT, array.getElements());
    }

    @Test
    void testBubbleSortAlreadySortedArrayUnchanged() throws ArrayException {

        IntArray array = new IntArray(ALREADY_SORTED);

        service.bubbleSort(array);

        assertArrayEquals(ALREADY_SORTED, array.getElements());
    }

    @Test
    void testBubbleSortNegativeElements() throws ArrayException {

        IntArray array = new IntArray(NEGATIVE_ELEMENTS);

        service.bubbleSort(array);

        assertArrayEquals(EXPECTED_NEGATIVE_SORTED, array.getElements());
    }

    @Test
    void testBubbleSortNullThrowsArrayException() {

        IntArray array = null;

        assertThrows(ArrayException.class, () -> service.bubbleSort(array));
    }


    @Test
    void testSelectionSortProducesAscendingOrder() throws ArrayException {

        IntArray array = new IntArray(UNSORTED_ELEMENTS);

        service.selectionSort(array);

        assertArrayEquals(EXPECTED_SORTED, array.getElements());
    }

    @Test
    void testSelectionSortSingleElementArrayUnchanged() throws ArrayException {

        IntArray array = new IntArray(SINGLE_ELEMENT);

        service.selectionSort(array);

        assertArrayEquals(SINGLE_ELEMENT, array.getElements());
    }

    @Test
    void testSelectionSortAlreadySortedArrayUnchanged() throws ArrayException {

        IntArray array = new IntArray(ALREADY_SORTED);

        service.selectionSort(array);

        assertArrayEquals(ALREADY_SORTED, array.getElements());
    }

    @Test
    void testSelectionSortNullThrowsArrayException() {

        IntArray array = null;

        assertThrows(ArrayException.class, () -> service.selectionSort(array));
    }
}
