package se.kth.iv1350.bikerepair.view;

/**
 * Shows the snapshot of a repair order right after it has been updated
 * by printing it to <code>System.out</code>.
 */
class RepairOrderView extends RepairOrderObserverTemplate
{
    /**
     * Prints the snapshot of a repair order to the console.
     */
    @Override
    protected void doHandleRepairOrderUpdate()
    {
        System.out.println("\n=== REPAIR ORDER UPDATED ===");
        System.out.println(getRepairOrderDTO());
        System.out.println("============================\n");
    }

    /**
     * Handles errors occurring during printing.
     * @param exception The thrown exception.
     */
    @Override
    protected void handleErrors(Exception exception)
    {
        System.out.println("Could not show repair order update.");
        exception.printStackTrace();
    }
}