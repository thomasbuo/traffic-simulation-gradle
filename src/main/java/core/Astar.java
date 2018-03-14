package core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import datastructures.Intersection;
import datastructures.Road;
import datastructures.StreetMap;

public class Astar {
	
	private StreetMap streetMap;
	private Intersection start;
	private Intersection end;
	
	private ArrayList<Intersection> solution = new ArrayList();
	
	public Astar(StreetMap map, Intersection start, Intersection end)
	{
		streetMap = map;
		this.start = start;
		this.end = end;	
		
		
	}
	
	public void createPath(Intersection start)
	{
		Deque<Intersection> stack = new ArrayDeque<Intersection>();
		stack.push(start);
		ArrayList<Intersection> possiblePaths = new ArrayList();
		ArrayList<Double> possiblePathsWeights = new ArrayList();
		Intersection top = stack.pop();
		for(int i = 0; i < stack.size() ; i++){	
			
			possiblePaths.add(streetMap.getIntersection(top.getConnections().get(i).getDestination()));
			Road r = streetMap.getRoads().get(top.getConnections().get(i).getRoad());
			double distance = r.getLength() + 0;
			possiblePathsWeights.add(distance);			
		}		
		boolean changed = true;
		while(changed)
		{
			changed = false;
		}
		
	}
	
	
	
	

}
