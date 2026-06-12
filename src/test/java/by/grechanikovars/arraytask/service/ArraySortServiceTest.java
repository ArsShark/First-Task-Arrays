package by.grechanikovars.arraytask.service;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.service.impl.ArraySortServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArraySortServiceTest {

  private ArraySortService service;

  @BeforeEach
  void setUp() {
    service = new ArraySortServiceImpl();
  }

  static Stream<Arguments> provideArraysForSorting() {
    return Stream.of(
            Arguments.of(
                    new int[]{3, 1, 2},
                    new int[]{1, 2, 3}
            ),
            Arguments.of(
                    new int[]{9, 7, 5, 3, 1},
                    new int[]{1, 3, 5, 7, 9}
            ),
            Arguments.of(
                    new int[]{42},
                    new int[]{42}
            ),
            Arguments.of(
                    new int[]{-3, -1, -5},
                    new int[]{-5, -3, -1}
            ),
            Arguments.of(
                    new int[]{0, -100, 100, 0},
                    new int[]{-100, 0, 0, 100}
            )
    );
  }

  @ParameterizedTest
  @MethodSource("provideArraysForSorting")
  void givenArray_whenBubbleSort_thenSortedArrayReturned(
          int[] source,
          int[] expected
  ) throws ArrayException {

    // given
    IntArray array = new IntArray(source);

    // when
    service.bubbleSort(array);
    int[] actual = array.getElements();

    // then
    assertArrayEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("provideArraysForSorting")
  void givenArray_whenSelectionSort_thenSortedArrayReturned(
          int[] source,
          int[] expected
  ) throws ArrayException {
    IntArray array = new IntArray(source);

    service.selectionSort(array);
    int[] actual = array.getElements();


    assertArrayEquals(expected, actual);
  }
}