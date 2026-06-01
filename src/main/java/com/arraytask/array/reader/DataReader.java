package com.arraytask.array.reader;

import com.arraytask.array.exception.ArrayException;

import java.util.List;

/**
 * Interface for reading lines of text from an external source.
 */
public interface DataReader {

    /**
     * @param filePath relative path to the file
     * @return list of lines (may include blank lines)
     * @throws ArrayException if the file cannot be read
     */
    List<String> readLinesFromFile(String filePath) throws ArrayException;
}
