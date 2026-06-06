package by.grechanikovars.arraytask.comparator;

import by.grechanikovars.arraytask.entity.IntArray;

import java.util.Comparator;

public class ArrayFirstElementComparator implements Comparator<IntArray> {

  @Override
  public int compare(IntArray first, IntArray second) {
    int[] firstElements = first.getElements();
    int[] secondElements = second.getElements();
    if (firstElements.length == 0 && secondElements.length == 0) {
      return 0;
    }
    if (firstElements.length == 0) {
      return -1;
    }
    if (secondElements.length == 0) {
      return 1;
    }
    return Integer.compare(firstElements[0], secondElements[0]);
  }
}
