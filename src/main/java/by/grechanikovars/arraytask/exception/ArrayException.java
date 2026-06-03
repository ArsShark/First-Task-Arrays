package by.grechanikovars.arraytask.exception;

public class ArrayException extends Exception {

    /**
     * @param message description of the error
     */
    public ArrayException(String message) {
        super(message);
    }

    /**
     * @param message description of the error
     * @param cause   the underlying cause
     */
    public ArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
