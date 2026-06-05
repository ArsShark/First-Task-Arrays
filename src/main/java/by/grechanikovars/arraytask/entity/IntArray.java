package by.grechanikovars.arraytask.entity;

import by.grechanikovars.arraytask.observer.ArrayObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IntArray extends AbstractArray {

    private static long idCounter = 0L;

    private final long id;
    private int[] elements;
    private final List<ArrayObserver> observers;


    public IntArray(int[] elements) {
        this.id = ++idCounter;
        this.elements = Arrays.copyOf(elements, elements.length);
        this.observers = new ArrayList<>();
    }

    @Override
    public long getId() {
        return id;
    }

    public int[] getElements() {
        return Arrays.copyOf(elements, elements.length);
    }


    public void setElements(int[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
        notifyObservers();
    }

    public void addObserver(ArrayObserver observer) {
        observers.add(observer);
    }

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
