package fr.ecp.IS1220.MyFoodora.Food;

import fr.ecp.IS1220.MyFoodora.Users.Restaurant;

public abstract class Meal extends Food{
	private MealType mealtype;
	private double mealItemsPrice;
	private Item[] mealItems;
	

	public Meal(Restaurant restaurant, MealType mealtype, Item[] mealItems) {
		super(restaurant);
		this.mealtype = mealtype;
		this.mealItems = mealItems;
		this.mealItemsPrice = 0;
		for (Item item : mealItems){
			this.mealItemsPrice += item.getItemprice();
		}
	}

	public Item[] getMealItems() {
		return mealItems;
	}

	public void setMealItems(Item[] mealItems) {
		this.mealItems = mealItems;
	}

	public MealType getMealtype() {
		return mealtype;
	}

	public void setMealtype(MealType mealtype) {
		this.mealtype = mealtype;
	}

	public double getMealprice() {
		if (this.getRestaurant().getMealsOfTheWeek().contains(this)){
			return (this.mealItemsPrice*(1-this.getRestaurant().getSpecific_discount_factor()));
		}
		return this.mealItemsPrice*(1-this.getRestaurant().getGeneric_discount_factor());
	}

}
