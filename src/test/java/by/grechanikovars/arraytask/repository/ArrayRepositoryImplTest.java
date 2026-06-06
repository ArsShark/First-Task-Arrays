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

  private static final int[] SMALL_ELEMENTS = {1, 2, 3};
  private static final int[] LARGE_ELEMENTS = {100, 200, 300};
  private static final int[] SINGLE_ELEMENT = {42};

  private ArrayRepository repository;

  @BeforeEach
  void setUp() {
    repository = ArrayRepositoryImpl.getInstance();
  }

  @Test
  void testAddIncreasesSize() {
    // given
    IntArray array = new IntArray(SMALL_ELEMENTS);
    int sizeBefore = repository.getAll().size();
    // when
    repository.add(array);
    // then
    int sizeAfter = repository.getAll().size();
    assertEquals(sizeBefore + 1, sizeAfter);
  }

  @Test
  void testRemoveReturnsTrueForExistingId() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);
    long arrayId = array.getId();

    boolean result = repository.remove(arrayId);

    assertTrue(result);
  }

  @Test
  void testRemoveReturnsFalseForMissingId() {
    long missingId = Long.MAX_VALUE;

    boolean result = repository.remove(missingId);

    assertFalse(result);
  }

  @Test
  void testRemoveDecreasesSize() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);
    long arrayId = array.getId();
    int sizeBefore = repository.getAll().size();

    repository.remove(arrayId);

    int sizeAfter = repository.getAll().size();
    assertEquals(sizeBefore - 1, sizeAfter);
  }

  @Test
  void testFindAllByIdReturnsMatchingArray() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);
    long arrayId = array.getId();
    FindByIdSpecificationImpl spec = new FindByIdSpecificationImpl(arrayId);

    List<IntArray> result = repository.findAll(spec);

    assertEquals(1, result.size());
    assertEquals(arrayId, result.get(0).getId());
  }

  @Test
  void testFindAllFunctionalBySumGreaterThan() {
    IntArray small = new IntArray(SMALL_ELEMENTS);
    IntArray large = new IntArray(LARGE_ELEMENTS);
    repository.add(small);
    repository.add(large);
    FindBySumGreaterThanSpecificationImpl spec = new FindBySumGreaterThanSpecificationImpl(100L);

    List<IntArray> result = repository.findAllFunctional(spec);

    assertFalse(result.isEmpty());
    long largeId = large.getId();
    boolean containsLarge = false;
    for (IntArray a : result) {
      if (a.getId() == largeId) {
        containsLarge = true;
        break;
      }
    }
    assertTrue(containsLarge);
  }

  @Test
  void testSortByIdProducesAscendingOrder() throws ArrayException {
    IntArray first = new IntArray(LARGE_ELEMENTS);
    IntArray second = new IntArray(SMALL_ELEMENTS);
    repository.add(first);
    repository.add(second);

    repository.sort(new ArrayIdComparator());

    List<IntArray> all = repository.getAll();
    for (int i = 0; i < all.size() - 1; i++) {
      long currentId = all.get(i).getId();
      long nextId = all.get(i + 1).getId();
      assertTrue(currentId <= nextId);
    }
  }

  @Test
  void testSortBySizeProducesAscendingOrder() throws ArrayException {
    IntArray big = new IntArray(new int[]{1, 2, 3, 4, 5});
    IntArray small = new IntArray(SINGLE_ELEMENT);
    repository.add(big);
    repository.add(small);

    repository.sort(new ArraySizeComparator());

    List<IntArray> all = repository.getAll();
    for (int i = 0; i < all.size() - 1; i++) {
      int currentSize = all.get(i).size();
      int nextSize = all.get(i + 1).size();
      assertTrue(currentSize <= nextSize);
    }
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

    List<IntArray> result = repository.getAll();
    result.clear();

    assertFalse(repository.getAll().isEmpty());
  }

  @Test
  void testObserverDetachedAfterRemove() {
    IntArray array = new IntArray(SMALL_ELEMENTS);
    repository.add(array);
    long arrayId = array.getId();
    repository.remove(arrayId);

    ArrayWarehouse warehouseBefore = ArrayWarehouse.getInstance();
    warehouseBefore.removeStatistics(arrayId);
    array.setElements(new int[]{999, 999, 999});

    ArrayWarehouse warehouseAfter = ArrayWarehouse.getInstance();
    Optional<ArrayStatisticsData> result = warehouseAfter.getStatistics(arrayId);
    assertTrue(result.isEmpty());
  }
}