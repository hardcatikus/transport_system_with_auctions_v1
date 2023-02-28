// CArtAgO artifact code for project transport_org

package tools_of_bus;

import java.util.ArrayList;
import java.util.List;

import cartago.*;
import participants.Bus;

public class BusParkCreator extends Artifact {
	
	public static List<Bus> busList = new ArrayList<>();

	
	void init() {
		defineObsProperty("bus_list", busList);
	}

	@OPERATION
	void addBus(String name, double speed, int capacity, String point, double weightA, double weightB, double weightG, OpFeedbackParam <Bus> newBus) {
		double[] weights = {weightA/100, weightB/100, weightG/100};
		Bus bus = new Bus(name, speed, capacity, point, weights);
		busList.add(bus);
		getObsProperty("bus_list").updateValue(busList);
		newBus.set(bus);
	}
	
}

