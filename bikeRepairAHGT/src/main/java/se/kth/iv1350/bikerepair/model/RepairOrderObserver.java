package se.kth.iv1350.bikerepair.model;

/**
 *
 * Used for receiving notifications about repair orders. Every class
 * that implements this interface wants such notifications and fulfills this contract.
 * Whenever a repair order is updated, the instance of a implementing class is notified 
 * via the {@link #stateHasChanged} method.
 */
public interface RepairOrderObserver 
{
    
    /**
     * Invoked when a repair order's state has changed, for example
     * when a repair task has been added to it.
     * @param repairOrderDTO The snapshot of a repair order 
     * with the updated state.
     */
    void stateHasChanged(RepairOrderDTO repairOrderDTO);
    
}