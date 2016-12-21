package fr.ecp.IS1220.MyFoodora.Food;

import fr.ecp.IS1220.MyFoodora.Users.Restaurant;

public abstract class Food {
	private Restaurant restaurant;
	private String name;
	
	public Food(Restaurant restaurant, String name) {
		this.restaurant = restaurant;
		this.name = name;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
		return result;
	}

	
	//To avoid copies in menus and other food lists (inside a restaurant)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (!name.equals(other.name))
			return false;
		if (!restaurant.equals(other.restaurant))
			return false;
		return true;
	}

	
}
