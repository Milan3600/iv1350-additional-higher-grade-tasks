package se.kth.iv1350.bikerepair.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorMessageHandlerTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testShowErrorMessage() {
        ErrorMessageHandler handler = new ErrorMessageHandler();

        String message = "Something went wrong";
        handler.showErrorMessage(message);

        String output = outContent.toString();

        assertTrue(output.contains("ERROR:"), "Missing ERROR prefix");
        assertTrue(output.contains("Something went wrong"), "Missing error message");
        assertTrue(output.contains("TIME:"), "Missing timestamp label");
    }
}