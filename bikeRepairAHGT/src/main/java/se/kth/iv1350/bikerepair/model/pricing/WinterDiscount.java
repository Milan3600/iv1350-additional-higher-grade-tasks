package se.kth.iv1350.bikerepair.model.pricing;

import se.kth.iv1350.bikerepair.model.Amount;

/**
 * A <code>PricingStrategy</code> that calculates the price for a repair order
 * by applying a 25% discount. Valid during the winter period for all customers.
 */
public class WinterDiscount implements PricingStrategy
{
    @Override
    public Amount calculate(Amount baseTotal)
    {
        return baseTotal.multiply(0.75);
    }
}