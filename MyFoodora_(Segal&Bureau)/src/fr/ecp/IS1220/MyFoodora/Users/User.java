package fr.ecp.IS1220.MyFoodora.Users;

public abstract class User {
	private String name;
	private String username;
	private double hashedPassword;
	private static int counter = 0;
	private int id;
	
	public User(String name, String username, double hashedPassword) {
		super();
		this.name = name;
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.id = counter;
		counter ++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(double hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username.equals(other.username))
			return false;
		return true;
	}

	
}
