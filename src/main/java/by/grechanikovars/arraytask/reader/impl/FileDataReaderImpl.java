package by.grechanikovars.arraytask.reader.impl;

import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.reader.DataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileDataReaderImpl implements DataReader {

  private static final Logger logger = LogManager.getLogger(FileDataReaderImpl.class);

  @Override
  public List<String> readLinesFromFile(String filePath) throws ArrayException {
    Path path = Paths.get(filePath);
    try {
      List<String> lines = Files.readAllLines(path);
      logger.info("Read {} line(s) from file: {}", lines.size(), filePath);
      return lines;
    } catch (IOException e) {
      throw new ArrayException(String.format("Failed to read file: %s", filePath), e);
    }
  }
}
