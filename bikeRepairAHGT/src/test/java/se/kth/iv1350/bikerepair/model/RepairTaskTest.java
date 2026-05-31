package se.kth.iv1350.bikerepair.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepairTaskTest {
    
    public RepairTaskTest() {
    }
    
    @Test
    public void testToString()
    {
        RepairTask repairTask = 
        new RepairTask("Brake pads", "Replace all brake pads",
        new Amount(50, "EUR"));
        
        String expName = "Brake pads";
        String expDescription = "Replace all brake pads";
        String expCost = new Amount(50, "EUR").toString();
        String expState = RepairTaskState.INCOMPLETE.toString();
        
        String result = repairTask.toString();
        assertTrue(result.contains(expName), "Missing name of repair task");
        assertTrue(result.contains(expDescription), "Misisng description");
        assertTrue(result.contains(expCost), "Missing cost of repair task");
        assertTrue(result.contains(expState), "Missing state of repair task");
    }

}
