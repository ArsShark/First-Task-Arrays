package by.grechanikovars.arraytask.repository.impl;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.repository.ArrayRepository;
import by.grechanikovars.arraytask.warehouse.ArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayRepositoryImpl implements ArrayRepository {

  private static final Logger logger = LogManager.getLogger(ArrayRepositoryImpl.class);

  private static ArrayRepositoryImpl instance;

  private final List<IntArray> arrays;

  private ArrayRepositoryImpl() {
    arrays = new ArrayList<>();
  }

  public static ArrayRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new ArrayRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void add(IntArray array) {
    arrays.add(array);
    long arrayId = array.getId();
    logger.info("Array added to repository, id={}", arrayId);
  }

  @Override
  public boolean remove(long id) {
    IntArray target = null;
    for (IntArray array : arrays) {
      long arrayId = array.getId();
      if (arrayId == id) {
        target = array;
        break;
      }
    }
    if (target != null) {
      target.setObserver(null);
      arrays.remove(target);
      ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
      warehouse.removeStatistics(id);
      logger.info("Array removed from repository, id={}", id);
      return true;
    } else {
      logger.warn("Array with id={} not found in repository", id);
      return false;
    }
  }

  @Override
  public List<IntArray> findBy(Predicate<IntArray> predicate) {
    List<IntArray> result = new ArrayList<>();
    for (IntArray array : arrays) {
      if (predicate.test(array)) {
        result.add(array);
      }
    }
    return result;
  }

  @Override
  public List<IntArray> findByFunctional(Predicate<IntArray> predicate) {
    Stream<IntArray> arrayStream = arrays.stream().filter(predicate);
    return arrayStream.collect(Collectors.toList());
  }

  @Override
  public void sort(Comparator<IntArray> comparator) throws ArrayException {
    if (comparator == null) {
      throw new ArrayException("Comparator must not be null");
    }
    arrays.sort(comparator);
    logger.info("Repository sorted");
  }

  @Override
  public List<IntArray> getAll() {
    return new ArrayList<>(arrays);
  }
  public void clear() {
    arrays.clear();
  }
}