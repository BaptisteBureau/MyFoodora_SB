package fr.ecp.IS1220.MyFoodora.Food;

import fr.ecp.IS1220.MyFoodora.Users.Restaurant;

public abstract class Item extends Food{
	private double itemprice;
	private ItemType itemtype;
	private RegimeType regimetype;
	private boolean gluten_free;
	private int counter = 0;
	

	public Item(Restaurant restaurant, String name, ItemType itemtype, double itemprice, RegimeType regimetype, boolean gluten_free) {
		super(restaurant, name);
		this.itemtype = itemtype;
		this.regimetype = regimetype;
		this.gluten_free = gluten_free;
		this.itemprice = itemprice;
	}
	
	public Item(Restaurant restaurant, String name, ItemType itemtype, double itemprice, RegimeType regimetype) {
		super(restaurant, name);
		this.itemtype = itemtype;
		this.itemprice = itemprice;
		this.regimetype = regimetype;
		this.gluten_free = false;
	}
	
	public Item(Restaurant restaurant, String name, ItemType itemtype, double itemprice, boolean gluten_free) {
		super(restaurant, name);
		this.itemtype = itemtype;
		this.itemprice = itemprice;
		this.regimetype = RegimeType.Standard;
		this.gluten_free = gluten_free;
	}
	
	public Item(Restaurant restaurant, String name, ItemType itemtype, double itemprice) {
		super(restaurant,name);
		this.itemtype = itemtype;
		this.itemprice = itemprice;
		this.regimetype = RegimeType.Standard;
		this.gluten_free = false;
	}


	public double getItemprice() {
		return itemprice;
	}

	public void setItemprice(double itemprice) {
		this.itemprice = itemprice;
	}

	public ItemType getItemtype() {
		return itemtype;
	}

	public void setItemtype(ItemType itemtype) {
		this.itemtype = itemtype;
	}

	public RegimeType getRegimetype() {
		return regimetype;
	}

	public void setRegimetype(RegimeType regimetype) {
		this.regimetype = regimetype;
	}

	public boolean isGluten_free() {
		return gluten_free;
	}

	public void setGluten_free(boolean gluten_free) {
		this.gluten_free = gluten_free;
	}

	public int getCounter() {
		return counter;
	}

	@Override
	public String toString() {
		return "Item [itemname=" + this.getName() + ", itemtype=" + itemtype + "]";
	}
	
	

	public void incrementCounter(){
		this.counter+=1;
		this.getRestaurant().incrementCounter();
	}
	

}
