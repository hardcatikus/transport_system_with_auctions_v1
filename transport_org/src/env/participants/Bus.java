package participants;

import java.util.ArrayList;
import java.util.List;

import tools_of_bus.Ride;

public class Bus {
	
	public static int numberOfBuses = 0;
	
    private String name;
	private double speed;
    private int capacity;
    private String point;
    private String busState;
    private double[] weights = new double[3];
    private List<Ride> rides = new ArrayList<>();

    public Bus(String name, double speed, int capacity, String point, double[] weights) {
    	this.name = name;
        this.speed = speed;
        this.capacity = capacity;
        this.point = point;
        this.busState = "has free sits";
        this.weights = weights;
    }
    
    public void addRide(Ride ride) {
    	this.rides.add(ride);
    }
    
    public List<Ride> getRides(){
    	return rides;
    }
    
    public String getName() {
    	return name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getBusState() {
        return busState;
    }

    public void setBusState(String busState) {
        this.busState = busState;
    }
    
    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }
    
    @Override
    public String toString() {
    	return "Bus: " + name;// + ", Скорость = " + speed
//    			+ ", Вместимость = " + capacity
//    			+ ", Текущее местоположение = " + point
//    			+ ", Текущее состояние = " + busState
//    			+ ", Веса = [" + weights[0] + ", " + weights[1] + ", "+ weights[2] + "]";
    }
    


}
