package fr.ecp.IS1220.MyFoodora.Food;
import fr.ecp.IS1220.MyFoodora.Users.Restaurant;

public class HalfMeal extends Meal{
	private HalfMealType halfmealtype;

	public HalfMeal(Restaurant restaurant, HalfMealType halfmealtype, Item[] mealItems) {
		super(restaurant, MealType.Half_meal, mealItems);
		this.halfmealtype = halfmealtype;
	}

	public HalfMealType getHalfmealtype() {
		return halfmealtype;
	}	
}
