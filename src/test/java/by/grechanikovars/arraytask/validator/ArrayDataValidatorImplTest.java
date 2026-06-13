package by.grechanikovars.arraytask.validator;

import by.grechanikovars.arraytask.validator.impl.ArrayDataValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDataValidatorImplTest {

  private DataValidator validator;

  static Stream<Arguments> provideValidLines() {
    return Stream.of(
            Arguments.of("1; 2; 3"),
            Arguments.of("1, 2, 3"),
            Arguments.of("3 4 7"),
            Arguments.of("11 - 2 - 42"),
            Arguments.of("99"),
            Arguments.of("100 200 300")
    );
  }

  static Stream<Arguments> provideInvalidLines() {
    return Stream.of(
            Arguments.of("1y1 21 32"),
            Arguments.of("6..5 77"),
            Arguments.of("abc xyz"),
            Arguments.of(""),
            Arguments.of("   "),
            Arguments.of("!@#$%")
    );
  }

  @BeforeEach
  void setUp() {
    validator = new ArrayDataValidatorImpl();
  }

  @ParameterizedTest
  @MethodSource("provideValidLines")
  void testValidLineParametrized(String line) {
    // given — line provided by method source
    // when
    boolean actual = validator.isLineValid(line);
    // then
    assertTrue(actual);
  }

  @ParameterizedTest
  @MethodSource("provideInvalidLines")
  void testInvalidLineParametrized(String line) {
    boolean actual = validator.isLineValid(line);

    assertFalse(actual);
  }

  @Test
  void testNullLineIsInvalid() {
    boolean actual = validator.isLineValid(null);

    assertFalse(actual);
  }

  @Test
  void testEmptyLineIsInvalid() {
    String line = "";

    boolean actual = validator.isLineValid(line);

    assertFalse(actual);
  }
}