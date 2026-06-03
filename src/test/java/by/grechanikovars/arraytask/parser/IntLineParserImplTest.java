package by.grechanikovars.arraytask.parser;

import by.grechanikovars.arraytask.parser.impl.IntLineParserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for IntLineParserImpl.
 * Structure: given (preconditions), when (method call), then (assertion).
 */
class IntLineParserImplTest {

    private LineParser parser;

    @BeforeEach
    void setUp() {
        parser = new IntLineParserImpl();
    }

    @Test
    void testParseSemicolonSeparatedLine() {
        // given
        String line = "1; 2; 3";
        // when
        int[] result = parser.parseLine(line);
        // then
        assertArrayEquals(new int[]{1, 2, 3}, result);
    }

    @Test
    void testParseCommaSeparatedLine() {
        String line = "10, 20, 30";

        int[] result = parser.parseLine(line);

        assertArrayEquals(new int[]{10, 20, 30}, result);
    }

    @Test
    void testParseSpaceSeparatedLine() {
        String line = "3 4 7";

        int[] result = parser.parseLine(line);

        assertArrayEquals(new int[]{3, 4, 7}, result);
    }

    @Test
    void testParseInvalidLineExtractsAvailableIntegers() {

        String line = "1y1 21 32";

        int[] result = parser.parseLine(line);

        assertArrayEquals(new int[]{1, 1, 21, 32}, result);
    }

    @Test
    void testParseLineWithDoubleDotExtractsIntegers() {

        String line = "6..5 77";

        int[] result = parser.parseLine(line);

        assertArrayEquals(new int[]{6, 5, 77}, result);
    }

    @Test
    void testParseLineWithNoIntegersReturnsEmptyArray() {

        String line = "abc xyz";

        int[] result = parser.parseLine(line);

        assertEquals(0, result.length);
    }

    @Test
    void testParseEmptyLineReturnsEmptyArray() {

        String line = "";

        int[] result = parser.parseLine(line);

        assertEquals(0, result.length);
    }

    @Test
    void testParseSingleNumber() {

        String line = "99";

        int[] result = parser.parseLine(line);

        assertArrayEquals(new int[]{99}, result);
    }
}
