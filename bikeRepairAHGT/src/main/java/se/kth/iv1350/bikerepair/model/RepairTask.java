package se.kth.iv1350.bikerepair.model;

/**
 * Represents the repair task that needs to be done on the bike. It contains
 * all the information about the repair task.
 *
 */
public class RepairTask 
{
    private String name;
    private String description;
    private Amount cost;
    private RepairTaskState state;
    
    
    /**
     * Creates an instance.
     * @param name The name of the repair task
     * @param description Detailed description of the repair task.
     * @param cost Cost of the repair task.
     */
    public RepairTask(String name, String description, Amount cost)
    {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.state = RepairTaskState.INCOMPLETE;
    }
    
    
    /**
     * Returns the name of the repair task.
     * @return Name of the repair task.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the description of the repair task.
     * @return Description of the repair task.
     */
    public String getDescription()
    {
        return description;
    }
    
   
    
    /**
     * Returns the cost of the repair task.
     * @return The total cost for the repair task.
     */
    public Amount getCost()
    {
        return cost;
    }
    
    /**
     * Returns the state of thee repair task.
     * @return The state.
     */
    public RepairTaskState getState()
    {
        return state;
    }
    
    
    @Override
    /**
     * Prints out the repair task well-formatted.
     */
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Name: " + name + ", ");
        builder.append("Description: " + description + ", ");
        builder.append("Cost: " + cost + ", ");
        builder.append("State: " + state);
        return builder.toString();
    }
    
}
