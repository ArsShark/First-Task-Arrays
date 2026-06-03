package by.grechanikovars.arraytask.validator;

/**
 * Interface for validating a single line of raw input data.
 */
public interface DataValidator {

    /**
     * @param line line to validate
     * @return true if the line is fully valid, false otherwise
     */
    boolean isLineValid(String line);
}
