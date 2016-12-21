package fr.ecp.IS1220.MyFoodora.Users;
 
import java.util.ArrayList;
import fr.ecp.IS1220.MyFoodora.Exceptions.*;
import fr.ecp.IS1220.MyFoodora.Food.*;
import fr.ecp.IS1220.MyFoodora.Policies.ShippedOrder;
import fr.ecp.IS1220.MyFoodora.System.*;
 
public class Restaurant extends User{
       private Point adress;
       private ArrayList<Item> menu;
       private ArrayList<Meal> listOfMeals;
       private ArrayList<Meal> mealsOfTheWeek;
       private double generic_discount_factor;
       private double specific_discount_factor;
       private ArrayList<Order> shippedOrder;
       private MyFoodoraSystem system = MyFoodoraSystem.getInstance();
       private int counter = 0;


       public Restaurant(String name, Point adress, String username, double hashedPassword) {
             super(name, username, hashedPassword);
             this.adress = adress;
             this.menu = new ArrayList<Item>();
             this.listOfMeals = new ArrayList<Meal>();
             this.mealsOfTheWeek = new ArrayList<Meal>();
             this.shippedOrder = new ArrayList<Order>();
       }
 
 
       public Restaurant(String name, Point adress, String username, double hashedPassword,
                    double generic_discount_factor, double specific_discount_factor) {
             super(name, username, hashedPassword);
             this.adress = adress;
             this.menu = new ArrayList<Item>();
             this.listOfMeals = new ArrayList<Meal>();
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
    	   ArrayList<Order> newlist = this.getShippedOrder();
    	   newlist.add(order);
    	   this.setShippedOrder(newlist);
       }
       
       
       //Return starters of the restaurant in the menu
       public ArrayList<Item> getStarters() {
    	   ArrayList<Item> starters = this.getMenu();
    	   for (Item item : starters){
    		   if(!item.getItemtype().equals(ItemType.Starter))
    			   starters.remove(item);
    	   }
    	   return starters;
       }

       //Return main dishes of the restaurant in the menu
       public ArrayList<Item> getMainDishes() {
    	   ArrayList<Item> mainDishes = this.getMenu();
    	   for (Item item : mainDishes){
    		   if(!item.getItemtype().equals(ItemType.Main_dish))
    			   mainDishes.remove(item);
    	   }
    	   return mainDishes;
       }

       //Return desserts of the restaurant in the menu
       public ArrayList<Item> getDesserts() {
    	   ArrayList<Item> desserts = this.getMenu();
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
       

       
       //Add item to the menu
       public void addItemToMenu(Item item) throws ItemAlreadyInMenuException{
             if(this.getMenu().contains(item))
            	 throw new ItemAlreadyInMenuException();
             else {
            	 ArrayList<Item> newMenu = this.getMenu();
            	 newMenu.add(item);
            	 this.setMenu(newMenu);
             }
       }
      
      
       //Remove item from the menu
       //Problem if the Item is not in the Menu, exception thrown
       public void removeItemFromMenu(Item item) throws ItemNotInMenuException{
             ArrayList<Item> newMenu = this.getMenu();
             if(!newMenu.contains(item))
            	 throw new ItemNotInMenuException();
             else {
            	 newMenu.remove(item);
            	 this.setMenu(newMenu);
             }
       }
      
      
       //Add meal to the menu
       public void addMealToMenu(Meal meal) throws MealAlreadyInMenuException{
             ArrayList<Meal> newListOfMeals = this.getListOfMeals();
             if (newListOfMeals.contains(meal))
            	 throw new MealAlreadyInMenuException();
             else {
                 newListOfMeals.add(meal);
                 this.setListOfMeals(newListOfMeals);          	 
             }

       }
      
      
       //Remove a meal from the menu
       //Problem if the meal is not in the menu, exception thrown
       public void removeMealFromMenu(Meal meal) throws MealNotInMenuException{
             ArrayList<Meal> newListOfMeals = this.getListOfMeals();
             if(!newListOfMeals.contains(meal))
            	 throw new MealNotInMenuException();
             else {
            	 newListOfMeals.remove(meal);
            	 this.setListOfMeals(newListOfMeals);
             }
       }
       
       //Add meal to the meals of the Week list
       public void addMealToMealsOfTheWeek(Meal meal) throws MealNotInMenuException, MealAlreadyMealOfTheWeekException {
           if(!this.getListOfMeals().contains(meal))
        	   throw new MealNotInMenuException();
           else if (this.getMealsOfTheWeek().contains(meal))
        	   throw new MealAlreadyMealOfTheWeekException();
           else {
        	   ArrayList<Meal> newMealsOfTheWeek = this.getMealsOfTheWeek();
        	   newMealsOfTheWeek.add(meal);
        	   this.setMealsOfTheWeek(newMealsOfTheWeek);
           }
       }
       
       //Remove a meal from the meals of the week list
       public void removeMealFromMealsOfTheWeek(Meal meal) throws MealNotInMenuException, MealNotMealOfTheWeekException {
    	   if(!this.getListOfMeals().contains(meal))
        	   throw new MealNotInMenuException();
    	   else if (!this.getMealsOfTheWeek().contains(meal))
        	   throw new MealNotMealOfTheWeekException();
    	   else {
        	   ArrayList<Meal> newMealsOfTheWeek = this.getMealsOfTheWeek();
        	   newMealsOfTheWeek.remove(meal);
        	   this.setMealsOfTheWeek(newMealsOfTheWeek);
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