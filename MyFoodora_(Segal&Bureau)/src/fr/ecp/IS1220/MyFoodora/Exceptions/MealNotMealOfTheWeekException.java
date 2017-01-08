package fr.ecp.IS1220.MyFoodora.Exceptions;

@SuppressWarnings("serial")
public class MealNotMealOfTheWeekException extends Exception{
	
	public MealNotMealOfTheWeekException(){
		super();
		System.out.println("This meal was not a meal of the week.");
	}
}
