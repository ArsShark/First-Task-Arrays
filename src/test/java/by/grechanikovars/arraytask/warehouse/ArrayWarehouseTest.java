package by.grechanikovars.arraytask.warehouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArrayWarehouseTest {

  private static final long ARRAY_ID_1 = 9001L;
  private static final long ARRAY_ID_2 = 9002L;
  private static final long MISSING_ID = 9999L;
  private static final ArrayStatisticsData STATS_1 =
          new ArrayStatisticsData(1, 10, 55L, 5.5);
  private static final ArrayStatisticsData STATS_2 =
          new ArrayStatisticsData(-3, 7, 20L, 4.0);

  private ArrayWarehouse warehouse;

  @BeforeEach
  void setUp() {
    warehouse = ArrayWarehouse.getInstance();
  }

  @Test
  void testGetInstanceReturnsSameObject() {
    // given / when
    ArrayWarehouse first = ArrayWarehouse.getInstance();
    ArrayWarehouse second = ArrayWarehouse.getInstance();
    // then
    assertSame(first, second);
  }

  @Test
  void testUpdateAndRetrieveStatisticsReturnsPresent() {
    warehouse.updateStatistics(ARRAY_ID_1, STATS_1);

    Optional<ArrayStatisticsData> actual = warehouse.getStatistics(ARRAY_ID_1);

    assertTrue(actual.isPresent());
  }

  @Test
  void testRetrievedStatisticsHaveCorrectValues() {
    warehouse.updateStatistics(ARRAY_ID_1, STATS_1);
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(ARRAY_ID_1);

    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertAll(
            () -> assertEquals(1,    actual.min()),
            () -> assertEquals(10,   actual.max()),
            () -> assertEquals(55L,  actual.sum()),
            () -> assertEquals(5.5,  actual.average(), 0.001)
    );
  }

  @Test
  void testRetrievedStatisticsHaveCorrectMinForNegativeValues() {
    warehouse.updateStatistics(ARRAY_ID_2, STATS_2);

    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(ARRAY_ID_2);

    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertAll(
            () -> assertEquals(-3,  actual.min()),
            () -> assertEquals(20L, actual.sum())
    );
  }

  @Test
  void testGetStatisticsForMissingIdReturnsEmpty() {
    Optional<ArrayStatisticsData> actual = warehouse.getStatistics(MISSING_ID);

    assertTrue(actual.isEmpty());
  }

  @Test
  void testRemoveStatisticsDeletesEntry() {
    warehouse.updateStatistics(ARRAY_ID_1, STATS_1);

    warehouse.removeStatistics(ARRAY_ID_1);

    Optional<ArrayStatisticsData> actual = warehouse.getStatistics(ARRAY_ID_1);
    assertTrue(actual.isEmpty());
  }

  @Test
  void testUpdateOverwritesPreviousStatistics() {
    warehouse.updateStatistics(ARRAY_ID_1, STATS_1);

    warehouse.updateStatistics(ARRAY_ID_1, STATS_2);

    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(ARRAY_ID_1);
    assertTrue(statsOpt.isPresent());
    ArrayStatisticsData actual = statsOpt.get();
    assertEquals(-3, actual.min());
  }
}