package fr.ecp.IS1220.MyFoodora.Food;

import java.util.Arrays;

import fr.ecp.IS1220.MyFoodora.Users.Restaurant;

public class Meal extends Food{
	private MealType mealtype;
	private double mealItemsPrice;
	private Item[] mealItems;
	private int counter;
	

	public Meal(Restaurant restaurant, String name, MealType mealtype, Item[] mealItems) {
		super(restaurant, name);
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
	
	public int getCounter() {
		return counter;
	}

	public void incrementCounter(){
		this.counter+=1;
		this.getRestaurant().incrementCounter();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(mealItems);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (!Arrays.equals(mealItems, other.mealItems))
			return false;
		return true;
	}



}
