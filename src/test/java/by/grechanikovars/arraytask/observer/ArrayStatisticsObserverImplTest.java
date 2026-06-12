package by.grechanikovars.arraytask.observer;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.observer.impl.ArrayStatisticsObserverImpl;
import by.grechanikovars.arraytask.warehouse.ArrayStatisticsData;
import by.grechanikovars.arraytask.warehouse.ArrayWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStatisticsObserverImplTest {

  private static final int[] SAMPLE_ELEMENTS  = {2, 4, 6};
  private static final int[] UPDATED_ELEMENTS = {10, 20, 30};

  private ArrayObserver observer;
  private ArrayWarehouse warehouse;

  @BeforeEach
  void setUp() {
    observer = new ArrayStatisticsObserverImpl();
    warehouse = ArrayWarehouse.getInstance();
  }

  @Test
  void testUpdateStoresStatisticsInWarehouse() {
    // given
    IntArray array = new IntArray(SAMPLE_ELEMENTS);
    // when
    observer.update(array);
    // then
    long arrayId = array.getId();
    Optional<ArrayStatisticsData> actual = warehouse.getStatistics(arrayId);
    assertTrue(actual.isPresent());
  }

  @Test
  void testUpdateStoresCorrectMin() {
    IntArray array = new IntArray(SAMPLE_ELEMENTS);
    int expected = 2;

    observer.update(array);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);
    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertEquals(expected, actual.min());
  }

  @Test
  void testUpdateStoresCorrectMax() {
    IntArray array = new IntArray(SAMPLE_ELEMENTS);
    int expected = 6;

    observer.update(array);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);

    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertEquals(expected, actual.max());
  }

  @Test
  void testUpdateStoresCorrectSum() {
    IntArray array = new IntArray(SAMPLE_ELEMENTS);
    long expected = 12L;

    observer.update(array);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);
    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertEquals(expected, actual.sum());
  }

  @Test
  void testUpdateStoresCorrectAverage() {
    IntArray array = new IntArray(SAMPLE_ELEMENTS);
    double expected = 4.0;

    observer.update(array);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);
    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertEquals(expected, actual.average(), 0.001);
  }

  @Test
  void testUpdateStoresAllStatisticsCorrectly() {
    IntArray array = new IntArray(SAMPLE_ELEMENTS);

    observer.update(array);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);
    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertAll(
            () -> assertEquals(2, actual.min()),
            () -> assertEquals(6, actual.max()),
            () -> assertEquals(12L, actual.sum()),
            () -> assertEquals(4.0, actual.average(), 0.001)
    );
  }

  @Test
  void testSetElementsTriggersObserverAndUpdatesWarehouse() {
    IntArray array = new IntArray(SAMPLE_ELEMENTS);
    array.setObserver(observer);
    observer.update(array);

    array.setElements(UPDATED_ELEMENTS);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);
    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertAll(
            () -> assertEquals(10, actual.min()),
            () -> assertEquals(30, actual.max()),
            () -> assertEquals(60L, actual.sum())
    );
  }

  @Test
  void testUpdateOnEmptyArrayDoesNotStoreStatistics() {
    IntArray array = new IntArray(new int[]{});

    observer.update(array);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> actual = warehouse.getStatistics(arrayId);
    assertTrue(actual.isEmpty());
  }
}