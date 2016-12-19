package fr.ecp.IS1220.MyFoodora.Users;
 
import java.util.ArrayList;
import fr.ecp.IS1220.MyFoodora.Exceptions.*;
import fr.ecp.IS1220.MyFoodora.Food.*;
import fr.ecp.IS1220.MyFoodora.System.*;
 
public class Restaurant extends User{
       private Point adress;
       private String username;
       private ArrayList<Item> menu;
       private ArrayList<Meal> listOfMeals;
       private ArrayList<Meal> mealsOfTheWeek;
       private double generic_discount_factor;
       private double specific_discount_factor;
       private ArrayList<Order> shippedOrder;
      
 
       public Restaurant(String name, Point adress, String username) {
             super(name);
             this.adress = adress;
             this.username = username;
             this.menu = new ArrayList<Item>();
             this.listOfMeals = new ArrayList<Meal>();
             this.mealsOfTheWeek = new ArrayList<Meal>();
             this.shippedOrder = new ArrayList<Order>();
       }
 
 
       public Restaurant(String name, Point adress, String username,
                    double generic_discount_factor, double specific_discount_factor) {
             super(name);
             this.adress = adress;
             this.username = username;
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
 
       public String getUsername() {
             return username;
       }
 
       public void setUsername(String username) {
             this.username = username;
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
 
 
       //Add item to the menu
       public void addItemToMenu(Item item){
             ArrayList<Item> newMenu = getMenu();
             newMenu.add(item);
             setMenu(newMenu);
       }
      
      
       //Remove item from the menu
       //Problem if the Item is not in the Menu, exception thrown
       public void removeItemFromMenu(Item item) throws ItemNotInMenuException{
             ArrayList<Item> newMenu = getMenu();
             if(!newMenu.contains(item))
                    throw new ItemNotInMenuException();
             else
             {
                    newMenu.remove(item);
                    setMenu(newMenu);
             }
 
       }
      
      
       //Create a new meal
       public void addMealToMenu(Meal meal){
             ArrayList<Meal> newListOfMeals = getListOfMeals();
             newListOfMeals.add(meal);
             setListOfMeals(newListOfMeals);
       }
      
      
       //Remove a meal from the menu
       //Problem if the meal is not in the menu, exception thrown
       public void removeMealFromMenu(Meal meal) throws MealNotInMenuException{
             ArrayList<Meal> newListOfMeals = getListOfMeals();
             if(!newListOfMeals.contains(meal))
                    throw new MealNotInMenuException();
             else
             {
             newListOfMeals.remove(meal);
             setListOfMeals(newListOfMeals);
             }
       }

      
       //Sorting of shipped orders
       public void sortShippedOrder(){
            
       }
}