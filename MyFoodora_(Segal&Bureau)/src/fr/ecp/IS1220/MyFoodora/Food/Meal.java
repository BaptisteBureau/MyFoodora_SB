package fr.ecp.IS1220.MyFoodora.Food;

import java.util.ArrayList;

import fr.ecp.IS1220.MyFoodora.Users.Restaurant;

public class Meal extends Food{
	
	private static final long serialVersionUID = 8577739057974583512L;
	
	private MealType mealtype;
	private double mealItemsPrice;
	private ArrayList<Item> mealItems;
	private int counter;
	private RegimeType regimeType;
	private boolean glutenFree;
	

	public Meal(Restaurant restaurant, String name, MealType mealtype, ArrayList<Item> mealItems, RegimeType regimeType, boolean glutenFree) {
		super(restaurant, name);
		this.mealtype = mealtype;
		this.mealItems = mealItems;
		this.mealItemsPrice = 0;
		for (Item item : mealItems){
			if(item != null){
				this.mealItemsPrice += item.getItemprice();
			}
		}
		this.regimeType = regimeType;
		this.glutenFree = glutenFree;
	}

	public ArrayList<Item> getMealItems() {
		return mealItems;
	}
	
	public ArrayList<String> getItemNames() {
		ArrayList<String> itemNames = new ArrayList<String>();
		for (Item item : this.mealItems){
			itemNames.add(item.getName());
		}
		return itemNames;
	}

	public void setMealItems(ArrayList<Item> mealItems) {
		this.mealItems = mealItems;
	}

	public MealType getMealtype() {
		return mealtype;
	}

	public void setMealtype(MealType mealtype) {
		this.mealtype = mealtype;
	}

	
	public RegimeType getRegimeType() {
		return regimeType;
	}

	public boolean isGlutenFree() {
		return glutenFree;
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
		result = prime * result + ((mealItems == null) ? 0 : mealItems.hashCode());
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
		if (mealItems == null) {
			if (other.mealItems != null)
				return false;
		} else if (!mealItems.equals(other.mealItems))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Meal [mealname = " + this.getName() + ", mealItems = " + this.getItemNames() + ", mealtype = " + this.getMealtype() + ", mealprice = " + this.getMealprice() +  "]";
	}



}
