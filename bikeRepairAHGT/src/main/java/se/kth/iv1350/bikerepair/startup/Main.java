package se.kth.iv1350.bikerepair.startup;

import java.io.IOException;
import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;
import se.kth.iv1350.bikerepair.util.LogHandler;
import se.kth.iv1350.bikerepair.view.View;

/**
 * Initiates the startup of the application. Runs all the system operations
 * in the static method main.
 */
public class Main 
{
   
  /**
   * Starts the application.
   * @param args No argument is needed for the application.
   */
  public static void main(String[] args)
  {
      try
      {   
          RegistryCreator regCreator = new RegistryCreator();
          Printer printer = new Printer();
          LogHandler logger = new LogHandler();
          Controller contr = new Controller(regCreator, printer);
          View view = new View(contr, logger);
          view.exampleAllSystemOperations();
      }
      catch(IOException exception)
      {
          System.out.println("The application cannot start");
          exception.printStackTrace();
      }
  }
}