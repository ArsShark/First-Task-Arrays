package by.grechanikovars.arraytask.warehouse;

import by.grechanikovars.arraytask.entity.IntArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseManagerTest {

  private static final int[] ELEMENTS_A = {1, 2, 3};
  private static final int[] ELEMENTS_B = {10, 20, 30};
  private static final int[] NEW_ELEMENTS = {100, 200, 300};

  private WarehouseManager manager;
  private ArrayWarehouse warehouse;

  @BeforeEach
  void setUp() {
    manager   = new WarehouseManager();
    warehouse = ArrayWarehouse.getInstance();
  }

  @Test
  void testAttachObserverPopulatesWarehouse() {
    // given
    IntArray array = new IntArray(ELEMENTS_A);
    // when
    manager.attachObserver(array);
    // then
    long arrayId = array.getId();
    Optional<ArrayStatisticsData> actual = warehouse.getStatistics(arrayId);
    assertTrue(actual.isPresent());
  }

  @Test
  void testAttachObserverStoresCorrectStatistics() {
    IntArray array = new IntArray(ELEMENTS_A);

    manager.attachObserver(array);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);
    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertAll(
            () -> assertEquals(1,   actual.min()),
            () -> assertEquals(3,   actual.max()),
            () -> assertEquals(6L,  actual.sum()),
            () -> assertEquals(2.0, actual.average(), 0.001)
    );
  }

  @Test
  void testDetachObserverRemovesStatisticsFromWarehouse() {
    IntArray array = new IntArray(ELEMENTS_A);
    manager.attachObserver(array);

    manager.detachObserver(array);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> actual = warehouse.getStatistics(arrayId);
    assertTrue(actual.isEmpty());
  }

  @Test
  void testAfterDetachSetElementsDoesNotUpdateWarehouse() {
    IntArray array = new IntArray(ELEMENTS_A);
    manager.attachObserver(array);
    long arrayId = array.getId();
    manager.detachObserver(array);
    warehouse.removeStatistics(arrayId);

    array.setElements(NEW_ELEMENTS);

    Optional<ArrayStatisticsData> actual = warehouse.getStatistics(arrayId);
    assertTrue(actual.isEmpty());
  }

  @Test
  void testSetElementsAfterAttachUpdatesWarehouse() {
    IntArray array = new IntArray(ELEMENTS_A);
    manager.attachObserver(array);

    array.setElements(ELEMENTS_B);

    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);
    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertAll(
            () -> assertEquals(10,   actual.min()),
            () -> assertEquals(30,   actual.max()),
            () -> assertEquals(60L,  actual.sum())
    );
  }

  @Test
  void testInitializeAllPopulatesWarehouseForEachArray() {
    IntArray arrayA = new IntArray(ELEMENTS_A);
    IntArray arrayB = new IntArray(ELEMENTS_B);
    List<IntArray> arrays = List.of(arrayA, arrayB);

    manager.initializeAll(arrays);

    long idA = arrayA.getId();
    long idB = arrayB.getId();
    assertAll(
            () -> assertTrue(warehouse.getStatistics(idA).isPresent()),
            () -> assertTrue(warehouse.getStatistics(idB).isPresent())
    );
  }
}