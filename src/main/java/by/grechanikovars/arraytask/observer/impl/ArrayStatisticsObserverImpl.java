package by.grechanikovars.arraytask.observer.impl;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.observer.ArrayObserver;
import by.grechanikovars.arraytask.service.ArrayStatService;
import by.grechanikovars.arraytask.service.impl.ArrayStatServiceImpl;
import by.grechanikovars.arraytask.warehouse.ArrayStatisticsData;
import by.grechanikovars.arraytask.warehouse.ArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ArrayStatisticsObserverImpl implements ArrayObserver {

    private static final Logger logger = LogManager.getLogger(ArrayStatisticsObserverImpl.class);

    private final ArrayStatService statService;

    public ArrayStatisticsObserverImpl() {
        this.statService = new ArrayStatServiceImpl();
    }

    @Override
    public void update(IntArray array) {
        try {
            Optional<Integer> optMin = statService.findMin(array);
            Optional<Integer> optMax = statService.findMax(array);
            Optional<Long> optSum = statService.findSum(array);
            Optional<Double> optAvg = statService.findAverage(array);

            if (optMin.isPresent() && optMax.isPresent()
                    && optSum.isPresent() && optAvg.isPresent()) {
                int minValue = optMin.get();
                int maxValue = optMax.get();
                long sumValue = optSum.get();
                double avgValue = optAvg.get();
                ArrayStatisticsData data =
                        new ArrayStatisticsData(minValue, maxValue, sumValue, avgValue);
                ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
                long arrayId = array.getId();
                warehouse.updateStatistics(arrayId, data);
            } else {
                long arrayId = array.getId();
                logger.warn("Cannot compute statistics for empty array id={}", arrayId);
            }
        } catch (ArrayException e) {
            long arrayId = array.getId();
            logger.error("Statistics update failed for array id={}", arrayId, e);
        }
    }
}
