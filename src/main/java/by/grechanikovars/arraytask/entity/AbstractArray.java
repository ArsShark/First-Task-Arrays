package by.grechanikovars.arraytask.entity;

public abstract class AbstractArray {

  protected long id;

  public long getId() {
    return id;
  }

  public abstract int length();
}