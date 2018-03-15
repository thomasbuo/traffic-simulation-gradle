package datastructures;

import java.util.ArrayList;

public class Intersection {
	
	private int x_coord;
	private int y_coord;
	
	private int tl_phase_length;
	private int time_till_toggle;
	
	private int cost;
	private Intersection parent;
	public void resetCost()
	{
		cost = 1000000000;
	}
	public void resetParent()
	{
		parent = null;
	}
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Intersection getParent() {
		return parent;
	}

	public void setParent(Intersection parent) {
		this.parent = parent;
	}

	
	
	/**
	 * Connections are of format (road, intersection, traffic light)
	 */
	private ArrayList<Connection> connections;
	
	public Intersection(int x, int y) {
		this.x_coord = x;
		this.y_coord = y;
		
		this.tl_phase_length = 120;
		this.time_till_toggle = this.tl_phase_length;
		
		this.connections = new ArrayList<Connection>();
	}
	
	// GETTERS / SETTERS
	
	public int getXCoord() {
		return x_coord;
	}
	
	public int getYCoord() {
		return y_coord;
	}
	
	public int getTlPhaseLength() {
		return tl_phase_length;
	}

	public void setTlPhaseLength(int tl_phase_length) {
		this.tl_phase_length = tl_phase_length;
	}

	public ArrayList<Integer> getOutgoingRoadIds() {
		ArrayList<Integer> outgoing = new ArrayList<Integer>();
		for (Connection c : this.connections) {
			outgoing.add(c.getRoad());
		}
		
		return outgoing;
	}
	
	public ArrayList<Integer> getConnectedIntersectionIds() {
		ArrayList<Integer> intersections = new ArrayList<Integer>();
		for (Connection c : this.connections) {
			intersections.add(c.getDestination());
		}
		
		return intersections;
	}
	
	public int getRoadTo(int to) {
		for (Connection c : this.connections) {
			if ((int) c.getDestination() == to) {
				return c.getDestination();
			}
		}
		
		return -1;
	}
	
	public ArrayList<Connection> getConnections() {
		return this.connections;
	}
	
	public ArrayList<TrafficLight> getTrafficLights() {
		ArrayList<TrafficLight> traffic_lights = new ArrayList<TrafficLight>();
		for (Connection c : this.connections) {
			traffic_lights.add(c.getTrafficlight());
		}
		
		return traffic_lights;
	}
	
	// MODIFICATION
	
	public void addConnection(int road_id, int intersection_id, TrafficLight trafficlight) {
		connections.add(new Connection(road_id, intersection_id, trafficlight));
	}
	
	public int removeConnection(int intersection_id) {
		int removed_road = -1;
		for (int i = 0; i < this.connections.size(); i++) {
			if (this.connections.get(i).getDestination() == intersection_id) {
				removed_road = this.connections.get(i).getRoad();
				this.connections.remove(i);
			}
		}
		
		return removed_road;
	}
	
	public void adjustConnectionsAfterIntersectionRemoval(int removed_intersection_id) {
		for (Connection c : this.connections) {
			if (c.getDestination() > removed_intersection_id) {
				c.setDestination(c.getDestination() - 1);
			}
		}
	}
	
	public void adjustConnectionsAfterRoadRemoval(int removed_road_id) {
		for (Connection c : this.connections) {
			if (c.getRoad() > removed_road_id) {
				c.setRoad(c.getRoad() - 1);
			}
		}
	}
	
	// CHECKS
	
	public boolean connectionCanBeAdded() {
		if (numbConnections() < 4) {
			return true;
		}
		
		return false;
	}
	
	public boolean equalCoordinatesWith(Intersection intersection) {
		return (intersection.getXCoord() == this.x_coord && intersection.getYCoord() == this.y_coord);
	}

	// ACTIONS
	
	public void updateTrafficLights() {
		if (this.time_till_toggle == 0) {
			for (TrafficLight tl : this.getTrafficLights()) {
				tl.toggle();
			}
			
			this.time_till_toggle = this.tl_phase_length;
		}
		
		this.time_till_toggle--;
	}
	
	public void initializeTrafficLightSettings() {
		// TODO make it so that facing roads have same initial status etc.
	}

	// OTHER
	
	public int numbConnections() {
		return this.connections.size();
	}
	
	public String toString() {
		return "Intersection: (" + this.x_coord + ", " + this.y_coord + ")";
	}

	
}
