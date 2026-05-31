package se.kth.iv1350.bikerepair.model.pricing;

/**
 * A singleton that creates <code>PricingStrategy</code> algorithms.
 */
public class PricingFactory 
{    
    private static final PricingFactory PRICING_FACTORY = new PricingFactory();
    
    private PricingFactory()
    {
        
    }
    
    /**
     * Returns the only instance of PricingFactory.
     * @return The instance of PricingFactory.
     */
    public static PricingFactory getPricingFactory()
    {
        return PRICING_FACTORY;
    }
    
    /**
     * Returns a <code>PricingStrategy</code> that performs the default
     * pricing algorithm.
     * @return The default pricing algorithm.
     */
    public PricingStrategy getDefaultPricing() 
    {
        return new NoDiscount();
    }
    
    /**
     * Returns a <code>PricingStrategy</code> that performs the loyalty
     * pricing algorithm.
     * @return The loyalty pricing algorithm.
     */
    public PricingStrategy getLoyaltyDiscount()
    {
        return new LoyaltyDiscount();
    }
    
    /**
     * Returns a <code>PricingStrategy</code> that performs the winter
     * pricing algorithm.
     * @return The winter pricing algorithm.
     */
    public PricingStrategy getWinterDiscount()
    {
        return new WinterDiscount();
    }
    
}