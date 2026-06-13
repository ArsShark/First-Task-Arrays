package by.grechanikovars.arraytask.reader;

import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.reader.impl.FileDataReaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FileDataReaderImplTest {
  private static final String VALID_FILE_PATH   = "src/test/resources/test-arrays.txt";
  private static final String MISSING_FILE_PATH = "data/nonexistent.txt";

  private static final int EXPECTED_LINE_COUNT  = 3;
  private static final String EXPECTED_FIRST_LINE = "1; 2; 3";

  private DataReader reader;

  static Stream<Arguments> provideInvalidPaths() {
    return Stream.of(
            Arguments.of("data/nonexistent.txt"),
            Arguments.of("completely/wrong/path.txt"),
            Arguments.of("")
    );
  }

  static Stream<Arguments> provideValidPathsAndExpectedSizes() {
    return Stream.of(
            Arguments.of(VALID_FILE_PATH, EXPECTED_LINE_COUNT)
    );
  }

  @ParameterizedTest
  @MethodSource("provideValidPathsAndExpectedSizes")
  void testReadLinesParametrized(String path, int expectedSize) throws ArrayException {
    List<String> actual = reader.readLinesFromFile(path);

    assertEquals(expectedSize, actual.size());
  }

  @ParameterizedTest
  @MethodSource("provideInvalidPaths")
  void testReadLinesFromInvalidPathThrowsException(String path) {

    assertThrows(ArrayException.class, () -> reader.readLinesFromFile(path));
  }
  @BeforeEach
  void setUp() {
    reader = new FileDataReaderImpl();
  }

  @Test
  void testReadLinesFromFileReturnsCorrectLineCount() throws ArrayException {
    List<String> actual = reader.readLinesFromFile(VALID_FILE_PATH);

    assertEquals(EXPECTED_LINE_COUNT, actual.size());
  }

  @Test
  void testReadLinesFromFileReturnsCorrectFirstLine() throws ArrayException {
    List<String> actual = reader.readLinesFromFile(VALID_FILE_PATH);

    assertAll(
            () -> assertFalse(actual.isEmpty()),
            () -> assertEquals(EXPECTED_FIRST_LINE, actual.get(0))
    );
  }

  @Test
  void testReadLinesFromFileReturnsNonEmptyList() throws ArrayException {
    List<String> actual = reader.readLinesFromFile(VALID_FILE_PATH);

    assertFalse(actual.isEmpty());
  }

  @Test
  void testReadLinesFromMissingFileThrowsArrayException() {

    assertThrows(ArrayException.class, () -> reader.readLinesFromFile(MISSING_FILE_PATH));
  }
}