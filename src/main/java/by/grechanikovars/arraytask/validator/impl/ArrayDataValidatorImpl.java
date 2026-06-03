package by.grechanikovars.arraytask.validator.impl;

import by.grechanikovars.arraytask.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Validates whether a line from the data file contains only integers
 * separated by allowed characters (spaces, commas, semicolons, hyphens).
 */
public class ArrayDataValidatorImpl implements DataValidator {

    private static final Logger logger = LogManager.getLogger(ArrayDataValidatorImpl.class);
    private static final Pattern VALID_LINE_PATTERN =
            Pattern.compile("^[\\d\\s,;\\-\u2013\u2014]+$");

    @Override
    public boolean isLineValid(String line) {
        if (line == null || line.isBlank()) {
            return false;
        }
        boolean valid = VALID_LINE_PATTERN.matcher(line.trim()).matches();
        if (!valid) {
            logger.warn("Line contains invalid tokens: [{}]", line);
        }
        return valid;
    }
}
