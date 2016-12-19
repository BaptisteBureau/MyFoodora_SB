package fr.ecp.IS1220.MyFoodora.Exceptions;
 
public class CourierAlreadyRegisteredException extends Exception{
      
       public CourierAlreadyRegisteredException(){
             System.out.println("The courier is already registered in the system.");
       }
      
}