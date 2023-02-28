package tools_of_bus;

import participants.Bus;
import participants.Passenger;

public class Ride {
	
	private int id;
	private Passenger passenger;
	private Bus bus;
	private double busBid;
	
	Ride(int id, Passenger passenger, Bus bus, double busBid){
		this.id=id;
		this.passenger = passenger;
		this.bus = bus;
		this.busBid = busBid;
	}
	
    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public double getBusBid() {
        return busBid;
    }

    public void setBusBid(double busBid) {
        this.busBid = busBid;
    }
    
    public int getId() {
    	return id;
    }
    
    @Override
    public String toString() {
    	return "Ride: " +passenger + "; "+ bus + "; Bid: " + busBid;
    }

}

