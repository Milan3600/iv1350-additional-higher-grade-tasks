package se.kth.iv1350.bikerepair.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiagnosticReportTest {
    private DiagnosticReport diagnosticReport;
    
    public DiagnosticReportTest() {
    }
    
    
    @BeforeEach
    public void setUp() 
    {
        diagnosticReport = new DiagnosticReport();
    }
    
    @AfterEach
    public void tearDown() 
    {
        diagnosticReport = null;
    }

 

    @Test
    public void testToString() 
    {
        String result = diagnosticReport.toString();
        
        assertTrue(result.contains(diagnosticReport.getDateOfCreation().toString()), 
                "Date of creation is missing from the string");
        assertTrue(result.contains(diagnosticReport.getDiagnosticTask(0).toString()),
                "Diagnostic task is missing from the string");
    }
    
}
