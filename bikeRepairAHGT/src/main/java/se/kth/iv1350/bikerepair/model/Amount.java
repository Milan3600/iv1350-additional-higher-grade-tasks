package se.kth.iv1350.bikerepair.model;

/**
 * Represents an amount of specified currency and value. 
 *
 */
public final class Amount 
{
    private final double value;
    private final String currency;
    
    /**
     * Creates an instance.
     * @param value The specified value.
     * @param currency The specified currency.
     */
    public Amount(double value, String currency)
    {
        this.value = value;
        this.currency = currency;
    }
    
    /**
     * Performs addition with two instances of <code>Amount</code> and
     * returns an instance of <code>Amount</code> with the result.
     * @param amount The <code>Amount</code> to perform addition on.
     * @return The result of the addition.
     */
    public Amount plus(Amount amount)
    {
        return new Amount(this.value + amount.value, this.currency);
    }
    
    /**
     * Performs subtraction with two instances of <code>Amount</code> and
     * returns an instance of <code>Amount</code> with the result.
     * @param amount The <code>Amount</code> to perform subtraction on.
     * @return The result of the subtraction.
     */
    public Amount minus(Amount amount)
    {
        return new Amount(value - amount.value, this.currency);
    }
    
    /**
     * Multiplies this amount by a factor.
     * @param factor the multiplier.
     * @return a new Amount representing the product.
     */
    public Amount multiply(double factor)
    {
        return new Amount(value * factor, this.currency);
    }
    
    
    /**
     * Compares two instances of <code>Amount</code>.
     * They are equal if all of their fields are equal.
     * @param otherObject The other <code>Amount</code> object to compare to.
     * @return <code>true</code> if the compared amount has the same <code>value</code>
     * and <code>currency</code> as this amount.
     */
    @Override
    public boolean equals(Object otherObject)
    {
        if(!(otherObject instanceof Amount))
            return false;
        Amount otherAmount = (Amount) otherObject;
        return value == otherAmount.value && currency.equals(otherAmount.currency);
    }
    
    
    /**
     * Creates a string that contains all information of a specific Amount.  
     * @return Formatted string with all Amount attributes.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(value + " " + currency);
        return builder.toString();
    }
    
}