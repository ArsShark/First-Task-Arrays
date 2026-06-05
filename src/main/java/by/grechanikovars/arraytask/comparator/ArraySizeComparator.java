package by.grechanikovars.arraytask.comparator;

import by.grechanikovars.arraytask.entity.IntArray;

import java.util.Comparator;

public class ArraySizeComparator implements Comparator<IntArray> {

    @Override
    public int compare(IntArray first, IntArray second) {
        int firstSize = first.size();
        int secondSize = second.size();
        return Integer.compare(firstSize, secondSize);
    }
}
