package fr.ecp.IS1220.MyFoodora.Food;

import fr.ecp.IS1220.MyFoodora.Exceptions.MealNotValid;

public class MealFactory {
	private Item starter;
	private Item mainDish;
	private Item dessert;
	private MealType type;
	
	public MealFactory(){}
	
	public void createMeal(Item starter, Item mainDish, Item dessert) throws MealNotValid{
		this.starter = starter;
		this.mainDish = mainDish;
		this.dessert = dessert;
		
		if(this.starter == null && this.dessert != null && this.mainDish != null)
			this.type = MealType.MainDish_and_Dessert;
		else if(this.starter != null && this.dessert == null && this.mainDish != null)
			this.type = MealType.Starter_and_MainDish;
		else if(this.starter != null && this.dessert != null && this.mainDish != null)
			this.type = MealType.Full_meal;
		else
			throw new MealNotValid();
		
		new Meal(this.type);
	}
}
