package by.grechanikovars.arraytask.specification.impl;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.specification.ArraySpecification;
import by.grechanikovars.arraytask.warehouse.ArrayStatisticsData;
import by.grechanikovars.arraytask.warehouse.ArrayWarehouse;

import java.util.Optional;

public class FindByMinLessThanSpecificationImpl implements ArraySpecification {

  private final int threshold;

  public FindByMinLessThanSpecificationImpl(int threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(IntArray array) {
    ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
    long arrayId = array.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(arrayId);
    if (statsOpt.isPresent()) {
      ArrayStatisticsData stats = statsOpt.get();
      int min = stats.min();
      return min < threshold;
    } else {
      return false;
    }
  }
}