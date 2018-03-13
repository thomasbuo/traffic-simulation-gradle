package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import a_star_stuff.AStarSearchContext;
import a_star_stuff.Cost;
import a_star_stuff.Estimation;
import a_star_stuff.Expansion;
import a_star_stuff.SearchContext;
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
	
	public ArrayList<Car> getCars() {
		return this.cars;
	}
	
	public StreetMap getStreetMap() {
		return this.street_map;
	}
	
	public void start() {
		run = true;
		System.out.println("start");
		
		
		// Okay so have to do the following:
		//    define the proper expansion [ given a streetmap and intersection(or intersection id), return all neighboring intersections]
		//    define the proper cost func [ given a streetmap, a start and end intersections, return the weight of the road between them]
		//	  define the proper estimation function, aka A* heuristic [ given a streetmap and an intersection, compute some value for it such as manhattan distance from target destination ]
		//
		// Dummy expansion cost and estimation:
		
		Expansion<SearchContext<StreetMap, Intersection>, Intersection> expansionFcn =
				new Expansion<SearchContext<StreetMap, Intersection>, Intersection>() {

					@Override
					public Iterable<Intersection> expand(SearchContext<StreetMap, Intersection> c, Intersection p) {
						// as an example:
						//  c.domain() returns you the StreetMap you will pass it
						//  c.start()/c.target() returns you the endpoints so you can also use them (you would use the target position in the estimation probably to get some distance)
						StreetMap ourMap = c.domain();
						
						List<Intersection> neighboringIntersections = 
								new ArrayList<>();
						
						for (Integer neighbourId : p.getConnectedIntersectionIds()) {
							neighboringIntersections.add(ourMap.getIntersection(neighbourId));
						}
						
						return neighboringIntersections;
					}
			
		};
		
		Cost<SearchContext<StreetMap, Intersection>, Intersection> costFcn = 
				new Cost<SearchContext<StreetMap, Intersection>, Intersection>() {
			
					@Override
					public float compute(SearchContext<StreetMap, Intersection> c, Intersection from, Intersection to) {
						// TODO Auto-generated method stub
						return 0;
					}
					
		};
		
		Estimation<SearchContext<StreetMap, Intersection>, Intersection> estimationFcn =
				new Estimation<SearchContext<StreetMap, Intersection>, Intersection>() {

					@Override
					public float estimate(SearchContext<StreetMap, Intersection> c, Intersection from, Intersection to) {
						// TODO Auto-generated method stub
						return 0;
					}
			
		};
		
		// Search for first car
		
		Car bestCar = getCars().get(0);
		
		SearchContext<StreetMap, Intersection> searchAlgo = new AStarSearchContext<StreetMap, Intersection>();
		
		searchAlgo.expansion(expansionFcn);
		searchAlgo.cost(costFcn);
		searchAlgo.estimation(estimationFcn);
		
		searchAlgo.setDomain(street_map);
		searchAlgo.endpoints(bestCar.getStartPoint(), bestCar.getEndPoint());
		
		searchAlgo.execute();
		
		List<Intersection> pathToVICTORY = searchAlgo.solution();
		
		searchAlgo.clear();
		
		// Search for second car
		
		bestCar = getCars().get(1);
		
		searchAlgo.expansion(expansionFcn);
		searchAlgo.cost(costFcn);
		searchAlgo.estimation(estimationFcn);
		
		searchAlgo.setDomain(street_map);
		searchAlgo.endpoints(bestCar.getStartPoint(), bestCar.getEndPoint());
		
		searchAlgo.execute();
		
		pathToVICTORY = searchAlgo.solution();
		
		// TO INFINITY, AND BEYOND!
		
		//	.
		//	.
		//	.
		
		
	}

	public void stop() {
		run = false;
		System.out.println("stop");
	}
	
	public void reset() {
		this.cars.clear();
	}
}