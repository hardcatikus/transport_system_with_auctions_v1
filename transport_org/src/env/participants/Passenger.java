package participants;

public class Passenger {
	
	public static int numberOfPassengers = 0;
	
    private String name;	
	private double price;
	private String startPoint;
	private String endPoint;
	private String startTime;
	private String endTime;
	private String wayTime;
	private String passengerState;
	
    public Passenger(String name, double price, String startPoint, String endPoint, String startTime) {
    	this.name = name;
        this.price = price;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.passengerState = "Unassigned";
    }
    
    public String getName() {
    	return name;
    }

    public double getPrice() {
        return price;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getWayTime() {
        return wayTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setWayTime(String wayTime) {
        this.wayTime = wayTime;
    }
    
    public String getPassengerState() {
        return passengerState;
    }

    public void setPassengerState(String passengerState) {
        this.passengerState = passengerState;
    }
    
    @Override
    public String toString() {
    	return "Passenger: " + name;// + ", Стоимость поездки = " + price 
//    				+ ", Начальная точка = " + startPoint 
//    				+ ", Конечная точка = " + endPoint 
//    				+ ", Время заказа = " + startTime
//    				+ ", Состояние = " + passengerState;
    }
}
