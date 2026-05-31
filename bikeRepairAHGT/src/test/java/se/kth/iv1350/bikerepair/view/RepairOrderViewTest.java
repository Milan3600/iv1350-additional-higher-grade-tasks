package se.kth.iv1350.bikerepair.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.bikerepair.integration.BikeDTO;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.model.RepairOrder;
import se.kth.iv1350.bikerepair.model.RepairOrderCreateDTO;
import se.kth.iv1350.bikerepair.model.RepairTask;
import se.kth.iv1350.bikerepair.model.Amount;

public class RepairOrderViewTest {

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
    public void testStateHasChangedPrintsRepairOrderUpdate() {

        RepairOrderView view = new RepairOrderView();

        CustomerDTO customer = new CustomerDTO(
                "Bob",
                "bob@mail.com",
                "0701111111",
                new BikeDTO("BrandX", "ModelY", "SN1")
        );

        RepairOrderCreateDTO createDTO =
                new RepairOrderCreateDTO("Broken wheel", "0701111111", "SN1");

        RepairOrder order = new RepairOrder(createDTO, customer);
        order.setId(1);

        order.addRepairOrderObserver(view);

        order.addDiagnosticResult(0, "OK");

        order.addRepairTask(
                new RepairTask(
                        "Fix wheel",
                        "Replace spokes",
                        new Amount(200, "EUR"))
        );

        // This triggers observer
        order.accept();

        String output = outContent.toString();

        assertTrue(output.contains("REPAIR ORDER UPDATED"),
                "Missing update header");

        assertTrue(output.contains("Bob"),
                "Missing customer information");

        assertTrue(output.contains("Fix wheel"),
                "Missing repair task");

        assertTrue(output.contains("EUR"),
                "Missing currency info");
    }
}