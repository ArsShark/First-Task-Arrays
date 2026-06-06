package by.grechanikovars.arraytask.service.impl;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.service.ArrayStatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class ArrayStatServiceImpl implements ArrayStatService {

  private static final Logger logger = LogManager.getLogger(ArrayStatServiceImpl.class);

  @Override
  public Optional<Integer> findMin(IntArray array) throws ArrayException {
    if (array == null) {
      throw new ArrayException("Array must not be null");
    }
    int[] elements = array.getElements();
    IntStream elementStream = IntStream.of(elements);
    OptionalInt optMin = elementStream.min();
    if (optMin.isPresent()) {
      int minValue = optMin.getAsInt();
      logger.info("Min value: {}", minValue);
      return Optional.of(minValue);
    } else {
      logger.warn("findMin called on empty array");
      return Optional.empty();
    }
  }

  @Override
  public Optional<Integer> findMax(IntArray array) throws ArrayException {
    if (array == null) {
      throw new ArrayException("Array must not be null");
    }
    int[] elements = array.getElements();
    IntStream elementStream = IntStream.of(elements);
    OptionalInt optMax = elementStream.max();
    if (optMax.isPresent()) {
      int maxValue = optMax.getAsInt();
      logger.info("Max value: {}", maxValue);
      return Optional.of(maxValue);
    } else {
      logger.warn("findMax called on empty array");
      return Optional.empty();
    }
  }

  @Override
  public Optional<Long> findSum(IntArray array) throws ArrayException {
    if (array == null) {
      throw new ArrayException("Array must not be null");
    }
    int[] elements = array.getElements();
    if (elements.length == 0) {
      logger.warn("findSum called on empty array");
      return Optional.empty();
    }
    IntStream elementStream = IntStream.of(elements);
    LongStream longStream = elementStream.asLongStream();
    long sum = longStream.sum();
    logger.info("Sum: {}", sum);
    return Optional.of(sum);
  }

  @Override
  public Optional<Double> findAverage(IntArray array) throws ArrayException {
    if (array == null) {
      throw new ArrayException("Array must not be null");
    }
    int[] elements = array.getElements();
    IntStream elementStream = IntStream.of(elements);
    OptionalDouble optAvg = elementStream.average();
    if (optAvg.isPresent()) {
      double avgValue = optAvg.getAsDouble();
      logger.info("Average: {}", avgValue);
      return Optional.of(avgValue);
    } else {
      logger.warn("findAverage called on empty array");
      return Optional.empty();
    }
  }
}
