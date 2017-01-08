package fr.ecp.IS1220.MyFoodora.Exceptions;

@SuppressWarnings("serial")
public class ItemNotInMenuException extends Exception{

	public ItemNotInMenuException(){
		super();
		System.out.println("This item was not in your menu before.");
	}
	
}
