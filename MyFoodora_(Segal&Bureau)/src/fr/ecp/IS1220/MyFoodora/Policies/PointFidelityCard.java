package fr.ecp.IS1220.MyFoodora.Policies;

public class PointFidelityCard {
	private int points;
	private boolean discount = false;
	
	public PointFidelityCard(){
		this.points = 0;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}
	
	
}
