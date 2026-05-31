package se.kth.iv1350.bikerepair.controller;

/**
 * Thrown when no customer exists for a specified phone number.
 */
public class CustomerNotFoundException extends Exception
{
    private String phoneNumberNotFound;
    
    /**
     * Creates an instance with a message describing for which phone number customer
     * could not be found.
     * @param phoneNumberNotFound The phone number that is not associated with any customer.
     */
    CustomerNotFoundException(String phoneNumberNotFound)
    {
        super("No customer is associated with phone number: " + phoneNumberNotFound);
        this.phoneNumberNotFound = phoneNumberNotFound;
    }
    
    /**
     * @return The phone number that is not associated with any customer.
     */
    public String getPhoneNumberNotFound()
    {
        return phoneNumberNotFound;
    }
}