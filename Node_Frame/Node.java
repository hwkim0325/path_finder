package Node_Frame;

import static Event.CheckInput.*;

import Pathfinding.WayPoints;

public class Node {
	
	public NodeType type;
	public float R = GridColor[0];
	public float G = GridColor[1];
	public float B = GridColor[2];
	
	public boolean walkable = true;
	public int gridX = 0;
	public int gridY = 0;
	public WayPoints wayPoints;
	public int movementPenalty = 0;
	
	public int gCost = 0;
	public int hCost = 0;
	public int fCost = 0;
	public Node parent;
	int heapIndex = 0;
	
	public Node(boolean _walkable, int _gridX, int _gridY, NodeType _type) {
		walkable = _walkable;
		gridX = _gridX;
		gridY = _gridY;
		type = _type;
	}
	
	public int CompareTo(Node nodeToCompare) {
		int compare = fCost > nodeToCompare.fCost? 1 : 
						fCost == nodeToCompare.fCost? 0 : -1;	
		if (compare == 0) {
			compare = hCost > nodeToCompare.hCost? 1 : 
						hCost == nodeToCompare.hCost? 0 : -1;
		}
		return compare;
	}
	
	public int fCost() {
		return gCost + hCost;
	}
	
	public int getHeapIndex() {
		return heapIndex;
	}

	public void setHeapIndex(int heapIndex) {
		this.heapIndex = heapIndex;
	}
	
}
