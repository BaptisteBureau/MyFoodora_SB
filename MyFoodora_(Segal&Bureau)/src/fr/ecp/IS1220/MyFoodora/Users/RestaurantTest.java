package fr.ecp.IS1220.MyFoodora.Users;

import fr.ecp.IS1220.MyFoodora.Exceptions.MealNotValid;
import fr.ecp.IS1220.MyFoodora.System.Point;

public class RestaurantTest {
	public static void main(String[] Arg){
		Point adresse = new Point(0,0);
		Restaurant restaurant = new Restaurant("Macdo", adresse, "Macdo", "125");
		try {
			restaurant.createMeal();
		} catch (MealNotValid e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
