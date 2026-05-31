package se.kth.iv1350.bikerepair.model;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.bikerepair.integration.BikeDTO;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;

public class RepairOrderTest {
    private RepairOrder repairOrder;
    private RepairTask repairTask;
    
    public RepairOrderTest() {
    }
    
    @BeforeEach
    public void setUp() 
    {
        repairOrder = new RepairOrder(
        new RepairOrderCreateDTO("Broken seat", "03871382", "TEST123"),
        new CustomerDTO("Bob", "bob123@gmail.com", "03871382",
        new BikeDTO("sBike", "s5" , "TEST123")));
        
        repairTask = new RepairTask("Test", "Just a test",
        new Amount(500, "EUR"));
    }
    
    @AfterEach
    public void tearDown() 
    {
        repairOrder = null;
        repairTask = null;
    }
    
    @Test
    public void testSetId()
    {
        repairOrder.setId(123);
        int expResult = 123;
        int result = repairOrder.getRepairOrderId();
        assertEquals(expResult, result, "The repair order is not set properly");
    }
    
    @Test
    public void testAccept()
    {
        repairOrder.accept();
        RepairOrderState expResult = RepairOrderState.ACCEPTED;
        RepairOrderState result = repairOrder.getState();
        assertEquals(expResult, result, "The state was not set to ACCEPTED");
    }
    
    @Test
    public void testHasIdMatch()
    {
        repairOrder.setId(4);
        
        boolean expResult = true;
        boolean result = repairOrder.hasId(4);
        assertEquals(expResult, result, "Repair order does not have the Id it was assigned");
    }
    
    @Test
    public void testHasIdDoesNotMatch()
    {
        repairOrder.setId(44);
        
        boolean expResult = false;
        boolean result = repairOrder.hasId(11);
        assertEquals(expResult, result, "Returns true even though repair order was assigned"
                + "a different Id");
    }
    
    @Test
    public void testHasPhoneNumberMatch()
    {
        String validPhoneNumber = "03871382";
        boolean expResult = true;
        boolean result = repairOrder.hasPhoneNumber(validPhoneNumber);
        assertEquals(expResult, result, "Returns false even though the phone number is valid");
    }
 
    @Test
    public void testHasPhoneNumberDoesNotMatch()
    {
        String madeUpPhoneNumber = "01020301";
        boolean expResult = false;
        boolean result = repairOrder.hasPhoneNumber(madeUpPhoneNumber);
        assertEquals(expResult, result, "Returns true even though the phone number is maed up");
    }
    
    @Test
    public void testHasSameIdAsMatch()
    {
        repairOrder.setId(12);
        
        RepairOrder updatedRepairOrder = new RepairOrder(
        new RepairOrderCreateDTO("Broken seat", "03871382", "This was changed"),
        new CustomerDTO("Bob", "bob123@gmail.com", "03871382",
        new BikeDTO("sBike", "s5" , "This was changed")));
        updatedRepairOrder.setId(12);
        
        boolean expResult = true;
        boolean result = repairOrder.hasSameIdAs(updatedRepairOrder);
        assertEquals(expResult, result, "Repair orders are expected to have the same Id");
    }
    
    @Test
    public void testHasSameIdAsDoesNotMatch()
    {
        repairOrder.setId(39);
        
        RepairOrder someoneElse = new RepairOrder(
        new RepairOrderCreateDTO("Front wheel went off", "09737372", "BMX32"),
        new CustomerDTO("Michael", "mike321@gmail.com", "09737372",
        new BikeDTO("Lbike", "L1" , "BMX32")));
        someoneElse.setId(40);
        
        boolean expResult = false;
        boolean result = repairOrder.hasSameIdAs(someoneElse);
        assertEquals(expResult, result, "Two different repair orders have the same Id");
    }
    
    @Test
    public void testAddRepairTask()
    {       
        repairOrder.addRepairTask(repairTask);
        int expResult = 1;
        int result = repairOrder.getRepairTasks().size();
        assertEquals(expResult, result, "The repair task was not added");
    }
    
    @Test
    public void testAddDiagnosticResult()
    {
        repairOrder.addDiagnosticResult(0, "TEST");
        String expResult = "TEST";
        String result = repairOrder.getDiagnosticReport()
                .getDiagnosticTask(0)
                .getResult();
        assertEquals(expResult, result, "The result was not added correctly");
    }
    
    @Test
    public void testToString()
    {
        repairOrder.setId(100);
        repairOrder.addRepairTask(repairTask);
        
        String expId = Integer.toString(repairOrder.getRepairOrderId());
        String expState = repairOrder.getState().toString();
        String expCustomer = repairOrder.getCustomerDetails().toString();
        String expProblem = repairOrder.getProblemDescr();
        String expCreated = repairOrder.getDateTimeOfCreation().toString();
        String expCompletion = repairOrder.getEstimatedCompletionDate().toString();
        String expDiagnosticReport = repairOrder.getDiagnosticReport().toString();
        List<RepairTask> expRepTasks = repairOrder.getRepairTasks();
            
        String result = repairOrder.toString();
        assertTrue(result.contains(expId), "Missing Id");
        assertTrue(result.contains(expState), "Missing state");
        assertTrue(result.contains(expCustomer), "Missing customer details");
        assertTrue(result.contains(expProblem), "Missing problem description");
        assertTrue(result.contains(expCreated), "Missing date of creation");
        assertTrue(result.contains(expCompletion), "Missing completion date");
        assertTrue(result.contains(expDiagnosticReport), "Missing diagnostic report");
        for(RepairTask repTask : expRepTasks)
            assertTrue(result.contains(repTask.toString()), "Missing repair task");
    }
}
