package se.kth.iv1350.bikerepair.controller;

import se.kth.iv1350.bikerepair.model.RepairOrderDTO;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;
import se.kth.iv1350.bikerepair.integration.CustomerRegistry;
import se.kth.iv1350.bikerepair.integration.CustomerRegistryException;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RepairOrderRegistry;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.Receipt;
import se.kth.iv1350.bikerepair.model.RepairOrder;
import se.kth.iv1350.bikerepair.model.RepairOrderCreateDTO;
import se.kth.iv1350.bikerepair.model.RepairOrderObserver;
import se.kth.iv1350.bikerepair.model.RepairTask;

/**
 * This is the application's only Controller. It forwards method calls 
 * to classes in lower layers.
 */
public class Controller 
{
    private RegistryCreator regCreator;
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry repairOrderRegistry;
    private Printer printer;
    private List<RepairOrderObserver> repairOrderObservers = new ArrayList<>();
    
    /**
     * Creates an instance.
     * @param regCreator RegistryCreator creates all the other
     * registries. Used to get the created registries.
     * @param printer The printer that is called to print out the receipt
     * to <code>System.out</code>.
     */
    public Controller(RegistryCreator regCreator, Printer printer)
    {
        this.regCreator = regCreator;
        this.customerRegistry = regCreator.getCustomerRegistry();
        this.repairOrderRegistry = regCreator.getRepairOrderRegistry();
        this.printer = printer;
    }
    
    /**
     * Calls the corresponding method in CustomerRegistry
     * to retrieve the customer's information.
     * @param phoneNum Customer's phone number.
     * @return Customer's information.
     * @throws CustomerNotFoundException if no customer is found associated with the
     * specified phone number.
     * @throws OperationFailedException if unable to find a customer, for any other
     * reason than the customer not existing.
     */
    public CustomerDTO findCustomer(String phoneNum) throws CustomerNotFoundException,
            OperationFailedException
    {
        try
        {    
            CustomerDTO foundCustomer = customerRegistry.findCustomer(phoneNum);
            if(foundCustomer == null)
               throw new CustomerNotFoundException(phoneNum);
            
            return foundCustomer;
        }
        catch(CustomerRegistryException custRegException)
        {
            throw new OperationFailedException("Could not retrieve customer", custRegException);
        }
    }
    
    /**
     * Calls the corresponding method in RepairOrderRegistry to save
     * the newly created repair order.
     * @param repairOrderDTO The object that is used to 
     * create the repair order.
     */
    public void createRepairOrder(RepairOrderCreateDTO repairOrderDTO)
    {
        CustomerDTO customerDTO = customerRegistry.findCustomer
        (repairOrderDTO.getCustomerPhoneNumber());
        RepairOrder repairOrder = new RepairOrder(repairOrderDTO,
        customerDTO);
        repairOrder.addRepairOrderObservers(repairOrderObservers);
        repairOrderRegistry.createRepairOrder(repairOrder);
    }
    
    /**
    * Returns all the stored repair orders as DTOs.
    * @return A list of <code>RepairOrderDTO</code> containing
    * information of all repair orders.
    */
    public List<RepairOrderDTO> findAllRepairOrders()
    {
        List<RepairOrder> repairOrders =
            repairOrderRegistry.findAllRepairOrders();

        List<RepairOrderDTO> repairOrderViewDTOs =
            new ArrayList<>();

        for(RepairOrder repairOrder : repairOrders)
            {
               repairOrderViewDTOs.add(
               createRepairOrderDTO(repairOrder));
            }

        return repairOrderViewDTOs;
    }
    
    
   /**
   * Finds the repair order by phone number and returns it as a DTO.
   * @param phoneNumber The phone number used as search criteria.
   * @return <code>RepairOrderDTO</code> representing the found repair order,
   * or <code>null</code> if no match is found.
   */
   public RepairOrderDTO findRepairOrderByPhoneNumber(String phoneNumber)
   {
        RepairOrder repairOrder =
        repairOrderRegistry.findRepairOrderByPhoneNumber(phoneNumber);

        return createRepairOrderDTO(repairOrder);
   }

    
    /**
     * Adds the result to a specific diagnostic task in a repair order.
     * @param repairOrderId The repair order to add the diagnostic task result to.
     * @param diagTaskIndex Specific diagnostic task to add the result to.
     * @param result The result to add.
     */
    public void addDiagnosticResult(int repairOrderId, int diagTaskIndex, String result)
    {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        repairOrder.addDiagnosticResult(diagTaskIndex, result);
        updateRepairOrder(repairOrder);
    }
    
    /**
     * Adds the repair task to a specific repair order.
     * @param repairOrderId The specific repair order where the repair task shall be added.
     * @param name Name of the repair task.
     * @param description Description of the repair task.
     * @param amount Cost of the repair task.
     */
    public void addRepairTask(int repairOrderId, String name, String description, Amount amount)
    {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        RepairTask repairTask = new RepairTask(name, description, amount);
        repairOrder.addRepairTask(repairTask);
        updateRepairOrder(repairOrder);
    }
    
    
    /**
     * Registers that repair order has been accepted, saves it, and prints a receipt
     * of the entire repair order.
     * @param repairOrderId The repair order to mark as accepted.
     */
    public void acceptRepairOrder(int repairOrderId)
    {
        RepairOrder repairOrder = repairOrderRegistry.findRepairOrderById(repairOrderId);
        repairOrder.accept();
        updateRepairOrder(repairOrder);
        Receipt receipt = new Receipt(repairOrder);
        printer.printReceipt(receipt);
    }
    
    /**
     * The specified observer will receive notifications when any change
     * has been made to a repair order.
     * @param observer The observer that will be notified.
     */
    public void addRepairOrderObserver(RepairOrderObserver observer)
    {
        repairOrderObservers.add(observer);
    }
    
    private void updateRepairOrder(RepairOrder repairOrder)
    {
        repairOrderRegistry.updateRepairOrder(repairOrder);
    }
    
    private RepairOrderDTO createRepairOrderDTO(RepairOrder repairOrder)
    {
        if(repairOrder == null)
            return null;
        
        List<String> repairTasks = new ArrayList<>();

        for(RepairTask repairTask : repairOrder.getRepairTasks())
         {
           repairTasks.add(repairTask.toString());
         }

        String totalCost = "";

        if(repairOrder.getTotalCost() != null)
         {
           totalCost = repairOrder.getTotalCost().toString();
         }

        return new RepairOrderDTO(
            repairOrder.getRepairOrderId(),
            repairOrder.getCustomerDetails().toString(),
            repairOrder.getProblemDescr(),
            repairOrder.getDateTimeOfCreation(),
            repairOrder.getEstimatedCompletionDate(),
            repairOrder.getState().toString(),
            repairOrder.getDiagnosticReport().toString(),
            repairTasks,
            totalCost);
    }
    
}