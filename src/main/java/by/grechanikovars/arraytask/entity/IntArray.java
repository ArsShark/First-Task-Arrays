package by.grechanikovars.arraytask.entity;

import by.grechanikovars.arraytask.annotation.NotifyObserver;
import by.grechanikovars.arraytask.observer.ArrayObserver;
import by.grechanikovars.arraytask.util.IdGenerator;

import java.util.Arrays;

public class IntArray extends AbstractArray {

  private int[] elements;
  private ArrayObserver observer;

  public IntArray(int[] elements) {
    this.id = IdGenerator.generateId();
    this.elements = Arrays.copyOf(elements, elements.length);
  }

  public int[] getElements() {
    return Arrays.copyOf(elements, elements.length);
  }

  @NotifyObserver
  public void setElements(int[] elements) {
    this.elements = Arrays.copyOf(elements, elements.length);
    notifyObserver();
  }

  public void setObserver(ArrayObserver observer) {
    this.observer = observer;
  }

  private void notifyObserver() {
    if (observer != null) {
      observer.update(this);
    }
  }

  @Override
  public int length() {
    return elements.length;
  }

  @Override
  public String toString() {
    return String.format("IntArray{id=%d, elements=%s}", id, Arrays.toString(elements));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IntArray)) {
      return false;
    }
    IntArray intArray = (IntArray) o;
    return id == intArray.id;
  }

  @Override
  public int hashCode() {
    return Long.hashCode(id);
  }
}