package fr.ecp.IS1220.MyFoodora.System;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import java.io.*;

import fr.ecp.IS1220.MyFoodora.Exceptions.UserNotFoundException;
import fr.ecp.IS1220.MyFoodora.Food.*;
import fr.ecp.IS1220.MyFoodora.Policies.*;
import fr.ecp.IS1220.MyFoodora.Users.*;

public class MyFoodoraSystem implements Serializable{
	
	private static final long serialVersionUID = -5467532064868242643L;

	/**
	 * D
	 */
	private Calendar startDate;
	
	/**
	 * Array Lists of all registered users
	 */
	private ArrayList<Manager> registeredManagers = new ArrayList<Manager>();
	private ArrayList<Restaurant> registeredRestaurants = new ArrayList<Restaurant>();
	private ArrayList<Customer> registeredCustomers = new ArrayList<Customer>();
	private ArrayList<Courier> registeredCouriers = new ArrayList<Courier>();
	
	/**
	 * Array Lists of orders to deliver and delivered orders
	 */
	private ArrayList<Order> ordersToDeliver = new ArrayList<Order>();
	private ArrayList<Order> deliveredOrders = new ArrayList<Order>();
	
	/**
	 * Array Lists of lottery fidelity cards to decide the winner
	 */
	private ArrayList<LotteryFidelityCard> activedLotteryCards = new ArrayList<LotteryFidelityCard>();
	private Calendar lastLotteryDate = new GregorianCalendar();
	private Customer winner = null;
	
	/**
	 * Profit-related information
	 */
	private double serviceFee;
	private double markupPercentage;
	private double deliveryCost;
	
	/**
	 * Policies
	 */
	private Delivery deliverypolicy;
	private ShippedOrder shippolicy;
	private TargetProfit targetprofit;
	
	/**
	 * Live informations
	 */
	private User currentUser = null;
	private Restaurant currentRestaurant = null;
	private ArrayList<Food> currentOrder = new ArrayList<Food>();
	
	/**
	 * The constructor of the system is private
	 */
	private MyFoodoraSystem(){
		this.startDate = GregorianCalendar.getInstance();
		Manager firstManager = new Manager("ceo","ceo","123456");
		this.registeredManagers.add(firstManager);
	}
	
	/**
	 * Here we create a unique instance of the system
	 */
	private static MyFoodoraSystem uniqueInstance = new MyFoodoraSystem();
	
	/**
	 * This method permit to access to our system from all the classes of the project
	 * @return the unique MyFoodora System
	 */
	public static MyFoodoraSystem getInstance(){return uniqueInstance;}
	
	/**
	 * This method permit to reset the MyFoodora System
	 */
	public static void startFromNothing(){uniqueInstance = new MyFoodoraSystem();}
	
	
	/**
	 * All Getters and Setters
	 */
	public Calendar getStartDate() {
		return startDate;
	}
	
	public ArrayList<Manager> getRegisteredManagers() {
		return registeredManagers;
	}
	public void setRegisteredManagers(ArrayList<Manager> registeredManagers) {
		this.registeredManagers = registeredManagers;
	}
	public ArrayList<Restaurant> getRegisteredRestaurants() {
		return registeredRestaurants;
	}
	public void setRegisteredRestaurants(ArrayList<Restaurant> registeredRestaurants) {
		this.registeredRestaurants = registeredRestaurants;
	}
	public ArrayList<Customer> getRegisteredCustomers() {
		return registeredCustomers;
	}
	public void setRegisteredCustomers(ArrayList<Customer> registeredCustomers) {
		this.registeredCustomers = registeredCustomers;
	}
	public ArrayList<Courier> getRegisteredCouriers() {
		return registeredCouriers;
	}
	public void setRegisteredCouriers(ArrayList<Courier> registeredCourier) {
		this.registeredCouriers = registeredCourier;
	}
	
	/**
	 * @return all users of myFoodora
	 */
	public ArrayList<User> getAllRegisteredUsers() {
		ArrayList<User> allUsers = new ArrayList<User>();
		for (Manager manager : this.registeredManagers){
			allUsers.add((User)manager);
		}
		for (Restaurant restaurant : this.registeredRestaurants){
			allUsers.add((User)restaurant);
		}
		for (Courier courier : this.registeredCouriers){
			allUsers.add((User)courier);
		}
		for (Customer customer : this.registeredCustomers){
			allUsers.add((User)customer);
		}
		return allUsers;
	}
	
	
	public ArrayList<Order> getOrdersToDeliver() {
		return ordersToDeliver;
	}
	public void setOrdersToDeliver(ArrayList<Order> ordersToDeliver) {
		this.ordersToDeliver = ordersToDeliver;
	}
	public ArrayList<Order> getDeliveredOrders() {
		return deliveredOrders;
	}
	public void setDeliveredOrders(ArrayList<Order> deliveredOrders) {
		this.deliveredOrders = deliveredOrders;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public double getMarkupPercentage() {
		return markupPercentage;
	}
	public void setMarkupPercentage(double markupPercentage) {
		this.markupPercentage = markupPercentage;
	}
	public double getDeliveryCost() {
		return deliveryCost;
	}
	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	
	public Delivery getDeliverypolicy() {
		return deliverypolicy;
	}

	public void setDeliverypolicy(Delivery deliverypolicy) {
		this.deliverypolicy = deliverypolicy;
	}

	public ShippedOrder getShippolicy() {
		return shippolicy;
	}

	public void setShippolicy(ShippedOrder shippolicy) {
		this.shippolicy = shippolicy;
	}
	
	public TargetProfit getTargetprofit() {
		return targetprofit;
	}

	public void setTargetprofit(TargetProfit targetprofit) {
		this.targetprofit = targetprofit;
	}

	public ArrayList<LotteryFidelityCard> getActivedLotteryCards() {
		return activedLotteryCards;
	}

	public void setActivedLotteryCards(ArrayList<LotteryFidelityCard> activedLotteryCards) {
		this.activedLotteryCards = activedLotteryCards;
	}
	
	public Calendar getLastLotteryDate() {
		return lastLotteryDate;
	}

	public User getCurrentUser() {
		return currentUser;
	}
	
	public Restaurant getCurrentRestaurant() {
		return currentRestaurant;
	}
	
	public void setCurrentRestaurant(Restaurant restaurant) {
		this.currentRestaurant = restaurant;
	}
	
	public ArrayList<Food> getCurrentOrder() {
		return currentOrder;
	}
	
	public void setCurrentOrder(ArrayList<Food> order) {
		this.currentOrder = order;
	}
	
	public void addMealToCurrentOrder(Meal meal){
		this.currentOrder.add(meal);
	}

	public void addItemToCurrentOrder(Item item){
		this.currentOrder.add(item);
	}
	
	
	/**
	 * @return the list of all available Couriers in the system
	 */
	public ArrayList<Courier> getAvailableCouriers(){
		ArrayList<Courier> availableCouriers = new ArrayList<Courier>();
		for (Courier courier : registeredCouriers){
			if (courier.getState() == 1){
				availableCouriers.add(courier);
			}
		}
		return availableCouriers;
	}
	

	/**
	 * @param restaurant
	 * @param availableCouriers
	 * @return the nearest available courier from a restaurant
	 */
	public Courier getNearestCourier(Restaurant restaurant, ArrayList<Courier> availableCouriers){
		double shortestdistance = restaurant.getAdress().getDistanceTo(availableCouriers.get(0).getPosition());
		Courier nearestcourier = availableCouriers.get(0);
		for (Courier courier : availableCouriers){
			if (restaurant.getAdress().getDistanceTo(availableCouriers.get(0).getPosition()) < shortestdistance){
				nearestcourier = courier;
			}
		}
		return nearestcourier;
	}
	
	/**
	 * @param availableCouriers
	 * @return the courier with the least number of completed deliveries
	 */
	public Courier getLaziestCourier(ArrayList<Courier> availableCouriers){
		double minNumberOfDeliveries = availableCouriers.get(0).getDeliveries().size();
		Courier laziestCourier = availableCouriers.get(0);
		for (Courier courier : availableCouriers){
			if(courier.getDeliveries().size() < minNumberOfDeliveries){
				laziestCourier = courier;
			}
		}
		return laziestCourier;
	}
	
	/**
	 * Search a courier to deliver an order
	 * @param order
	 */
	public void findCourier(Order order){
		Courier courier = null;
		ArrayList<Courier> availableCouriers = getAvailableCouriers();
		if (deliverypolicy.equals(Delivery.FastestDelivery)){
			courier = this.getNearestCourier(order.getRestaurant(), availableCouriers);
		}

		if (deliverypolicy.equals(Delivery.FairOccupationDelivery)){
			courier = this.getLaziestCourier(availableCouriers);
		}
		courier.setCurrentDelivery(order);
	}
	
	
	/**
	 * Notification of users who gave their consensus of new special offers
	 */
	public void notifyUsersForSpecialOffers(){
		for (Customer customer : this.getRegisteredCustomers()){
			if(customer.isAcceptNotifications()){
				//send a phone notification to customer
			}
		}
	}
	
	
	/**
	 * @return the total income from the very first order
	 */
	public double calculateTotalIncome(){
		double res = 0;
		for (Order order : this.deliveredOrders){
			res += order.getOrderprice();
		}
		return res;
	}
	

	/**
	 * @return the total profit from the very first order
	 */
	public double calculateTotalProfit(){
		double res = 0;
		for (Order order : this.deliveredOrders){
			res += (order.getOrderprice() - order.getServiceFee()) * order.getMarkupPercentage() + order.getServiceFee() - order.getDeliveryCost() ;
		}
		return res;
	}
	
	/**
	 * The lottery doesn't work during the first 24 hours
	 * @return the winner of the lottery
	 */
	public Customer getLotteryWinner(){
		Calendar lotteryDate = Calendar.getInstance();
		Calendar dayBeforeLotteryDate = lotteryDate;
		dayBeforeLotteryDate.set(dayBeforeLotteryDate.get(Calendar.YEAR), dayBeforeLotteryDate.get(Calendar.MONTH), dayBeforeLotteryDate.getMaximum(Calendar.DAY_OF_MONTH) - 1);
		
		//If the last lottery was less than a day ago the winner doesn't change.
		if (dayBeforeLotteryDate.compareTo(lastLotteryDate) > 0){
			Random random = new Random();
			int index = random.nextInt(this.activedLotteryCards.size());
			winner = this.activedLotteryCards.get(index).getOwner();
			lastLotteryDate = lotteryDate;
		}
		
		return winner;
	
	}
	
	
	/**
	 * Permit a user to log in to the system
	 * @param username
	 * @param password
	 */
	public void login(String username, String password){
		if (this.currentUser != null){
			System.out.println("An other user is already logged in.");
			return;
		}
		User user = null;
		for (User users : this.registeredManagers){
			if (users.getUsername().equals(username)){
				user = users;
			}
		}
		for (User users : this.registeredCustomers){
			if (users.getUsername().equals(username)){
				user = users;
			}
		}
		for (User users : this.registeredRestaurants){
			if (users.getUsername().equals(username)){
				user = users;
			}
		}
		for (User users : this.registeredCouriers){
			if (users.getUsername().equals(username)){
				user = users;
			}
		}
		
		if (user == null){
			System.out.println("This username doesn't exist!");
			return;
		}
		
		if (!user.getHashedPassword().equals(new Password(password))){
			System.out.println("Failure : wrong password.");
		} else {
			this.currentUser = user;
			System.out.println("You're logged in!");
		}
	}

	/**
	 * Permit the current user to log out of the system
	 */
	public void logout(){
		this.currentUser = null;
		System.out.println("You have been disconnected!");
	}
	
    /**
     * Find an restaurant with its name
     * @param restaurantName
     * @return the restaurant
     * @throws UserNotFoundException
     */
    public Restaurant findRestaurantWithName(String restaurantName) throws UserNotFoundException{
 	   Restaurant unknownrestaurant = null;
 	   for(Restaurant restaurant : this.registeredRestaurants){
 		   if(restaurant.getName().equals(restaurantName)){
 			   unknownrestaurant = restaurant;
 		   }
 	   }
 	   if (unknownrestaurant == null){
 		   System.out.println("There is no restaurant called like this.");
 		   throw new UserNotFoundException();
 	   }
 	   return unknownrestaurant;
    }
    
    /**
     * Find an customer with its name
     * @param customerName
     * @return the customer
     * @throws UserNotFoundException
     */
    public Customer findCustomerWithName(String customerName) throws UserNotFoundException{
 	   Customer unknowncustomer = null;
 	   for(Customer customer : this.registeredCustomers){
 		   if(customer.getName().equals(customerName)){
 			   unknowncustomer = customer;
 		   }
 	   }
 	   if (unknowncustomer == null){
 		   System.out.println("There is no customer called like this.");
 		   throw new UserNotFoundException();
 	   }
 	   return unknowncustomer;
    }
    
    /**
     * Find an user with its name
     * @param userName
     * @return the user
     * @throws ObjectNotFoundException
     */
    public User findUserWithName(String userName) throws UserNotFoundException{
 	   User unknownUser = null;
 	   for(User user : this.getAllRegisteredUsers()){
 		   if(user.getName().equals(userName)){
 			   unknownUser = user;
 		   }
 	   }
 	   if (unknownUser == null){
 		   System.out.println("There is no user called like this.");
 		   throw new UserNotFoundException();
 	   }
 	   return unknownUser;
    }
    
    
    /**
     * Display all users of myFoodora
     */
    public void showAllUsers(){
    	System.out.println("Here is the list of all registered users");
    	
    	System.out.println("\n---------- MANAGERS ----------");
    	for (Manager manager : this.registeredManagers)
    		System.out.println(manager);
    	
    	System.out.println("\n---------- RESTAURANTS ----------");
    	for (Restaurant restaurant : this.registeredRestaurants)
    		System.out.println(restaurant);
    	
    	System.out.println("\n---------- COURIERS ----------");
    	for (Courier courier : this.registeredCouriers)
    		System.out.println(courier);
    	
    	System.out.println("\n---------- CUSTOMERS ----------");
    	for (Customer customer : this.registeredCustomers)
    		System.out.println(customer);
    }
}
