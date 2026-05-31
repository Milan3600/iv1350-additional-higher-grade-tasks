package se.kth.iv1350.bikerepair.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.bikerepair.model.RepairOrder;

/**
 * Contains methods that call the database to handle repair orders.
 * Since there is no database, repair orders are stored here in a list.
 */
  public class RepairOrderRegistry 
{
    private static final RepairOrderRegistry INSTANCE = new RepairOrderRegistry();
    private List<RepairOrder> repairOrders;
    private int amountOfRepairOrders;
      
    
    private RepairOrderRegistry()
    {
        repairOrders = new ArrayList<>();
        amountOfRepairOrders = 0;
    }
    
    /**
     * Returns the single instance of RepairOrderRegistry.
     * @return The singleton RepairOrderRegistry instance.
     */
    static RepairOrderRegistry getInstance()
    {
        return INSTANCE;
    }
    
    /**
     * Saves the repair order in the registry.
     * @param repairOrder The repair order to be saved.
     */
    public void createRepairOrder(RepairOrder repairOrder)
    {
        repairOrder.setId(amountOfRepairOrders);
        repairOrders.add(repairOrder);
        amountOfRepairOrders++;
    }
    
    /**
     * All stored repair orders are returned.
     * @return The returned repair orders in a list.
     */
    public List<RepairOrder> findAllRepairOrders()
    {
        return repairOrders;
    }
    
    /**
     * Searches for the repair order with the specified id.
     * @param repairOrderId The repair order id that is searched after.
     * @return Repair order with the matching id.
     */
    public RepairOrder findRepairOrderById(int repairOrderId)
    {
        for(RepairOrder repairOrder : repairOrders)
            if(repairOrder.hasId(repairOrderId))
                return repairOrder;
        return null;
    }
    
    /**
     * Searches for the repair order with the specified phone number.
     * @param phoneNumber The phone number is the searching criteria.
     * @return Repair order with the matching phone number.
     */
    public RepairOrder findRepairOrderByPhoneNumber(String phoneNumber)
    {
        for(RepairOrder repairOrder : repairOrders)
            if(repairOrder.hasPhoneNumber(phoneNumber))
                return repairOrder;
        return null;
    }
    
    /**
     * Updates the repair order by searching for the right repair order
     * and overwriting the repair order with the same id with the updated one.
     * @param updatedRepairOrder The updated repair order to be saved.
     */
    public void updateRepairOrder(RepairOrder updatedRepairOrder)
    {
        for(int i = 0; i < repairOrders.size(); i++)
        {
            if(repairOrders.get(i).hasSameIdAs(updatedRepairOrder))
            {
              repairOrders.set(i, updatedRepairOrder);
              break;
            }
        }
    }
    
    void clearForTest()
    {
        repairOrders.clear();
        amountOfRepairOrders = 0;
    }
  }