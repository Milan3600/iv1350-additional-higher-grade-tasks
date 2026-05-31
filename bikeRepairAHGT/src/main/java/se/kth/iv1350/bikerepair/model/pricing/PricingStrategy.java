package se.kth.iv1350.bikerepair.model.pricing;

import se.kth.iv1350.bikerepair.model.Amount;

/**
 * Defines the ability to calculate the total price of a repair order.
 * Classes interested in this ability implements this interface.
 */
public interface PricingStrategy 
{
    /**
     * Calculates the total price of a repair order.
     * @param baseTotal The base price of a repair order before any discounts
     * have been applied.
     * @return Final price of a repair order.
     */
    Amount calculate(Amount baseTotal);
}