package fr.ecp.IS1220.MyFoodora.Exceptions;

public class ItemNotInMenuException extends Exception{

	public ItemNotInMenuException(){
		System.out.println("The item you are trying to supress is not in the menu. "
				+ "Try again if you want to delete an other item.");
	}
	
}
