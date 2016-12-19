package fr.ecp.IS1220.MyFoodora.Users;

import java.util.ArrayList;

import fr.ecp.IS1220.MyFoodora.Food.*;
import fr.ecp.IS1220.MyFoodora.Policies.*;
import fr.ecp.IS1220.MyFoodora.System.*;

public class Customer extends User{
	private String surname;
	private double[] adress = {0.0, 0.0};
	private String email;
	private long phone;
	private String username;
	private boolean acceptNotifications = false;
	private ArrayList<Order> myorders;
	private FidelityCard card = FidelityCard.BasicCard;
	public PointFidelityCard pointCard = new PointFidelityCard();
	public LotteryFidelityCard lotteryCard = null;
	

	public Customer(String name, String surname, double[] adress, String email, long phone, String username) {
		super(name);
		this.surname = surname;
		this.adress = adress;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.myorders = new ArrayList<Order>();
	}

	

	public FidelityCard getCard() {
		return card;
	}



	public void setCard(FidelityCard card) {
		
		/*
		 * If the user had a fidelity card and decides to change, the card gets removed 
		 * from the system
		 */
		if(this.card == FidelityCard.LotteryCard){
			MyFoodoraSystem system = MyFoodoraSystem.getInstance();
			ArrayList<LotteryFidelityCard> old = system.getActivedLotteryCards();
			old.remove(this.card);
			system.setActivedLotteryCards(old);
		}
		this.card = card;
		
		//If the user changes to fidelity card, it gets registered for the lottery
		if(card == FidelityCard.LotteryCard)
			lotteryCard = new LotteryFidelityCard();
	}



	public boolean isAcceptNotifications() {
		return acceptNotifications;
	}


	public void setAcceptNotifications(boolean acceptNotifications) {
		this.acceptNotifications = acceptNotifications;
	}


	public String getSurname() {
		return surname;
	}

	public ArrayList<Order> getMyorders() {
		return myorders;
	}


	public void setMyorders(ArrayList<Order> myorders) {
		this.myorders = myorders;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}

	public double[] getAdress() {
		return adress;
	}

	public void setAdress(double[] adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	//Add an order to the Customer's list of orders
	public void addOrderToCustomer(Order order){
		ArrayList<Order> newlist = this.getMyorders();
		newlist.add(order);
		this.setMyorders(newlist);
	}
	
	//Order items à-la-carte
	public void orderItemsALaCarte(ArrayList<Food> itemsordered){
		Order order = new Order(itemsordered, this);
		this.addOrderToCustomer(order);
	}
	
	//Order meals
	public void orderMeal(ArrayList<Food> mealsordered){
		Order order = new Order(mealsordered, this);
		this.addOrderToCustomer(order);
	}
	
	
	//Register to a fidelity card plan
	public void registerToFidelityCard(){
		
	}
	
	
	//Unsubscribe from a fidelity card plan
	public void unsubscribeFromFidelityCard(){
		
	}
	
	
	/*
	 * Access the information related to their account: including history of orders, 
	 * and points acquired with a fidelity program
	 */
	public String accessInformations(){
		String info = this.myorders.toString();
		return info;
	}
	
	
	//Give consensus to be notified whenever a new special offer is set by any restaurant
	public void giveConsensusForNotifications(){
		this.setAcceptNotifications(true);
	}
	
	
	//Remove consensus to be notified whenever a new special offer is set by any restaurant
	public void removeConsensusForNotifications(){
		this.setAcceptNotifications(false);
	}

}
