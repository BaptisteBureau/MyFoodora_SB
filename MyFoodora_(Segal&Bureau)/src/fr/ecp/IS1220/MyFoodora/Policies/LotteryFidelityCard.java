package fr.ecp.IS1220.MyFoodora.Policies;

import fr.ecp.IS1220.MyFoodora.Users.Customer;
import java.io.*;

public class LotteryFidelityCard implements Serializable {
	
	private static final long serialVersionUID = 9059018478996485980L;
	
	private int id;
	private static int counter = 1;
	private Customer owner;
	
	public LotteryFidelityCard(Customer owner){
		this.id = counter;
		counter++;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public Customer getOwner() {
		return owner;
	}
}
