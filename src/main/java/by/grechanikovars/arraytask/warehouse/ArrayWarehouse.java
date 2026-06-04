package by.grechanikovars.arraytask.warehouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Singleton warehouse that caches statistics for every IntArray in the repository.
 * Key   — array id.
 * Value — ArrayStatisticsData (min, max, sum, average).
 *
 * <p>Non-thread-safe Singleton as required by the task specification.
 * Must NOT implement ArrayObserver.
 */
public class ArrayWarehouse {

    private static final Logger logger = LogManager.getLogger(ArrayWarehouse.class);

    private static ArrayWarehouse instance;

    private final Map<Long, ArrayStatisticsData> statisticsMap;

    private ArrayWarehouse() {
        statisticsMap = new HashMap<>();
    }

    /**
     * Returns the single instance of ArrayWarehouse.
     *
     * @return singleton instance
     */
    public static ArrayWarehouse getInstance() {
        if (instance == null) {
            instance = new ArrayWarehouse();
        }
        return instance;
    }

    /**
     * Stores or replaces statistics for the given array id.
     *
     * @param arrayId id of the array
     * @param data    computed statistics
     */
    public void updateStatistics(long arrayId, ArrayStatisticsData data) {
        statisticsMap.put(arrayId, data);
        logger.info("Warehouse updated for array id={}: {}", arrayId, data);
    }

    /**
     * Retrieves statistics for the given array id.
     *
     * @param arrayId id of the array
     * @return Optional with statistics, or empty if not found
     */
    public Optional<ArrayStatisticsData> getStatistics(long arrayId) {
        ArrayStatisticsData data = statisticsMap.get(arrayId);
        return Optional.ofNullable(data);
    }

    /**
     * Removes statistics entry for the given array id.
     *
     * @param arrayId id of the array to remove
     */
    public void removeStatistics(long arrayId) {
        statisticsMap.remove(arrayId);
        logger.info("Warehouse entry removed for array id={}", arrayId);
    }
}