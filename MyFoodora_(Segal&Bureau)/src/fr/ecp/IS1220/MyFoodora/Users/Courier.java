package fr.ecp.IS1220.MyFoodora.Users;

import java.util.ArrayList;

import fr.ecp.IS1220.MyFoodora.Exceptions.*;
import fr.ecp.IS1220.MyFoodora.System.*;

public class Courier extends User{

	private static final long serialVersionUID = 4462665006656044851L;
	
	private Point position;
	private String phone;
	private Order currentDelivery = null;
	private ArrayList<Order> deliveries;
	
	//Actual state of the courier : 0 = off-duty and 1 = on-duty
	private int state = 0;
	

	public Courier(String name, Point position, String phone, String username, String password) {
		super(name, username, password);
		this.position = position;
		this.phone = phone;
		this.deliveries = new ArrayList<Order>();
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<Order> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(ArrayList<Order> deliveries) {
		this.deliveries = deliveries;
	}

	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	
    //Register their account to the system
    //Problem if the account is already registered, throws exception
    public void registerAccount() throws CourierAlreadyRegisteredException{
          ArrayList<Courier> old = MyFoodoraSystem.getInstance().getRegisteredCouriers();
          if(old.contains(this))
                 throw new CourierAlreadyRegisteredException();
          else
          {
                 old.add(this);
                 MyFoodoraSystem.getInstance().setRegisteredCouriers(old);
          }   
    }
   
   
    //Unregister their account to the system
    //Problem if the account is not registered, throws exception
    public void unregisterAccount() throws CourierNotRegisteredException{
          ArrayList<Courier> old = MyFoodoraSystem.getInstance().getRegisteredCouriers();
          if(!old.contains(this))
                 throw new CourierNotRegisteredException();
          else
          {
                 old.remove(this);
                 MyFoodoraSystem.getInstance().setRegisteredCouriers(old);
          }
    }
	
	public Order getCurrentDelivery() {
		return currentDelivery;
	}

	public void setCurrentDelivery(Order currentDelivery) {
		this.currentDelivery = currentDelivery;
	}
	
	
	//Accept delivery call
	public void acceptDelivery(){
		if (this.currentDelivery == null){
			System.out.println("You don't have any deliveries to do.");
		} else {
			Order order = this.currentDelivery;
			this.currentDelivery = null;
			order.setDelivered();
			order.getCustomer().addOrderToCustomer(order);
			order.getRestaurant().addOrder(order);
			this.deliveries.add(order);
		}
	}
	


	//Refuse delivery call
	public void refuseDelivery(){
		this.setState(0);
		if (this.currentDelivery == null){
			System.out.println("You don't have any deliveries to do.");
		} else {
			Order order = this.currentDelivery;
			MyFoodoraSystem.getInstance().findCourier(order);
			this.currentDelivery = null;
		}
	}


	@Override
	public String toString() {
		return "Courier [name=" + this.getName() + ", username=" + this.getUsername() + ", status=" + this.getStatus() + ", state=" + state + ", position=" + position + ", phone=" + phone + "]";
	}
	
	
}
