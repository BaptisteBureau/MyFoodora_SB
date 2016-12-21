package fr.ecp.IS1220.MyFoodora.Users;

import java.util.ArrayList;
import java.util.Calendar;

import fr.ecp.IS1220.MyFoodora.Policies.Delivery;
import fr.ecp.IS1220.MyFoodora.System.*;

public class Manager extends User{
	private String surname;
	private String username;
	
	
	public Manager(String name, String surname, String username, double hashedPassword) {
		super(name, username, hashedPassword);
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
	public void addUser(User u) {
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
	public double computeTotalIncome(Calendar date){
		double res = 0;
		MyFoodoraSystem system = MyFoodoraSystem.getInstance();
		int i = system.getDeliveredOrders().size() - 1;
		while (date.compareTo(system.getDeliveredOrders().get(i).getOrderdate()) <= 0){
			i --;
			res += system.getDeliveredOrders().get(i).getOrderprice();
		}
		return res;
	}
	
	
	//Computing the total profit over a time period
	public double computeTotalProfit(Calendar date){
		double res = 0;
		MyFoodoraSystem system = MyFoodoraSystem.getInstance();
		int i = system.getDeliveredOrders().size() - 1;
		while (date.compareTo(system.getDeliveredOrders().get(i).getOrderdate()) <= 0){
			i --;
			Order order = system.getDeliveredOrders().get(i);
			res += (order.getOrderprice() - order.getServiceFee()) * order.getMarkupPercentage();
			res += order.getServiceFee() - order.getDeliveryCost();
		}
		return res;
	}
	
	
	/*
	 * Calculate the average income per customer (total income divided by the number of customers 
	 * who placed at least one order) 
	 */
	public double averageIncomePerCustomer(){
		double res = computeTotalIncome(MyFoodoraSystem.getInstance().getStartingDate());
		res = res/MyFoodoraSystem.getInstance().getRegisteredCustomers().size();
		return res;
	}
	
	
	//Determine the service-fee to meet a target profit
	public double calculateServiceFee_targetProfit(double targetProfit, double markupPercentage, double deliveryCost) 
			throws ArithmeticException {
		double serviceFee = 0;
		Calendar startingDate = Calendar.getInstance();
		
		//Create a date one month before
		startingDate.set(startingDate.get(Calendar.YEAR), startingDate.get(Calendar.MONTH) - 1, startingDate.get(Calendar.DAY_OF_MONTH));
		MyFoodoraSystem system = MyFoodoraSystem.getInstance();
		
		//Price of all food delivered during the last month
		double priceOfFoodDelivered = 0;
		
		//Number of order delivered during the last month
		double numberOfOrder = 0;
		int i = system.getDeliveredOrders().size() - 1;
		while (startingDate.compareTo(system.getDeliveredOrders().get(i).getOrderdate()) <= 0){
			i --;
			Order order = system.getDeliveredOrders().get(i);
			priceOfFoodDelivered += order.getOrderprice() - order.getServiceFee();
			numberOfOrder ++;
		}
		if (numberOfOrder == 0){
			System.out.println("You can't use this method as there were no order made during the last month.");
			throw new ArithmeticException();
		}
		else {
			serviceFee += deliveryCost + (targetProfit - priceOfFoodDelivered*markupPercentage)/numberOfOrder;
		}
		return serviceFee;
	}
	
	
	//Determine the markup percentage to meet a target profit
	public double calculateMarkupPercentage_targetProfit(double targetProfit, double serviceFee, double deliveryCost)	
			throws ArithmeticException {
		double markupPercentage = 0;
		Calendar startingDate = Calendar.getInstance();
		
		//Create a date one month before
		startingDate.set(startingDate.get(Calendar.YEAR), startingDate.get(Calendar.MONTH) - 1, startingDate.get(Calendar.DAY_OF_MONTH));
		MyFoodoraSystem system = MyFoodoraSystem.getInstance();
		
		//Price of all food delivered during the last month
		double priceOfFoodDelivered = 0;
		
		//Number of order delivered during the last month
		double numberOfOrder = 0;
		int i = system.getDeliveredOrders().size() - 1;
		while (startingDate.compareTo(system.getDeliveredOrders().get(i).getOrderdate()) <= 0){
			i --;
			Order order = system.getDeliveredOrders().get(i);
			priceOfFoodDelivered += order.getOrderprice() - order.getServiceFee();
			numberOfOrder ++;
		}
		if (numberOfOrder == 0){
			System.out.println("You can't use this method as there were no order made during the last month.");
			throw new ArithmeticException();
		}
		else {
			markupPercentage += (targetProfit - numberOfOrder*(serviceFee - deliveryCost))/priceOfFoodDelivered;
		}
		return markupPercentage;
	}
	
	
	//Determine the delivery cost to meet a target profit
	public double calculateDeliveryCost_targetProfit(double targetProfit, double serviceFee, double markupPercentage)	
			throws ArithmeticException {
		double deliveryCost = 0;
		Calendar startingDate = Calendar.getInstance();
		
		//Create a date one month before
		startingDate.set(startingDate.get(Calendar.YEAR), startingDate.get(Calendar.MONTH) - 1, startingDate.get(Calendar.DAY_OF_MONTH));
		MyFoodoraSystem system = MyFoodoraSystem.getInstance();
		
		//Price of all food delivered during the last month
		double priceOfFoodDelivered = 0;
		
		//Number of order delivered during the last month
		double numberOfOrder = 0;
		int i = system.getDeliveredOrders().size() - 1;
		while (startingDate.compareTo(system.getDeliveredOrders().get(i).getOrderdate()) <= 0){
			i --;
			Order order = system.getDeliveredOrders().get(i);
			priceOfFoodDelivered += order.getOrderprice() - order.getServiceFee();
			numberOfOrder ++;
		}
		if (numberOfOrder == 0){
			System.out.println("You can't use this method as there were no order made during the last month.");
			throw new ArithmeticException();
		}
		else {
			deliveryCost += serviceFee - (targetProfit - priceOfFoodDelivered*markupPercentage)/numberOfOrder;
		}
		return deliveryCost;
	}
	
	
	//Returns the least selling restaurant
	public Restaurant LeastSellingRestaurant(){
		ArrayList<Restaurant> restaurants= MyFoodoraSystem.getInstance().getRegisteredRestaurants();
		Restaurant leastSellingRestaurant = null;
		if (restaurants.isEmpty())
			System.out.println("There is no restaurant in the system.");
		else {
			leastSellingRestaurant = restaurants.get(0);
			for (Restaurant restaurant : restaurants){
				if (restaurant.getCounter() < leastSellingRestaurant.getCounter()){
					leastSellingRestaurant = restaurant;
				}
			}
		}
		return leastSellingRestaurant;
	}
	
	
	//Returns the most selling restaurant
	public Restaurant MostSellingRestaurant(){
		ArrayList<Restaurant> restaurants= MyFoodoraSystem.getInstance().getRegisteredRestaurants();
		Restaurant mostSellingRestaurant = null;
		if (restaurants.isEmpty())
			System.out.println("There is no restaurant in the system.");
		else {
			mostSellingRestaurant = restaurants.get(0);
			for (Restaurant restaurant : restaurants){
				if (restaurant.getCounter() > mostSellingRestaurant.getCounter()){
					mostSellingRestaurant = restaurant;
				}
			}
		}
		return mostSellingRestaurant;
	}
	
	
	//Returns the most active courier of the fleet
	public Courier MostActiveCourier(){
		ArrayList<Courier> couriers= MyFoodoraSystem.getInstance().getRegisteredCouriers();
		Courier mostSellingCourier = null;
		if (couriers.isEmpty())
			System.out.println("There is no courier in the system.");
		else {
			mostSellingCourier = couriers.get(0);
			for (Courier courier : couriers){
				if (courier.getDeliveries().size() > mostSellingCourier.getDeliveries().size()){
					mostSellingCourier = courier;
				}
			}
		}
		return mostSellingCourier;
	}
	
	
	//Returns the least active courier of the fleet
	public Courier LeastActiveCourier(){
		ArrayList<Courier> couriers= MyFoodoraSystem.getInstance().getRegisteredCouriers();
		Courier leastSellingCourier = null;
		if (couriers.isEmpty())
			System.out.println("There is no courier in the system.");
		else {
			leastSellingCourier = couriers.get(0);
			for (Courier courier : couriers){
				if (courier.getDeliveries().size() < leastSellingCourier.getDeliveries().size()){
					leastSellingCourier = courier;
				}
			}
		}
		return leastSellingCourier;
	}
	
	
	/*
	 * Setting the current delivery-policy to determine which courier is assigned to deliver 
	 * an order placed by a customer
	 */
	public void setDeliveryPolicy(Delivery deliverypolicy){
		MyFoodoraSystem.getInstance().setDeliverypolicy(deliverypolicy);
	}
}
