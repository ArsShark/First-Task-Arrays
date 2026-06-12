package by.grechanikovars.arraytask.specification;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.observer.impl.ArrayStatisticsObserverImpl;
import by.grechanikovars.arraytask.specification.impl.FindByIdSpecificationImpl;
import by.grechanikovars.arraytask.specification.impl.FindByMaxGreaterThanSpecificationImpl;
import by.grechanikovars.arraytask.specification.impl.FindByMinLessThanSpecificationImpl;
import by.grechanikovars.arraytask.specification.impl.FindBySumGreaterThanSpecificationImpl;
import by.grechanikovars.arraytask.specification.impl.FindBySumLessThanSpecificationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArraySpecificationTest {

  private static final int[] SMALL_ELEMENTS = {1, 2, 3};
  private static final int[] LARGE_ELEMENTS = {100, 200, 300};

  private IntArray smallArray;
  private IntArray largeArray;

  @BeforeEach
  void setUp() {
    ArrayStatisticsObserverImpl observer = new ArrayStatisticsObserverImpl();
    smallArray = new IntArray(SMALL_ELEMENTS);
    observer.update(smallArray);
    largeArray = new IntArray(LARGE_ELEMENTS);
    observer.update(largeArray);
  }

  @Test
  void testFindByIdMatchesCorrectArray() {
    // given
    long targetId = smallArray.getId();
    FindByIdSpecificationImpl spec = new FindByIdSpecificationImpl(targetId);
    // when
    boolean actual = spec.test(smallArray);
    // then
    assertTrue(actual);
  }

  @Test
  void testFindByIdDoesNotMatchOtherId() {
    long targetId = smallArray.getId();
    FindByIdSpecificationImpl spec = new FindByIdSpecificationImpl(targetId);

    boolean actual = spec.test(largeArray);

    assertFalse(actual);
  }

  @Test
  void testFindBySumGreaterThanMatchesLargeArray() {
    FindBySumGreaterThanSpecificationImpl spec =
            new FindBySumGreaterThanSpecificationImpl(100L);

    boolean actual = spec.test(largeArray);

    assertTrue(actual);
  }

  @Test
  void testFindBySumGreaterThanDoesNotMatchSmallArray() {
    FindBySumGreaterThanSpecificationImpl spec =
            new FindBySumGreaterThanSpecificationImpl(100L);

    boolean actual = spec.test(smallArray);

    assertFalse(actual);
  }

  @Test
  void testFindBySumLessThanMatchesSmallArray() {
    FindBySumLessThanSpecificationImpl spec =
            new FindBySumLessThanSpecificationImpl(100L);

    boolean actual = spec.test(smallArray);

    assertTrue(actual);
  }

  @Test
  void testFindBySumLessThanDoesNotMatchLargeArray() {
    FindBySumLessThanSpecificationImpl spec =
            new FindBySumLessThanSpecificationImpl(100L);

    boolean actual = spec.test(largeArray);

    assertFalse(actual);
  }

  @Test
  void testFindByMaxGreaterThanMatchesLargeArray() {
    FindByMaxGreaterThanSpecificationImpl spec =
            new FindByMaxGreaterThanSpecificationImpl(50);

    boolean actual = spec.test(largeArray);

    assertTrue(actual);
  }

  @Test
  void testFindByMaxGreaterThanDoesNotMatchSmallArray() {
    FindByMaxGreaterThanSpecificationImpl spec =
            new FindByMaxGreaterThanSpecificationImpl(50);

    boolean actual = spec.test(smallArray);

    assertFalse(actual);
  }

  @Test
  void testFindByMinLessThanMatchesSmallArray() {
    FindByMinLessThanSpecificationImpl spec =
            new FindByMinLessThanSpecificationImpl(10);

    boolean actual = spec.test(smallArray);

    assertTrue(actual);
  }

  @Test
  void testFindByMinLessThanDoesNotMatchLargeArray() {
    FindByMinLessThanSpecificationImpl spec =
            new FindByMinLessThanSpecificationImpl(10);

    boolean actual = spec.test(largeArray);

    assertFalse(actual);
  }
}