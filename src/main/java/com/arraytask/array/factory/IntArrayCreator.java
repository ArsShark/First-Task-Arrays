package com.arraytask.array.factory;

import com.arraytask.array.entity.IntArray;

public class IntArrayCreator implements ArrayCreator {

    @Override
    public IntArray create(int[] elements) {
        return new IntArray(elements);
    }
}
