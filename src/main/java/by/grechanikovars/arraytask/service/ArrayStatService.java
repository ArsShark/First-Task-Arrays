package by.grechanikovars.arraytask.service;

import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;

import java.util.Optional;

public interface ArrayStatService {

    Optional<Integer> findMin(IntArray array) throws ArrayException;

    Optional<Integer> findMax(IntArray array) throws ArrayException;

    Optional<Long> findSum(IntArray array) throws ArrayException;

    Optional<Double> findAverage(IntArray array) throws ArrayException;
}
