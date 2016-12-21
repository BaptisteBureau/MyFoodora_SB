package fr.ecp.IS1220.MyFoodora.Exceptions;

public class MealAlreadyInMenuException extends Exception {
	
	public MealAlreadyInMenuException() {
		super();
		System.out.println("This meal is already in your menu.");
	}
}
