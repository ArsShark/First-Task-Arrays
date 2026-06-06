package by.grechanikovars.arraytask.repository;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.specification.ArraySpecification;

import java.util.Comparator;
import java.util.List;

public interface ArrayRepository {

  void add(IntArray array);

  boolean remove(long id);

  List<IntArray> findAll(ArraySpecification specification);

  List<IntArray> findAllFunctional(ArraySpecification specification);

  void sort(Comparator<IntArray> comparator) throws ArrayException;

  List<IntArray> getAll();
}
