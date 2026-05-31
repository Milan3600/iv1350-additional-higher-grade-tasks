package se.kth.iv1350.bikerepair.integration;

/**
 * Contains information about a specific bike.
 *
 */
public class BikeDTO 
{
  private final String brand;
  private final String model;
  private final String serialNumber;
  
  /**
   * Creates an instance.
   * @param brand The brand of the bike.
   * @param model The model of the bike.
   * @param serialNumber The unique serial number of the bike. 
   */
  public BikeDTO(String brand, String model, String serialNumber)
  {
      this.brand = brand;
      this.model = model;
      this.serialNumber = serialNumber;
  }
  
  /**
   * 
   * Creates a custom string that contains the entire bike information.
   * @return String with bike info.
   */
  @Override
  public String toString()
  {
      StringBuilder builder = new StringBuilder();
      builder.append("brand: " + brand + ", ");
      builder.append("model: " + model + ", ");
      builder.append("serial number: " + serialNumber);
      
      return builder.toString();
  }
  
  /**
   * Returns the brand of the bike.
   * @return The brand.
   */
  public String getBrand()
  {
      return brand;
  }
  
  /**
   * Returns the model of the bike.
   * @return The model.
   */
  public String getModel()
  {
      return model;
  }
  
  /**
   * Returns the serial number of the bike.
   * @return The serial number.
   */
  public String getSerialNumber()
  {
      return serialNumber;
  }
}
