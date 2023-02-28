// CArtAgO artifact code for project transport_org

package tools_of_auctioneer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cartago.*;
import participants.*;
import tools_of_bus.Ride;
import tools_of_bus.RideSelecter;

public class AuctionManager extends Artifact {
	
	public static int round = 0;
	
	public static List<Bus> busParticipants = new ArrayList<>();
	public static List<Passenger> requests = new ArrayList<>();
	public static List<ComplexRide> roundWinners = new ArrayList<>();
	public static ComplexRide winnerRides = new ComplexRide();
	public static ComplexRide endResult = new ComplexRide();
	
	void init() {
		defineObsProperty("auction_state", "not started");
		defineObsProperty("bus_participants", busParticipants);
		defineObsProperty("requests", requests);
		defineObsProperty("round", round);
		defineObsProperty("end_result", endResult);
	}

	@OPERATION
	void fixatePassengerList(List<Passenger> passengerList) {
		System.out.println("[AUCTION MANAGER] A set of passengers has been gathered");
		requests = new ArrayList<>(passengerList);
		getObsProperty("requests").updateValue(requests);
	}
	
	@OPERATION
	void fixateBusList(List<Bus> busList) {
		System.out.println("[AUCTION MANAGER] A set of bus has been gathered");
		busParticipants = new ArrayList<>(busList);
		getObsProperty("bus_participants").updateValue(busParticipants);
	}	
	
	@OPERATION
	void launchAuction() {
		getObsProperty("auction_state").updateValue("started");
		System.out.println("[AUCTION MANAGER] Round " + ++round + " has been started");
		getObsProperty("round").updateValue(round);
	}

	
	@OPERATION
	void countUp() {
		if(RideSelecter.countResults == busParticipants.size() && round == 1) {
			chooseRoundOneWinners();
			for(int i=0; i < winnerRides.winRides.size(); i++) {
				System.out.println(winnerRides.winRides.get(i));
			}
			roundWinners.add(0,winnerRides);
			System.out.println("[AUCTION MANAGER] Round " + round + " has been finished");
			launchNextRound();
		}
		if(RideSelecter.countResults == busParticipants.size() && round == 2) {
			ComplexRide roundTwoWinner = RideSelecter.complexRideSecondRound.get(0);
			for(int i=0; i < RideSelecter.complexRideSecondRound.size(); i++) {
				if(roundTwoWinner.getTotalBid() > RideSelecter.complexRideSecondRound.get(i).getTotalBid()) {
					roundTwoWinner = RideSelecter.complexRideSecondRound.get(i);
				}
			}
			roundWinners.add(1,roundTwoWinner);
			System.out.println(roundTwoWinner);
			System.out.println("[AUCTION MANAGER] Round " + round + " has been finished");
			launchNextRound();
		}
		if(RideSelecter.countResults == busParticipants.size() && round == 3) {
			ComplexRide roundThreeWinner = RideSelecter.complexRideThirdRound.get(0);
			for(int i=0; i < RideSelecter.complexRideThirdRound.size(); i++) {
				if(roundThreeWinner.getTotalBid() > RideSelecter.complexRideThirdRound.get(i).getTotalBid()) {
					roundThreeWinner = RideSelecter.complexRideThirdRound.get(i);
				}
			}
			roundWinners.add(2,roundThreeWinner);
			System.out.println(roundThreeWinner);
			System.out.println("[AUCTION MANAGER] Round " + round + " has been finished");
			getObsProperty("auction_state").updateValue("closed");
		}
//		if(round == requests.size()) {
//			getObsProperty("auction_state").updateValue("closed");
//		}
	}
	
	void chooseRoundOneWinners() {		
		for(int i=0; i < requests.size(); i++) {
			winnerRides.winRides.add(i,RideSelecter.rides.get(i));
			for(int j=1; j < busParticipants.size(); j++) {
				Ride currentRide = RideSelecter.rides.get(i+j*requests.size());
				if(currentRide.getBusBid() < winnerRides.winRides.get(i).getBusBid()) {
					winnerRides.winRides.set(i,RideSelecter.rides.get(i+j*requests.size()));
				}

			}
		}
	}

	
	void launchNextRound() {
		
		RideSelecter.countResults = 0;
		System.out.println("[AUCTION MANAGER] Round " + ++round + " has been started");
		getObsProperty("round").updateValue(round);
		
	}
	
	@OPERATION
	public void declareResults() {
		ComplexRide winCombination = (roundWinners.get(0).getTotalBid() > roundWinners.get(2).getTotalBid())? roundWinners.get(2): roundWinners.get(0);
		findSecondRoundWinnerCombination();
		winCombination = (winCombination.getTotalBid() > roundWinners.get(2).getTotalBid())? roundWinners.get(1): winCombination;
		endResult = winCombination;
		getObsProperty("end_result").updateValue(endResult);
		System.out.println("[AUCTION MANAGER] The auction has been finished");
		System.out.println("[AUCTION MANAGER] Winner combination: " + endResult);
		JOptionPane.showMessageDialog(null, endResult,"Winner combination", JOptionPane.INFORMATION_MESSAGE);
		getObsProperty("auction_state").updateValue("results_ready");
	}
	
	
	public void findSecondRoundWinnerCombination() {
		for(int i=0; i< 2;i++) {
			if(roundWinners.get(0).winRides.get(i).getBus() != roundWinners.get(1).winRides.get(0).getBus() 
					&& roundWinners.get(0).winRides.get(i).getBus() != roundWinners.get(1).winRides.get(1).getBus() 
					&& roundWinners.get(0).winRides.get(i).getPassenger() != roundWinners.get(1).winRides.get(0).getPassenger()
					&& roundWinners.get(0).winRides.get(i).getPassenger() != roundWinners.get(1).winRides.get(1).getPassenger()) {
				roundWinners.get(1).winRides.add(roundWinners.get(0).winRides.get(i));
			}
		}
	}
	
}

