// CArtAgO artifact code for project transport_org

package tools_of_auctioneer;

import cartago.*;
import java.util.ArrayList;
import java.util.List;
import participants.Passenger;

public class RequestGatherer extends Artifact {
	
	public static List<Passenger> passengerList = new ArrayList<>();
	
	void init(int maxNumberOfPassengers) {
		defineObsProperty("treshold", maxNumberOfPassengers);
		defineObsProperty("gathering_state", "not opened");
		defineObsProperty("passenger_list", passengerList);
	}

	@OPERATION
	void checkAuctionState() {
		if(passengerList.size() == 0) {
			getObsProperty("gathering_state").updateValue("started");
			System.out.println("[REQUEST GATHERER] Gathering is " + getObsProperty("gathering_state").stringValue());
		}
		if(passengerList.size() == getObsProperty("treshold").intValue()) {
			getObsProperty("gathering_state").updateValue("closed");
			getObsProperty("passenger_list").updateValue(passengerList);
			printPassengerList();
			System.out.println("[REQUEST GATHERER] Gathering is " + getObsProperty("gathering_state").stringValue());
		}
	}
	
	@OPERATION
	void addPassenger(String name, double price, String startPoint, String endPoint, String startTime) {
		checkAuctionState();
		Passenger passenger = new Passenger(name, price, startPoint, endPoint, startTime);
		passengerList.add(passenger);
		checkAuctionState();
	}
	
	@OPERATION
	void printPassengerList() {
		for(int i=0; i < passengerList.size(); i++) {
			System.out.println(passengerList.get(i));
		}
	}

}

