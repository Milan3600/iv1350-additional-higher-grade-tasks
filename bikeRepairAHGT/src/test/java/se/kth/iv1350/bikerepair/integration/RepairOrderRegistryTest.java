package se.kth.iv1350.bikerepair.integration;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.bikerepair.model.RepairOrder;
import se.kth.iv1350.bikerepair.model.RepairOrderCreateDTO;

public class RepairOrderRegistryTest {
    private RepairOrderRegistry repairOrderRegistry;
    private RepairOrder repairOrder;
    
    public RepairOrderRegistryTest() 
    {
    }
    
    @BeforeEach
    public void setUp() 
    {
        repairOrderRegistry = RepairOrderRegistry.getInstance();
        repairOrderRegistry.clearForTest();
        repairOrder = new RepairOrder(
        new RepairOrderCreateDTO("The bell is broken", "072123456", "AX21"),
        new CustomerDTO("Ronald", "ron123@gmail.com", "072123456", 
        new BikeDTO("eBike", "2026", "AX21")));
    }
    
    @AfterEach
    public void tearDown() 
    {
        repairOrderRegistry = null;
        repairOrder = null;
    }

    @Test
    public void testCreateRepairOrder() 
    {
        repairOrderRegistry.createRepairOrder(repairOrder);
        int repairOrderId = repairOrder.getRepairOrderId();
        RepairOrder storedRepairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        assertNotNull(storedRepairOrder, "Repair order was not stored");
        assertEquals(repairOrderId, storedRepairOrder.getRepairOrderId(),
                "Stored repair order has wrong Id");
        
    }
    
    @Test
    public void testFindAllRepairOrders()
    {
        repairOrderRegistry.createRepairOrder(repairOrder);
        
        List<RepairOrder> repairOrders = repairOrderRegistry.findAllRepairOrders();
        for(RepairOrder returnedRepairOrder : repairOrders) 
        {
            if(returnedRepairOrder.hasSameIdAs(repairOrder)) 
             {
                 return;
             }
        }
        fail("The repair order was not found");
    }
    
    @Test
    public void testFindAllRepairOrdersEmpty()
    {
        List<RepairOrder> repairOrders = repairOrderRegistry.findAllRepairOrders();
        assertEquals(0, repairOrders.size(), "Expected no repair orders in an empty registry");
    }
    
    @Test
    public void testFindRepairOrderById()
    {
        repairOrderRegistry.createRepairOrder(repairOrder);
        int repairOrderId = repairOrder.getRepairOrderId();
        RepairOrder storedRepairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        assertNotNull(storedRepairOrder, "Repair order was not returned");
        assertEquals(repairOrderId, storedRepairOrder.getRepairOrderId(), "Returned"
                + "repair order has incorrect Id");
    }
    
    @Test
    public void testFindRepairOrderByIdNonexisting()
    {
        int madeUpId = -1;
        RepairOrder result = repairOrderRegistry.findRepairOrderById(madeUpId);
        RepairOrder expResult = null;
        assertEquals(expResult, result, "A repair order was found with a made up Id");
    }
    
    @Test
    public void testFindRepairOrderByIdEmptyRegistry()
    {
        int Id = 0;
        RepairOrder result = repairOrderRegistry.findRepairOrderById(Id);
        RepairOrder expResult = null;
        assertEquals(expResult, result, "Empty registry returned a repair order");
    }
    
    @Test
    public void testFindRepairOrderByPhoneNumber()
    {
        repairOrderRegistry.createRepairOrder(repairOrder);        
        String phoneNumber = repairOrder.getCustomerDetails().getPhoneNumber();
        
        int expResult = repairOrder.getRepairOrderId();
        int result = repairOrderRegistry.
                findRepairOrderByPhoneNumber(phoneNumber).getRepairOrderId();
        assertEquals(expResult, result, "A repair order was not correctly retrieved by phone number");
    }
    
    @Test
    public void testFindRepairOrderByPhoneNumberNonexisting()
    {
        repairOrderRegistry.createRepairOrder(repairOrder);
        String madeUpPhoneNumber = "0123456";
        
        RepairOrder expResult = null;
        RepairOrder result = repairOrderRegistry.findRepairOrderByPhoneNumber(madeUpPhoneNumber);
        assertEquals(expResult, result, "A repair order was found with a fake phone number");
    }
    
    @Test
    public void testFindRepairOrderByPhoneNumberEmptyString()
    {
        repairOrderRegistry.createRepairOrder(repairOrder);
        String emptyString = "";
        
        RepairOrder expResult = null;
        RepairOrder result = repairOrderRegistry.findRepairOrderByPhoneNumber(emptyString);
        assertEquals(expResult, result, "An empty string returned a repair order");
    }
    
    @Test
    public void testFindRepairOrderByPhoneNumberEmptyRegistry()
    {
        String phoneNumber = "024681012";        
        RepairOrder expResult = null;
        RepairOrder result = 
                repairOrderRegistry.findRepairOrderByPhoneNumber(phoneNumber);
        
        assertEquals(expResult, result, "Empty registry returned a repair order");
    }
    
    @Test
    public void testUpdateRepairOrder()
    {
        repairOrderRegistry.createRepairOrder(repairOrder);
        
        repairOrder.addDiagnosticResult(0, "TEST");
        repairOrderRegistry.updateRepairOrder(repairOrder);
        
        RepairOrder stored = repairOrderRegistry.findRepairOrderById
        (repairOrder.getRepairOrderId());
        
        String expResult = "TEST";
        String result = stored.getDiagnosticReport().getDiagnosticTask(0).getResult();
        
        assertEquals(expResult, result, "The repair order was not updated");
    }
    
}
