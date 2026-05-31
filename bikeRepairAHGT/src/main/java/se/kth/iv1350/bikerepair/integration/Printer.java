package se.kth.iv1350.bikerepair.integration;

import se.kth.iv1350.bikerepair.model.Receipt;

/**
 * Represents the external system Printer. 
 * 
 */
public class Printer 
{
    
    /**
     * Prints to <code>System.out</code> instead of printing an actual receipt on paper.
     * @param receipt The receipt that should be printed.
     */
    public void printReceipt(Receipt receipt)
    {
        System.out.println("------------- Receipt -------------");
        System.out.println(receipt.createReceiptString());
        System.out.println("---------- End of receipt ----------");
    }
}
