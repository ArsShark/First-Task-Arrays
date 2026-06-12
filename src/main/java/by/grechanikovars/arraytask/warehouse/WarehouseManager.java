package by.grechanikovars.arraytask.warehouse;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.observer.ArrayObserver;
import by.grechanikovars.arraytask.observer.impl.ArrayStatisticsObserverImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class WarehouseManager {

  private static final Logger logger = LogManager.getLogger(WarehouseManager.class);

  private final ArrayWarehouse warehouse;

  public WarehouseManager() {
    this.warehouse = ArrayWarehouse.getInstance();
  }

  public void attachObserver(IntArray array) {
    ArrayObserver observer = new ArrayStatisticsObserverImpl();
    array.setObserver(observer);
    observer.update(array);
    logger.info("Observer attached and initial statistics computed for id={}", array.getId());
  }

  public void detachObserver(IntArray array) {
    long arrayId = array.getId();
    array.setObserver(null);
    warehouse.removeStatistics(arrayId);
    logger.info("Observer detached and statistics removed for id={}", arrayId);
  }

  public void initializeAll(List<IntArray> arrays) {
    for (IntArray array : arrays) {
      attachObserver(array);
    }
  }
}