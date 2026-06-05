package by.grechanikovars.arraytask.comparator;

import by.grechanikovars.arraytask.entity.IntArray;

import java.util.Comparator;

public class ArrayIdComparator implements Comparator<IntArray> {

    @Override
    public int compare(IntArray first, IntArray second) {
        long firstId = first.getId();
        long secondId = second.getId();
        return Long.compare(firstId, secondId);
    }
}
