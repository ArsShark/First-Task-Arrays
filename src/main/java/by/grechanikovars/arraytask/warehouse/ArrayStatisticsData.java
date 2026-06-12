package by.grechanikovars.arraytask.warehouse;

public record ArrayStatisticsData(int min, int max, long sum, double average) {

  @Override
  public String toString() {
    return String.format(
            "ArrayStatisticsData{min=%d, max=%d, sum=%d, average=%.2f}",
            min, max, sum, average);
  }
}