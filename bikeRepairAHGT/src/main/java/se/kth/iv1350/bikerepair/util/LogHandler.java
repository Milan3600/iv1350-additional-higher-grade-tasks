package se.kth.iv1350.bikerepair.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The program's logger. It saves the logs on a file.
 */
public class LogHandler 
{
    private static final String LOG_FILE_NAME = "bikerepair-log.txt";
    private PrintWriter logFile;
    
    /**
     * Creates an instance.
     * @throws IOException if the file cannot be opened or written to.
     */
    public LogHandler() throws IOException
    {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }
    
    /**
     * Appends the log with the specified thrown exception.
     * @param exception The exception that will be logged.
     */
    public void logException(Exception exception)
    {
        StringBuilder messageToLog = new StringBuilder();
        messageToLog.append("Exception thrown on " + currentTime() + ": ");
        messageToLog.append(exception.getMessage());
        logFile.println(messageToLog);
        exception.printStackTrace(logFile);
        logFile.print("\n");
    }
 
    private String currentTime() 
    {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return current.format(formatter);
    }
    
}