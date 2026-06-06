package by.grechanikovars.arraytask.factory.impl;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.factory.ArrayCreator;

public class IntArrayCreatorImpl implements ArrayCreator {

  @Override
  public IntArray create(int[] elements) {
    return new IntArray(elements);
  }
}
