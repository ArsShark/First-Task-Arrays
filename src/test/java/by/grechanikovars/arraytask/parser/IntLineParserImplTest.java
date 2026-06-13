package by.grechanikovars.arraytask.parser;

import by.grechanikovars.arraytask.parser.impl.IntLineParserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntLineParserImplTest {
  private static final String INVALID_LETTERS = "1y1 21 32";
  private static final String DOUBLE_DOT_LINE = "6..5 77";

  private static final int[] EXPECTED_LETTERS = {1, 1, 21, 32};
  private static final int[] EXPECTED_DOUBLE_DOT = {6, 5, 77};

  private LineParser parser;

  static Stream<Arguments> provideValidLinesAndExpected() {
    return Stream.of(
            Arguments.of("1; 2; 3", new int[]{1, 2, 3}),
            Arguments.of("10, 20, 30", new int[]{10, 20, 30}),
            Arguments.of("3 4 7", new int[]{3, 4, 7}),
            Arguments.of("99", new int[]{99}),
            Arguments.of("-5 -3 -1", new int[]{-5, -3, -1}),
            Arguments.of("0 0 0", new int[]{0, 0, 0})
    );
  }

  static Stream<Arguments> provideInvalidLinesAndExpectedSize() {
    return Stream.of(
            Arguments.of("abc xyz", 0),
            Arguments.of("", 0),
            Arguments.of("   ", 0),
            Arguments.of("!@#$%", 0)
    );
  }

  @BeforeEach
  void setUp() {
    parser = new IntLineParserImpl();
  }


  @ParameterizedTest
  @MethodSource("provideValidLinesAndExpected")
  void testParseValidLineParametrized(String line, int[] expected) {
    int[] actual = parser.parseLine(line);

    assertArrayEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("provideInvalidLinesAndExpectedSize")
  void testParseInvalidLineParametrized(String line, int expectedSize) {
    int[] actual = parser.parseLine(line);

    assertEquals(expectedSize, actual.length);
  }

  @Test
  void testParseInvalidLineExtractsAvailableIntegers() {
    int[] actual = parser.parseLine(INVALID_LETTERS);

    assertArrayEquals(EXPECTED_LETTERS, actual);
  }

  @Test
  void testParseLineWithDoubleDotExtractsIntegers() {
    int[] actual = parser.parseLine(DOUBLE_DOT_LINE);

    assertArrayEquals(EXPECTED_DOUBLE_DOT, actual);
  }
}