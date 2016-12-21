package fr.ecp.IS1220.MyFoodora.Users;
 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.ecp.IS1220.MyFoodora.Exceptions.*;
import fr.ecp.IS1220.MyFoodora.Food.*;
import fr.ecp.IS1220.MyFoodora.Policies.ShippedOrder;
import fr.ecp.IS1220.MyFoodora.System.*;
 
public class Restaurant extends User{
       private Point adress;
       private ArrayList<Item> listOfItems;
       private ArrayList<Item> menu;
       private ArrayList<Meal> listOfMeals;
       private ArrayList<Meal> mealsInMenu;
       private ArrayList<Meal> mealsOfTheWeek;
       private double generic_discount_factor;
       private double specific_discount_factor;
       private ArrayList<Order> shippedOrder;
       private MyFoodoraSystem system = MyFoodoraSystem.getInstance();
       private int counter = 0;


       public Restaurant(String name, Point adress, String username, double hashedPassword) {
             super(name, username, hashedPassword);
             this.adress = adress;
             this.listOfItems = new ArrayList<Item>();
             this.menu = new ArrayList<Item>();
             this.listOfMeals = new ArrayList<Meal>();
             this.mealsInMenu = new ArrayList<Meal>();
             this.mealsOfTheWeek = new ArrayList<Meal>();
             this.shippedOrder = new ArrayList<Order>();
       }
 
 
       public Restaurant(String name, Point adress, String username, double hashedPassword,
                    double generic_discount_factor, double specific_discount_factor) {
             super(name, username, hashedPassword);
             this.adress = adress;
             this.listOfItems = new ArrayList<Item>();
             this.menu = new ArrayList<Item>();
             this.listOfMeals = new ArrayList<Meal>();
             this.mealsInMenu = new ArrayList<Meal>();
             this.generic_discount_factor = generic_discount_factor;
             this.specific_discount_factor = specific_discount_factor;
             this.mealsOfTheWeek = new ArrayList<Meal>();
             this.shippedOrder = new ArrayList<Order>();
       }
 


       public Point getAdress() {
             return adress;
       }
 
       public void setAdress(Point adress) {
             this.adress = adress;
       }
        
       public ArrayList<Item> getListOfItems() {
    	   return listOfItems;
       }

       public void setListOfItems(ArrayList<Item> listOfItems) {
    	   this.listOfItems = listOfItems;
       }

       public ArrayList<Item> getMenu() {
             return menu;
       }
 
       public void setMenu(ArrayList<Item> menu) {
             this.menu = menu;
       }
 
       public ArrayList<Meal> getListOfMeals() {
             return listOfMeals;
       }
 
       public void setListOfMeals(ArrayList<Meal> listOfMeals) {
             this.listOfMeals = listOfMeals;
       }
 
       public ArrayList<Meal> getMealsInMenu() {
    	   return mealsInMenu;
       }

       public void setMealsInMenu(ArrayList<Meal> mealsInMenu) {
    	   this.mealsInMenu = mealsInMenu;
       }

       public ArrayList<Meal> getMealsOfTheWeek() {
             return mealsOfTheWeek;
       }
 
       public void setMealsOfTheWeek(ArrayList<Meal> mealsOfTheWeek) {
             this.mealsOfTheWeek = mealsOfTheWeek;
       }
 
       public double getGeneric_discount_factor() {
             return generic_discount_factor;
       }
 
       public void setGeneric_discount_factor(double generic_discount_factor) {
             this.generic_discount_factor = generic_discount_factor;
       }
 
       public double getSpecific_discount_factor() {
             return specific_discount_factor;
       }
 
       public void setSpecific_discount_factor(double specific_discount_factor) {
             this.specific_discount_factor = specific_discount_factor;
       }
       
       
       public ArrayList<Order> getShippedOrder() {
    	   return shippedOrder;
       }


       public void setShippedOrder(ArrayList<Order> shippedOrder) {
    	   this.shippedOrder = shippedOrder;
       }


       public void addShippedOrder(Order order){
    	   this.shippedOrder.add(order);
       }
       
       
       //Return starters of the restaurant in the menu
       public ArrayList<Item> getStarters() {
    	   ArrayList<Item> starters = this.listOfItems;
    	   for (Item item : starters){
    		   if(!item.getItemtype().equals(ItemType.Starter))
    			   starters.remove(item);
    	   }
    	   return starters;
       }

       //Return main dishes of the restaurant in the menu
       public ArrayList<Item> getMainDishes() {
    	   ArrayList<Item> mainDishes = this.listOfItems;
    	   for (Item item : mainDishes){
    		   if(!item.getItemtype().equals(ItemType.Main_dish))
    			   mainDishes.remove(item);
    	   }
    	   return mainDishes;
       }

       //Return desserts of the restaurant in the menu
       public ArrayList<Item> getDesserts() {
    	   ArrayList<Item> desserts = this.listOfItems;
    	   for (Item item : desserts){
    		   if(!item.getItemtype().equals(ItemType.Dessert))
    			   desserts.remove(item);
    	   }
    	   return desserts;
       }


       public int getCounter() {
    	   return counter;
       }

       public void incrementCounter() {
    	   this.counter ++;
       }
       

       //Create item
       public void createItem(){
    	   Scanner sc = new Scanner(System.in);
    	   ItemType type = null;
    	   double price = 0.0;
    	   String answer = "";
    	   
    	   //Regime type and gluten free value are both set on standard by default.
    	   RegimeType regimeType = RegimeType.Standard;
    	   boolean glutenFree = false;
    	   
    	   //choose type for the item
    	   //We get the user to enter the type of the item while he can't get the right type and wants to start over.
    	   
    	   while(type == null){
        	   System.out.println("Is this item 1 - a starter, 2 - a main dish, or 3 - a dessert ? Choose 1, 2 or 3.");
    		   try{
	    		   int t = sc.nextInt();
	    		   if(t==1)
	    			   type = ItemType.Starter;
	    		   else if(t==2)
	    			   type = ItemType.Main_dish;
	    		   else if(t==3)
	    			   type = ItemType.Dessert;
	    		   else{
	    			   
	    			   //Here, the user entered an integer but not between 1 and 3.
	    			   //We ask him if he wants to retry. If not, we get out of the loop and the method ends.
	    			   
	    			   System.out.println("The type you're trying to select is not valid. You should choose a number between 1 and 3.");
	    			   System.out.println("Do you like to try again ? Yes / No");
	    			   sc.nextLine();
	    			   answer = sc.nextLine();
	    			   if(answer.toLowerCase().charAt(0) != 'y')
	        			   break;
	    		   }
	    		   
	    	   } catch(InputMismatchException e){
	    		   
	    		   //Here, the user didn't enter an integer.
	    		   //We ask him if he wants to retry. If not, we get out of the loop and the method ends.
	    		   
	    		   System.out.println("You're trying to enter a wrong type. You're supposed to choose a number between 1 and 3.");
	    		   System.out.println("Do you like to try again ? Yes / No");
	    		   sc.nextLine();
	    		   answer = sc.nextLine();
	    		   if(answer.toLowerCase().charAt(0) != 'y')
	    			   break;
	    	   }
    	   }
    	   
    	   
    	   //If we get there while type is still null, means the user failed to select a type and does not want to start over.
    	   //In this case, the method ends.
    	   
    	   if(type != null){
	    	   sc.nextLine(); //Empties the scanner
	    	   
	    	   //Choose a name for the item. It shouldn't be already attributed to an other existing item.
	    	   
	    	   String name = "";
	    	   while(name == ""){
		    	   System.out.println("What's the name of this new item ?");
	    		   name = sc.nextLine();
	    		   
	    		   //We check if the name already exists.
	    		   
	    		   for(Item i : listOfItems){
	    			   if(i.getName() == name){
	    				   System.out.println("The item named " + name + " already exists.");
	    				   name = "";
	    			   }
	    		   }
	    		   
	    		   //If it does, we ask the user if he wants to try again.
	    		   
	    		   if(name == ""){
    				   System.out.println("Do you like to try again ? Yes / No");
    	    		   sc.nextLine();
    	    		   answer = sc.nextLine();
    	    		   if(answer.toLowerCase().charAt(0) != 'y')
    	    			   break;
    			   }
	    	   }
	    	   
	    	   //If we reach this state without name, means the user wants to cancel the creation of the item.
	    	   //In this case, the method ends.
	    	   
	    	   if(name != ""){
	    		   
		    	   //set the price of the item
	    		   //Here, two errors can appear : the user didn't type a double or the price is null or negative.
	    		   //Same principle than before.
	    		   
		    	   while(price <= 0.0){
			    	   System.out.println("At what price should this item be sold ?");
		    		   try{
		    			   price = sc.nextDouble();
			    	   } catch(InputMismatchException e){
			    		   System.out.println("You're supposed to enter a positive double number for the price.");
			    		   System.out.println("Do you like to try again ? Yes / No");
		    			   sc.nextLine(); //Empties the scanner
		    			   answer = sc.nextLine();
		    			   if(answer.toLowerCase().charAt(0) != 'y')
		        			   break;
			    	   }
		    		   
			    	   if(price <= 0.0){
			    		   System.out.println("You're supposed to enter a positive double number for the price.");
			    		   System.out.println("Do you like to try again ? Yes / No");
		    			   sc.nextLine(); //Empties the scanner
		    			   answer = sc.nextLine();
		    			   if(answer.toLowerCase().charAt(0) != 'y')
		        			   break;
			    	   }
		    	   }
		    	   
		    	   //if we get there and the price is still nod valid, means the user wants to cancel the creation of this item.
		    	   //In this case, the method ends.
		    	   
		    	   if(price > 0.0){

			    	   System.out.println("Is this item vegetarian ? Yes / No");
			    	   sc.nextLine(); //Empties the scanner
			    	   answer = sc.nextLine();
			    	   if(answer.toLowerCase().charAt(0) != 'y'){
			    		   regimeType = RegimeType.Vegetarian;
			    		   System.out.println("The regime type of this item has been set on vegetarian.");
			    	   }
			    	   
			    	   System.out.println("Is this item gluten-free ? Yes / No");
			    	   answer = sc.nextLine();
			    	   if(answer.toLowerCase().charAt(0) != 'y'){
			    		   glutenFree = true;
			    		   System.out.println("This item is defined as gluten-free.");
			    	   }
			    	   
			    	   //Creation of the item
			    	   Item item = new Item(this, name, type, price, regimeType, glutenFree);
			    	   this.listOfItems.add(item);
			    	   
			    	   //We check if the user wants to add this item to his menu, with description of the item created.
			    	   
			    	   System.out.println("Do you want to add this item to the menu ? \n" + item.toString());
			    	   answer = sc.nextLine();
			    	   if(answer.toLowerCase().charAt(0) != 'y'){
			    		   this.menu.add(item); 
			    		   System.out.println("This item has been added to your menu.");
			    	   }
			    	   else{
			    		   System.out.println("This item hasn't been added to your menu");
			    	   }
		    	   }
	    	   }
	    	   
    	   }
    	   sc.close();
       }
       
       
       //Add item to the menu
       public void addItemToMenu(Item item) throws ItemAlreadyInMenuException{
             if(this.getMenu().contains(item))
            	 throw new ItemAlreadyInMenuException();
             else {
            	 this.menu.add(item);
             }
       }
      
      
       //Remove item from the menu
       //Problem if the Item is not in the Menu, exception thrown
       public void removeItemFromMenu(Item item) throws ItemNotInMenuException{
             if(!this.menu.contains(item))
            	 throw new ItemNotInMenuException();
             else {
            	 this.menu.remove(item);
             }
       }

       
       //Create meal
       public void createMeal() throws MealNotValid {
    	   Item starter = null;
    	   Item mainDish = null;
    	   Item dessert = null;
    	   Item[] mealItems = {starter, mainDish, dessert};
    	   String name = "";
    	   String answer = "";
    	   Scanner sc = new Scanner(System.in);
    	   RegimeType regimeType = RegimeType.Vegetarian;
    	   boolean glutenFree = false;
    	   
    	   //Choose a name for this meal. It shouldn't be already attributed to an other existing meal.
    	   
    	   while(name == ""){
        	   System.out.println("What's the name of the meal ?");
    		   name = sc.nextLine();
    		   for(Meal m : listOfMeals){
    			   if(m.getName() == name){
    				   System.out.println("The meal named " + name + " already exists.");
    				   name = "";
    				   break; //Breaks from the for loop only.
    			   }
    		   }
    		   
    		   if(name == ""){
				   System.out.println("Do you like to try again ? Yes / No");
	    		   sc.nextLine();
	    		   answer = sc.nextLine();
	    		   if(answer.toLowerCase().charAt(0) != 'y')
	    			   break;
			   }
    	   }
    	   
    	   //If we reach this state without name, means the user wants to cancel the creation of this meal.
    	   //In this case, the method ends.
    	   
    	   if(name == ""){
    		   
	    	   //The user has to choose whether the meal contains a starter or not.
    		   
	    	   System.out.println("Does this meal contain a starter ? Yes / No");
	    	   answer = sc.nextLine();
	    	   if(answer.toLowerCase().charAt(0) != 'y'){ //Check if the answer is similar to yes (avoids misunderstanding)
	    		   
	    		   //Now, the user has to choose his starter from his starters list.
	    		   //We create this startersName Array List to link names of items with items themselves.
	    		   //If the item is not a starter, his name is set to "".
	    		   //Allows to have a connection between the index of the starters name in startersName and its index in listOfItems
	    		   
	    		   ArrayList<String> startersName = new ArrayList<String>();
	    		   System.out.println("Choose between your list of starters :");
	    		   for(Item i : listOfItems){
	    			   if(i.getItemtype() == ItemType.Starter){
	        			   System.out.println(i.getName());
	        			   startersName.add(i.getName().toLowerCase());
	    			   }
	    			   else{
	    				   startersName.add("");
	    			   }
	    		   }
	    		   
	    		   //We check if the user enters an starter name that is in his item's list (without consideration for case).
	    		   
	    		   while(starter == null){
	    			   answer = sc.nextLine().toLowerCase();
	    			   if(startersName.contains(answer)){
	    				   int index = startersName.indexOf(answer);
	    				   starter = listOfItems.get(index);
	    			   }
	    			   else{
	    				   System.out.println("You do not have an item named " + answer + " in your menu.");
	    				   System.out.println("Do you like to try again ? Yes / No");
	    	    		   answer = sc.nextLine();
	    	    		   if(answer.toLowerCase().charAt(0) != 'y')
	    	    			   break;
	    			   }
	    		   }
	    		   
	    	   }
	    	   
	    	   //The meal necessarily contains a main dish, so we do not have to ask.
	    	   //Same thing than before, he has to select an existing item from his list.
	    	   
	    	   ArrayList<String> mainDishesName = new ArrayList<String>();
			   System.out.println("Choose between your list of main dishes :");
			   
			   for(Item i : listOfItems){
				   if(i.getItemtype() == ItemType.Main_dish){
	    			   System.out.println(i.getName());
	    			   mainDishesName.add(i.getName().toLowerCase());
				   }
				   else{
					   mainDishesName.add("");
				   }
			   }
			   
			   while(mainDish == null){
				   answer = sc.nextLine().toLowerCase();
				   if(mainDishesName.contains(answer)){
					   int index = mainDishesName.indexOf(answer);
					   mainDish = listOfItems.get(index);
				   }
				   else{
					   System.out.println("You do not have an item named " + answer + " in your menu.");
					   System.out.println("Do you like to try again ? Yes / No");
		    		   answer = sc.nextLine();
		    		   if(answer.toLowerCase().charAt(0) != 'y')
		    			   break;
				   }
			   }
			   
	    	   //Exactly the same thing than for starters.
			   
			   System.out.println("Does this meal contain a dessert ? Yes / No");
	    	   answer = sc.nextLine();
	    	   if(answer.toLowerCase().charAt(0) != 'y'){
	    		   ArrayList<String> dessertsName = new ArrayList<String>();
	    		   System.out.println("Choose between your list of desserts :");
	    		   for(Item i : listOfItems){
	    			   if(i.getItemtype() == ItemType.Dessert){
	        			   System.out.println(i.getName());
	        			   dessertsName.add(i.getName().toLowerCase());
	    			   }
	    			   else{
	    				   dessertsName.add("");
	    			   }
	    		   }
	    		   while(dessert == null){
	    			   answer = sc.nextLine().toLowerCase();
	    			   if(dessertsName.contains(answer)){
	    				   int index = dessertsName.indexOf(answer);
	    				   dessert = listOfItems.get(index);
	    			   }
	    			   else{
	    				   System.out.println("You do not have an item named " + answer + " in your menu.");
	    				   System.out.println("Do you like to try again ? Yes / No");
	    	    		   answer = sc.nextLine();
	    	    		   if(answer.toLowerCase().charAt(0) != 'y')
	    	    			   break;
	    			   }
	    		   }
	    		   
	    	   }
	    	   
	    	   //We select the type of the meal according to the user's choices.
	    	   //If the type is not valid, an exception is thrown.
	    	   
	    	   MealType mealType;
	    	   if(starter == null && dessert != null && mainDish != null)
	    		   mealType = MealType.MainDish_and_Dessert;
	    	   else if(starter != null && dessert == null && mainDish != null)
	    		   mealType = MealType.Starter_and_MainDish;
	    	   else if(starter != null && dessert != null && mainDish != null)
	    		   mealType = MealType.Full_meal;
	    	   else
	    		   throw new MealNotValid();
	    	   
	   		
	    	   //The regime type is set on vegetarian if all the items from the meal are vegetarian.
	    	   //Same thing for gluten-free option.
	    	   //(by default on vegetarian(respectively gluten-free) and get standard if a single item is not vegetarian(respectively gluten-free)).
	    	
	    	   for(Item item : mealItems){
	    		   if(item.getRegimetype() == RegimeType.Standard)
	    			   regimeType = RegimeType.Standard;
	    		   if(!item.isGluten_free())
	    			   glutenFree = false;
	    	   }
	    	   
	    	   Meal meal = new Meal(this, name, mealType, mealItems, regimeType, glutenFree);
	    	   this.listOfMeals.add(meal);
	    	   
	    	 //We check if the user wants to add this meal to his menu, with description of the meal created.
	    	   
	    	   System.out.println("Do you want to add this meal to the menu ? \n" + meal.toString());
	    	   answer = sc.nextLine();
	    	   if(answer.toLowerCase().charAt(0) != 'y'){
	    		   this.mealsInMenu.add(meal); 
	    		   System.out.println("This meal has been added to your menu.");
	    	   }
	    	   else{
	    		   System.out.println("This meal hasn't been added to your menu");
	    	   }
    	   }
    	   sc.close();
       }
       
       
       //Add meal to the menu
       public void addMealToMenu(Meal meal) throws MealAlreadyInMenuException{
             if (this.mealsInMenu.contains(meal))
            	 throw new MealAlreadyInMenuException();
             else {
                 this.mealsInMenu.add(meal);         	 
             }

       }
      
      
       //Remove a meal from the menu
       //Problem if the meal is not in the menu, exception thrown
       public void removeMealFromMenu(Meal meal) throws MealNotInMenuException{
             if(!this.mealsOfTheWeek.contains(meal))
            	 throw new MealNotInMenuException();
             else {
            	 this.mealsOfTheWeek.remove(meal);
             }
       }
       
       //Add meal to the meals of the Week list
       public void addMealToMealsOfTheWeek(Meal meal) throws MealNotInMenuException, MealAlreadyMealOfTheWeekException {
           if(!this.mealsInMenu.contains(meal))
        	   throw new MealNotInMenuException();
           else if (this.mealsOfTheWeek.contains(meal))
        	   throw new MealAlreadyMealOfTheWeekException();
           else {
        	   this.mealsOfTheWeek.add(meal);
           }
       }
       
       //Remove a meal from the meals of the week list
       public void removeMealFromMealsOfTheWeek(Meal meal) throws MealNotInMenuException, MealNotMealOfTheWeekException {
    	   if(!this.mealsInMenu.contains(meal))
        	   throw new MealNotInMenuException();
    	   else if (!this.mealsOfTheWeek.contains(meal))
        	   throw new MealNotMealOfTheWeekException();
    	   else {
        	   this.mealsOfTheWeek.remove(meal);
           }
       }

      
       //Sorting of shipped orders
       public void sortShippedOrder(){
    	   ShippedOrder policy = system.getShippolicy();
    	   if(policy == ShippedOrder.MostLeastOrderedHalfMeal){
    		   System.out.println(this.getSortedShippedHalfMeals());
    	   }
    	   if (policy == ShippedOrder.MostLeastOrderedItemALaCarte){
    		   System.out.println(this.getSortedShippedItems());
    	   }
       }
       
       //Return the list of meals from the most shipped to the least shipped
       public ArrayList<Meal> getSortedShippedHalfMeals() {
    	   ArrayList<Meal> sortedHalfmeals = new ArrayList<Meal>();
    	   ArrayList<Meal> halfmeals = this.getListOfMeals();
    	   //We extract the list of half meals
    	   for (Meal meal : halfmeals) {
    		   if (meal.getMealtype().equals(MealType.Full_meal)){
    			   halfmeals.remove(meal);
    		   }
    	   }
    	   if (this.getListOfMeals().isEmpty()){
    		   System.out.println("There is no meal.");
    	   }
    	   else if (halfmeals.isEmpty()){
    		   System.out.println("There is no half meal");
    	   }
    	   else {
    		   sortedHalfmeals.add(halfmeals.get(0));
    		   for (Meal halfmeal : this.getListOfMeals()){
    			   int i = 0;
    			   while (halfmeal.getCounter()> sortedHalfmeals.get(i).getCounter()){
            	   i++;
    			   }
    			   sortedHalfmeals.add(i, halfmeal);
    		   }
    	   }
    	   return sortedHalfmeals;
       }
       
     //Return the list of items from the most shipped to the least shipped
       public ArrayList<Item> getSortedShippedItems() {
    	   ArrayList<Item> items = new ArrayList<Item>();
    	   if (this.getMenu().isEmpty()){
    		   System.out.println("Menu is empty.");
    	   }
    	   else {
    		   items.add(this.getMenu().get(0));
    		   for (Item item : this.getMenu()){
        		   int i = 0;
        		   while (item.getCounter()> items.get(i).getCounter()){
        			   i++;
        		   }
        		   items.add(i, item);
        	   }
    	   }
    	   return items;
       }
       
}