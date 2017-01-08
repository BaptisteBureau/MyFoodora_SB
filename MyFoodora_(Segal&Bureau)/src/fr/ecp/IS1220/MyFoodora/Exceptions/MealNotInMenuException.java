package fr.ecp.IS1220.MyFoodora.Exceptions;

@SuppressWarnings("serial")
public class MealNotInMenuException extends Exception {
	
	public MealNotInMenuException() {
		super();
		System.out.println("This meal was not in your menu before.");
	}
}
