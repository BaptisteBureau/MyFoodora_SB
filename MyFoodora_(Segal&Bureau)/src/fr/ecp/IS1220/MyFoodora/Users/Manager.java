package fr.ecp.IS1220.MyFoodora.Users;

import java.util.ArrayList;
import java.util.Calendar;

import fr.ecp.IS1220.MyFoodora.Exceptions.NameAlreadyUsedException;
import fr.ecp.IS1220.MyFoodora.Policies.Delivery;
import fr.ecp.IS1220.MyFoodora.Policies.TargetProfit;
import fr.ecp.IS1220.MyFoodora.System.*;

public class Manager extends User{

	private static final long serialVersionUID = 3110522330704842524L;


	public Manager(String name, String username, String password) {
		super(name, username, password);
	}


	/**
	 * Add any kind of user (restaurant, customers and/or couriers) to the system
	 * @param user
	 */
	public void addUser(User u) throws NameAlreadyUsedException {
		if (MyFoodoraSystem.getInstance().getAllRegisteredUsers().contains(u)){
			System.out.println("This username is already taken, please choose another one.");
			throw new NameAlreadyUsedException();
		}
		else {
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
	}
	
	
	/**
	 * Remove any kind of user (restaurant, customers and/or couriers) from the system
	 * @param user
	 */
	public void removeUser(User u){
		if (u instanceof Restaurant){
			ArrayList<Restaurant> newList = MyFoodoraSystem.getInstance().getRegisteredRestaurants();
			newList.remove((Restaurant) u);
			MyFoodoraSystem.getInstance().setRegisteredRestaurants(newList);
			return;
		}
		if (u instanceof Customer){
			ArrayList<Customer> newList = MyFoodoraSystem.getInstance().getRegisteredCustomers();
			newList.remove((Customer) u);
			MyFoodoraSystem.getInstance().setRegisteredCustomers(newList);
			return;
		}
		if (u instanceof Courier){
			ArrayList<Courier> newList = MyFoodoraSystem.getInstance().getRegisteredCouriers();
			newList.remove((Courier) u);
			MyFoodoraSystem.getInstance().setRegisteredCouriers(newList);
			return;
		}
		System.out.println("You can't remove this User");
	}
	
	
	/**
	 * Activate any kind of user (restaurant, customers and/or couriers) of the system
	 * @param user
	 */
	public void activateUser(User u){
		u.activate();
	}
	
	
	/**
	 * Deactivate any kind of user (restaurant, customers and/or couriers) of the system
	 * @param user
	 */
	public void deactivateUser(User u){
		u.disactivate();
	}
	
	
	/**
	 * Change the service-fee percentage
	 * @param serviceFee
	 */
	public void changeServiceFee(double serviceFee){
		MyFoodoraSystem.getInstance().setServiceFee(serviceFee);
	}
	
	
	/**
	 * Change the mark-up percentage
	 * The new mark-up percentage must be between 0 and 1
	 * @param markupPercentage
	 */
	public void changeMarkupPercentage(double markupPercentage){
		if (0 <= markupPercentage && markupPercentage <= 1)
			MyFoodoraSystem.getInstance().setMarkupPercentage(markupPercentage);
		else
			System.out.println(" The mark-up percentage has not been changed !");
			System.out.println("Please try again, with a number between 0 and 1.");
	}
	
	
	/**
	 * Change the delivery cost
	 * @param deliveryCost
	 */
	public void changeDeliveryCost(double deliveryCost){
		MyFoodoraSystem.getInstance().setDeliveryCost(deliveryCost);
	}
	
	
	/**
	 * Computing the total income over a time period, from a start date until today
	 * @param start date of the time period
	 * @return total income during the period
	 */
	public double computeTotalIncome(Calendar date){
		double res = 0;
		MyFoodoraSystem system = MyFoodoraSystem.getInstance();
		int i = system.getDeliveredOrders().size() - 1;
		while (i >= 0 && date.compareTo(system.getDeliveredOrders().get(i).getOrderdate()) <= 0){
			res += system.getDeliveredOrders().get(i).getOrderprice();
			i --;
		}
		return res;
	}
	
	/**
	 * Computing the total income over a time period, from a start date until an end date
	 * @param startDate
	 * @param endDate
	 * @return total income between the starting date and the end date
	 */
	public double computeTotalIncome(Calendar startDate, Calendar endDate){
		return this.computeTotalIncome(startDate) - this.computeTotalIncome(endDate);
	}
	
	
	/**
	 * Computing the total profit over a time period, from a start date until today
	 * @param start date of the time period
	 * @return total profit during the period
	 */
	public double computeTotalProfit(Calendar date){
		double res = 0;
		MyFoodoraSystem system = MyFoodoraSystem.getInstance();
		int i = system.getDeliveredOrders().size() - 1;
		while (i >= 0 && date.compareTo(system.getDeliveredOrders().get(i).getOrderdate()) <= 0){
			Order order = system.getDeliveredOrders().get(i);
			res += (order.getOrderprice() - order.getServiceFee()) * order.getMarkupPercentage();
			res += order.getServiceFee() - order.getDeliveryCost();
			i --;
		}
		return res;
	}
	
	/**
	 * Computing the total profit over a time period, from a start date until an end date
	 * @param startDate
	 * @param endDate
	 * @return total profit between the starting date and the end date
	 */
	public double computeTotalProfit(Calendar startDate, Calendar endDate){
		return this.computeTotalProfit(startDate) - this.computeTotalProfit(endDate);
	}
	
	
	/**
	 * Calculate the average income per customer (total income divided by the number of customers who placed at least one order)
	 * @return average income per active customer
	 */
	public double averageIncomePerCustomer(){
		double res = computeTotalIncome(MyFoodoraSystem.getInstance().getStartDate());
		double activeCustomers = 0;
		for (Customer customer : MyFoodoraSystem.getInstance().getRegisteredCustomers()){
			if(!customer.getMyorders().isEmpty())
				activeCustomers += 1;
		}
		res = res/activeCustomers;
		return res;
	}
	
	
	/**
	 * Determine the serviceFee to meet a target profit
	 * @param targetProfit
	 * @param markupPercentage
	 * @param deliveryCost
	 * @return the serviceFee
	 * @throws ArithmeticException
	 */
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
	
	
	/**
	 * Determine the markup percentage to meet a target profit
	 * @param targetProfit
	 * @param serviceFee
	 * @param deliveryCost
	 * @return the markup percentage
	 * @throws ArithmeticException
	 */
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
	
	
	/**
	 * Determine the delivery cost to meet a target profit
	 * @param targetProfit
	 * @param serviceFee
	 * @param markupPercentage
	 * @return the deliveryCost
	 * @throws ArithmeticException
	 */
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
	
	/**
	 * Adjust the service fee, the mark-up percentage, or the delivery cost
	 * Taking into account the target profit policy in order to meet a target monthly profit 
	 * @param targetProfit
	 */
	public void meetTargetProfit(double targetProfit){
		double currentServiceFee = MyFoodoraSystem.getInstance().getServiceFee();
		double currentMarkUpPercentage = MyFoodoraSystem.getInstance().getMarkupPercentage();
		double currentDeliveryCost = MyFoodoraSystem.getInstance().getDeliveryCost();
		TargetProfit targetProfitPolicy = MyFoodoraSystem.getInstance().getTargetprofit();
		if (targetProfitPolicy.equals(TargetProfit.ServiceFee)){
			double newServiceFee = this.calculateServiceFee_targetProfit(targetProfit, currentMarkUpPercentage, currentDeliveryCost);
			this.changeServiceFee(newServiceFee);
			System.out.println("The new service fee is " + newServiceFee);
		}
		if (targetProfitPolicy.equals(TargetProfit.Markup)){
			double newMarkUp = this.calculateMarkupPercentage_targetProfit(targetProfit, currentServiceFee, currentDeliveryCost);
			this.changeMarkupPercentage(newMarkUp);
			System.out.println("The new mark-up percentage is " + newMarkUp);
		}
		if (targetProfitPolicy.equals(TargetProfit.DeliveryCost)){
			double newDeliveryCost = this.calculateDeliveryCost_targetProfit(targetProfit, currentServiceFee, currentMarkUpPercentage);
			this.changeDeliveryCost(newDeliveryCost);
			System.out.println("The new delivery cost is ");
		}
	}
	
	/**
	 * Sort restaurants in decreasing order w.r.t. the number of delivered orders
	 * For this method we create an other list of restaurants by placing each restaurant at the right place (beginning by the end)
	 * This is not the best way to sort a list but this technique shows high performance when reiterated
	 * In fact the list of registered restaurants will be almost sorted at each very moment
	 */
	public void sortRestaurants() {
		ArrayList<Restaurant> oldRestaurants = MyFoodoraSystem.getInstance().getRegisteredRestaurants();
		ArrayList<Restaurant> newRestaurants = new ArrayList<Restaurant>();
		for (Restaurant restaurant : oldRestaurants){
			if (newRestaurants.isEmpty()){
				newRestaurants.add(restaurant);
			} else {
				int i = newRestaurants.size();
				Restaurant restaurantToCompare = newRestaurants.get(i - 1);
				while (restaurantToCompare.getShippedOrder().size() < restaurant.getShippedOrder().size() && i > 0){
					i--;
					if (i > 0)
						restaurantToCompare = newRestaurants.get(i - 1);
				}
				if (i<newRestaurants.size())
					newRestaurants.add(i, restaurant);
				else
					newRestaurants.add(restaurant);
			}
		}
		MyFoodoraSystem.getInstance().setRegisteredRestaurants(newRestaurants);
	}
	
	/**
	 * @return the least selling restaurant
	 */
	public Restaurant leastSellingRestaurant(){
		this.sortRestaurants();
		ArrayList<Restaurant> restaurants= MyFoodoraSystem.getInstance().getRegisteredRestaurants();
		Restaurant leastSellingRestaurant = null;
		if (restaurants.isEmpty())
			System.out.println("There is no restaurant in the system.");
		else
			leastSellingRestaurant = restaurants.get(restaurants.size() - 1);
		return leastSellingRestaurant;
	}
	
	/**
	 * @return the most selling restaurant
	 */
	public Restaurant mostSellingRestaurant(){
		this.sortRestaurants();
		ArrayList<Restaurant> restaurants= MyFoodoraSystem.getInstance().getRegisteredRestaurants();
		Restaurant mostSellingRestaurant = null;
		if (restaurants.isEmpty())
			System.out.println("There is no restaurant in the system.");
		else
			mostSellingRestaurant = restaurants.get(0);
		return mostSellingRestaurant;
	}
	
	
	/**
	 * Sort couriers in decreasing order w.r.t. the number of completed deliveries
	 * For this method we create an other list of couriers by placing each courier at the right place (beginning by the end)
	 * This is not the best way to sort a list but this technique shows high performance when reiterated
	 * In fact the list of registered couriers will be almost sorted at each very moment
	 */
	public void sortCouriers() {
		ArrayList<Courier> oldCouriers = MyFoodoraSystem.getInstance().getRegisteredCouriers();
		ArrayList<Courier> newCouriers = new ArrayList<Courier>();
		for (Courier courier : oldCouriers){
			if (newCouriers.isEmpty()){
				newCouriers.add(courier);
			} else {
				int i = newCouriers.size();
				Courier courierToCompare = newCouriers.get(i - 1);
				while (courierToCompare.getDeliveries().size() < courier.getDeliveries().size() && i>0){
					i--;
					if(i>0)
						courierToCompare = newCouriers.get(i - 1);
				}
				if (i<newCouriers.size())
					newCouriers.add(i, courier);
				else
					newCouriers.add(courier);
			}
			
		}
		MyFoodoraSystem.getInstance().setRegisteredCouriers(newCouriers);
	}
	
	/**
	 * @return the most active courier of the fleet
	 */
	public Courier mostActiveCourier(){
		this.sortCouriers();
		ArrayList<Courier> couriers= MyFoodoraSystem.getInstance().getRegisteredCouriers();
		Courier mostSellingCourier = null;
		if (couriers.isEmpty())
			System.out.println("There is no courier in the system.");
		else
			mostSellingCourier = couriers.get(0);
		return mostSellingCourier;
	}
	
	
	/**
	 * @return the least active courier of the fleet
	 */
	public Courier leastActiveCourier(){
		this.sortCouriers();
		ArrayList<Courier> couriers= MyFoodoraSystem.getInstance().getRegisteredCouriers();
		Courier leastSellingCourier = null;
		if (couriers.isEmpty())
			System.out.println("There is no courier in the system.");
		else
			leastSellingCourier = couriers.get(0);
		return leastSellingCourier;
	}
	
	
	/**
	 * Setting the current delivery-policy to determine which courier is assigned
	 * to deliver an order placed by a customer
	 * @param deliveryPolicy
	 */
	public void setDeliveryPolicy(Delivery deliverypolicy){
		MyFoodoraSystem.getInstance().setDeliverypolicy(deliverypolicy);
	}
	
	/**
	 * Setting the current delivery-policy to determine which courier is assigned
	 * to deliver an order placed by a customer with a string argument.
	 * @param (String) deliveryPolicy
	 */
	public void setDeliveryPolicy(String deliverypolicy){
		if (deliverypolicy.equalsIgnoreCase("fastestDelivery"))
			this.setDeliveryPolicy(Delivery.FastestDelivery);
		if (deliverypolicy.equalsIgnoreCase("fairOccupationDelivery"))
			this.setDeliveryPolicy(Delivery.FairOccupationDelivery);
	}

	/**
	 * Setting the current target profit policy to determine which cost must be adjusted
	 * to meet a certain target profit
	 * @param targetProfitPolicy
	 */
	public void setTargetProfitPolicy(TargetProfit targetProfitPolicy){
		MyFoodoraSystem.getInstance().setTargetprofit(targetProfitPolicy);
	}
	
	/**
	 * Setting the current target profit policy to determine which cost must be adjusted
	 * to meet a certain target profit with a string argument
	 * @param (String) targetProfitPolicy
	 */
	public void setTargetProfitPolicy(String targetProfitPolicy){
		if (targetProfitPolicy.equalsIgnoreCase("serviceFee"))
			this.setTargetProfitPolicy(TargetProfit.ServiceFee);
		if (targetProfitPolicy.equalsIgnoreCase("markUpPercentage"))
			this.setTargetProfitPolicy(TargetProfit.Markup);
		if (targetProfitPolicy.equalsIgnoreCase("deliveryCost"))
			this.setTargetProfitPolicy(TargetProfit.DeliveryCost);
	}

	
	@Override
	public String toString() {
		return "Manager [name=" + this.getName() + ", username=" + this.getUsername() + ", status=" + this.getStatus() + "]";
	}
	
	
	
}
