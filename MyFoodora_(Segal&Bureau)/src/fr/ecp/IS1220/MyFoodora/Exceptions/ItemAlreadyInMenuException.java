package fr.ecp.IS1220.MyFoodora.Exceptions;

@SuppressWarnings("serial")
public class ItemAlreadyInMenuException extends Exception {

	public ItemAlreadyInMenuException() {
		super();
		System.out.println("This item is already in the menu of your restaurant.");
		System.out.println("Please check that you use different names for your items and try again.");
	}

}
