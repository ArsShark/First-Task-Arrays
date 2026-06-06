package by.grechanikovars.arraytask.specification.impl;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.specification.ArraySpecification;

public class FindByIdSpecificationImpl implements ArraySpecification {

  private final long id;

  public FindByIdSpecificationImpl(long id) {
    this.id = id;
  }

  @Override
  public boolean specify(IntArray array) {
    long arrayId = array.getId();
    return arrayId == id;
  }
}
