package com.arraytask.array.reader;

import com.arraytask.array.exception.ArrayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileDataReader implements DataReader {

    private static final Logger logger = LogManager.getLogger(FileDataReader.class);

    @Override
    public List<String> readLinesFromFile(String filePath) throws ArrayException {
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            logger.info("Read {} line(s) from file: {}", lines.size(), filePath);
            return lines;
        } catch (IOException e) {
            throw new ArrayException("Failed to read file: " + filePath, e);
        }
    }
}
