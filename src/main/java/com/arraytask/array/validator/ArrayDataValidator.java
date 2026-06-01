package com.arraytask.array.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Validates whether a line from the data file contains only integers
 * separated by allowed characters (spaces, commas, semicolons, hyphens).
 */
public class ArrayDataValidator implements DataValidator {

    private static final Logger logger = LogManager.getLogger(ArrayDataValidator.class);
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
