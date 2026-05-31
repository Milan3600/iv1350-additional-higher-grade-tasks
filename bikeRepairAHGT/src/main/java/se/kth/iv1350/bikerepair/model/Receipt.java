package se.kth.iv1350.bikerepair.model;

import java.util.List;

/**
 * The receipt of a repair order.
 *
 */
public class Receipt 
{
    private final RepairOrder repairOrder;
   
    
    /**
     * Creates an instance.
     * @param repairOrder The repair order to be added on a receipt.
     */
    public Receipt(RepairOrder repairOrder)
    {
        this.repairOrder = repairOrder;
    }
    
    
    /**
     * Creates a formatted and structured string that contains all information
     * of a specific repair order.
     * @return The structured receipt string.
     */
    public String createReceiptString()
    {
        StringBuilder builder = new StringBuilder();
        appendWithLine(builder, "Repair Order");
        endSection(builder);
        
        builder.append("Repair order Id: ");
        appendWithLine(builder, "" + repairOrder.getRepairOrderId());
        
        builder.append("Created: ");
        appendWithLine(builder, "" + repairOrder.getDateTimeOfCreation());
        
        builder.append("Problem description: ");
        appendWithLine(builder, repairOrder.getProblemDescr());
        
        builder.append("Diagnostic report ");
        endSection(builder);
        builder.append(repairOrder.getDiagnosticReport());
        
        builder.append("Repair tasks ");
        endSection(builder);
        List<RepairTask> repairTasks = repairOrder.getRepairTasks();
        for(RepairTask repairTask : repairTasks)
            appendWithLine(builder, "" + repairTask);
                
        builder.append("Status of repair order: ");
        appendWithLine(builder, "" + repairOrder.getState());
        
        builder.append("Estimated completion date for repair: ");
        appendWithLine(builder, "" + repairOrder.getEstimatedCompletionDate());
        
        builder.append("Total cost: ");
        appendWithLine(builder, "" + repairOrder.getTotalCost());
        endSection(builder);
        
        
        builder.append("Bike information");
        endSection(builder);
        
        builder.append("Brand: ");
        appendWithLine(builder, repairOrder.getCustomerDetails().getBikeDetails().getBrand());
        builder.append("Model: ");
        appendWithLine(builder, repairOrder.getCustomerDetails().getBikeDetails().getModel());
        builder.append("Serial number: ");
        appendWithLine(builder, repairOrder.getCustomerDetails().getBikeDetails().getSerialNumber());
        endSection(builder);
        
        builder.append("Customer information");
        endSection(builder);
        
        builder.append("Name: ");
        appendWithLine(builder, repairOrder.getCustomerDetails().getName());
        builder.append("Email: ");
        appendWithLine(builder, repairOrder.getCustomerDetails().getEmail());
        builder.append("Phone number: ");
        appendWithLine(builder, repairOrder.getCustomerDetails().getPhoneNumber());        
        
        return builder.toString();
        
    }
    
    
    private void appendWithLine(StringBuilder builder, String Line)
    {
        builder.append(Line);
        builder.append("\n");
    }
    
    private void endSection(StringBuilder builder)
    {
        builder.append("\n");
    }
    
}
