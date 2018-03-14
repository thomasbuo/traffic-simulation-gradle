package datastructures;

public class TrafficLight {
	private String status;
	private int road;
	private int intersection;

	public TrafficLight(int road, int intersection) {
		this.road = road;
		this.intersection = intersection;
		
		this.status = "R";
	}

	// GETTERS / SETTERS
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		if (status != "R" && status != "G") {
			System.out.println("Illegal status '" + status + "'");
		}
		this.status = status;
	}
	
	

	// ACTIONS
	
	public void toggle() {
		if (this.status == "R") {
			this.setStatus("G");
		} else if (this.status == "G") {
			this.setStatus("R");
		}
	}
}
