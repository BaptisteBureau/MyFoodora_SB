package fr.ecp.IS1220.MyFoodora.Exceptions;

@SuppressWarnings("serial")
public class CourierNotRegisteredException extends Exception {
	
    public CourierNotRegisteredException(){
        System.out.println("The courier is not registered in the system.");
  }
    
}
