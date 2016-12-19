package fr.ecp.IS1220.MyFoodora.Users;

import java.util.ArrayList;

import fr.ecp.IS1220.MyFoodora.System.MyFoodoraSystem;

public class Manager extends User{
	private String surname;
	private String username;
	
	
	public Manager(String name, String surname, String username) {
		super(name);
		this.surname = surname;
		this.username = username;
	}


	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	
	@Override
	public String toString() {
		return "Manager [name=" + this.getName() + ", surname=" + surname + ", username=" + username + "]";
	}


	//Add any kind of user (restaurant, customers and/or couriers) to the system
	public void addUser(User u){
		if (u instanceof Manager){
			ArrayList<Manager> newList = MyFoodoraSystem.getInstance().getRegisteredManagers();
			newList.add((Manager) u);
			MyFoodoraSystem.getInstance().setRegisteredManagers(newList);
		}
		if (u instanceof Restaurant){
			ArrayList<Restaurant> newList = MyFoodoraSystem.getInstance().getRegisteredRestaurants();
			newList.add((Restaurant) u);
			MyFoodoraSystem.getInstance().setRegisteredRestaurants(newList);
		}
		if (u instanceof Customer){
			ArrayList<Customer> newList = MyFoodoraSystem.getInstance().getRegisteredCustomers();
			newList.add((Customer) u);
			MyFoodoraSystem.getInstance().setRegisteredCustomers(newList);
		}
		if (u instanceof Courier){
			ArrayList<Courier> newList = MyFoodoraSystem.getInstance().getRegisteredCouriers();
			newList.add((Courier) u);
			MyFoodoraSystem.getInstance().setRegisteredCouriers(newList);
		}
	}
	
	
	//Remove any kind of user (restaurant, customers and/or couriers) from the system
	public void removeUser(User u){
		if (u instanceof Restaurant){
			ArrayList<Restaurant> newList = MyFoodoraSystem.getInstance().getRegisteredRestaurants();
			newList.remove((Restaurant) u);
			MyFoodoraSystem.getInstance().setRegisteredRestaurants(newList);
		}
		if (u instanceof Customer){
			ArrayList<Customer> newList = MyFoodoraSystem.getInstance().getRegisteredCustomers();
			newList.remove((Customer) u);
			MyFoodoraSystem.getInstance().setRegisteredCustomers(newList);
		}
		if (u instanceof Courier){
			ArrayList<Courier> newList = MyFoodoraSystem.getInstance().getRegisteredCouriers();
			newList.remove((Courier) u);
			MyFoodoraSystem.getInstance().setRegisteredCouriers(newList);
		}
		else {
			System.out.println("You can't remove this User");
		}
	}
	
	
	//Activate any kind of user (restaurant, customers and/or couriers) of the system
	public void activateUser(User u){
		
	}
	
	
	//Deactivate any kind of user (restaurant, customers and/or couriers) of the system
	public void deactivateUser(User u){
		
	}
	
	
	//Changing the service-fee percentage
	public void changeServiceFee(double serviceFee){
		MyFoodoraSystem.getInstance().setServiceFee(serviceFee);
	}
	
	
	//Changing the markup percentage
	public void changeMarkupPercentage(double markupPercentage){
		MyFoodoraSystem.getInstance().setMarkupPercentage(markupPercentage);
	}
	
	
	//Changing the delivery cost
	public void changeDeliveryCost(double deliveryCost){
		MyFoodoraSystem.getInstance().setDeliveryCost(deliveryCost);
	}
	
	
	//Computing the total income over a time period
	public double computeTotalIncome(){
		double res = 0;
		return res;
	}
	
	
	//Computing the total profit over a time period
	public double computeTotalProfit(){
		double res = 0;
		return res;
	}
	
	
	/*
	 * Calculate the average income per customer (total income divided by the number of customers 
	 * who placed at least one order) 
	 */
	public double averageIncomePerCustomer(){
		double res = computeTotalIncome();
		res = res/MyFoodoraSystem.getInstance().getRegisteredCustomers().size();
		return res;
	}
	
	
	//Determine the service-fee to meet a target profile
	public double calculateServiceFee_targetProfile(){
		double res = 0.0;
		return res;
	}
	
	
	//Determine the markup percentage to meet a target profile
	public double calculateMarkupPercentage_targetProfile(){
		double res = 0.0;
		return res;
	}
	
	
	//Determine the delivery cost to meet a target profile
	public double calculateDeliveryCost_targetProfile(){
		double res = 0.0;
		return res;
	}
	
	
	//Returns the id of the least selling restaurant
	public int LeastSellingRestaurant(){
		int res = 0;
		return res;
	}
	
	
	//Returns the id of the most selling restaurant
	public int MostSellingRestaurant(){
		int res = 0;
		return res;
	}
	
	
	//Returns the id of the most active courier of the fleet
	public int MostActiveCourier(){
		int res = 0;
		return res;
	}
	
	
	//Returns the id of the least active courier of the fleet
	public int LeastActiveCourier(){
		int res = 0;
		return res;
	}
	
	
	/*
	 * Setting the current delivery-policy to determine which courier is assigned to deliver 
	 * an order placed by a customer
	 */
	public void setDeliveryPolicy(){
		
	}
}
