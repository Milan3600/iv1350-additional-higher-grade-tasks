package se.kth.iv1350.bikerepair.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CustomerRegistryTest {
    private CustomerRegistry customerRegistry;
    private String phoneNumberTiedToACustomer;
    private String phoneNumberNotTiedToACustomer;
    private String emptyString;
    
    @BeforeEach
    public void setUp() 
    {
        customerRegistry = CustomerRegistry.getInstance();
        phoneNumberTiedToACustomer = "072123456";
        phoneNumberNotTiedToACustomer = "0123456789";
        emptyString = "";
    }
    
    @AfterEach
    public void tearDown() 
    {
        customerRegistry = null;
        phoneNumberTiedToACustomer = null;
        phoneNumberNotTiedToACustomer = null;
        emptyString = null;
    }
    
    @Test
    public void testFindCustomerMatch() 
    {
        CustomerDTO foundCustomer = customerRegistry.findCustomer(phoneNumberTiedToACustomer);
        String foundPhoneNumber = foundCustomer.getPhoneNumber();
        assertEquals(phoneNumberTiedToACustomer, foundPhoneNumber, "Customer was not found");
    }
    
    @Test
    public void testFindCustomerNoMatch()
    {
        CustomerDTO foundCustomer = customerRegistry.findCustomer(phoneNumberNotTiedToACustomer);
        assertEquals(null, foundCustomer, "Customer was found but it should not");
    }
    
    @Test
    public void testFindCustomerEmptyString()
    {
        CustomerDTO foundCustomer = customerRegistry.findCustomer(emptyString);
        assertEquals(null, foundCustomer, "Empty phone number should not return a customer");
    }
    
    @Test
    public void testFindCustomerNull()
    {
        CustomerDTO foundCustomer = customerRegistry.findCustomer(null);
        assertEquals(null, foundCustomer, "Customer was found");
    }
    
    @Test
    public void testFindCustomerDatabaseFailure()
    {
        String databaseFailureTrigger = "404";
        
        try
        {
            customerRegistry.findCustomer(databaseFailureTrigger);
            fail("Expected CustomerRegistryException to be thrown, but it did not");
        }
        catch(CustomerRegistryException exception)
        {
            assertTrue(exception.getMessage() != null, "Exception message should exist");
            assertTrue(exception.getMessage().contains("unavailable"), "Wrong exception "
                    + "message");
        }
    }
    
}