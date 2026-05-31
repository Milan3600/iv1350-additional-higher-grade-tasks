package se.kth.iv1350.bikerepair.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.bikerepair.integration.BikeDTO;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;

public class ReceiptTest {

    @Test
    public void testCreateReceiptString() 
    {
        BikeDTO bike = new BikeDTO(
                "xTreme",
                "W40",
                "XYZ123");

        CustomerDTO customer = new CustomerDTO(
                "John",
                "john123@gmail.com",
                "072123456",
                bike);

        RepairOrderCreateDTO repairOrderDTO = new RepairOrderCreateDTO(
                "The electric motor gave up",
                "072123456",
                "XYZ123");

        RepairOrder repairOrder =
                new RepairOrder(repairOrderDTO, customer);

        repairOrder.setId(0);

        repairOrder.addDiagnosticResult(0, "PASSED");
        repairOrder.addDiagnosticResult(1, "PASSED");

        repairOrder.addRepairTask(
                new RepairTask(
                        "Replace electric motor",
                        "The electric motor showed no signs of life. "
                                + "A new one was ordered",
                        new Amount(350, "EUR")));

        repairOrder.accept();

        Receipt receipt = new Receipt(repairOrder);

        String expId =
                Integer.toString(repairOrder.getRepairOrderId());

        String expCreateDate =
                repairOrder.getDateTimeOfCreation().toString();

        String expProblemDescr =
                repairOrder.getProblemDescr();

        String expDiagReport =
                repairOrder.getDiagnosticReport().toString();

        List<RepairTask> expRepairTasks =
                repairOrder.getRepairTasks();

        String expStateRepairOrder =
                repairOrder.getState().toString();

        String expCompletionDate =
                repairOrder.getEstimatedCompletionDate().toString();

        String expTotalCost =
                repairOrder.getTotalCost().toString();

        String expBikeBrand =
                repairOrder.getCustomerDetails()
                        .getBikeDetails().getBrand();

        String expBikeModel =
                repairOrder.getCustomerDetails()
                        .getBikeDetails().getModel();

        String expBikeSerialNo =
                repairOrder.getCustomerDetails()
                        .getBikeDetails().getSerialNumber();

        String expCustomerName =
                repairOrder.getCustomerDetails().getName();

        String expCustomerEmail =
                repairOrder.getCustomerDetails().getEmail();

        String expCustomerPhoneNumber =
                repairOrder.getCustomerDetails().getPhoneNumber();

        String result = receipt.createReceiptString();

        assertTrue(result.contains("Repair order Id: " + expId),
                "Missing repair order Id");

        assertTrue(result.contains(expCreateDate),
                "Missing creation date");

        assertTrue(result.contains(expProblemDescr),
                "Missing problem description");

        assertTrue(result.contains(expDiagReport),
                "Missing diagnostic report");

        for(RepairTask repTask : expRepairTasks)
        {
            assertTrue(result.contains(repTask.toString()),
                    "Missing repair task");
        }

        assertTrue(result.contains(expStateRepairOrder),
                "Missing state of repair order");

        assertTrue(result.contains(expCompletionDate),
                "Missing completion date");

        assertTrue(result.contains("Total cost: " + expTotalCost),
                "Missing total cost");

        assertTrue(result.contains(expBikeBrand),
                "Missing bike brand");

        assertTrue(result.contains(expBikeModel),
                "Missing bike model");

        assertTrue(result.contains(expBikeSerialNo),
                "Missing bike serial number");

        assertTrue(result.contains("Name: " + expCustomerName),
                "Missing customer name");

        assertTrue(result.contains(expCustomerEmail),
                "Missing customer email");

        assertTrue(result.contains(expCustomerPhoneNumber),
                "Missing customer phone number");
    }
}