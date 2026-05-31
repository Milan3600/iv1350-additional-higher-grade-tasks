package se.kth.iv1350.bikerepair.controller;

import se.kth.iv1350.bikerepair.model.RepairOrderDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.bikerepair.integration.BikeDTO;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.integration.CustomerRegistryException;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.RepairOrderCreateDTO;


public class ControllerTest {
    private Controller contr;
    private RegistryCreator registryCreator;
    private Printer printer;
    private RepairOrderCreateDTO repairOrderDTO;
    
    
    public ControllerTest() {
    }
    
    @BeforeEach
    public void setUp() 
    {
        printer = new Printer();
        registryCreator = new RegistryCreator();
        registryCreator.resetForTest();
        contr = new Controller(registryCreator, printer);
        repairOrderDTO = new RepairOrderCreateDTO("Broken steering wheel", "076132418", "XYZ092");
    }
    
    @AfterEach
    public void tearDown() 
    {
        printer = null;
        registryCreator = null;
        contr = null;
        repairOrderDTO = null;
    }
    
    @Test
    public void testFindCustomerExisting()
    {   
            String validPhoneNumber = "072123456";
            CustomerDTO searchedCustomer = 
            new CustomerDTO("John", "john123@gmail.com", "072123456", 
            new BikeDTO("xTreme", "W40", "ABC123"));
            CustomerDTO expResult = searchedCustomer;
            BikeDTO expBike = expResult.getBikeDetails();
            
            try
        {
            CustomerDTO result = contr.findCustomer(validPhoneNumber);
            BikeDTO actualBike = result.getBikeDetails();
        
            assertEquals(expResult.getName(), result.getName(), "The name does not match");
            assertEquals(expResult.getEmail(), result.getEmail(), "The email does not match");
            assertEquals(expResult.getPhoneNumber(), result.getPhoneNumber(), "The phone number"
                + "does not match");
            assertEquals(expBike.getSerialNumber(), 
                     actualBike.getSerialNumber(), "Bike serial number mismatch");
        }
        catch(Exception ex)
        {
            fail("Customer should exist, but exception was thrown");
        }
    }
    
    @Test
    public void testFindCustomerNonexisting()
    {
        String madeUpPhoneNumber = "0123456789";
        
        try
        {
            contr.findCustomer(madeUpPhoneNumber);
            fail("Expected CustomerNotFoundException to be thrown");
        }
        catch(CustomerNotFoundException ex)
        {
            assertEquals(madeUpPhoneNumber, ex.getPhoneNumberNotFound(),
                    "Wrong phone number stored in exception");
        }
        catch(OperationFailedException ex)
        {
            fail("Wrong exception type");
        }
    }
    
    @Test
    public void testFindCustomerDatabaseFailure()
    {
        String databaseFailureTrigger = "404";
        
        try
        {
            contr.findCustomer(databaseFailureTrigger);
            fail("Expected OperationFailedException");
        }
        catch(OperationFailedException ex)
        {
            assertTrue(ex.getCause() != null, "OperationFailedException should wrap"
                    + "underlying cause");
            assertTrue(ex.getCause() instanceof CustomerRegistryException, 
                    "The root cause has incorrect type");
            assertTrue(ex.getMessage() != null, "Exception message should exist");
        }
        catch(CustomerNotFoundException ex)
        {
            fail("Wrong exception type");
        }
    }

    @Test
    public void testCreateRepairOrder()
    {        
        int before = contr.findAllRepairOrders().size();
        contr.createRepairOrder(repairOrderDTO);
        int after = contr.findAllRepairOrders().size();
        
        assertEquals(before + 1, after, "The repair order was not added to the registry");
    }
    
    @Test
    public void testFindAllRepairOrders()
    {
        int before = contr.findAllRepairOrders().size();
        contr.createRepairOrder(repairOrderDTO);
        int after = contr.findAllRepairOrders().size();
        
        assertEquals(before + 1, after, "findAllRepairOrders did not return the"
                + "expected amount of repair orders");
    }
    
    @Test
    public void testFindAllRepairOrdersEmptyRegistry()
    {
        int expResult = 0;
        int result = contr.findAllRepairOrders().size();
        assertEquals(expResult, result, "An empty registry returned repair order(s)");
    }
    
    @Test
    public void testFindRepairOrderByPhoneNumberExisting()
    {
        contr.createRepairOrder(repairOrderDTO);
        String validPhoneNumber = repairOrderDTO.getCustomerPhoneNumber();
        String validBikeSerialNo = repairOrderDTO.getBikeSerialNo();

        RepairOrderDTO result = contr.findRepairOrderByPhoneNumber(validPhoneNumber);
        assertTrue(result.toString().contains(validPhoneNumber),
                     "Wrong repair order was returned");
        assertTrue(result.toString().contains(validBikeSerialNo),
                     "Bike serial number mismatch");
    }
    
    @Test
    public void testFindRepairOrderByPhoneNumberNonexisting()
    {
        String madeUpPhoneNumber = "0123456789";
        RepairOrderDTO expResult = null;
        RepairOrderDTO result = contr.findRepairOrderByPhoneNumber(madeUpPhoneNumber);
        assertEquals(expResult, result, "Made up phone number returned a repair order");
    }
    
    @Test
    public void testFindRepairOrderByPhoneNumberEmptyString()
    {
        String emptyString = "";
        RepairOrderDTO expResult = null;
        RepairOrderDTO result = contr.findRepairOrderByPhoneNumber(emptyString);
        assertEquals(expResult, result, "An empty string returned a repair order");
    }
    
    @Test
    public void testFindRepairOrderByPhoneNumberEmptyRegistry()
    {
        String phoneNumber = "0498323822";
        RepairOrderDTO expResult = null;
        RepairOrderDTO result = contr.findRepairOrderByPhoneNumber(phoneNumber);
        assertEquals(expResult, result, "Empty registry returned a repair order");
    }
    
    @Test
    public void testAddDiagnosticResult()
    {
        String phoneNumber = repairOrderDTO.getCustomerPhoneNumber();
        contr.createRepairOrder(repairOrderDTO);
        
        RepairOrderDTO repOrder = contr.findRepairOrderByPhoneNumber
        (phoneNumber);
        
        int repairOrderId = repOrder.getRepairOrderId();
        contr.addDiagnosticResult(repairOrderId, 0, "TEST");
        
        RepairOrderDTO updatedOrder = contr.findRepairOrderByPhoneNumber(phoneNumber);
        assertTrue(updatedOrder.toString().contains("TEST"), "Diagnostic task's result"
                + "was not updated");
    }
    
    @Test
    public void testAddRepairTask()
    {
        String phoneNumber = repairOrderDTO.getCustomerPhoneNumber();
        contr.createRepairOrder(repairOrderDTO);
        RepairOrderDTO initialRepairOrder = contr.findRepairOrderByPhoneNumber(phoneNumber);
        
        int repairOrderId = initialRepairOrder.getRepairOrderId();
        contr.addRepairTask(repairOrderId, "Test", "Just a test", new Amount(10, "EUR"));
        
        RepairOrderDTO updatedRepairOrder = contr.findRepairOrderByPhoneNumber(phoneNumber);
        
        String afterOrder = updatedRepairOrder.toString();
        assertTrue(afterOrder.contains("Test"), "Repair task was not added");
        assertTrue(afterOrder.contains("Just a test"), "Repair task description missing");
    }
    
    @Test
    public void testAcceptRepairOrder()
    {
        String phoneNumber = repairOrderDTO.getCustomerPhoneNumber();
        contr.createRepairOrder(repairOrderDTO);
        
        RepairOrderDTO repairOrder = contr.findRepairOrderByPhoneNumber(phoneNumber);
        int repairOrderId = repairOrder.getRepairOrderId();
        contr.acceptRepairOrder(repairOrderId);
        
        RepairOrderDTO updatedOrder = contr.findRepairOrderByPhoneNumber(phoneNumber);
        assertTrue(updatedOrder.toString().contains("ACCEPTED"), "The state was not changed"
                + "to ACCEPTED");
    }
}