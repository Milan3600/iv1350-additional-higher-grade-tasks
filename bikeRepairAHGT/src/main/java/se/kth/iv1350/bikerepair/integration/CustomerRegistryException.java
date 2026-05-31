package se.kth.iv1350.bikerepair.integration;

/**
 * The exception that is thrown when an operation fails in <code>CustomerRegistry</code>,
 * or if the connection could not be established.
 */
public class CustomerRegistryException extends RuntimeException
{
    
    /**
     * Creates an instance that describes the specific error in the message.
     * @param message The error message that describes the issue.
     */
    public CustomerRegistryException(String message)
    {
        super(message);
    }
    
}
