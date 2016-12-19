package fr.ecp.IS1220.MyFoodora.Food;

import fr.ecp.IS1220.MyFoodora.Users.Restaurant;

public class Dessert extends Item{

	public Dessert(Restaurant restaurant, String itemname, ItemType itemtype, double itemprice, boolean gluten_free) {
		super(restaurant, itemname, itemtype, itemprice, gluten_free);
		// TODO Auto-generated constructor stub
	}

	public Dessert(Restaurant restaurant, String itemname, ItemType itemtype, double itemprice, RegimeType regimetype,
			boolean gluten_free) {
		super(restaurant, itemname, itemtype, itemprice, regimetype, gluten_free);
		// TODO Auto-generated constructor stub
	}

	public Dessert(Restaurant restaurant, String itemname, ItemType itemtype, double itemprice, RegimeType regimetype) {
		super(restaurant, itemname, itemtype, itemprice, regimetype);
		// TODO Auto-generated constructor stub
	}

	public Dessert(Restaurant restaurant, String itemname, ItemType itemtype, double itemprice) {
		super(restaurant, itemname, itemtype, itemprice);
		// TODO Auto-generated constructor stub
	}


}
