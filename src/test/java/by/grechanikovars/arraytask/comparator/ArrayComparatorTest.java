package by.grechanikovars.arraytask.comparator;

import by.grechanikovars.arraytask.entity.IntArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayComparatorTest {

  private static final int[] SMALL_ELEMENTS  = {1, 2, 3};
  private static final int[] LARGE_ELEMENTS  = {10, 20};
  private static final int[] SINGLE_ELEMENT  = {99};
  private static final int[] FIVE_ELEMENTS   = {1, 2, 3, 4, 5};
  private static final int[] EMPTY_ELEMENTS  = {};

  static Stream<Arguments> provideFirstElementPairs() {
    return Stream.of(
            Arguments.of(new int[]{1}, new int[]{2}, -1),
            Arguments.of(new int[]{5}, new int[]{5}, 0),
            Arguments.of(new int[]{9}, new int[]{3},  1)
    );
  }

  static Stream<Arguments> provideSizePairs() {
    return Stream.of(
            Arguments.of(new int[]{1},       new int[]{1, 2},  -1),
            Arguments.of(new int[]{1, 2},    new int[]{1, 2},   0),
            Arguments.of(new int[]{1, 2, 3}, new int[]{1},      1)
    );
  }

  @ParameterizedTest
  @MethodSource("provideFirstElementPairs")
  void testFirstElementComparatorParametrized(int[] a, int[] b, int expectedSign) {
    IntArray arrayA = new IntArray(a);
    IntArray arrayB = new IntArray(b);
    ArrayFirstElementComparator comparator = new ArrayFirstElementComparator();

    int actual = comparator.compare(arrayA, arrayB);

    assertEquals(expectedSign, Integer.signum(actual));
  }

  @ParameterizedTest
  @MethodSource("provideSizePairs")
  void testSizeComparatorParametrized(int[] a, int[] b, int expectedSign) {
    IntArray arrayA = new IntArray(a);
    IntArray arrayB = new IntArray(b);
    ArraySizeComparator comparator = new ArraySizeComparator();

    int actual = comparator.compare(arrayA, arrayB);

    assertEquals(expectedSign, Integer.signum(actual));
  }
  @Test
  void testIdComparatorLowerIdComesFirst() {
    // given
    IntArray lower = new IntArray(SMALL_ELEMENTS);
    IntArray higher = new IntArray(LARGE_ELEMENTS);
    ArrayIdComparator comparator = new ArrayIdComparator();
    // when
    int actual = comparator.compare(lower, higher);
    // then
    assertTrue(actual < 0);
  }

  @Test
  void testIdComparatorHigherIdComesLast() {
    IntArray lower = new IntArray(SMALL_ELEMENTS);
    IntArray higher = new IntArray(LARGE_ELEMENTS);
    ArrayIdComparator comparator = new ArrayIdComparator();

    int actual = comparator.compare(higher, lower);

    assertTrue(actual > 0);
  }

  @Test
  void testIdComparatorEqualIdsReturnsZero() {

    IntArray array = new IntArray(SMALL_ELEMENTS);
    ArrayIdComparator comparator = new ArrayIdComparator();

    int actual = comparator.compare(array, array);

    assertEquals(0, actual);
  }

  @Test
  void testSizeComparatorSmallerArrayComesFirst() {
    IntArray small = new IntArray(SINGLE_ELEMENT);   // length 1
    IntArray big   = new IntArray(FIVE_ELEMENTS);    // length 5
    ArraySizeComparator comparator = new ArraySizeComparator();

    int actual = comparator.compare(small, big);

    assertTrue(actual < 0);
  }

  @Test
  void testSizeComparatorBiggerArrayComesLast() {
    IntArray small = new IntArray(SINGLE_ELEMENT);
    IntArray big   = new IntArray(FIVE_ELEMENTS);
    ArraySizeComparator comparator = new ArraySizeComparator();

    int actual = comparator.compare(big, small);

    assertTrue(actual > 0);
  }

  @Test
  void testSizeComparatorSameSizeReturnsZero() {
    IntArray first  = new IntArray(SMALL_ELEMENTS);  // length 3
    IntArray second = new IntArray(new int[]{7, 8, 9}); // length 3
    ArraySizeComparator comparator = new ArraySizeComparator();

    int actual = comparator.compare(first, second);

    assertEquals(0, actual);
  }

  @Test
  void testFirstElementComparatorSmallerFirstComesFirst() {
    IntArray first  = new IntArray(new int[]{1, 9, 9});  // first element 1
    IntArray second = new IntArray(new int[]{5, 0, 0});  // first element 5
    ArrayFirstElementComparator comparator = new ArrayFirstElementComparator();

    int actual = comparator.compare(first, second);

    assertTrue(actual < 0);
  }

  @Test
  void testFirstElementComparatorLargerFirstComesLast() {
    IntArray first  = new IntArray(new int[]{5, 0, 0});
    IntArray second = new IntArray(new int[]{1, 9, 9});
    ArrayFirstElementComparator comparator = new ArrayFirstElementComparator();

    int actual = comparator.compare(first, second);

    assertTrue(actual > 0);
  }

  @Test
  void testFirstElementComparatorEmptyArrayComesFirst() {
    IntArray empty    = new IntArray(EMPTY_ELEMENTS);
    IntArray nonEmpty = new IntArray(SMALL_ELEMENTS);
    ArrayFirstElementComparator comparator = new ArrayFirstElementComparator();

    int actual = comparator.compare(empty, nonEmpty);

    assertTrue(actual < 0);
  }

  @Test
  void testFirstElementComparatorBothEmptyReturnsZero() {
    IntArray first  = new IntArray(EMPTY_ELEMENTS);
    IntArray second = new IntArray(EMPTY_ELEMENTS);
    ArrayFirstElementComparator comparator = new ArrayFirstElementComparator();

    int actual = comparator.compare(first, second);

    assertEquals(0, actual);
  }
}