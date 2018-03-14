package datastructures;

public class Connection {

	private int road;
	private int destination;
	private TrafficLight trafficlight;
	
	public Connection(int road, int destination, TrafficLight trafficlight) {
		this.road = road;
		this.destination = destination;
		
		if (trafficlight == null) {
			this.trafficlight = new TrafficLight(road, destination);
		} else {
			this.trafficlight = trafficlight;
		}
	}

	/**
	 * @return the road
	 */
	public int getRoad() {
		return road;
	}

	/**
	 * @param road the road to set
	 */
	public void setRoad(int road) {
		this.road = road;
	}

	/**
	 * @return the destination
	 */
	public int getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(int destination) {
		this.destination = destination;
	}

	/**
	 * @return the trafficlight
	 */
	public TrafficLight getTrafficlight() {
		return trafficlight;
	}

	/**
	 * @param trafficlight the trafficlight to set
	 */
	public void setTrafficlight(TrafficLight trafficlight) {
		this.trafficlight = trafficlight;
	}

	@Override
	public String toString() {
		return "Connection [road=" + road + ", destination=" + destination + ", trafficlight=" + trafficlight + "]";
	}

}
