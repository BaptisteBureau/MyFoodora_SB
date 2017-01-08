package fr.ecp.IS1220.MyFoodora.Exceptions;
 
@SuppressWarnings("serial")
public class CourierAlreadyRegisteredException extends Exception{
      
       public CourierAlreadyRegisteredException(){
             System.out.println("The courier is already registered in the system.");
       }
      
}