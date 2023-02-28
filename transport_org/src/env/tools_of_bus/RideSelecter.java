// CArtAgO artifact code for project transport_org

package tools_of_bus;

import cartago.*;

import java.util.ArrayList;
import java.util.List;

import participants.Bus;
import participants.Passenger;
import tools_of_auctioneer.ComplexRide;

public class RideSelecter extends Artifact {
	
		public static List<Ride> rides = new ArrayList<>();
		public static int countResults = 0;
		public static int numOfRides = 0;
		public static List<ComplexRide> complexRideSecondRound = new ArrayList<>();
		public static List<ComplexRide> complexRideThirdRound = new ArrayList<>();
	
	
		void init() {
			defineObsProperty("all_rides", rides);
			defineObsProperty("count_results", countResults);
		}

	@OPERATION
	public void createRideBids(Bus bus, List<Passenger> passengerList) {
		
		double[] bids = calculateBids(bus,passengerList);
		for(int i=0; i<bids.length; i++) {
			Ride ride = new Ride(++numOfRides,passengerList.get(i), bus, bids[i]);
			rides.add(ride);
			bus.addRide(ride);
			System.out.println(ride);
		}	
		System.out.println("[RIDE SELECTER] rides have been added by " +  bus.getName());
		getObsProperty("all_rides").updateValue(rides);
		getObsProperty("count_results").updateValue(++countResults);
	}
	
	
	public int calculatePathsLength(String busPoint, Passenger passenger) {
		int length = 0;
		length+=PathBuilder.getLengthOfPath(busPoint + passenger.getStartPoint());
		length+=PathBuilder.getLengthOfPath(passenger.getStartPoint() + passenger.getEndPoint()); 
		return length;
	}
	
	public double[] calculateBids(Bus bus, List<Passenger> passengerList) {
		double[] bids = new double[passengerList.size()];
		double[] weights = bus.getWeights();
		for(int i=0; i<bids.length; i++) {
			bids[i]+=(-1) * weights[0] * passengerList.get(i).getPrice() 
					+ weights[1] * calculatePathsLength(bus.getPoint(),passengerList.get(i)) + weights[2] * 360;
			bids[i] = Math.ceil(bids[i] * Math.pow(10, 2)) / Math.pow(10, 2);
		}
		return bids;
	}
	

	@OPERATION
	public void findBestBidsCombination(int round, Bus bus) { 
		
		if(round == 2) {
			List<Ride> rideBids = bus.getRides();
			Ride minRide = rideBids.get(0);
			for(int i=1; i < rideBids.size(); i++) {
				if (minRide.getBusBid() > rideBids.get(i).getBusBid()) {
					minRide = rideBids.get(i);
				}
			}
			Ride nextMinRide = (minRide.getId() == rideBids.get(0).getId())? rideBids.get(1) : rideBids.get(0);
			for(int i=0; i < rideBids.size(); i++) {
				if (nextMinRide.getBusBid() > rideBids.get(i).getBusBid() && minRide.getId() != rideBids.get(i).getId()) {
					nextMinRide = rideBids.get(i);
				}
			}
			ComplexRide complexride = new ComplexRide(minRide,nextMinRide);
			complexRideSecondRound.add(complexride);
			System.out.println(minRide + " and " + nextMinRide);
			System.out.println("[RIDE SELECTER] rides have been added by " +  minRide.getBus().getName());
			getObsProperty("count_results").updateValue(++countResults);
		}
		if(round == 3) {
			List<Ride> rideBids = bus.getRides();
			Ride minRide = rideBids.get(0);
			for(int i=1; i < rideBids.size(); i++) {
				if (minRide.getBusBid() > rideBids.get(i).getBusBid()) {
					minRide = rideBids.get(i);
				}
			}
			Ride nextMinRide = (minRide.getId() == rideBids.get(0).getId())? rideBids.get(1) : rideBids.get(0);
			for(int i=0; i < rideBids.size(); i++) {
				if (nextMinRide.getBusBid() > rideBids.get(i).getBusBid() && minRide.getId() != rideBids.get(i).getId()) {
					nextMinRide = rideBids.get(i);
				}
			}
			Ride nextMinRide2 = (minRide.getId() == rideBids.get(0).getId())? rideBids.get(1) : rideBids.get(0);
			for(int i=0; i < rideBids.size(); i++) {
				if (nextMinRide2.getBusBid() > rideBids.get(i).getBusBid()
						&& minRide.getId() != rideBids.get(i).getId() && nextMinRide.getId() != rideBids.get(i).getId()) {
					nextMinRide2 = rideBids.get(i);
				}
			}
			ComplexRide complexride = new ComplexRide(minRide,nextMinRide, nextMinRide2);
			complexRideThirdRound.add(complexride);
			System.out.println(minRide + " and " + nextMinRide + " and " + nextMinRide);
			System.out.println("[RIDE SELECTER] rides have been added by " +  minRide.getBus().getName());
			getObsProperty("count_results").updateValue(++countResults);
		}
	}
}



