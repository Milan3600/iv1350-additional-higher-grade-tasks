package se.kth.iv1350.bikerepair.model;

/**
 * Represents the diagnostic task that contains the information about the task
 * such as result and cost.
 *
 */
public class DiagnosticTask 
{
  private String name;
  private String description;
  private Amount cost;
  private String result;
  
  /**
   * Creates an instance.
   * @param name The name of the task.
   * @param description Description of the task.
   * @param cost How much the task costs.
   */
  public DiagnosticTask(String name, String description,
        Amount cost)
  {
      this.name = name;
      this.description = description;
      this.cost = cost;
      this.result = "AWAITING_TECHNICIAN";
  }
  
  /**
   * Sets the result to the diagnostic task.
   * @param diagTaskResult The result that shall be set.
   */
  public void setResult(String diagTaskResult)
  {
      this.result = diagTaskResult;
  }
  
  /**
   * Creates a formatted string that contains all information of a
   * <code>DiagnosticTask</code> instance.
   * @return The formatted string with all of <code>DiagnosticTask</code> info.
   */
  @Override
  public String toString()
  {
      StringBuilder builder = new StringBuilder();
      builder.append("Name: " + name +"| ");
      builder.append("Description: " + description +"| ");
      builder.append("Cost: " + cost +"| ");
      builder.append("Result: " + result);
      return builder.toString();
  }
  
  /**
   * Get the name of the diagnostic task.
   * @return The name of the diagnostic task.
   */
  public String getName()
  {
      return name;
  }
  
  /**
   * Get the description of the diagnostic task.
   * @return The name of the diagnostic task.
   */
  public String getDescription()
  {
      return description;
  }
  
  /**
   * Get the cost of the diagnostic task.
   * @return The diagnostic task cost.
   */
  public Amount getCost()
  {
      return cost;
  }
  
  /**
   * Get the result of the diagnostic task.
   * @return The result of the diagnostic task.
   */
  public String getResult()
  {
      return result;
  }
}
