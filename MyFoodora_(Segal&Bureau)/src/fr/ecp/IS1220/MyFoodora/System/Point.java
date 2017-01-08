package fr.ecp.IS1220.MyFoodora.System;

import java.io.*;

/**
 * This class is used for the address and position of all kind of users
 */
public class Point implements Serializable{

	private static final long serialVersionUID = 2792695309218572291L;
	
	double x;
	double y;
	
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Compute the distance between the actual point and the point in argument
	 * @param the second point
	 * @return the distance between the two points
	 */
	public double getDistanceTo(Point p){
		return Math.sqrt((p.getX()-this.x)*(p.getX()-this.x)+(p.getY()-this.y)*(p.getY()-this.y));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	
}
