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

    private static final int[] SAMPLE_ELEMENTS = {2, 4, 6};
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
        Optional<ArrayStatisticsData> result = warehouse.getStatistics(arrayId);
        assertTrue(result.isPresent());
    }

    @Test
    void testUpdateStoresCorrectMin() {
        IntArray array = new IntArray(SAMPLE_ELEMENTS);

        observer.update(array);

        long arrayId = array.getId();
        Optional<ArrayStatisticsData> result = warehouse.getStatistics(arrayId);
        assertTrue(result.isPresent());
        ArrayStatisticsData stats = result.get();
        assertEquals(2, stats.getMin());
    }

    @Test
    void testUpdateStoresCorrectMax() {
        IntArray array = new IntArray(SAMPLE_ELEMENTS);

        observer.update(array);

        long arrayId = array.getId();
        Optional<ArrayStatisticsData> result = warehouse.getStatistics(arrayId);
        assertTrue(result.isPresent());
        ArrayStatisticsData stats = result.get();
        assertEquals(6, stats.getMax());
    }

    @Test
    void testUpdateStoresCorrectSum() {
        IntArray array = new IntArray(SAMPLE_ELEMENTS);

        observer.update(array);

        long arrayId = array.getId();
        Optional<ArrayStatisticsData> result = warehouse.getStatistics(arrayId);
        assertTrue(result.isPresent());
        ArrayStatisticsData stats = result.get();
        assertEquals(12L, stats.getSum());
    }

    @Test
    void testUpdateStoresCorrectAverage() {
        IntArray array = new IntArray(SAMPLE_ELEMENTS);

        observer.update(array);

        long arrayId = array.getId();
        Optional<ArrayStatisticsData> result = warehouse.getStatistics(arrayId);
        assertTrue(result.isPresent());
        ArrayStatisticsData stats = result.get();
        assertEquals(4.0, stats.getAverage(), 0.001);
    }

    @Test
    void testSetElementsTriggersObserverAndUpdatesWarehouse() {
        IntArray array = new IntArray(SAMPLE_ELEMENTS);
        array.addObserver(observer);
        observer.update(array);

        array.setElements(UPDATED_ELEMENTS);

        long arrayId = array.getId();
        Optional<ArrayStatisticsData> result = warehouse.getStatistics(arrayId);
        assertTrue(result.isPresent());
        ArrayStatisticsData stats = result.get();
        assertEquals(10, stats.getMin());
        assertEquals(30, stats.getMax());
        assertEquals(60L, stats.getSum());
    }

    @Test
    void testUpdateOnEmptyArrayDoesNotStoreStatistics() {

        IntArray array = new IntArray(new int[]{});

        observer.update(array);

        long arrayId = array.getId();
        Optional<ArrayStatisticsData> result = warehouse.getStatistics(arrayId);
        assertTrue(result.isEmpty());
    }
}
