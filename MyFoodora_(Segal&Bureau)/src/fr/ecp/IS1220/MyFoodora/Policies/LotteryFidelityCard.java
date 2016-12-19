package fr.ecp.IS1220.MyFoodora.Policies;

import java.util.ArrayList;
import fr.ecp.IS1220.MyFoodora.System.*;

public class LotteryFidelityCard {
	private int id;
	private static int counter = 1;
	
	public LotteryFidelityCard(){
		this.id = counter;
		counter++;
	}

	public int getId() {
		return id;
	}
	
	
}
