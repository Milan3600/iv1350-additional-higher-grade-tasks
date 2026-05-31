package se.kth.iv1350.bikerepair.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {
    
    public AmountTest() 
    {
    }
   

    @Test
    public void testPlus() 
    {
        String currency = "EUR";
        int valueOfAmt1 = 5;
        int valueOfAmt2 = 7;
        Amount amt1 = new Amount(valueOfAmt1, currency);
        Amount amt2 = new Amount(valueOfAmt2, currency);
        Amount expResult = new Amount(valueOfAmt1 + valueOfAmt2, currency);
        Amount result = amt1.plus(amt2);
        assertEquals(expResult, result, "The addition result is wrong");
    }
    
    @Test
    public void testPlusNegResult()
    {
        String currency = "EUR";
        int valueOfAmt1 = 2;
        int valueOfAmt2 = -15;
        Amount amt1 = new Amount(valueOfAmt1, currency);
        Amount amt2 = new Amount(valueOfAmt2, currency);
        Amount expResult = new Amount(valueOfAmt1 + valueOfAmt2, currency);
        Amount result = amt1.plus(amt2);
        assertEquals(expResult, result, "The addition result is wrong");
    }
    
    @Test
    public void testPlusZeroResult()
    {
        String currency = "EUR";
        int valueOfAmt1 = -5;
        int valueOfAmt2 = 5;
        Amount amt1 = new Amount(valueOfAmt1, currency);
        Amount amt2 = new Amount(valueOfAmt2, currency);
        Amount expResult = new Amount(valueOfAmt1 + valueOfAmt2, currency);
        Amount result = amt1.plus(amt2);
        assertEquals(expResult, result, "The addition result is wrong");
    }
   
    @Test
    public void testMinus() 
    {
        String currency = "USD";
        int valueOfAmt1 = 13;
        int valueOfAmt2 = 3;
        Amount amt1 = new Amount(valueOfAmt1, currency);
        Amount amt2 = new Amount(valueOfAmt2, currency);
        Amount expResult = new Amount(valueOfAmt1 - valueOfAmt2, currency);
        Amount result = amt1.minus(amt2);
        assertEquals(expResult, result, "The subtraction result is wrong");
    }
    
    
    @Test
    public void testMinusNegResult()
    {
        String currency = "USD";
        int valueOfAmt1 = 2;
        int valueOfAmt2 = 9;
        Amount amt1 = new Amount(valueOfAmt1, currency);
        Amount amt2 = new Amount(valueOfAmt2, currency);
        Amount expResult = new Amount(valueOfAmt1 - valueOfAmt2, currency);
        Amount result = amt1.minus(amt2);
        assertEquals(expResult, result, "The subtraction result is wrong");
    }

    @Test
    public void testMinusZeroResult()
    {
        String currency = "EUR";
        int valueOfAmt1 = -11;
        int valueOfAmt2 = -11;
        Amount amt1 = new Amount(valueOfAmt1, currency);
        Amount amt2 = new Amount(valueOfAmt2, currency);
        Amount expResult = new Amount(valueOfAmt1 - valueOfAmt2, currency);
        Amount result = amt1.minus(amt2);
        assertEquals(expResult, result, "The subtraction result is wrong");
    }
    
    
    @Test
    public void testEqual()
    {
        String currency = "EUR";
        int amt1Value = 33;
        int amt2Value = 33;
        Amount amt1 = new Amount(amt1Value, currency);
        Amount amt2 = new Amount(amt2Value, currency);
        boolean expResult = true;
        boolean result =  amt1.equals(amt2);
        assertEquals(expResult, result, "Two identical amount instances are not equal");   
    }
    
    @Test
    public void testNotEqual()
    {
        String currency = "EUR";
        int amt1Value = 1;
        int amt2Value = 0;
        Amount amt1 = new Amount(amt1Value, currency);
        Amount amt2 = new Amount(amt2Value, currency);
        boolean expResult = false;
        boolean result = amt1.equals(amt2);
        assertEquals(expResult, result, "Two different amount instances are equal");
    }
    
    @Test
    public void testEqualValueDifferentCurrency()
    {
        String currency1 = "EUR";
        String currency2 = "USD";
        int amt1Value = 20;
        int amt2Value = 20;
        Amount amt1 = new Amount(amt1Value, currency1);
        Amount amt2 = new Amount(amt2Value, currency2);
        boolean expResult = false;
        boolean result = amt1.equals(amt2);
        assertEquals(expResult, result, "Amount instances with same values"
                + "but different currencies are equal");
    }
    
    @Test
    public void testEqualsNull()
    {
        Amount amt = new Amount(10, "EUR");
        Object nullObject = null;
        boolean expResult = false;
        boolean result = amt.equals(nullObject);
        assertEquals(expResult, result, "An instance of amount equals null");
    }
    
    @Test
    public void testToString() 
    {
        String currency = "EUR";
        int valueAmt = 99;
        Amount amt = new Amount(valueAmt, currency);
        String expResult = Double.toString(valueAmt) + " " + currency;
        String result = amt.toString();
        assertEquals(expResult, result, "The returned string created by toString is wrong");
    }
    
}
