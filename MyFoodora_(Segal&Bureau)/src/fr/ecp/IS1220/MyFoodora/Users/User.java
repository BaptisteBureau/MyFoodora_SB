package fr.ecp.IS1220.MyFoodora.Users;

public abstract class User {
	private String name;
	private static int counter = 0;
	private int id;
	
	public User(String name) {
		super();
		this.name = name;
		this.id = counter;
		counter ++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

}
