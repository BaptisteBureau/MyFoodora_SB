package fr.ecp.IS1220.MyFoodora.Users;

import java.util.ArrayList;

import fr.ecp.IS1220.MyFoodora.Exceptions.*;
import fr.ecp.IS1220.MyFoodora.System.*;

public class Courier extends User{
	private String surname;
	private Point position = new Point(0,0);
	private long phone;
	private ArrayList<Order> deliveries;
	private String username;
	
	//Actual state of the courier : 0 = off-duty and 1 = on-duty
	private int state = 0;
	

	public Courier(String name, String surname, long phone, ArrayList<Order> deliveries,
			String username) {
		super(name);
		this.surname = surname;
		this.phone = phone;
		this.deliveries = new ArrayList<Order>();
		this.username = username;
	}
	

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public ArrayList<Order> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(ArrayList<Order> deliveries) {
		this.deliveries = deliveries;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	
    //Register their account to the system
    //Problem if the account is already registered, throws exception
    public void registerAccount(MyFoodoraSystem system) throws CourierAlreadyRegisteredException{
          ArrayList<Courier> old = system.getRegisteredCouriers();
          if(old.contains(this))
                 throw new CourierAlreadyRegisteredException();
          else
          {
                 old.add(this);
                 system.setRegisteredCouriers(old);
          }   
    }
   
   
    //Unregister their account to the system
    //Problem if the account is not registered, throws exception
    public void unregisterAccount(MyFoodoraSystem system) throws CourierNotRegisteredException{
          ArrayList<Courier> old = system.getRegisteredCouriers();
          if(!old.contains(this))
                 throw new CourierNotRegisteredException();
          else
          {
                 old.remove(this);
                 system.setRegisteredCouriers(old);
          }
    }
	
	//Accept delivery call
	public void acceptDelivery(Order order){
		order.setCourier(this);
	}
	
	
	//Refuse delivery call
	public void refuseDelivery(Order order){
		this.setState(0);
		MyFoodoraSystem.getInstance().allocateCourier(order);
	}
}
