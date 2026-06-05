package by.grechanikovars.arraytask.warehouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArrayWarehouse {

    private static final Logger logger = LogManager.getLogger(ArrayWarehouse.class);

    private static ArrayWarehouse instance;

    private final Map<Long, ArrayStatisticsData> statisticsMap;

    private ArrayWarehouse() {
        statisticsMap = new HashMap<>();
    }

    public static ArrayWarehouse getInstance() {
        if (instance == null) {
            instance = new ArrayWarehouse();
        }
        return instance;
    }

    public void updateStatistics(long arrayId, ArrayStatisticsData data) {
        statisticsMap.put(arrayId, data);
        logger.info("Warehouse updated for array id={}: {}", arrayId, data);
    }

    public Optional<ArrayStatisticsData> getStatistics(long arrayId) {
        ArrayStatisticsData data = statisticsMap.get(arrayId);
        return Optional.ofNullable(data);
    }

    public void removeStatistics(long arrayId) {
        statisticsMap.remove(arrayId);
        logger.info("Warehouse entry removed for array id={}", arrayId);
    }
}
