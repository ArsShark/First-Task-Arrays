package by.grechanikovars.arraytask.service;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;

import java.util.Optional;

/**
 * Service interface for statistical computations on an IntArray.
 *
 * <p>Optional is used for min, max, sum, and average because these
 * operations are undefined on an empty array.
 */
public interface ArrayStatService {

    /**
     * @param array source array
     * @return Optional containing the minimum, or empty if the array is empty
     * @throws ArrayException if array is null
     */
    Optional<Integer> findMin(IntArray array) throws ArrayException;

    /**
     * @param array source array
     * @return Optional containing the maximum, or empty if the array is empty
     * @throws ArrayException if array is null
     */
    Optional<Integer> findMax(IntArray array) throws ArrayException;

    /**
     * @param array source array
     * @return Optional containing the sum, or empty if the array is empty
     * @throws ArrayException if array is null
     */
    Optional<Long> findSum(IntArray array) throws ArrayException;

    /**
     * @param array source array
     * @return Optional containing the average, or empty if the array is empty
     * @throws ArrayException if array is null
     */
    Optional<Double> findAverage(IntArray array) throws ArrayException;
}
