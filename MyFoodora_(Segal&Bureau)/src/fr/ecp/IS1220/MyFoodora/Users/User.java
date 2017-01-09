package fr.ecp.IS1220.MyFoodora.Users;

import fr.ecp.IS1220.MyFoodora.System.Password;
import java.io.*;

public abstract class User implements Serializable{

	private static final long serialVersionUID = 883693267585561128L;
	
	private String name;
	private String username;
	private Password hashedPassword;
	private static int counter = 0;
	private int id;
	private boolean status ;
	
	public User(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.status = true;
		this.hashedPassword = new Password(password) ;
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

	public Password getHashedPassword() {
		return hashedPassword;
	}

	public int getId() {
		return id;
	}

	public void activate(){status = true;}

	public void disactivate(){status = false;}

	public boolean getStatus(){return status;}

	
	
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
		if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", username=" + username + ", status=" + status + "]";
	}
	
	
}
