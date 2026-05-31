package se.kth.iv1350.bikerepair.model.pricing;

import se.kth.iv1350.bikerepair.model.Amount;

/**
 * A <code>PricingStrategy</code> that calculates the price 
 * of a repair order for a loyal customer. Applies a fixed 10% discount.
 */
public class LoyaltyDiscount implements PricingStrategy
{   
    @Override
    public Amount calculate(Amount baseTotal)
    {
        return baseTotal.multiply(0.9);
    }
}