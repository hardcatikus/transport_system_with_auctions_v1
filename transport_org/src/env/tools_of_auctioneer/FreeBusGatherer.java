// CArtAgO artifact code for project transport_org

package tools_of_auctioneer;

import java.util.ArrayList;
import java.util.List;

import cartago.*;
import participants.Bus;

public class FreeBusGatherer extends Artifact {
	
	public static List<Bus> freeBusList = new ArrayList<>();
	
	void init() {
		defineObsProperty("free_bus_list", freeBusList);
		defineObsProperty("free_bus_list_state", "not started");
	}

	@OPERATION
	void findFreeBus(List<Bus> busList) {
		getObsProperty("free_bus_list_state").updateValue("started");
		System.out.println("[FREE BUS GATHERER] Gathering is " + getObsProperty("free_bus_list_state").stringValue());
		for (Bus bus : busList) {
			if(bus.getBusState().equals("has free sits")) {
				freeBusList.add(bus);
				System.out.println(bus);
			}
		}
		getObsProperty("free_bus_list").updateValue(freeBusList);
		getObsProperty("free_bus_list_state").updateValue("closed");
		System.out.println("[FREE BUS GATHERER] Gathering is "  + getObsProperty("free_bus_list_state").stringValue());
	}
}

