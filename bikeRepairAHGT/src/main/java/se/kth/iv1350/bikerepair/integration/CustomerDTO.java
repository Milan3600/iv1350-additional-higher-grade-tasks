package se.kth.iv1350.bikerepair.integration;

/**
 * Contains information of a customer.
 *
 */
public final class CustomerDTO 
{
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final BikeDTO bikeDetails;
      
    /**
     * Creates an instance.
     * @param name The name of the customer.
     * @param email Customer's email address.
     * @param phoneNumber Customer's phone number.
     * @param bikeDetails Details of customer's bike.
     */
    public CustomerDTO(String name, String email, 
            String phoneNumber, BikeDTO bikeDetails)
    {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bikeDetails = bikeDetails;
    }
    
    
    /**
     * Returns the name of the customer.
     * @return The name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns customer's email address.
     * @return The email address.
     */
    public String getEmail()
    {
        return email;
    }
    
    
    /**
     * Returns the customer's phone number.
     * @return The phone number.
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    
    /**
     * Returns the bike details in a <code>BikeDTO</code> object.
     * @return Customer's bike details.
     */
    public BikeDTO getBikeDetails()
    {
        return bikeDetails;
    }
       
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("name: " + name + ", ");
        builder.append("\n");
        builder.append("email: " + email + ", ");
        builder.append("\n");
        builder.append("phone number: " + phoneNumber + ", ");
        builder.append("\n");
        builder.append("bike details: " + bikeDetails);
        builder.append("\n");
        
        return builder.toString();
    }
    
}