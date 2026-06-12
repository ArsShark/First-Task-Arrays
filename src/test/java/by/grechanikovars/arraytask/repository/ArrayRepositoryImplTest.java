package by.grechanikovars.arraytask.repository;

import by.grechanikovars.arraytask.comparator.ArrayIdComparator;
import by.grechanikovars.arraytask.comparator.ArraySizeComparator;
import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.repository.impl.ArrayRepositoryImpl;
import by.grechanikovars.arraytask.specification.impl.FindByIdSpecificationImpl;
import by.grechanikovars.arraytask.specification.impl.FindBySumGreaterThanSpecificationImpl;
import by.grechanikovars.arraytask.warehouse.ArrayStatisticsData;
import by.grechanikovars.arraytask.warehouse.ArrayWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRepositoryImplTest {

  private static final int[] SMALL_ELEMENTS  = {1, 2, 3};
  private static final int[] LARGE_ELEMENTS  = {100, 200, 300};
  private static final int[] SINGLE_ELEMENT  = {42};
  private static final int[] FIVE_ELEMENTS   = {1, 2, 3, 4, 5};

  private ArrayRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    repository = ArrayRepositoryImpl.getInstance();
    repository.clear();
  }

  @Test
  void testAddIncreasesSize() {
    // given
    IntArray array = new IntArray(SMALL_ELEMENTS);
    int expected = 1;
    // when
    repository.add(array);
    // then
    int actual = repository.getAll().size();
    assertEquals(expected, actual);
  }

  @Test
  void testRemoveReturnsTrueForExistingId() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);
    long arrayId = array.getId();

    boolean actual = repository.remove(arrayId);

    assertTrue(actual);
  }

  @Test
  void testRemoveReturnsFalseForMissingId() {
    long missingId = Long.MAX_VALUE;

    boolean actual = repository.remove(missingId);

    assertFalse(actual);
  }

  @Test
  void testRemoveDecreasesSize() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);
    long arrayId = array.getId();

    repository.remove(arrayId);

    int actual = repository.getAll().size();
    assertEquals(0, actual);
  }

  @Test
  void testFindByIdReturnsMatchingArray() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);
    long arrayId = array.getId();
    FindByIdSpecificationImpl spec = new FindByIdSpecificationImpl(arrayId);

    List<IntArray> actual = repository.findBy(spec);

    assertAll(
            () -> assertEquals(1, actual.size()),
            () -> assertEquals(arrayId, actual.get(0).getId())
    );
  }

  @Test
  void testFindByIdReturnsEmptyForMissingId() {
    FindByIdSpecificationImpl spec = new FindByIdSpecificationImpl(Long.MAX_VALUE);

    List<IntArray> actual = repository.findBy(spec);

    assertTrue(actual.isEmpty());
  }

  @Test
  void testFindByFunctionalSumGreaterThanReturnsLargeArray() {
    IntArray large = new IntArray(LARGE_ELEMENTS);
    repository.add(large);
    ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
    long largeId = large.getId();
    warehouse.updateStatistics(largeId, new ArrayStatisticsData(100, 300, 600L, 200.0));
    FindBySumGreaterThanSpecificationImpl spec = new FindBySumGreaterThanSpecificationImpl(100L);

    List<IntArray> actual = repository.findByFunctional(spec);

    assertFalse(actual.isEmpty());
  }

  @Test
  void testSortByIdPlacesLowerIdFirst() throws ArrayException {
    IntArray lower = new IntArray(SMALL_ELEMENTS);
    IntArray higher = new IntArray(LARGE_ELEMENTS);
    long lowerId = lower.getId();
    long higherId = higher.getId();
    repository.add(higher);
    repository.add(lower);

    repository.sort(new ArrayIdComparator());

    List<IntArray> actual = repository.getAll();
    assertAll(
            () -> assertEquals(lowerId, actual.get(0).getId()),
            () -> assertEquals(higherId, actual.get(1).getId())
    );
  }

  @Test
  void testSortBySizePlacesSmallerArrayFirst() throws ArrayException {
    IntArray big = new IntArray(FIVE_ELEMENTS);
    IntArray small = new IntArray(SINGLE_ELEMENT);
    repository.add(big);
    repository.add(small);

    repository.sort(new ArraySizeComparator());

    List<IntArray> actual = repository.getAll();
    assertAll(
            () -> assertEquals(1, actual.get(0).length()),
            () -> assertEquals(5, actual.get(1).length())
    );
  }

  @Test
  void testSortWithNullComparatorThrowsException() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);

    assertThrows(ArrayException.class, () -> repository.sort(null));
  }

  @Test
  void testGetAllReturnsCopy() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);

    List<IntArray> copy = repository.getAll();
    copy.clear();

    assertFalse(repository.getAll().isEmpty());
  }

  @Test
  void testObserverDetachedAfterRemove() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);
    long arrayId = array.getId();
    repository.remove(arrayId);
    ArrayWarehouse.getInstance().removeStatistics(arrayId);

    array.setElements(new int[]{999, 999, 999});

    Optional<ArrayStatisticsData> actual = ArrayWarehouse.getInstance().getStatistics(arrayId);
    assertTrue(actual.isEmpty());
  }
}