package by.grechanikovars.arraytask.service;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;

public interface ArraySortService {

  void bubbleSort(IntArray array) throws ArrayException;

  void selectionSort(IntArray array) throws ArrayException;
}
