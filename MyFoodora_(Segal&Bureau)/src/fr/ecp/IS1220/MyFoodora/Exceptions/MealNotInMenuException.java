package fr.ecp.IS1220.MyFoodora.Exceptions;

public class MealNotInMenuException extends Exception{
	public void MealNotInMenuException(){
		System.out.println("The meal you are trying to supress is not in the menu. "
				+ "Try again if you want to delete an other meal.");
	}
}
