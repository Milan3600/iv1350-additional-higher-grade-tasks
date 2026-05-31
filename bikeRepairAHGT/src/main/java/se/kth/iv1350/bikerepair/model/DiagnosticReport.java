package se.kth.iv1350.bikerepair.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the diagnostic report. 
 * 
 */
public class DiagnosticReport 
{
    private List<DiagnosticTask> diagnosticTasks = new ArrayList<>();
    private LocalDateTime dateOfCreation;
    
    /**
     * Creates an instance.
     *
     */
    public DiagnosticReport()
    {
        addDiagnosticTasks();
        this.dateOfCreation = LocalDateTime.now();        
    }
    
    private void addDiagnosticTasks()
    {
        diagnosticTasks.add(new DiagnosticTask("Basic Tune-up",
        "Inspection of gears, brakes, chains, tires. Includes adjusments and lubrication",
        new Amount(45, "EUR")));
        
        diagnosticTasks.add(new DiagnosticTask("Wheel truing",
        "Checks wheels for wobble and adjusts spoke tension.",
        new Amount(13, "EUR")));
    }
    
    /**
     * Get the diagnostic task with the specified Id, which is the same as its index.
     * @param Id The id, index, that is tied to a specific diagnostic task.
     * @return The diagnostic with the matching id.
     */
    public DiagnosticTask getDiagnosticTask(int Id)
    {
        return diagnosticTasks.get(Id);
    }
    
    /**
     * Creates a formatted string that contains all information of a diagnostic report.
     * @return String with diagnostic report info.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Diagnostic report created: " + dateOfCreation);
        builder.append("\n");
        builder.append("Diagnostic Tasks: ");
        builder.append("\n");
        for(DiagnosticTask diagTask : diagnosticTasks)
        {
           builder.append(diagTask);
           builder.append("\n");
        }
        return builder.toString();
    }
    
    /**
     * Get the all diagnostic task in a list. 
     * @return Diagnostic tasks in a list.
     */
    public List<DiagnosticTask> getAllDiagnosticTasks ()
    {
        return diagnosticTasks;
    }
    
    /**
     * Get the <code>LocalDateTime</code> of when diagnostic report was created.
     * @return The date and time of creation.
     */
    public LocalDateTime getDateOfCreation()
    {
        return dateOfCreation;
    }
    
}
