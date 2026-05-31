package se.kth.iv1350.bikerepair.startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

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
    public void testMain() {
        Main.main(new String[]{});

        String output = outContent.toString();
        
        assertTrue(output.contains("cannot start") || output.length() > 0,
        "Main did not produce expected startup output");

        assertTrue(output.toLowerCase().contains("repair") ||
                   output.toLowerCase().contains("customer") ||
                   output.toLowerCase().contains("order"),
                "Main did not run expected system startup output");
    }
}