package se.kth.iv1350.bikerepair.integration;


/**
 * Responsible for retrieving and providing access to the registries 
 * used in the application.
 *
 */
public class RegistryCreator 
{
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    
    /**
     * Creates an instance. Retrieves the registries used in the application.
     */
    public RegistryCreator()
    {
        this.customerRegistry = CustomerRegistry.getInstance();
        this.repairOrderRegistry = RepairOrderRegistry.getInstance();
    }
    
    /**
     * Get the value of customerRegistry.
     * 
     * @return The value of CustomerRegistry.
     */
    public CustomerRegistry getCustomerRegistry()
    {
        return customerRegistry;
    }
    
    /**
     * Get the value of repairOrderRegistry.
     * @return The value of repairOrderRegistry.
     */
    public RepairOrderRegistry getRepairOrderRegistry()
    {
        return repairOrderRegistry;
    }
    
    
    /**
     * Resets the registries to their initial test state.
     * This method is intended only for unit testing.
     */
    public void resetForTest()
    {
        CustomerRegistry.getInstance().clearForTest();
        RepairOrderRegistry.getInstance().clearForTest();
    }
}