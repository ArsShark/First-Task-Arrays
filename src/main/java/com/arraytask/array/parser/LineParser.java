package com.arraytask.array.parser;

/**
 * Interface for parsing a single line of text into an int array.
 */
public interface LineParser {

    /**
     * @param line text line to parse
     * @return array of extracted integers (may be empty)
     */
    int[] parseLine(String line);
}
