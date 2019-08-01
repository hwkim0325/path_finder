package Pathfinding;

import static Node_Frame.Grid.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import Node_Frame.Grid;
import Node_Frame.Node;
import Node_Frame.NodeType;

import static Event.CheckInput.*;

public class Pathfinding {
	
	Grid grid = Grid.getInstance();
	
	public static int step = 0;
	
	public Pathfinding() {
	}
	
	public void StartFindPath(WayPoints start, WayPoints goal)
	{
		FindPath(start, goal);
	}
	
	public void FindPath(WayPoints start, WayPoints goal) {
		long startTime = System.currentTimeMillis();
		
		ArrayList<WayPoints> wayPoints = new ArrayList<>();
		boolean pathSuccess = false;
		
		Node startNode = getNode(start.x, start.y);
		Node goalNode = getNode(goal.x, goal.y);
		startNode.parent = startNode;
		
		if(startNode.walkable && goalNode.walkable) {
			Heap openSet = new Heap(MaxSize());
			HashSet<Node> closedSet = new HashSet<Node>();
			openSet.Add(startNode);
			
			while(openSet.Count() >0) {
				Node currentNode = openSet.RemoveFirst();
				closedSet.add(currentNode);

				if(currentNode == goalNode) {
					long endTime = System.currentTimeMillis();
					long diff = endTime - startTime;
					System.out.println("Path found: " + diff + " ms");
					pathSuccess = true;
					break;
				}
				
				for(Node neighbour : GetNeighbours(currentNode)) {
					if(!neighbour.walkable || closedSet.contains(neighbour)) {
						continue;
					}
					
					int newMovementCostToNeighbour = currentNode.gCost + GetDistance(currentNode, neighbour) + neighbour.movementPenalty;
					if (newMovementCostToNeighbour < neighbour.gCost || !openSet.Contains(neighbour)) {
						neighbour.gCost = newMovementCostToNeighbour;
						neighbour.hCost = GetDistance(neighbour, goalNode);
						neighbour.fCost = neighbour.fCost();
						neighbour.parent = currentNode;

						if (!openSet.Contains(neighbour))
						{
							openSet.Add(neighbour);
							step++;
							if(neighbour.type != NodeType.GOAL && neighbour.type != NodeType.WARNING && neighbour.type != NodeType.SEARCHING)
								SetColor(neighbour.gridX, neighbour.gridY, SearchingColor[0], SearchingColor[1], SearchingColor[2], NodeType.SEARCHING, neighbour.walkable);
						}
						else 
							openSet.UpdateItem(neighbour);
					}
				}
			}
		}
		if (pathSuccess) {
			wayPoints = RetracePath(startNode,goalNode);
		}
		PathRequestManager.FinishedProcessingPath(wayPoints,pathSuccess);
	}

	ArrayList<WayPoints> RetracePath(Node startNode, Node endNode) {
		ArrayList<Node> path = new ArrayList<Node>();
		Node currentNode = endNode;
		
		while (currentNode != startNode) {
			path.add(currentNode);
			currentNode = currentNode.parent;
		}
		ArrayList<WayPoints> wayPoints = SimplifyPath(path);
		Collections.reverse(wayPoints);
		return wayPoints;
	}
	
	ArrayList<WayPoints> SimplifyPath(ArrayList<Node> path) {
		ArrayList<WayPoints> wayPoints = new ArrayList<WayPoints>();
		
		for (int i = 0; i < path.size(); i ++) {
			wayPoints.add(new WayPoints());
			wayPoints.get(i).x = path.get(i).gridX;
			wayPoints.get(i).y = path.get(i).gridY;
		}
		return wayPoints;
	}
	
	int GetDistance(Node nodeA, Node nodeB) {
		int dstX = Math.abs(nodeA.gridX - nodeB.gridX);
		int dstY = Math.abs(nodeA.gridY - nodeB.gridY);
		
		if (dstX > dstY)
			return 14*dstY + 10* (dstX-dstY);
		return 14*dstX + 10 * (dstY-dstX);
	}
	
}
