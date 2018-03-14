package core;

import java.util.ArrayList;
import java.util.Random;

import datastructures.StreetMap;
import datastructures.Car;
import datastructures.Intersection;

public class Simulation {

	private boolean run = false;
	
	private StreetMap street_map;
	private ArrayList<Car> cars;
	
	public Simulation(StreetMap map) {
		this.street_map = map;
		this.cars = new ArrayList<Car>();
	}
	
	// GETTERS / SETTERS
	
	public ArrayList<Car> getCars() {
		return this.cars;
	}
	
	public StreetMap getStreetMap() {
		return this.street_map;
	}
	
	// ACTIONS
	
	public void addCar(Car car) {
		this.cars.add(car);	
	}
	
	public void addRandomCar() {
		this.street_map.getIntersections();
		this.street_map.getRoads();
		
		Random r = new Random();
		int origin = r.nextInt(this.street_map.getIntersections().size());
		int destination;
		do {
			destination = r.nextInt(this.street_map.getIntersections().size());
		} while (destination == origin);
	
		this.addCar(new Car(this.street_map.getIntersection(origin), this.street_map.getIntersection(destination)));
		System.out.println("created new car, x: " + this.street_map.getIntersection(origin).getXCoord() + ", y: " + this.street_map.getIntersection(origin).getYCoord() + ", total: "+ this.getCars().size());
	}
	
	public void generateRandomCars(int n_cars) {
		for (int i = 0; i < n_cars; i++) {
			this.addRandomCar();
		}
	}
	
	// SIMULATION
	
	public void start() {
		run = true;
		System.out.println("start");
		
		// Initialize
		for (Intersection is : this.street_map.getIntersections()) {
			is.initializeTrafficLightSettings();
		}
		
		int t = 1;
		int timesteps = 1000;
		while (t < timesteps) { // ultimately, have some sort of "simulation finished" function here checking if everybody arrived

			for (Car car : this.cars) {
				// update traffic light statuses
				this.street_map.update();
				// recalculate car positions
				car.update(this.cars, t);
				
				System.out.println(car);
			}
			
			t++;
		}
	}

	public void stop() {
		run = false;
		System.out.println("stop");
	}
	
	public void reset() {
		this.cars.clear();
	}
}