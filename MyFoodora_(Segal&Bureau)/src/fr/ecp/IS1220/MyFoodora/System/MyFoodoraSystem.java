package fr.ecp.IS1220.MyFoodora.System;

import java.util.ArrayList;
import java.util.Random;

import fr.ecp.IS1220.MyFoodora.Food.*;
import fr.ecp.IS1220.MyFoodora.Policies.*;
import fr.ecp.IS1220.MyFoodora.Users.*;

public class MyFoodoraSystem {
	//Array Lists of all registered users
	private ArrayList<Manager> registeredManagers = new ArrayList<Manager>();
	private ArrayList<Restaurant> registeredRestaurants = new ArrayList<Restaurant>();
	private ArrayList<Customer> registeredCustomers = new ArrayList<Customer>();
	private ArrayList<Courier> registeredCouriers = new ArrayList<Courier>();
	
	//Array Lists of orders to deliver and delivered orders
	private ArrayList<Order> ordersToDeliver = new ArrayList<Order>();
	private ArrayList<Order> deliveredOrders = new ArrayList<Order>();
	
	//Array Lists of lottery fidelity cards to decide the winner
	private ArrayList<LotteryFidelityCard> activedLotteryCards = new ArrayList<LotteryFidelityCard>();
	private LotteryFidelityCard winner = null;
	
	//Profit-related information
	private double serviceFee;
	private double markupPercentage;
	private double deliveryCost;
	
	//Policies
	private Delivery deliverypolicy;
	private ShippedOrder shippolicy;
	private TargetProfit targetprofit;
	
	private MyFoodoraSystem(){chooseWinner();}
	
	private static MyFoodoraSystem uniqueInstance = new MyFoodoraSystem();
	
	public static MyFoodoraSystem getInstance(){
		return uniqueInstance;
	}
	
	//All Getters and Setters
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
	
	public LotteryFidelityCard getWinner() {
		return winner;
	}

	public void setWinner(LotteryFidelityCard winner) {
		this.winner = winner;
	}

	public ArrayList<LotteryFidelityCard> getActivedLotteryCards() {
		return activedLotteryCards;
	}

	public void setActivedLotteryCards(ArrayList<LotteryFidelityCard> activedLotteryCards) {
		this.activedLotteryCards = activedLotteryCards;
	}

	//Return the list of all available Couriers in the system
	public ArrayList<Courier> getAvailableCouriers(){
		ArrayList<Courier> availableCouriers = new ArrayList<Courier>();
		for (Courier courier : registeredCouriers){
			if (courier.getState() == 1){
				availableCouriers.add(courier);
			}
		}
		return availableCouriers;
	}
	

	//Find the nearest available courier from a restaurant
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
	
	//Find the courier with the least number of completed deliveries
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
	
	//Allocation of a courier to an order
	public void allocateCourier(Order order){
		ArrayList<Courier> availableCouriers = getAvailableCouriers();
		if (deliverypolicy.equals(Delivery.FastestDelivery)){
			order.setCourier(this.getNearestCourier(order.getRestaurant(), availableCouriers));
		}

		if (deliverypolicy.equals(Delivery.FairOccupationDelivery)){
			order.setCourier(this.getLaziestCourier(availableCouriers));
		}
	}
	
	//Notification of users who gave their consensus of new special offers
	public void notifyUsersForSpecialOffers(){
		for (Customer customer : this.getRegisteredCustomers()){
			if(customer.isAcceptNotifications()){
				//send a phone notification to customer
			}
		}
	}
	
	
	//Calculation of the total income
	public double calculateTotalIncome(){
		double res = 0;
		for (Order order : this.deliveredOrders){
			res += order.getOrderprice();
		}
		return res;
	}
	

	//Calculation of the total profit
	public double calculateTotalProfit(){
		double res = 0;
		for (Order order : this.deliveredOrders){
			res += (order.getOrderprice() - order.getServiceFee()) * order.getMarkupPercentage() + order.getServiceFee() - order.getDeliveryCost() ;
		}
		return res;
	}
	
	
	public void chooseWinner(){
		Random random = new Random();
		int index = random.nextInt(this.activedLotteryCards.size());
		setWinner(this.activedLotteryCards.get(index));
	}
	
	
}
