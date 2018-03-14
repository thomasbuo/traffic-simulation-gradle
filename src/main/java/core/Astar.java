package core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
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
		createPath(start);
		
	}
	
	public Intersection createPath(Intersection start)
	{
		if(start == null)
		{
			return end;
		}
		Deque<Intersection> stack = new ArrayDeque<Intersection>();
		stack.push(start);
		ArrayList<Intersection> possiblePaths = new ArrayList();
		ArrayList<Double> possiblePathsWeights = new ArrayList();
		Intersection top = stack.pop();
		for(int i = 0; i < stack.size() ; i++){	
			
			possiblePaths.add(streetMap.getIntersection(top.getConnections().get(i).getDestination()));
			Road r = streetMap.getRoads().get(top.getConnections().get(i).getRoad());
			double distance = r.getLength() + (Math.sqrt(Math.pow(possiblePaths.get(possiblePaths.size()-1).getXCoord() - end.getXCoord(), 2) + 
					Math.pow(possiblePaths.get(possiblePaths.size()-1).getXCoord() - end.getYCoord(),2)));
			possiblePathsWeights.add(distance);			
		}		
		boolean changed = true;
		while(changed)
		{
			changed = false;
			for(int i = 0; i<possiblePaths.size()-1; i++)
			{
				if(possiblePathsWeights.get(i) < possiblePathsWeights.get(i+1))
				{
					changed = true;
					Collections.swap(possiblePaths, i, i+1);
					Collections.swap(possiblePathsWeights, i, i+1);
				}
			}
		}
		
		for(int i = 0 ; i < possiblePaths.size();i++)
		{
			stack.push(possiblePaths.get(i));
		}
		
		return createPath(stack.pop());
		
	}
	
	
	
	

}
