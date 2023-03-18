package pl.polsl.michaltetych.error;

/**
 * Error class for own exceptions
 * 
 * @author Michał Tetych
 * @version 3.0
 */
public class ErrorClass extends Exception {
    /**
     * constructor taking error message string as parameter
     * @param errorMessage string with message
     */
    public ErrorClass(String errorMessage)
    {
        super(errorMessage);
    }
}

