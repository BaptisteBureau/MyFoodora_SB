package fr.ecp.IS1220.MyFoodora.Exceptions;

public class MealAlreadyMealOfTheWeekException extends Exception{
	
	public MealAlreadyMealOfTheWeekException(){
		super();
		System.out.println("This meal is already a meal of the week.");
	}
}

