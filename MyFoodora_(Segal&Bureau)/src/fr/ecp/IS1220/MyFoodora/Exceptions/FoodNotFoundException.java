package fr.ecp.IS1220.MyFoodora.Exceptions;

@SuppressWarnings("serial")
public class FoodNotFoundException extends Exception {

	public FoodNotFoundException() {
		super("This food cannot be found, please try again.");
		// TODO Auto-generated constructor stub
	}

	public FoodNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FoodNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FoodNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
