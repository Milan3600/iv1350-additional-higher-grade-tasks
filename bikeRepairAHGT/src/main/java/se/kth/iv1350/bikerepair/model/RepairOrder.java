package se.kth.iv1350.bikerepair.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import static se.kth.iv1350.bikerepair.model.RepairOrderState.ACCEPTED;
import se.kth.iv1350.bikerepair.model.pricing.PricingFactory;

/**
 * Represents the repair order that is tied to a customer.
 * 
 */
public class RepairOrder 
{
  private CustomerDTO customerDetails;
  private String problemDescr;
  private LocalDateTime dateTimeOfCreation;
  private int repairOrderId;
  private DiagnosticReport diagnosticReport;
  private List<RepairTask> repairTasks;
  private Amount totalCost;
  private RepairOrderState state;
  private LocalDateTime estimatedCompletionDate;
  private List<RepairOrderObserver> repairOrderObservers = new ArrayList<>(); 
     
  /**
   * Creates an instance.
   * @param repairOrderDTO  Contains customer's problem description
   * that will be extracted.
   * @param customerDTO Contains all of customer's information.
   */
  public RepairOrder(RepairOrderCreateDTO repairOrderDTO,
          CustomerDTO customerDTO)
  {
      this.customerDetails = customerDTO;
      this.problemDescr = repairOrderDTO.getProblemDescr();
      this.dateTimeOfCreation = LocalDateTime.now();
      this.estimatedCompletionDate = LocalDateTime.now().plusWeeks(1);
      this.diagnosticReport = new DiagnosticReport();
      this.repairTasks = new ArrayList<>();
      this.state = RepairOrderState.NEWLY_CREATED;
      
  }
  
  /**
   * Sets the Id to the repair order and is only used by RepairOrderRegistry.
   * @param Id The Id that is set.
   */
  public void setId(int Id)
  {
      this.repairOrderId = Id;
  }
  
  /**
   * Registers that the repair order has been accepted by setting the state
   * of the repair order to <code>ACCEPTED</code>.
   */
  public void accept()
  {
      setState(ACCEPTED);
      notifyObservers();
  }
  
  private void setState(RepairOrderState state)
  {
      this.state = state;
  }
  
  private void recalculateTotalCost()
  {
      Amount baseTotal = calculateDiagnosticCost().plus(calculateRepairCost());
      this.totalCost = PricingFactory.getPricingFactory().getDefaultPricing()
              .calculate(baseTotal);
  }
  
  private Amount calculateDiagnosticCost()
  {
      List<DiagnosticTask> diagTasks = diagnosticReport.getAllDiagnosticTasks();
      Amount diagTasksTotalCost = new Amount(0, "EUR");
      for(DiagnosticTask diagTask : diagTasks)
      {
          diagTasksTotalCost = diagTasksTotalCost.plus(diagTask.getCost());
      }
      return diagTasksTotalCost;
  }
  
  private Amount calculateRepairCost()
  {   
      Amount repairTasksTotalCost = new Amount(0, "EUR");
      for(RepairTask repairTask : repairTasks)
      {
          repairTasksTotalCost = repairTasksTotalCost.plus(repairTask.getCost());
      }
      return repairTasksTotalCost;
  }
  
  /**
   * Checks if the specified Id is present in a repair order.
   * @param Id The Id that is being compared.
   * @return <code>true</code> if the repair order contains the specified Id, 
   * <code>false</code> if it does not.
   */
  public boolean hasId(int Id)
  {
      return this.repairOrderId == Id;
  }
  
  /**
   * Checks if the specified phone number is present in a repair order.
   * @param phoneNumber The phone number that is being compared.
   * @return <code>true</code> if the repair order contains the specified phone number,
   * <code>false</code> if it does not.
   */
  public boolean hasPhoneNumber(String phoneNumber)
  {
      return this.customerDetails.getPhoneNumber().equals(phoneNumber);
  }  
 
  /**
   * Checks if the specified repair order has the same Id as this one.
   * @param otherRepairOrder The repair order to compare with.
   * @return <code>true</code> if the repair orders have the same Id,
   * <code>false</code> if they do not.
   */
  public boolean hasSameIdAs(RepairOrder otherRepairOrder)
  {
      return otherRepairOrder.hasId(repairOrderId);
  }
  
  /**
   * Adds the specified repair task to the repair order. The total cost is
   * recalculated to reflect the added repair task and repair order state is set
   * to <code>READY_FOR_APPROVAL</code>.
   * @param repairTask The repair task to be added.
   */
  public void addRepairTask(RepairTask repairTask)
  {
      repairTasks.add(repairTask);
      recalculateTotalCost();
      setState(RepairOrderState.READY_FOR_APPROVAL);
      notifyObservers();
  }
  
  /**
   * Adds the result to the specified diagnostic task. 
   * @param diagTaskIndex The diagnostic task state to be updated.
   * @param result The result to set the diagnostic task to.
   */
  public void addDiagnosticResult(int diagTaskIndex, String result)
  {
      DiagnosticTask diagTask = diagnosticReport.getDiagnosticTask(diagTaskIndex);
      diagTask.setResult(result);
      notifyObservers();
  }
  
    /**
   * Get customer details.
   * @return Customer details.
   */
  public CustomerDTO getCustomerDetails() 
  {
        return customerDetails;
  }

  /**
   * Get the problem description.
   * @return Problem description.
   */
  public String getProblemDescr() 
  {
        return problemDescr;
  }

  /**
   * Get the date and time of creation for the repair order.
   * @return Date and time of creation for the repair order.
   */
  public LocalDateTime getDateTimeOfCreation() 
  {
        return dateTimeOfCreation;
  }

  /**
   * Get the repair order Id.
   * @return Repair order Id.
   */
  public int getRepairOrderId() 
  {
        return repairOrderId;
  }
    
  /**
   * Get the diagnostic report.
   * @return Diagnostic report.
   */
  public DiagnosticReport getDiagnosticReport()
  {
        return diagnosticReport;
  }

  /**
   * Get all the repair tasks.
   * @return All repair tasks in a list.
   */
  public List<RepairTask> getRepairTasks() 
  {
        return repairTasks;
  }

  /**
   * Get the total cost of a specific repair order.
   * @return The total cost of repair order.
   */
  public Amount getTotalCost() 
  {
        return totalCost;
  }

  /**
   * Get the state of the repair order, e.g., <code>ACCEPTED</code>.
   * @return State of the repair order.
   */
  public RepairOrderState getState() 
  {
        return state;
  }

  /**
   * Get the estimation of when the repair order will be completed.
   * @return Estimate of when repair order will be done.
   */
  public LocalDateTime getEstimatedCompletionDate() 
  {
        return estimatedCompletionDate;
  }
  
  
  
  /**
   * Creates a formatted string that has the entire information of a repair order.
   * @return The formatted string of repair order.
   */
  @Override
  public String toString()
  {
      StringBuilder builder = new StringBuilder();
      builder.append("Repair Order ID: " + repairOrderId);
      builder.append("\n");
      builder.append("State: " + state);
      builder.append("\n");
      builder.append("Customer " + customerDetails);
      builder.append("Problem : " + problemDescr);
      builder.append("\n");
      builder.append("Created: " + dateTimeOfCreation.toString());
      builder.append("\n");
      builder.append("Estimated completion: " + estimatedCompletionDate.toString());
      builder.append("\n");
      builder.append("Diagnostic report " + "\n" + diagnosticReport);
      builder.append("Repair tasks ");
      builder.append("\n");
      for(RepairTask repairTask : repairTasks)
      {
          builder.append(repairTask);
          builder.append("\n");
      }
      
      return builder.toString();
  }
  
  
  /**
   * Adds a specified observer that will be notified of changes made
   * to a repair order.
   * @param observer The observer that will be notified.
   */
  public void addRepairOrderObserver(RepairOrderObserver observer)
  {
      repairOrderObservers.add(observer);
  }
  
  
  /**
   * Adds multiple observers that will be notified of changes made to
   * a repair order.
   * @param observers The observers to notify.
   */
  public void addRepairOrderObservers(List<RepairOrderObserver> observers)
  {
      repairOrderObservers.addAll(observers);
  }
  
  private RepairOrderDTO createDTO()
  {
    List<String> tasks = new ArrayList<>();
    for (RepairTask task : repairTasks)
    {
        tasks.add(task.toString());
    }
    String totalCostStr =
        totalCost == null ? "" : totalCost.toString();

    return new RepairOrderDTO(
        repairOrderId,
        customerDetails.toString(),
        problemDescr,
        dateTimeOfCreation,
        estimatedCompletionDate,
        state.toString(),
        diagnosticReport.toString(),
        tasks,
        totalCostStr
    );
  }
  
  private void notifyObservers()
  {
      RepairOrderDTO repairOrderDTO = createDTO();
      for(RepairOrderObserver observer : repairOrderObservers)
          observer.stateHasChanged(repairOrderDTO);
  }
  
}