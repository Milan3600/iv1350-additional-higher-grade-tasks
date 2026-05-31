package se.kth.iv1350.bikerepair.view;

import se.kth.iv1350.bikerepair.model.RepairOrderDTO;
import se.kth.iv1350.bikerepair.model.RepairOrderObserver;

/**
 * Template class for observers reacting to repair order updates.
 * Contains the common exception handling structure.
 */
public abstract class RepairOrderObserverTemplate implements RepairOrderObserver
{
    private RepairOrderDTO repairOrderDTO;

    /**
     * Called when a repair order has been updated.
     * @param repairOrderDTO Snapshot of the updated repair order.
     */
    @Override
    public void stateHasChanged(RepairOrderDTO repairOrderDTO)
    {
        this.repairOrderDTO = repairOrderDTO;
        handleRepairOrderUpdate();
    }

    private void handleRepairOrderUpdate()
    {
        try
        {
            doHandleRepairOrderUpdate();
        }
        catch(Exception exception)
        {
            handleErrors(exception);
        }
    }

    /**
     * Performs the observer-specific update handling.
     * @throws Exception if update handling fails.
     */
    protected abstract void doHandleRepairOrderUpdate()
    throws Exception;

    /**
     * Handles exceptions thrown during update handling.
     * @param exception The thrown exception.
     */
    protected abstract void handleErrors(Exception exception);

    /**
     * Returns the updated repair order.
     * @return The updated repair order.
     */
    protected RepairOrderDTO getRepairOrderDTO()
    {
        return repairOrderDTO;
    }
}