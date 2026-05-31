package se.kth.iv1350.bikerepair.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;
import se.kth.iv1350.bikerepair.util.LogHandler;

public class ViewTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private View view;

    @BeforeEach
    public void setUp() throws IOException 
    {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        RegistryCreator registryCreator = new RegistryCreator();
        registryCreator.resetForTest();
        Printer printer = new Printer();
        Controller controller = new Controller(registryCreator, printer);
        LogHandler logger = new LogHandler();

        view = new View(controller, logger);
    }

    @AfterEach
    public void tearDown() 
    {
        System.setOut(originalOut);
    }

    @Test
    public void testExampleAllSystemOperations() 
    {
        view.exampleAllSystemOperations();

        String output = outContent.toString();

        assertTrue(output.contains("repair order") ||
                   output.contains("Customer") ||
                   output.contains("diagnostic"),
                   "Missing main system flow output");

        assertTrue(output.length() > 0,
                "Nothing printed to System.out");
    }

    @Test
    public void testErrorAndFailureOutput() 
    {
        view.exampleAllSystemOperations();

        String output = outContent.toString();

        assertTrue(output.toLowerCase().contains("error") ||
                   output.toLowerCase().contains("failed") ||
                   output.contains("404"),
                   "Missing error handling output");
    }

    @Test
    public void testReceiptOutputViaView() 
    {
        view.exampleAllSystemOperations();

        String output = outContent.toString();

        assertTrue(output.contains("Receipt"), "Missing receipt header");
        assertTrue(output.contains("Repair order Id"), "Missing repair order ID");
        assertTrue(output.contains("Total cost"), "Missing total cost");
        assertTrue(output.contains("Bike information"), "Missing bike section");
        assertTrue(output.contains("Customer information"), "Missing customer section");
    }
}