package fr.ecp.IS1220.MyFoodora.System;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import fr.ecp.IS1220.MyFoodora.Exceptions.*;
import fr.ecp.IS1220.MyFoodora.Food.*;
import fr.ecp.IS1220.MyFoodora.Policies.FidelityCard;
import fr.ecp.IS1220.MyFoodora.Users.*;


public class CommandLine {

	/**
	 *  MyFoodora System
	 */
    MyFoodoraSystem myFoodoraSystem = MyFoodoraSystem.getInstance();


    /**
     * This scanner will read all user commands
     */
    private Scanner scanner = new Scanner(System.in);
    
    /**
     * This method permit to save data of our system
     */
    public void save() {
        try {
        	FileOutputStream fileOut = new FileOutputStream("/tmp/MyFoodoraSystem.ser");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(myFoodoraSystem);
        	out.close();
        	fileOut.close();
        	System.out.printf("Serialized data is saved in /tmp/MyFoodoraSystem.ser");
        } catch(IOException i) {i.printStackTrace();}
    }
    
    /**
     * This method permit to load saved data of our system
     */
    public void load() {
    	try {
    		FileInputStream fileIn = new FileInputStream("/tmp/MyFoodoraSystem.ser");
    		ObjectInputStream in = new ObjectInputStream(fileIn);
    		myFoodoraSystem = (MyFoodoraSystem) in.readObject();
    		in.close();
    		fileIn.close();
    	} catch(IOException i) {
    		i.printStackTrace();
    		return;
    	}catch(ClassNotFoundException c) {
    		c.printStackTrace();
    		return;
    	}
    }

    /**
     * This loop permits read all command until the you want to quit
     */
    public void run() {
        String command = null;
        while (!(command = this.scanner.nextLine()).equalsIgnoreCase("quit")){
            command(command);
        }
    }

    
    /**
     * This method is the CLUI's brain which understands users' messages
     * @param the command made by the user
     */
    public void command(String string)  {
        String[] commands = string.split(" \"");
        for(int i = 0;i<commands.length;i++)
            commands[i] = commands[i].replaceAll("\"", "");

        switch (commands[0]) {
            case "login":
                if (commands.length == 3) {
                    myFoodoraSystem.login(commands[1], commands[2]);
                } else {
                    System.out.println("The input doesn't correspond to what is expected");
                }
                break;
            case "showCurrentUser":
                myFoodoraSystem.getCurrentUser();
                break;
            case "logout":
                myFoodoraSystem.logout();
                break;
                
                
            //Manager
            case "registerRestaurant":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 5) {
                        Restaurant restaurant = new Restaurant(commands[1], StringToAdressPoint(commands[2]), commands[3], commands[4]);
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.addUser(restaurant);
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            	
            case "registerCustomer":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
                    if (commands.length == 9) {
                    	Customer customer = new Customer(commands[1] + " " + commands[2], commands[3], StringToAdressPoint(commands[4]), commands[5], commands[6], commands[7], commands[8]);
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.addUser(customer);
                    } else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            	
            case "registerCourier":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 6) {
            			Courier courier = new Courier(commands[1] + " " + commands[2], StringToAdressPoint(commands[3]), commands[4], commands[5], commands[6]);
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.addUser(courier);
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "registerManager":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 5) {
            			Manager newManager = new Manager(commands[1] + " " + commands[2], commands[3], commands[4]);
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.addUser(newManager);
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            
            case "showAllUsers":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		myFoodoraSystem.showAllUsers();
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "removeUser":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
						try {
							User user = myFoodoraSystem.findUserWithName(commands[1]);
	                        manager.removeUser(user);
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            
            case "disactivateUser":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
						try {
							User user = myFoodoraSystem.findUserWithName(commands[1]);
	                        manager.deactivateUser(user);
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "activateUser":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
						try {
							User user = myFoodoraSystem.findUserWithName(commands[1]);
	                        manager.activateUser(user);
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "associateCard":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 3) {
						try {
							Customer customer = myFoodoraSystem.findCustomerWithName(commands[1]);
	                        switch (commands[2]){
	            			case "BasicFidelityCard":
	            				customer.registerToFidelityCard(FidelityCard.BasicCard);
	            				break;
	            			case "LotteryFidelityCard":
	            				customer.registerToFidelityCard(FidelityCard.LotteryCard);
	            				break;
	            			case "PointFidelityCard":
	            				customer.registerToFidelityCard(FidelityCard.PointCard);
	            				break;
	                        }
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            
            case "setDeliveryPolicy":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.setDeliveryPolicy(commands[1]);
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "setProfitPolicy":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.setTargetProfitPolicy(commands[1]);
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "showTotalProfit":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		System.out.println("Total Profit = " + myFoodoraSystem.calculateTotalProfit());
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            
            case "showTotalProfitOverAPeriod":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 3) {
            			Calendar startDate = Calendar.getInstance();
            			Calendar endDate = Calendar.getInstance();
            			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
            			try {
            				startDate.setTime(sdf.parse(commands[1] + "00:00:00"));
                			endDate.setTime(sdf.parse(commands[2] + "23:59:59"));
						} catch (ParseException e) {
							e.printStackTrace();
						}
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.computeTotalIncome(startDate, endDate);
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            	
            case "showTotalIncome":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		System.out.println("Total Income = " + myFoodoraSystem.calculateTotalIncome());
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "ShowAverageIncomePerCustomer":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
            		System.out.println("Average Income Per Customer = " + manager.averageIncomePerCustomer());
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "showAllShippedOrders":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		System.out.println("--- All Shipped Orders ---");
            		for (Order order : myFoodoraSystem.getDeliveredOrders()){
            			System.out.println(order);
            		}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "meetTargetProfit":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.meetTargetProfit(Double.parseDouble(commands[1]));
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
          
            case "setServiceFee":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.changeServiceFee(Double.parseDouble(commands[1]));
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "setMarkupPercentage":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.changeMarkupPercentage(Double.parseDouble(commands[1]));
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "setDeliveryCost":
                if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                        manager.changeDeliveryCost(Double.parseDouble(commands[1]));
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "showService_fee":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		System.out.println("Service Fee = " + myFoodoraSystem.getServiceFee());
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "showMarkup_Percentage":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		System.out.println("Mark-Up Percentage = " + myFoodoraSystem.getMarkupPercentage());
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "showDelivery_Cost":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		System.out.println("Delivery Cost = " + myFoodoraSystem.getDeliveryCost());
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "showRestaurants":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
                    Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                    manager.sortRestaurants();
                    int i = 1;
                    System.out.println("--- Top Restaurants ---\n");
                    for (Restaurant restaurant : myFoodoraSystem.getRegisteredRestaurants()){
                    	System.out.println(i + " - " + restaurant.getName() + " has already shipped " + restaurant.getShippedOrder().size() + ".");
                    	i++;
                    }
                } else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "showCouriers":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
                    Manager manager = (Manager)myFoodoraSystem.getCurrentUser();
                    manager.sortCouriers();
                    int i = 1;
                    System.out.println("--- Top Couriers ---\n");
                    for (Courier courier : myFoodoraSystem.getRegisteredCouriers()){
                    	System.out.println(i + " - " + courier.getName() + " has already delivered " + courier.getDeliveries().size() + ".");
                    	i++;
                    }
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "showAllCustomers":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
                    System.out.println("--- All Customers ---\n");
                    for (Customer customer : myFoodoraSystem.getRegisteredCustomers()){
                    	System.out.println(customer);
                    }
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            
            case "runtest":
            	if(myFoodoraSystem.getCurrentUser() instanceof Manager){
            		if (commands.length == 2) {
                        runtest(commands[1]);
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                

            //Restaurant
            case "createItem":
            	if(myFoodoraSystem.getCurrentUser() instanceof Restaurant){
            		if (commands.length == 1) {
                        Restaurant restaurant = (Restaurant)myFoodoraSystem.getCurrentUser();
                        restaurant.createItem();
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            	
            case "createMeal":
            	if(myFoodoraSystem.getCurrentUser() instanceof Restaurant){
            		if (commands.length == 1) {
                        Restaurant restaurant = (Restaurant)myFoodoraSystem.getCurrentUser();
                        try {
							restaurant.createMeal();
						} catch (MealNotValid e) {
							e.printStackTrace();
						}
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            	
            case "showMeal":
            	if(myFoodoraSystem.getCurrentUser() instanceof Restaurant){
            		if (commands.length == 2) {
                        Restaurant restaurant = (Restaurant)myFoodoraSystem.getCurrentUser();
                        try {
							System.out.println(restaurant.findMealWithName(commands[1]));
						} catch (FoodNotFoundException e) {
							e.printStackTrace();
						}
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            	
            case "showMyMenu":
            	if(myFoodoraSystem.getCurrentUser() instanceof Restaurant){
            		if (commands.length == 1) {
                        Restaurant restaurant = (Restaurant)myFoodoraSystem.getCurrentUser();
                        restaurant.showMenu();
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
            
            case "setSpecialOffer":
            	if(myFoodoraSystem.getCurrentUser() instanceof Restaurant){
            		if (commands.length == 2) {
                        Restaurant restaurant = (Restaurant)myFoodoraSystem.getCurrentUser();
                        try {
							restaurant.addMealToMealsOfTheWeek(restaurant.findMealWithName(commands[1]));
						} catch (MealNotInMenuException | MealAlreadyMealOfTheWeekException | FoodNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            		} else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;

            case "removeFromSpecialOffer":
            	if(myFoodoraSystem.getCurrentUser() instanceof Restaurant){
            		if (commands.length == 2) {
                        Restaurant restaurant = (Restaurant)myFoodoraSystem.getCurrentUser();
                        try {
							restaurant.removeMealFromMealsOfTheWeek(restaurant.findMealWithName(commands[1]));
						} catch (MealNotInMenuException | MealNotMealOfTheWeekException | FoodNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    } else {System.out.println("The input doesn't correspond to what is expected");}
    			} else {System.out.println("You're not authorized to do that.");}
    			break;
                
            case "showOrdersOfRestaurant":
            	if(myFoodoraSystem.getCurrentUser() instanceof Restaurant){
            		if (commands.length == 1) {
                        Restaurant restaurant = (Restaurant)myFoodoraSystem.getCurrentUser();
                        for (Order order : restaurant.getShippedOrder()){
                        	System.out.println(order);
                        }
                    } else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;

            case "setGenericDiscountFactor":
            	if(myFoodoraSystem.getCurrentUser() instanceof Restaurant){
            		if (commands.length == 2) {
                        Restaurant restaurant = (Restaurant)myFoodoraSystem.getCurrentUser();
                        restaurant.setGeneric_discount_factor(Double.parseDouble(commands[1]));
                    } else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "setSpecialDiscountFactor":
            	if(myFoodoraSystem.getCurrentUser() instanceof Restaurant){
            		if (commands.length == 2) {
                        Restaurant restaurant = (Restaurant)myFoodoraSystem.getCurrentUser();
                        restaurant.setSpecific_discount_factor(Double.parseDouble(commands[1]));
                    } else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;

                
            //Customer
            case "showRestaurant":
                if (commands.length == 1) {
                	for (Restaurant restaurant : myFoodoraSystem.getRegisteredRestaurants()){
                    	System.out.println(restaurant);
                	}
                } else {System.out.println("The input doesn't correspond to what is expected");}
                break;

            case "showMenu":
                if (commands.length == 2) {
                	try {
						myFoodoraSystem.findRestaurantWithName(commands[1]).showMenu();
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
                } else {System.out.println("The input doesn't correspond to what is expected");}
                break;
                
            case "showSpecialOffers":
            	User u = myFoodoraSystem.getCurrentUser();
            	if (u instanceof Customer && ((Customer) u).getCard().equals(FidelityCard.BasicCard)){
            		if (commands.length == 2) {
                    	try {
							myFoodoraSystem.findRestaurantWithName(commands[1]).showSpecialOffers();
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
                    } else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that because you're not a customer or your fidelity card is not a basic card");}
                break;
                
            case "startOrder":
               	if(myFoodoraSystem.getCurrentUser() instanceof Customer){
               		myFoodoraSystem.setCurrentOrder(new ArrayList<Food>());
               		try {
						myFoodoraSystem.setCurrentRestaurant(myFoodoraSystem.findRestaurantWithName(commands[1]));
					} catch (UserNotFoundException e) {
						e.printStackTrace();
					}
               	} else {System.out.println("You're not authorized to do that.");}
               	break;
              
            case "addItem2Order":
                if (commands.length == 2) {
                	Restaurant restaurant = myFoodoraSystem.getCurrentRestaurant();
                    try {
						myFoodoraSystem.addItemToCurrentOrder(restaurant.findItemWithName(commands[1]));
					} catch (FoodNotFoundException e) {
						e.printStackTrace();
					}
                } else {System.out.println("The input doesn't correspond to what is expected");}
                break;
                
            case "addMeal2Order":
                if (commands.length == 2) {
                	Restaurant restaurant = myFoodoraSystem.getCurrentRestaurant();
                    try {
						myFoodoraSystem.addMealToCurrentOrder(restaurant.findMealWithName(commands[1]));
					} catch (FoodNotFoundException e) {
						e.printStackTrace();
					}
                } else {System.out.println("The input doesn't correspond to what is expected"); }
                break;
                
            case "showOrder":
            	for (Food food : myFoodoraSystem.getCurrentOrder()){
            		System.out.println(food);
            	}
                break;
                
            case "endOrder":
            	Order order = new Order(myFoodoraSystem.getCurrentOrder(), (Customer)myFoodoraSystem.getCurrentUser());
                myFoodoraSystem.findCourier(order);
                System.out.println("You have ordered those articles :" + order);
                break;
                
            case "setNotified":
            	if(myFoodoraSystem.getCurrentUser() instanceof Customer){
            		if (commands.length == 2) {
                        Customer customer = (Customer)myFoodoraSystem.getCurrentUser();
                        if (commands[1].equals("on")){
                        	customer.giveConsensusForNotifications();
                        	System.out.println("Notifications have been activated.");
                        }
                        if (commands[2].equals("off")){
                        	customer.removeConsensusForNotifications();
                        	System.out.println("Notifications have been disactivated.");
                        }
                    } else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;
                
            case "showFidelityCard":
            	if(myFoodoraSystem.getCurrentUser() instanceof Customer){
               		Customer customer = (Customer)myFoodoraSystem.getCurrentUser();
               		System.out.println(customer.getCard());
               	} else {System.out.println("You're not authorized to do that.");}
               	break;
               	
            case "registerFidelityCard":
            	if(myFoodoraSystem.getCurrentUser() instanceof Customer){
            		if (commands.length == 2) {
            			Customer customer = (Customer)myFoodoraSystem.getCurrentUser();
            			switch (commands[1]){
            			case "BasicFidelityCard":
            				customer.registerToFidelityCard(FidelityCard.BasicCard);
            				break;
            			case "LotteryFidelityCard":
            				customer.registerToFidelityCard(FidelityCard.LotteryCard);
            				break;
            			case "PointFidelityCard":
            				customer.registerToFidelityCard(FidelityCard.PointCard);
            				break;
                        }
                    } else {System.out.println("The input doesn't correspond to what is expected");}
            	} else {System.out.println("You're not authorized to do that.");}
                break;


            //Courier
            case "onDuty":
            	if(myFoodoraSystem.getCurrentUser() instanceof Courier){
               		Courier courier = (Courier)myFoodoraSystem.getCurrentUser();
               		courier.setState(1);
               	} else {System.out.println("You're not authorized to do that.");}
               	break;
               	
            case "offDuty":
            	if(myFoodoraSystem.getCurrentUser() instanceof Courier){
               		Courier courier = (Courier)myFoodoraSystem.getCurrentUser();
               		courier.setState(0);
               	} else {System.out.println("You're not authorized to do that.");}
               	break;
               	
            case "refuse":
            	if(myFoodoraSystem.getCurrentUser() instanceof Courier){
               		Courier courier = (Courier)myFoodoraSystem.getCurrentUser();
               		courier.setState(0);
               	} else {System.out.println("You're not authorized to do that.");}
               	break;
               	
            case "accept":
            	if(myFoodoraSystem.getCurrentUser() instanceof Courier){
               		Courier courier = (Courier)myFoodoraSystem.getCurrentUser();
               		courier.setState(0);
               	} else {System.out.println("You're not authorized to do that.");}
               	break;
               	
            case "changePosition":
            	if(myFoodoraSystem.getCurrentUser() instanceof Courier){
               		Courier courier = (Courier)myFoodoraSystem.getCurrentUser();
               		courier.setPosition(StringToAdressPoint(commands[1]));
               	} else {System.out.println("You're not authorized to do that.");}
               	break;
               	
            case "help":
            	User user = myFoodoraSystem.getCurrentUser();
            	if (user == null){
            		System.out.println("List of available commands");
            		System.out.println("login <username> <password> : to log in to the system.");
            	}
            	if (user instanceof Manager){
                    System.out.println("List of available commands as Manager");
                    System.out.println("---------------------------");
                    System.out.println("logout <> : to log out.");
                    
                    System.out.println("\n--- Users Managing Commands ---");
                    System.out.println("registerRestaurant <name> <address> <username> <password> : to add a new restaurant to the system.");
                    System.out.println("registerCustomer <firstName> <lastName> <surname> <adress> <email> <phone (without spaces)> <username> <password> : to add a new customer to the system.");
                    System.out.println("registerCourier <firstName> <lastName> <position> <phone (without spaces)> <username> <password> : to add a new courier to the system.");
                    System.out.println("registerManager <firstName> <lastName> <username> <password> : to add a new manager to the system.");
                    System.out.println("showAllUsers : to show all users.");
                    System.out.println("removeUser <username> : to remove a user with given username.");
                    System.out.println("disactivateUser <username> : to disactivate a user with given username.");
                    System.out.println("activateUser : to activate a user with given username.");
                    System.out.println("associateCard <userName> <cardType : BasicFidelityCard OR LotteryFidelityCard OR PointFidelityCard> : to associate a fidelity card to a customer with given name.");
                    
                    System.out.println("\n--- Business Managing Commands ---");
                    System.out.println("setDeliveryPolicy <fastestDelivery OR fairOccupationDelivery> : to set the delivery policy to the passed argument.");
                    System.out.println("setProfitPolicy <serviceFee OR markupPercentage OR deliveryCost> : to set the profit policy to the passed argument.");
                    System.out.println("showTotalProfit : to show the total profit made since MyFoodora's beginning.");
                    System.out.println("showTotalProfit <startDate (MM/DD/YY)> <endDate (MM/DD/YY)> : to show total profit made between startDate and endDate");
                    System.out.println("showTotalIncome : to show the total income");
                    System.out.println("showAverageIncomePerCustomer : to show the average income per customer.");
                    System.out.println("showAllShippedOrders : to show all orders shipped by MyFoodora since its beginning.");
                    System.out.println("meetTargetProfit <targetProfit> : to adjust cost parameters in order to meet a target profit.");
                    System.out.println("setServiceFee <service_fee> : to set the service fee to a given value.");
                    System.out.println("setMarkupPercentage <markupPercentage>: to set the markup percentage to a given value.");
                    System.out.println("setDeliveryCost <deliverycost>: to set the delivery cost to a given value.");
                    System.out.println("showService_fee : to show the service fee.");
                    System.out.println("showMarkup_Percentage : to show the markup percentage.");
                    System.out.println("showDelivery_Cost : to show the delivery cost.");
                    System.out.println("showRestaurantTop : to show restaurants sorted in decreasing order w.r.t. the number of delivered orders.");
                    System.out.println("showCourierTop : to show couriers sorted in decreasing order w.r.t. the number of completed deliveries");
                    System.out.println("showCustomers : to show all customers.");
                    
                    System.out.println("\nruntest <testScenario-file> : executes the list of CLUI commands contained on the testScenario file.");
            	}
            	if (user instanceof Restaurant){
            		System.out.println("List of available commands as Restaurant");
                    System.out.println("---------------------------");
                    System.out.println("logout <> : to log out.");
                    
                    System.out.println("\ncreateItem : to add a new item to the menu.");
                    System.out.println("createMeal : to add a new meal to the menu.");
                    System.out.println("showMeal <mealName> : to show the content of a given meal.");
                    System.out.println("showMyMenu <> : to show the content of your restaurant menu (items and meals).");
                    System.out.println("setSpecialOffer <mealName> : to add a meal to the Meal-of-the-week special offer.");
                    System.out.println("removeFromSpecialOffer <mealName> : to remove a meal from the meal-of-the-week special offer.");
                    System.out.println("setGenericDiscountFactor <generic discount factor> : to set the discount factor on meal prices.");
                    System.out.println("setSpecificDiscountFActor <specific discount factor> : to set the discount factor on meal of the week prices.");
            	}
            	if (user instanceof Customer){
            		System.out.println("List of available commands as Customer");
                    System.out.println("---------------------------");
                    System.out.println("logout <> : to log out.");
                    
                    System.out.println("\nshowRestaurants : to see all existing restaurants.");
                    System.out.println("showMenu <restaurant> : to show the menu of a restaurant.");
                    System.out.println("startOrder <restaurant> : to see all existing restaurants.");
                    System.out.println("addMeal2Order <mealName> : to add a given meal to the order.");
                    System.out.println("addItem2Order <itemName> : to add a given item to the order.");
                    System.out.println("showOrder : to show the content of the current order.");
                    System.out.println("endOrder : to end an order and pay.");
                    System.out.println("setNotified <on OR off> : to enable or disable notification from MyFoodora.");
                    System.out.println("showFidelityCard : to show the current fidelity program");
                    System.out.println("registerFidelityCard <BasicFidelityCard OR LotteryFidelityCard OR PointFidelityCard> : to register to a given fidelity program.");
            	}     
                if (user instanceof Courier){
                    System.out.println("List of available commands:");
                    System.out.println("---------------------------");
                    System.out.println("logout <> : to log out.");
                    
                    System.out.println("\nonDuty : to set the state of the courier to 'on'");
                    System.out.println("offDuty : to set the state of the courier to 'off'");
                    System.out.println("refuse : to refuse the assigned order and delegate the order to other couriers");
                    System.out.println("accept : to accept the assigned order ");
                    System.out.println("changePosition <position> : to change the position to a given position");
                }

                break;
            default:
                System.out.println("Invalid syntax: type 'help' to display the list of available CLUI commands");
        }
    }

    public void runtest(String fileName) {

        MyFoodoraSystem.startFromNothing();

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("read the file line by line");

            reader = new BufferedReader(new FileReader(file));
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null && !nextLine.equalsIgnoreCase("quit")) {
                command(nextLine);
                }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

    }

    /**
     * The main function that is called at the beginning of the program.
     * @param args
     * @throws IOException
     * @throws UserNotFoundException
     */
    public static void main(String[] args) throws IOException, UserNotFoundException {

        CommandLine commandLine = new CommandLine();
        //If we suppress the load step, we start with a new system
        commandLine.load();
        commandLine.run();
        commandLine.save();
    }

    /**
     * Converts a String to a Point object.
     * @param the address with specific format : "x,y"
     * @return the address, as a Point object
     */
    private Point StringToAdressPoint(String address) {
        String[] coordinates = address.split(",");
        return new Point(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
    }

}

