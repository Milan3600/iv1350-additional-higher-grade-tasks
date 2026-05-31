package se.kth.iv1350.bikerepair.model.pricing;

import se.kth.iv1350.bikerepair.model.Amount;

/**
 * A <code>PricingStrategy</code> representing default pricing behavior.
 * No discount is applied to the total cost of a repair order.
 */
public class NoDiscount implements PricingStrategy
{
    @Override
    public Amount calculate(Amount baseTotal)
    {
        return baseTotal;
    }
}