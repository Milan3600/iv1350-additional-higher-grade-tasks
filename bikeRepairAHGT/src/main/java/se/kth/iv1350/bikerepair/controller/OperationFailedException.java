package se.kth.iv1350.bikerepair.controller;

/**
 * The exception is thrown when an operation fails. 
 * The reason why it failed is not included in the message.
 */
public class OperationFailedException extends Exception
{
    /**
     * Creates a new instance. It includes the specified message
     * and the root cause.
     * @param message The exception message.
     * @param cause The exception that caused this exception.
     */
    public OperationFailedException(String message, Exception cause)
    {
        super(message, cause);
    }
}
