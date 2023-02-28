package tools_of_auctioneer;

import java.util.ArrayList;
import java.util.List;

import tools_of_bus.Ride;

public class ComplexRide {
	
	public List <Ride> winRides = new ArrayList<>();
	
	public ComplexRide(Ride... rides) {
		for(Ride ride : rides) {
			winRides.add(ride);
		}
	}
	
	public double getTotalBid() {
		double result=0;
		for(int i=0; i<winRides.size();i++) {
			result += winRides.get(i).getBusBid();
		}
		result = Math.ceil(result * Math.pow(10, 2)) / Math.pow(10, 2);
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(Ride ride : winRides) {
			result.append(ride + ", ");
		}
		result.append("TotalBid: " + getTotalBid());
		return result.toString();
	}

}
