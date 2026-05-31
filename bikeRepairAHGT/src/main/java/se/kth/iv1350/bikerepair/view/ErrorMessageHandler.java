package se.kth.iv1350.bikerepair.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The responsibility of this class is to show relevant error messages
 * to the user.
 *
 */
public class ErrorMessageHandler 
{
    /**
     * Formats the error message and shows it to <code>System.out</code>.
     * @param message The error message.
     */
    void showErrorMessage(String message)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ERROR: ");
        builder.append(message);
        builder.append(", TIME: ");
        builder.append(currentTime());
        System.out.println(builder);
    }
 
    private String currentTime() 
    {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return current.format(formatter);
    }
}
