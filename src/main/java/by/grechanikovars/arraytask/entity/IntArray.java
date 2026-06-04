package by.grechanikovars.arraytask.entity;

import by.grechanikovars.arraytask.observer.ArrayObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Entity class that wraps a primitive int array.
 * Each instance has a unique auto-incremented id.
 * Observers are notified whenever elements are replaced via setElements().
 */
public class IntArray extends AbstractArray {

    private static long idCounter = 0L;

    private final long id;
    private int[] elements;
    private final List<ArrayObserver> observers;

    /**
     * Constructs an IntArray. Assigns a unique id automatically.
     *
     * @param elements source array (defensive copy is made)
     */
    public IntArray(int[] elements) {
        this.id = ++idCounter;
        this.elements = Arrays.copyOf(elements, elements.length);
        this.observers = new ArrayList<>();
    }

    @Override
    public long getId() {
        return id;
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
     * Replaces the internal array and notifies all registered observers.
     *
     * @param elements new elements (defensive copy is made)
     */
    public void setElements(int[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
        notifyObservers();
    }

    /**
     * Registers an observer that will be notified on element changes.
     *
     * @param observer observer to add
     */
    public void addObserver(ArrayObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes a previously registered observer.
     *
     * @param observer observer to remove
     */
    public void removeObserver(ArrayObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ArrayObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public String toString() {
        return "IntArray{id=" + id + ", elements=" + Arrays.toString(elements) + "}";
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