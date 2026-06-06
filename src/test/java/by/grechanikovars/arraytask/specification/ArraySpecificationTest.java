package by.grechanikovars.arraytask.specification;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.observer.ArrayObserver;
import by.grechanikovars.arraytask.observer.impl.ArrayStatisticsObserverImpl;
import by.grechanikovars.arraytask.specification.impl.*;
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
    ArrayObserver observer = new ArrayStatisticsObserverImpl();

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
    boolean result = spec.specify(smallArray);
    // then
    assertTrue(result);
  }

  @Test
  void testFindByIdDoesNotMatchOtherId() {
    long targetId = smallArray.getId();
    FindByIdSpecificationImpl spec = new FindByIdSpecificationImpl(targetId);

    boolean result = spec.specify(largeArray);

    assertFalse(result);
  }

  @Test
  void testFindBySumGreaterThanMatchesLargeArray() {
    FindBySumGreaterThanSpecificationImpl spec = new FindBySumGreaterThanSpecificationImpl(100L);

    boolean result = spec.specify(largeArray);

    assertTrue(result);
  }

  @Test
  void testFindBySumGreaterThanDoesNotMatchSmallArray() {
    FindBySumGreaterThanSpecificationImpl spec = new FindBySumGreaterThanSpecificationImpl(100L);

    boolean result = spec.specify(smallArray);

    assertFalse(result);
  }

  @Test
  void testFindBySumLessThanMatchesSmallArray() {
    FindBySumLessThanSpecificationImpl spec = new FindBySumLessThanSpecificationImpl(100L);

    boolean result = spec.specify(smallArray);

    assertTrue(result);
  }

  @Test
  void testFindBySumLessThanDoesNotMatchLargeArray() {
    FindBySumLessThanSpecificationImpl spec = new FindBySumLessThanSpecificationImpl(100L);

    boolean result = spec.specify(largeArray);

    assertFalse(result);
  }

  @Test
  void testFindByMaxGreaterThanMatchesLargeArray() {
    FindByMaxGreaterThanSpecificationImpl spec = new FindByMaxGreaterThanSpecificationImpl(50);

    boolean result = spec.specify(largeArray);

    assertTrue(result);
  }

  @Test
  void testFindByMaxGreaterThanDoesNotMatchSmallArray() {
    FindByMaxGreaterThanSpecificationImpl spec = new FindByMaxGreaterThanSpecificationImpl(50);

    boolean result = spec.specify(smallArray);

    assertFalse(result);
  }

  @Test
  void testFindByMinLessThanMatchesSmallArray() {
    FindByMinLessThanSpecificationImpl spec = new FindByMinLessThanSpecificationImpl(10);

    boolean result = spec.specify(smallArray);

    assertTrue(result);
  }

  @Test
  void testFindByMinLessThanDoesNotMatchLargeArray() {
    FindByMinLessThanSpecificationImpl spec = new FindByMinLessThanSpecificationImpl(10);

    boolean result = spec.specify(largeArray);

    assertFalse(result);
  }
}
