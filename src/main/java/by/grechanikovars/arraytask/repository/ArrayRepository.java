package by.grechanikovars.arraytask.repository;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface ArrayRepository {

  void add(IntArray array);

  boolean remove(long id);

  List<IntArray> findBy(Predicate<IntArray> predicate);

  List<IntArray> findByFunctional(Predicate<IntArray> predicate);

  void sort(Comparator<IntArray> comparator) throws ArrayException;

  List<IntArray> getAll();
}