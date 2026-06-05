package by.grechanikovars.arraytask.service;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.service.impl.ArrayStatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStatServiceTest {

    private static final int[] MIXED_ELEMENTS = {3, 1, 4, 1, 5, 9, 2, 6};
    private static final int[] EMPTY_ELEMENTS = {};
    private static final int[] SINGLE_ELEMENT = {42};
    private static final int[] NEGATIVE_ELEMENTS = {-5, -3, -1, -10};
    private static final int[] SYMMETRIC_ELEMENTS = {2, 4, 6};

    private ArrayStatService service;

    @BeforeEach
    void setUp() {
        service = new ArrayStatServiceImpl();
    }


    @Test
    void testFindMinReturnsSmallestElement() throws ArrayException {
        // given
        IntArray array = new IntArray(MIXED_ELEMENTS);
        // when
        Optional<Integer> result = service.findMin(array);
        // then
        assertTrue(result.isPresent());
        assertEquals(1, result.get());
    }

    @Test
    void testFindMinEmptyArrayReturnsEmpty() throws ArrayException {

        IntArray array = new IntArray(EMPTY_ELEMENTS);

        Optional<Integer> result = service.findMin(array);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMinNegativeElementsReturnsCorrectMin() throws ArrayException {

        IntArray array = new IntArray(NEGATIVE_ELEMENTS);

        Optional<Integer> result = service.findMin(array);

        assertTrue(result.isPresent());
        assertEquals(-10, result.get());
    }

    @Test
    void testFindMinNullThrowsArrayException() {

        IntArray array = null;

        assertThrows(ArrayException.class, () -> service.findMin(array));
    }


    @Test
    void testFindMaxReturnsLargestElement() throws ArrayException {

        IntArray array = new IntArray(MIXED_ELEMENTS);

        Optional<Integer> result = service.findMax(array);

        assertTrue(result.isPresent());
        assertEquals(9, result.get());
    }

    @Test
    void testFindMaxEmptyArrayReturnsEmpty() throws ArrayException {

        IntArray array = new IntArray(EMPTY_ELEMENTS);

        Optional<Integer> result = service.findMax(array);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMaxNullThrowsArrayException() {

        IntArray array = null;

        assertThrows(ArrayException.class, () -> service.findMax(array));
    }

    @Test
    void testFindSumReturnsTotalForSingleElement() throws ArrayException {

        IntArray array = new IntArray(SINGLE_ELEMENT);

        Optional<Long> result = service.findSum(array);

        assertTrue(result.isPresent());
        assertEquals(42L, result.get());
    }

    @Test
    void testFindSumReturnsTotalForMultipleElements() throws ArrayException {

        IntArray array = new IntArray(SYMMETRIC_ELEMENTS);

        Optional<Long> result = service.findSum(array);

        assertTrue(result.isPresent());
        assertEquals(12L, result.get());
    }

    @Test
    void testFindSumEmptyArrayReturnsEmpty() throws ArrayException {

        IntArray array = new IntArray(EMPTY_ELEMENTS);

        Optional<Long> result = service.findSum(array);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindSumNullThrowsArrayException() {

        IntArray array = null;

        assertThrows(ArrayException.class, () -> service.findSum(array));
    }

    @Test
    void testFindAverageReturnsCorrectMean() throws ArrayException {

        IntArray array = new IntArray(SYMMETRIC_ELEMENTS);

        Optional<Double> result = service.findAverage(array);

        assertTrue(result.isPresent());
        assertEquals(4.0, result.get(), 0.0001);
    }

    @Test
    void testFindAverageEmptyArrayReturnsEmpty() throws ArrayException {

        IntArray array = new IntArray(EMPTY_ELEMENTS);

        Optional<Double> result = service.findAverage(array);

        assertTrue(result.isEmpty());
    }

    @Test
    void testFindAverageNullThrowsArrayException() {

        IntArray array = null;

        assertThrows(ArrayException.class, () -> service.findAverage(array));
    }
}
