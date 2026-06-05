package by.grechanikovars.arraytask.reader;

import by.grechanikovars.arraytask.exception.ArrayException;

import java.util.List;

public interface DataReader {

    List<String> readLinesFromFile(String filePath) throws ArrayException;
}
