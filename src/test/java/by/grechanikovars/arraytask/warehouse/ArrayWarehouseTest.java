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
    void testUpdateAndRetrieveStatistics() {
        warehouse.updateStatistics(ARRAY_ID_1, STATS_1);

        Optional<ArrayStatisticsData> result = warehouse.getStatistics(ARRAY_ID_1);

        assertTrue(result.isPresent());
    }

    @Test
    void testRetrievedStatisticsHaveCorrectMin() {
        warehouse.updateStatistics(ARRAY_ID_1, STATS_1);

        Optional<ArrayStatisticsData> result = warehouse.getStatistics(ARRAY_ID_1);

        assertTrue(result.isPresent());
        ArrayStatisticsData stats = result.get();
        assertEquals(1, stats.getMin());
    }

    @Test
    void testRetrievedStatisticsHaveCorrectSum() {
        warehouse.updateStatistics(ARRAY_ID_2, STATS_2);

        Optional<ArrayStatisticsData> result = warehouse.getStatistics(ARRAY_ID_2);

        assertTrue(result.isPresent());
        ArrayStatisticsData stats = result.get();
        assertEquals(20L, stats.getSum());
    }

    @Test
    void testGetStatisticsForMissingIdReturnsEmpty() {
        Optional<ArrayStatisticsData> result = warehouse.getStatistics(MISSING_ID);

        assertTrue(result.isEmpty());
    }

    @Test
    void testRemoveStatisticsDeletesEntry() {
        warehouse.updateStatistics(ARRAY_ID_1, STATS_1);

        warehouse.removeStatistics(ARRAY_ID_1);

        Optional<ArrayStatisticsData> result = warehouse.getStatistics(ARRAY_ID_1);
        assertTrue(result.isEmpty());
    }

    @Test
    void testUpdateOverwritesPreviousStatistics() {
        warehouse.updateStatistics(ARRAY_ID_1, STATS_1);

        warehouse.updateStatistics(ARRAY_ID_1, STATS_2);

        Optional<ArrayStatisticsData> result = warehouse.getStatistics(ARRAY_ID_1);
        assertTrue(result.isPresent());
        ArrayStatisticsData stats = result.get();
        assertEquals(-3, stats.getMin());
    }
}
