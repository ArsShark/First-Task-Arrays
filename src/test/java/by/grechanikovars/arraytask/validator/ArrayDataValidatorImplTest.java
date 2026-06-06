package by.grechanikovars.arraytask.validator;

import by.grechanikovars.arraytask.validator.impl.ArrayDataValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ArrayDataValidatorImplTest {

  private DataValidator validator;

  @BeforeEach
  void setUp() {
    validator = new ArrayDataValidatorImpl();
  }

  @Test
  void testSemicolonSeparatedLineIsValid() {
    // given
    String line = "1; 2; 3";
    // when
    boolean result = validator.isLineValid(line);
    // then
    assertTrue(result);
  }

  @Test
  void testCommaSeparatedLineIsValid() {
    String line = "1, 2, 3";

    boolean result = validator.isLineValid(line);

    assertTrue(result);
  }

  @Test
  void testSpaceSeparatedLineIsValid() {

    String line = "3 4 7";

    boolean result = validator.isLineValid(line);

    assertTrue(result);
  }

  @Test
  void testHyphenSeparatedLineIsValid() {

    String line = "11 - 2 - 42";

    boolean result = validator.isLineValid(line);

    assertTrue(result);
  }

  @Test
  void testLineWithLettersIsInvalid() {

    String line = "1y1 21 32";

    boolean result = validator.isLineValid(line);

    assertFalse(result);
  }

  @Test
  void testLineWithDoubleDotIsInvalid() {

    String line = "6..5 77";

    boolean result = validator.isLineValid(line);

    assertFalse(result);
  }

  @Test
  void testNullLineIsInvalid() {

    String line = null;

    boolean result = validator.isLineValid(line);

    assertFalse(result);
  }

  @Test
  void testEmptyLineIsInvalid() {

    String line = "";

    boolean result = validator.isLineValid(line);

    assertFalse(result);
  }

  @Test
  void testBlankLineIsInvalid() {

    String line = "   ";

    boolean result = validator.isLineValid(line);

    assertFalse(result);
  }
}
