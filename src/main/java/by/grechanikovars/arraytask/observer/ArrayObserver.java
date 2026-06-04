package by.grechanikovars.arraytask.observer;
import by.grechanikovars.arraytask.entity.IntArray;

/**
 * Observer interface for the Observer design pattern.
 * Implementations are notified whenever an IntArray's elements change.
 */
public interface ArrayObserver {

    /**
     * Called by IntArray when its elements have been replaced.
     *
     * @param array the array that was changed
     */
    void update(IntArray array);
}