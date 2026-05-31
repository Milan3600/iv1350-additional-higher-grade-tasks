package se.kth.iv1350.bikerepair.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Immutable snapshot of a repair order.
 * Contains all information representing the current state of a repair order.
 */
public final class RepairOrderDTO
{
    private final int repairOrderId;
    private final String customerDetails;
    private final String problemDescr;
    private final LocalDateTime creationDate;
    private final LocalDateTime estimatedCompletionDate;
    private final String state;
    private final String diagnosticReport;
    private final List<String> repairTasks;
    private final String totalCost;

    /**
     * Creates an instance containing repair order presentation data.
     *
     * @param repairOrderId The repair order ID.
     * @param customerDetails Customer information.
     * @param problemDescr Description of the bike problem.
     * @param creationDate Date and time of repair order creation.
     * @param estimatedCompletionDate Estimated completion date.
     * @param state Current repair order state.
     * @param diagnosticReport Diagnostic report information.
     * @param repairTasks All repair tasks.
     * @param totalCost Total cost of the repair order.
     */
    public RepairOrderDTO(
            int repairOrderId,
            String customerDetails,
            String problemDescr,
            LocalDateTime creationDate,
            LocalDateTime estimatedCompletionDate,
            String state,
            String diagnosticReport,
            List<String> repairTasks,
            String totalCost)
    {
        this.repairOrderId = repairOrderId;
        this.customerDetails = customerDetails;
        this.problemDescr = problemDescr;
        this.creationDate = creationDate;
        this.estimatedCompletionDate = estimatedCompletionDate;
        this.state = state;
        this.diagnosticReport = diagnosticReport;
        this.repairTasks = repairTasks;
        this.totalCost = totalCost;
    }

    /**
     * Gets the repair order ID.
     *
     * @return The repair order ID.
     */
    public int getRepairOrderId()
    {
        return repairOrderId;
    }

    /**
     * Gets customer details.
     *
     * @return Customer details.
     */
    public String getCustomerDetails()
    {
        return customerDetails;
    }

    /**
     * Gets the problem description.
     *
     * @return The problem description.
     */
    public String getProblemDescr()
    {
        return problemDescr;
    }

    /**
     * Gets the creation date.
     *
     * @return Creation date.
     */
    public LocalDateTime getCreationDate()
    {
        return creationDate;
    }

    /**
     * Gets the estimated completion date.
     *
     * @return Estimated completion date.
     */
    public LocalDateTime getEstimatedCompletionDate()
    {
        return estimatedCompletionDate;
    }

    /**
     * Gets the repair order state.
     *
     * @return Repair order state.
     */
    public String getState()
    {
        return state;
    }

    /**
     * Gets the diagnostic report.
     *
     * @return Diagnostic report.
     */
    public String getDiagnosticReport()
    {
        return diagnosticReport;
    }

    /**
     * Gets all repair tasks.
     *
     * @return Repair tasks.
     */
    public List<String> getRepairTasks()
    {
        return repairTasks;
    }

    /**
     * Gets the total cost.
     *
     * @return Total cost.
     */
    public String getTotalCost()
    {
        return totalCost;
    }

    /**
     * Creates a formatted string representation of the repair order.
     *
     * @return Formatted repair order information.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("Repair Order ID: ");
        builder.append(repairOrderId);
        builder.append("\n");

        builder.append("State: ");
        builder.append(state);
        builder.append("\n");

        builder.append("Customer: ");
        builder.append(customerDetails);

        builder.append("Problem: ");
        builder.append(problemDescr);
        builder.append("\n");

        builder.append("Created: ");
        builder.append(creationDate);
        builder.append("\n");

        builder.append("Estimated completion: ");
        builder.append(estimatedCompletionDate);
        builder.append("\n");

        builder.append("Diagnostic report:");
        builder.append("\n");
        builder.append(diagnosticReport);

        builder.append("Repair tasks:");
        builder.append("\n");

        for(String repairTask : repairTasks)
        {
            builder.append(repairTask);
            builder.append("\n");
        }

        builder.append("Total cost: ");
        builder.append(totalCost);
        builder.append("\n");

        return builder.toString();
    }
}