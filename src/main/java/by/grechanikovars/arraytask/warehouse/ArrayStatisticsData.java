package by.grechanikovars.arraytask.warehouse;

public class ArrayStatisticsData {

  private final int min;
  private final int max;
  private final long sum;
  private final double average;

  public ArrayStatisticsData(int min, int max, long sum, double average) {
    this.min = min;
    this.max = max;
    this.sum = sum;
    this.average = average;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  public long getSum() {
    return sum;
  }

  public double getAverage() {
    return average;
  }

  @Override
  public String toString() {
    return String.format(
            "ArrayStatisticsData{min=%d, max=%d, sum=%d, average=%.2f}",
            min, max, sum, average);
  }
}
