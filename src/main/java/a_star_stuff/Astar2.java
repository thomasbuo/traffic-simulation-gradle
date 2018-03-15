package a_star_stuff;

import java.util.ArrayList;

import datastructures.Intersection;
import datastructures.StreetMap;

public class Astar2 {

	private Intersection start;
	private Intersection end;
	private StreetMap streetMap;
	public Astar2(StreetMap map)
	{		
		streetMap = map;
	}
	
	public Intersection getStart() {
		return start;
	}

	public void setStart(Intersection start) {
		this.start = start;
	}

	public Intersection getEnd() {
		return end;
	}

	public void setEnd(Intersection end) {
		this.end = end;
	}

	public ArrayList<Intersection> createPath()
	{
		System.out.println("start search");
		boolean targetFound = false;
		ArrayList<Intersection> openList = new ArrayList<>();
		ArrayList<Intersection> closedList = new ArrayList<>();
		closedList.add(start);
		start.setParent(start);
		
		while(!targetFound)
		{
			System.out.println("entered while "+ targetFound);
			Intersection working = null;
			for(int i = 0; i<closedList.size();i++)
			{
				if(working == null)
				{
					working = closedList.get(i);
				}
				else if(working.getCost() > closedList.get(i).getCost())
				{
					working = closedList.get(i);
				}
			}
			closedList.remove(working);
			
			for(int i = 0; i < working.getConnections().size(); i++)
			{
				
				System.out.println("checking connections");
				Intersection checking = streetMap.getIntersection(working.getConnections().get(i).getDestination());
				if(!working.getParent().equals(checking)) {
					int distance = streetMap.getRoads().get(working.getConnections().get(i).getRoad()).getLength() + 
							(int) Math.abs((checking.getXCoord() - end.getXCoord() + checking.getYCoord() - end.getYCoord())) ;
					openList.add(checking);
					if(checking.getParent() == null)
					{
						System.out.println("parent was null");
						checking.setParent(working);					
						checking.setCost(distance);
					}
					else if(checking.getCost() >= distance)
					{
						System.out.println("parent was not null");
						checking.setParent(working);					
						checking.setCost(distance);
					}
				}
				
			}
			System.out.println("checking which intersection should be closedList"+ " start: " +start.getXCoord()+", "+start.getYCoord()+ " end: " +end.getXCoord()+", "+end.getYCoord());
			Intersection lowestCost = null;
			
			for(int i = 0 ; i < openList.size() ; i++)
			{
				if(lowestCost == null)
				{
					lowestCost = openList.get(i);
				}
				else if(lowestCost.getCost() > openList.get(i).getCost())
				{
					lowestCost = openList.get(i);
				}
			}
			
			closedList.add(lowestCost);
			openList.remove(lowestCost);
			System.out.println("added to closedList");
			if(lowestCost.equals(end))
			{
				System.out.println("targetFound");
				targetFound = true;
			}
		
		}
		ArrayList<Intersection> path = new ArrayList();
		path.add(end);
		boolean endReached = false;
		while(!endReached)
		{
			Intersection toAdd = path.get(path.size()-1).getParent();
			if(toAdd.equals(start))
			{
				System.out.println("Start is found");
				endReached = true;
				break;
			}
			else
			{
				System.out.println("Added parent to path");
				path.add(toAdd);
			}
		}
		for(int i = 0; i< path.size() ; i++)
		{
			System.out.println("path: x: "+path.get(i).getXCoord()+", y: "+ path.get(i).getYCoord());
		}
		return path;
	}
}
