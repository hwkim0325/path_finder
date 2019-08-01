package Event;

import static Node_Frame.Grid.*;
import static Event.StateEvent.*;
import Node_Frame.NodeType;

public class CheckInput {
		
	static private CheckInput instance;
	
	public static final int PENALTY = 50;
	public static final int validMAX = 225;
	
	public int startCount = 0;
	private static int startX_check = 99, startY_check= 99;
	public int goalCount = 0;
	private static int goalX_check = 99, goalY_check = 99;
	private static int barrierCount = 0;
	private static int warningCount = 0;
	
	public static final float[] GridColor = { 189/255f, 189/255f, 189/255f};
	public static final float[] StartColor = { 67/255f, 166/255f, 217/255f };
	public static final float[] GoalColor = { 243/255f, 97/255f, 166/255f };
	public static final float[] BarrierColor = { 204/255f, 166/255f, 61/255f };
	public static final float[] WarningColor = { 150/255f, 150/255f, 150/255f };
	public static final float[] SearchingColor = { 140/255f, 220/255f, 255/255f};
	public static final float[] PathColor = { 255/255f, 150/255f, 150/255f};
		
	private CheckInput() {}
	
	public static CheckInput getInstance() {
		if(instance == null) {
			instance = new CheckInput();
		}
		return instance;
	}
	
	public void SetStart(int checkX, int checkY) {
		if((0 <= checkX && checkX <= 14 ) && (0 <= checkY && checkY <= 14)) {
			if((grid[checkX][checkY].type == NodeType.START))	
			{}
			else if(grid[checkX][checkY].type == NodeType.BARRIER) 
			{}		
			else if((grid[checkX][checkY].type == NodeType.WARNING))	
			{}	
			else if(instance.startCount !=0 && grid[checkX][checkY].type != NodeType.START && grid[checkX][checkY].type != NodeType.GOAL) 
			{		
				clear();
				SetColor(startX_check, startY_check, GridColor[0], GridColor[1], GridColor[2], NodeType.GRID, true);		
				SetColor(checkX, checkY, StartColor[0], StartColor[1], StartColor[2], NodeType.START, true);
				SetStartCheck(checkX, checkY);
			}
			else if((instance.startCount == 0 && grid[checkX][checkY].type == NodeType.GRID) 
					&& (0 <= checkX && checkX <= 14 ) && (0 <= checkY && checkY <= 14)) 
			{
				clear();
				SetColor(checkX, checkY, StartColor[0], StartColor[1], StartColor[2], NodeType.START, true);
				SetStartCheck(checkX, checkY);	
				instance.startCount = 1;
			}
		}
}

public void SetGoal(int checkX, int checkY) {
		
	if((0 <= checkX && checkX <= 14 ) && (0 <= checkY && checkY <= 14)) {	
		if((grid[checkX][checkY].type == NodeType.GOAL)) 
		{}	
		else if(grid[checkX][checkY].type == NodeType.BARRIER) 
		{}
		else if((grid[checkX][checkY].type == NodeType.WARNING))	
		{}	
		else if(instance.goalCount != 0  && grid[checkX][checkY].type != NodeType.START && grid[checkX][checkY].type != NodeType.GOAL) {
			clear();
			SetColor(goalX_check, goalY_check, GridColor[0], GridColor[1], GridColor[2],NodeType.GRID, true);			
			SetColor(checkX, checkY, GoalColor[0], GoalColor[1], GoalColor[2],NodeType.GOAL, true);
			SetGoalCheck(checkX, checkY);
		}
		else if((instance.goalCount == 0 && grid[checkX][checkY].type == NodeType.GRID) 
				&& (0 <= checkX && checkX <= 14 ) && (0 <= checkY && checkY <= 14))
		{	
				clear();
				SetColor(checkX, checkY, GoalColor[0], GoalColor[1], GoalColor[2], NodeType.GOAL, true);
				SetGoalCheck(checkX, checkY);
				instance.goalCount=1;
		}
	}
}
	
public void SetBarrier(int checkX, int checkY) {
		if(0 <= checkX && checkX <= 14 
				&& 0 <= checkY && checkY <= 14) {
			
			if(grid[checkX][checkY].type == NodeType.START)
			{}
			else if(grid[checkX][checkY].type == NodeType.GOAL)
			{}
			else if((grid[checkX][checkY].type == NodeType.BARRIER)) 
			{}			
			else if(barrierCount < validMAX 
					&& (grid[checkX][checkY].type == NodeType.WARNING))
			{
				clear();
				if(isStarted) 
					resetObstacle();
				SetColor(checkX, checkY, BarrierColor[0], BarrierColor[1], BarrierColor[2], NodeType.BARRIER, false);	
				warningCount--;
				barrierCount++;
			}
			else if(barrierCount < validMAX 
					&& ( grid[checkX][checkY].type != NodeType.START && grid[checkX][checkY].type != NodeType.GOAL)) { 
				clear();
				if(isStarted) 
					resetObstacle();
				SetColor(checkX, checkY, BarrierColor[0], BarrierColor[1], BarrierColor[2], NodeType.BARRIER, false);	
				barrierCount++;
			}
			else if(barrierCount == validMAX 
					&& ( grid[checkX][checkY].type != NodeType.START && grid[checkX][checkY].type != NodeType.GOAL)
					&& isStarted == true)
			{
				resetObstacle();
			} 
		}
	}

public void SetWarning(int checkX, int checkY) {
	if(0 <= checkX && checkX <= 14 
			&& 0 <= checkY && checkY <= 14) {
		if(grid[checkX][checkY].type == NodeType.START)
		{}
		else if(grid[checkX][checkY].type == NodeType.GOAL)
		{}		
		else if((grid[checkX][checkY].type == NodeType.WARNING))
		{}
		else if(warningCount < validMAX
				&& (grid[checkX][checkY].type == NodeType.BARRIER)) 
		{
			clear();
			if(isStarted) 
				resetObstacle();
			SetColor(checkX, checkY, WarningColor[0], WarningColor[1], WarningColor[2], NodeType.WARNING, PENALTY);
			warningCount++;
			barrierCount--;
		}
		else if(warningCount < validMAX 
				&& ( grid[checkX][checkY].type != NodeType.START && grid[checkX][checkY].type != NodeType.GOAL)) { 
			clear();
			if(isStarted) 
				resetObstacle();
			SetColor(checkX, checkY, WarningColor[0], WarningColor[1], WarningColor[2], NodeType.WARNING, PENALTY);
			warningCount++;
		}
		else if(warningCount == validMAX 
				&& ( grid[checkX][checkY].type != NodeType.START && grid[checkX][checkY].type != NodeType.GOAL && grid[checkX][checkY].type != NodeType.BARRIER)
				&& isStarted == true)
		{
			resetObstacle();
		} 
	}
}
public void SetGrid(int checkX, int checkY) {
	if(0 <= checkX && checkX <= 14 
			&& 0 <= checkY && checkY <= 14) {
		if(grid[checkX][checkY].type == NodeType.START)
		{}
		else if(grid[checkX][checkY].type == NodeType.GOAL)
		{}
		else if((grid[checkX][checkY].type == NodeType.BARRIER)) 
		{
			SetColor(checkX, checkY, GridColor[0], GridColor[1], GridColor[2], NodeType.GRID, true);
			setBarrierCount(getBarrierCount()-1);
		}		
		else if((grid[checkX][checkY].type == NodeType.WARNING))
		{
			SetColor(checkX, checkY, GridColor[0], GridColor[1], GridColor[2], NodeType.GRID, true);
			setWarningCount(getWarningCount()-1);
		}
	}
}

public void resetObstacle() {
	obstacleClear();
	setBarrierCount(0);
	setWarningCount(0);
	isStarted = false;
}


public void SetStartCheck(int checkX, int checkY) {
	startX_check = checkX;
	startY_check = checkY;
}


public void SetGoalCheck(int checkX, int checkY) {
	goalX_check = checkX;
	goalY_check = checkY;
}

public static int getStartX_check() {
	return startX_check;
}

public static void setStartX_check(int startX_check) {
	CheckInput.startX_check = startX_check;
}

public static int getStartY_check() {
	return startY_check;
}

public static void setStartY_check(int startY_check) {
	CheckInput.startY_check = startY_check;
}

public static int getGoalX_check() {
	return goalX_check;
}

public static void setGoalX_check(int goalX_check) {
	CheckInput.goalX_check = goalX_check;
}

public static int getGoalY_check() {
	return goalY_check;
}

public static void setGoalY_check(int goalY_check) {
	CheckInput.goalY_check = goalY_check;
}

public static int getBarrierCount() {
	return barrierCount;
}

public static void setBarrierCount(int barrierCount) {
	CheckInput.barrierCount = barrierCount;
}


public static int getWarningCount() {
	return warningCount;
}

public static void setWarningCount(int warningCount) {
	CheckInput.warningCount = warningCount;
}


}