package fr.ecp.IS1220.MyFoodora.System;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.io.*;

import fr.ecp.IS1220.MyFoodora.Food.*;
import fr.ecp.IS1220.MyFoodora.Policies.FidelityCard;
import fr.ecp.IS1220.MyFoodora.Users.*;

public class Order implements Serializable{
	
	private static final long serialVersionUID = -8932318025282018506L;
	
	/**
	 * Status of the order : 0 = waiting for delivery and 1 = delivered
	 */
	private int status;
	private ArrayList<Item> orderedItems;
	private ArrayList<Meal> orderedMeals;
	private Customer customer;
	private Restaurant restaurant;
	private double orderprice;
	private Calendar orderdate;
	private Courier courier;
	
	/**
	 * Profit-related information at the time of the order
	 */
	private double serviceFee;
	private double markupPercentage;
	private double deliveryCost;
	
	private MyFoodoraSystem system = MyFoodoraSystem.getInstance();

	public Order(ArrayList<Food> Food, Customer customer) {
		this.status = 0;
		this.orderedItems = new ArrayList<Item>();
		this.orderedMeals = new ArrayList<Meal>();
		this.customer = customer;
		for(Food food : Food){
			if (food instanceof Item){
				this.orderedItems.add((Item) food);
				Item item = (Item) food;
				item.incrementCounter();
			}
			if (food instanceof Meal){
				this.orderedMeals.add((Meal) food);
				Meal meal = (Meal) food;
				meal.incrementCounter();
			}
		}
		this.orderprice = 0;
		for (Item item : orderedItems){
			this.orderprice += item.getItemprice();
		}
		for (Meal meal : orderedMeals){
			this.orderprice += meal.getMealprice();
		}
		this.orderdate = GregorianCalendar.getInstance();
		this.restaurant = Food.get(0).getRestaurant();
		this.serviceFee = MyFoodoraSystem.getInstance().getServiceFee();
		this.markupPercentage = MyFoodoraSystem.getInstance().getMarkupPercentage();
		this.deliveryCost = MyFoodoraSystem.getInstance().getDeliveryCost();
		this.orderprice += this.serviceFee;
		
		/**
		 * If the customer has a point fidelity card, we check if he has a discount, 
		 * then add the number of points brought by his order.
		 */
		if(customer.getCard() == FidelityCard.PointCard){
			if(customer.pointCard.offerDiscount()){
				this.orderprice *= 0.9;
				customer.pointCard.reinitializeCard();
			}
			int points = (int)this.orderprice/10;
			int oldpoints = customer.pointCard.getPoints();
			customer.pointCard.setPoints(oldpoints + points);
		}
		
		/**
		 * If the customer has a lottery card, we check if he has won.
		 */
		if(customer.getCard() == FidelityCard.LotteryCard){
			if(customer == system.getLotteryWinner())
				orderprice = 0.0;
		}
		
	}
	
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public Calendar getOrderdate() {
		return orderdate;
	}

	public double getOrderprice() {
		return orderprice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDelivered() {
		this.status = 1;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public ArrayList<Item> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(ArrayList<Item> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public ArrayList<Meal> getOrderedMeals() {
		return orderedMeals;
	}

	public void setOrderedMeals(ArrayList<Meal> orderedMeals) {
		this.orderedMeals = orderedMeals;
	}


	public double getServiceFee() {
		return serviceFee;
	}

	public double getMarkupPercentage() {
		return markupPercentage;
	}

	public double getDeliveryCost() {
		return deliveryCost;
	}


	@Override
	public String toString() {
		String str = "Order [orderedItems=";
		for (Item item : this.orderedItems){
			str += item.getName();
		}
		str += ", orderedMeals=";
		for (Meal meal : this.orderedMeals){
			str += meal.getName();
		}
		str += ", restaurant=" + restaurant.getName();
		str += ", orderprice=" + this.getOrderprice();
		str += ", orderdate=" + (orderdate.get(Calendar.MONTH) + 1) + "/" + orderdate.get(Calendar.DAY_OF_MONTH) + "/" + orderdate.get(Calendar.YEAR) + "]";
		return str;
	}



	
}
