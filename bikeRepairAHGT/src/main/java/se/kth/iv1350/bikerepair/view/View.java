package se.kth.iv1350.bikerepair.view;

import java.io.IOException;
import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.controller.CustomerNotFoundException;
import se.kth.iv1350.bikerepair.controller.OperationFailedException;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.RepairOrderCreateDTO;
import se.kth.iv1350.bikerepair.util.LogHandler;

/**
 * This class represents the entire View.
 *
 */
public class View 
{
    private Controller contr;
    private ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();
    private LogHandler logger;
    
    
    /**
     * Creates a new instance.
     * @param contr Controller that is used for all system operations.
     * @param logger Logger that is used to log bugs and unchecked exceptions.
     * @throws IOException if the <code>RepairOrderLogger</code> cannot be created.
     */
    public View(Controller contr, LogHandler logger) throws IOException
    {
        this.contr = contr;
        this.logger = logger;
        contr.addRepairOrderObserver(new RepairOrderView());
        contr.addRepairOrderObserver(new RepairOrderLogger());
    }
    
    
    /**
     * Represents different user inputs to the user interface. It triggers all
     * system operations to execute in sequential order.
     */
    public void exampleAllSystemOperations()
    {
        
    try
    {
        
        String customerPhoneNumber = "072123456";
        CustomerDTO foundCustomer = contr.findCustomer(customerPhoneNumber);
        System.out.println("Result of searching for customer " + foundCustomer);
        
        try
        {
            String nonexistingPhoneNumber = "0123456789";
            System.out.println("Trying to find a customer with a made up phone number");
            contr.findCustomer(nonexistingPhoneNumber);
            errorMessageHandler.showErrorMessage("Fake phone number returned customer info");
        }
        catch(CustomerNotFoundException exception)
        {
            errorMessageHandler.showErrorMessage("No customer found for phone number: "
            + exception.getPhoneNumberNotFound());
        }
        catch(OperationFailedException exception)
        {
            writeToLogAndUser("Incorrect exception was thrown", exception);
        }
        
        try
        {
            String usedToFakeDatabaseUnavailable = "404";
            System.out.println("Testing that the database cannot be called");
            contr.findCustomer(usedToFakeDatabaseUnavailable);
            errorMessageHandler.showErrorMessage("Managed to retrieve customer information, "
                    + "should not be the case");
        }
        catch(OperationFailedException exception)
        {
            writeToLogAndUser("Unable to retrieve customer information", exception);
        }
        catch(CustomerNotFoundException exception)
        {
            writeToLogAndUser("Incorrect exception was thrown", exception);
        }
        
        
        RepairOrderCreateDTO repairOrderDTO = new RepairOrderCreateDTO("Faulty gear shifter",
                "072123456", "ABC123");
        contr.createRepairOrder(repairOrderDTO);
        System.out.println("The repair order has been created and saved to the database");
        
        contr.addDiagnosticResult(0, 0, "PASSED");
        contr.addDiagnosticResult(0, 1, "PASSED");
        System.out.println("The diagnostic result was added and "
                + "the repair order has been updated");
        
        contr.addRepairTask(0, "Fix gear shifter", 
        "Replace worn components and adjust gear indexing", new Amount(28, "EUR"));
        System.out.println("The repair task was added to the repair order");
        
        System.out.println("The repair order has been accepted.");
        contr.acceptRepairOrder(0);
    }
    
        catch(Exception exception)
        {
            writeToLogAndUser("The program is unavailable at the moment", exception);
        }
        
    }
    
    private void writeToLogAndUser(String messageToUser, Exception exc)
    {
        errorMessageHandler.showErrorMessage(messageToUser);
        logger.logException(exc);
    }
    
}