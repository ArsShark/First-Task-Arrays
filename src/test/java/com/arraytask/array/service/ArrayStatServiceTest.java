package com.arraytask.array.service;

import com.arraytask.array.entity.IntArray;
import com.arraytask.array.exception.ArrayException;
import com.arraytask.array.service.impl.ArrayStatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ArrayStatService.
 * Structure: given (preconditions), when (method call), then (assertion).
 */
class ArrayStatServiceTest {

    // --- Test constants (objects created via new, not via factory) ---
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

    // ===== findMin =====

    @Test
    void testFindMinReturnsSmallesElement() throws ArrayException {
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
        // given
        IntArray array = new IntArray(EMPTY_ELEMENTS);
        // when
        Optional<Integer> result = service.findMin(array);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMinNegativeElementsReturnsCorrectMin() throws ArrayException {
        // given
        IntArray array = new IntArray(NEGATIVE_ELEMENTS);
        // when
        Optional<Integer> result = service.findMin(array);
        // then
        assertTrue(result.isPresent());
        assertEquals(-10, result.get());
    }

    @Test
    void testFindMinNullThrowsArrayException() {
        // given
        IntArray array = null;
        // when & then
        assertThrows(ArrayException.class, () -> service.findMin(array));
    }

    // ===== findMax =====

    @Test
    void testFindMaxReturnsLargestElement() throws ArrayException {
        // given
        IntArray array = new IntArray(MIXED_ELEMENTS);
        // when
        Optional<Integer> result = service.findMax(array);
        // then
        assertTrue(result.isPresent());
        assertEquals(9, result.get());
    }

    @Test
    void testFindMaxEmptyArrayReturnsEmpty() throws ArrayException {
        // given
        IntArray array = new IntArray(EMPTY_ELEMENTS);
        // when
        Optional<Integer> result = service.findMax(array);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindMaxNullThrowsArrayException() {
        // given
        IntArray array = null;
        // when & then
        assertThrows(ArrayException.class, () -> service.findMax(array));
    }

    // ===== findSum =====

    @Test
    void testFindSumReturnsTotalForSingleElement() throws ArrayException {
        // given
        IntArray array = new IntArray(SINGLE_ELEMENT);
        // when
        Optional<Long> result = service.findSum(array);
        // then
        assertTrue(result.isPresent());
        assertEquals(42L, result.get());
    }

    @Test
    void testFindSumReturnsTotalForMultipleElements() throws ArrayException {
        // given
        IntArray array = new IntArray(SYMMETRIC_ELEMENTS);
        // when
        Optional<Long> result = service.findSum(array);
        // then
        assertTrue(result.isPresent());
        assertEquals(12L, result.get());
    }

    @Test
    void testFindSumEmptyArrayReturnsEmpty() throws ArrayException {
        // given
        IntArray array = new IntArray(EMPTY_ELEMENTS);
        // when
        Optional<Long> result = service.findSum(array);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindSumNullThrowsArrayException() {
        // given
        IntArray array = null;
        // when & then
        assertThrows(ArrayException.class, () -> service.findSum(array));
    }

    // ===== findAverage =====

    @Test
    void testFindAverageReturnsCorrectMean() throws ArrayException {
        // given
        IntArray array = new IntArray(SYMMETRIC_ELEMENTS);
        // when
        Optional<Double> result = service.findAverage(array);
        // then
        assertTrue(result.isPresent());
        assertEquals(4.0, result.get(), 0.0001);
    }

    @Test
    void testFindAverageEmptyArrayReturnsEmpty() throws ArrayException {
        // given
        IntArray array = new IntArray(EMPTY_ELEMENTS);
        // when
        Optional<Double> result = service.findAverage(array);
        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindAverageNullThrowsArrayException() {
        // given
        IntArray array = null;
        // when & then
        assertThrows(ArrayException.class, () -> service.findAverage(array));
    }
}
