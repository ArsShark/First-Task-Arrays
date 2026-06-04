package by.grechanikovars.arraytask.warehouse;

/**
 * Immutable value object that holds pre-computed statistics for one IntArray.
 * Stored in ArrayWarehouse, keyed by array id.
 */
public class ArrayStatisticsData {

    private final int min;
    private final int max;
    private final long sum;
    private final double average;

    /**
     * @param min     minimum element value
     * @param max     maximum element value
     * @param sum     sum of all elements
     * @param average arithmetic mean of all elements
     */
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
        return "ArrayStatisticsData{"
                + "min=" + min
                + ", max=" + max
                + ", sum=" + sum
                + ", average=" + average
                + "}";
    }
}