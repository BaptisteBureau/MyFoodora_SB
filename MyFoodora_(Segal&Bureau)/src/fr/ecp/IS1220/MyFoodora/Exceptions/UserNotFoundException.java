package fr.ecp.IS1220.MyFoodora.Exceptions;

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = -3581564961982834423L;

	public UserNotFoundException() {
		super("This user cannot be found, please try again.");
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
