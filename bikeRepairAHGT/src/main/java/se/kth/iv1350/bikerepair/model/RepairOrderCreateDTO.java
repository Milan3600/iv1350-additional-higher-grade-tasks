
package se.kth.iv1350.bikerepair.model;

/**
 * Contains the necessary information needed to create a repair order.
 *
 */
public final class RepairOrderCreateDTO 
{
    private final String problemDescr;
    private final String customerPhoneNumber;
    private final String bikeSerialNo;
    
    /**
     * Creates an instance.
     * @param problemDescr The problem description of the bike.
     * @param customerPhoneNumber Customer's phone number.
     * @param bikeSerialNo  The serial number of customer's bike.
     */
    public RepairOrderCreateDTO(String problemDescr, String customerPhoneNumber,
            String bikeSerialNo)
    {
        this.problemDescr = problemDescr;
        this.customerPhoneNumber = customerPhoneNumber;
        this.bikeSerialNo = bikeSerialNo;
    }
    
    /**
     * Returns the problem description of the bike.
     * @return The problem description of the bike.
     */
    public String getProblemDescr()
    {
        return problemDescr;
    }
    
    /**
     * Returns the customer's phone number.
     * @return Customer's phone number.
     */
    public String getCustomerPhoneNumber()
    {
        return customerPhoneNumber;
    }
    
    /**
     * Returns the serial number of the customer's bike.
     * @return Bike's serial number.
     */
    public String getBikeSerialNo()
    {
        return bikeSerialNo;
    }
}
