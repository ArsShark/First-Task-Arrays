package by.grechanikovars.arraytask.specification.impl;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.specification.ArraySpecification;
import by.grechanikovars.arraytask.warehouse.ArrayStatisticsData;
import by.grechanikovars.arraytask.warehouse.ArrayWarehouse;

import java.util.Optional;

public class FindBySumGreaterThanSpecificationImpl implements ArraySpecification {

  private final long threshold;

  public FindBySumGreaterThanSpecificationImpl(long threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(IntArray array) {
    ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);
    if (statsOpt.isPresent()) {
      ArrayStatisticsData stats = statsOpt.get();
      long sum = stats.sum();
      return sum > threshold;
    } else {
      return false;
    }
  }
}