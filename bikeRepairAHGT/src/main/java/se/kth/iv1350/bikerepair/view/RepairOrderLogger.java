package se.kth.iv1350.bikerepair.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Logs repair order updates to a file.
 */
class RepairOrderLogger extends RepairOrderObserverTemplate
{
    private PrintWriter logFile;

    /**
     * Creates an instance that logs to a file.
     * @throws IOException if the log file cannot be opened.
     */
    RepairOrderLogger() throws IOException
    {
        logFile = new PrintWriter(new FileWriter("repairorder-log.txt", true), true);
    }

    /**
     * Writes the updated repair order to the log file.
     */
    @Override
    protected void doHandleRepairOrderUpdate()
    {
        logFile.println("Repair order updated:");
        logFile.println(getRepairOrderDTO().toString());
        logFile.println("----------------------");
    }

    /**
     * Handles errors occurring during logging.
     * @param exception The thrown exception.
     */
    @Override
    protected void handleErrors(Exception exception)
    {
        System.out.println("Could not write repair order update to log file.");
        exception.printStackTrace();
    }
}