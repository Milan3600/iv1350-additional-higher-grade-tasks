package se.kth.iv1350.bikerepair.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.RepairOrder;
import se.kth.iv1350.bikerepair.model.RepairOrderCreateDTO;
import se.kth.iv1350.bikerepair.model.Receipt;

public class PrinterTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintReceipt() {
        BikeDTO bike = new BikeDTO("TestBrand", "ModelX", "SN123");

        CustomerDTO customer = new CustomerDTO(
                "Alice",
                "alice@mail.com",
                "0701234567",
                bike);

        RepairOrderCreateDTO createDTO =
                new RepairOrderCreateDTO("Broken chain", "0701234567", "SN123");

        RepairOrder repairOrder =
                new RepairOrder(createDTO, customer);

        repairOrder.setId(1);

        repairOrder.addDiagnosticResult(0, "PASSED");
        repairOrder.addRepairTask(
                new se.kth.iv1350.bikerepair.model.RepairTask(
                        "Fix chain",
                        "Replace broken chain",
                        new Amount(100, "EUR")));

        Receipt receipt = new Receipt(repairOrder);

        Printer printer = new Printer();
        printer.printReceipt(receipt);

        String output = outContent.toString();

        assertTrue(output.contains("Repair order Id"), "Missing header");
        assertTrue(output.contains("Alice"), "Missing customer name");
        assertTrue(output.contains("0701234567"), "Missing phone number");
        assertTrue(output.contains("Fix chain"), "Missing repair task");
        assertTrue(output.contains("EUR"), "Missing currency");
    }
}