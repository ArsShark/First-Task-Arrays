package by.grechanikovars.arraytask.parser.impl;

import by.grechanikovars.arraytask.parser.LineParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses integer values from a text line.
 * Even if the line contains invalid tokens, all recognisable integers are extracted and returned.
 */
public class IntLineParserImpl implements LineParser {

    private static final Logger logger = LogManager.getLogger(IntLineParserImpl.class);
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");

    @Override
    public int[] parseLine(String line) {
        Matcher matcher = NUMBER_PATTERN.matcher(line);
        List<Integer> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }
        if (numbers.isEmpty()) {
            logger.warn("No integers found in line: [{}]", line);
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
