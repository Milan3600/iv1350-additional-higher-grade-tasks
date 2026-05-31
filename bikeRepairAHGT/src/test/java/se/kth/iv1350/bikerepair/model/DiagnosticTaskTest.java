package se.kth.iv1350.bikerepair.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DiagnosticTaskTest {
    private DiagnosticTask diagTask;
    
    public DiagnosticTaskTest() {
    }
    
    @BeforeEach
    public void setUp() 
    {
        diagTask = new DiagnosticTask("Test", "Used to see if everything works",
        new Amount(1000, "EUR"));
    }
    
    @AfterEach
    public void tearDown() 
    {
        diagTask = null;
    }

    @Test
    public void testSetResult() 
    {
        String resultToSet = "UPDATED";
        diagTask.setResult(resultToSet);
        
        String expResult = "UPDATED";
        String result = diagTask.getResult();
        assertEquals(expResult, result, "The result was not set");
    }
    
    @Test
    public void testToString()
    {
        String name = diagTask.getName();
        String description = diagTask.getDescription();
        String cost = diagTask.getCost().toString();
        String result = diagTask.getResult();
        
        String resultString = diagTask.toString();
        
        assertTrue(resultString.contains(name), "Missing name");
        assertTrue(resultString.contains(description), "Missing description");
        assertTrue(resultString.contains(cost), "Misisng cost");
        assertTrue(resultString.contains(result), "Missing result");
    }
    
}
