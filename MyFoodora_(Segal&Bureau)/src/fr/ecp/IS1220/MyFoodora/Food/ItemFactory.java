package fr.ecp.IS1220.MyFoodora.Food;

public class ItemFactory {
	private ItemType type;
	
	public ItemFactory(){}
	
	public void createItem(ItemType type){
		this.type = type;
		
		new Item(this.type = type);
	}
}
