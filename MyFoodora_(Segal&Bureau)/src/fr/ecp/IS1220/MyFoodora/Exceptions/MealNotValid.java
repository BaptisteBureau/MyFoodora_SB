package fr.ecp.IS1220.MyFoodora.Exceptions;

public class MealNotValid extends Exception{
	public MealNotValid(){
		System.out.println("The meal you are trying to create is not valid. It should be composed"
				+ "of a main dish, and either a dessert or a starter, or both.");
	}
}
