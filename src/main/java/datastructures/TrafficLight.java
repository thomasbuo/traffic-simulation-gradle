package datastructures;

public class TrafficLight {
	private String status;

	public TrafficLight() {
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
