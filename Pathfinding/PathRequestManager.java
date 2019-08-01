package Pathfinding;

import java.util.ArrayList;
import Database.*;
import Node_Frame.NodeType;

import static Node_Frame.Grid.*;
import static Event.CheckInput.*;

public class PathRequestManager {

	static private PathRequestManager instance;
	
	ManagerQueue pathRequestQueue = new ManagerQueue();
	static Pathfinding pathfinding = new Pathfinding();
	PathRequest currentPathRequest;
	
	public static ArrayList<WayPoints> path = new ArrayList<>();
	public static boolean isProcessingPath = false;
	public static boolean pathSuccess = false;
	public static String pathText = null;
	
	public static DatabaseDAO databaseDAO = DatabaseDAO.getInstance();
	public static String startPoint = null;
	public static String endPoint = null;
	public static String distance = null;
	
	private PathRequestManager() {}
	
	public static PathRequestManager getInstance() {
		if(instance == null) {
			instance = new PathRequestManager();
		}
		return instance;
	}
	
	public void RequestPath(WayPoints pathStart, WayPoints pathEnd) {
		PathRequest newRequest = new PathRequest(pathStart,pathEnd);
		pathRequestQueue.enqueue(newRequest);
		TryProcessNext();
	}
	
	void TryProcessNext() {
		if (!isProcessingPath && pathRequestQueue.size() > 0) {
			currentPathRequest = pathRequestQueue.dequeue();
			isProcessingPath = true;
			pathfinding.StartFindPath(currentPathRequest.pathStart, currentPathRequest.pathEnd);
		}
	}
	
	public static void FinishedProcessingPath(ArrayList<WayPoints> wayPoints, boolean success) {
		isProcessingPath = false;
		pathSuccess = success;
		path = wayPoints;
		
		int pathX;
		int pathY;
		
				
		
		for(WayPoints path : wayPoints)
		{
			pathX = path.x;
			pathY = path.y;
			
			if(pathText == null)
				pathText = String.valueOf(path.x) + "," + String.valueOf(path.y) + ",";
			else
				pathText += String.valueOf(path.x) + "," + String.valueOf(path.y) + ",";
			
			if(grid[pathX][pathY].type != NodeType.GOAL)
				SetColor(pathX, pathY, PathColor[0], PathColor[1], PathColor[2], NodeType.PATH, true);
		}
		
		System.out.println(pathText);
		
		startPoint = getStartX_check() + "," + getStartY_check();
		endPoint = getGoalX_check() + "," + getGoalY_check();
		distance = path.size()+"";
		if(!UserDTO.getInstance().getId().equals("")) {
		if(!databaseDAO.checkPath(startPoint, endPoint, UserDTO.getInstance().getId()))
		{
			databaseDAO.addNewPath(startPoint, endPoint, distance, pathText, UserDTO.getInstance().getId());		
		} 
		else 
		{}
		
		pathText = null;
		}
	}

	public static String getStartPoint() {
		return startPoint;
	}

	public static void setStartPoint(String startPoint) {
		PathRequestManager.startPoint = startPoint;
	}

	public static String getEndPoint() {
		return endPoint;
	}

	public static void setEndPoint(String endPoint) {
		PathRequestManager.endPoint = endPoint;
	}

	public static String getDistance() {
		return distance;
	}

	public static void setDistance(String distance) {
		PathRequestManager.distance = distance;
	}

}
