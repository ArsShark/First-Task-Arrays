package com.arraytask.array.entity;

import java.util.Arrays;

/**
 * Entity class that wraps a primitive int array.
 * Defensive copies are used to preserve encapsulation.
 */
public class IntArray extends AbstractArray {

    private int[] elements;

    /**
     * Constructs an IntArray from the given int array.
     * A defensive copy is made to prevent external mutation.
     *
     * @param elements source array
     */
    public IntArray(int[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    /**
     * Returns a defensive copy of the internal array.
     *
     * @return copy of the elements
     */
    public int[] getElements() {
        return Arrays.copyOf(elements, elements.length);
    }

    /**
     * Replaces the internal array with a defensive copy of the given array.
     *
     * @param elements new elements
     */
    public void setElements(int[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
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
        return Arrays.equals(elements, intArray.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
}
