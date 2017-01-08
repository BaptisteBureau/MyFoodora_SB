package fr.ecp.IS1220.MyFoodora.Policies;

import java.io.*;

public class PointFidelityCard implements Serializable{
	
	private static final long serialVersionUID = -4256746499434498918L;
	
	private int points;
	
	public PointFidelityCard(){
		this.points = 0;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean offerDiscount() {
		if (this.points >= 100){
			return true;
		} else {
			return false;
		}
	}

	public void reinitializeCard(){
		this.points = 0;
	}
}
