package Node_Frame;

import static Drawing.Draw.*;
import static Event.CheckInput.*;

import java.util.ArrayList;

public class Grid {
		
	static private Grid instance;
	
	public static final int WIDTH = 20;
	public static final int HEIGHT = 20;
	public static final int MARGIN_WIDTH = 3;
	public static final int MARGIN_HEIGHT = 3;
	public static final int GRID_SIZE_X = 15;
	public static final int GRID_SIZE_Y = 15;	
	public static final int GRID_PADDING = 3;
	
	public static Node[][] grid;
	
	public static Grid getInstance() {
		if(instance == null) {
			instance = new Grid();
		}
		return instance;
	}
	
	private Grid() {
		if(grid==null) {
			grid = new Node[GRID_SIZE_X][GRID_SIZE_Y];
			for (int x = 0; x < GRID_SIZE_X; x ++) {
				for (int y = 0; y < GRID_SIZE_Y; y ++) {
					grid[x][y] = new Node(true, x, y, NodeType.GRID);
				}
			}
		}
	}
	
	public static void initGrid(int x, int y) {
		grid[x][y].walkable = true;
		grid[x][y].type = NodeType.GRID;
		grid[x][y].R = GridColor[0];
		grid[x][y].G = GridColor[1];
		grid[x][y].B = GridColor[2];
	}
	
	public static void reset() {
		for (int x = 0; x < GRID_SIZE_X; x ++) {
			for (int y = 0; y < GRID_SIZE_Y; y ++) {
				initGrid(x,y);
			}
		}
	}
	
	public static void obstacleClear() {
		for (int x = 0; x < GRID_SIZE_X; x ++) {
			for (int y = 0; y < GRID_SIZE_Y; y ++) {
				if(grid[x][y].type == NodeType.START || 
						grid[x][y].type == NodeType.GOAL)
					continue;
				initGrid(x,y);
			}
		}
	}
	
	public static void clear() {
		for (int x = 0; x < GRID_SIZE_X; x ++) {
			for (int y = 0; y < GRID_SIZE_Y; y ++) {
				if(grid[x][y].type == NodeType.START || 
						grid[x][y].type == NodeType.GOAL ||
							grid[x][y].type == NodeType.BARRIER ||
								grid[x][y].type == NodeType.WARNING)
					continue;
				initGrid(x,y);
			}
		}
	}
	
	public static ArrayList<Node> GetNeighbours(Node node) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (x == 0 && y == 0)
					continue;

				int checkX = node.gridX + x;
				int checkY = node.gridY + y;

				if (checkX >= 0 && checkX < GRID_SIZE_X && checkY >= 0 && checkY < GRID_SIZE_Y) {
					neighbours.add(grid[checkX][checkY]);
				}
			}
		}
		return neighbours;
	}
	
	public static void resetBarrier() {
		for (int x = 0; x < GRID_SIZE_X; x ++) {
			for (int y = 0; y < GRID_SIZE_Y; y ++) {
				if(grid[x][y].type == NodeType.BARRIER)
				{
					SetColor(x, y, GridColor[0], GridColor[1], GridColor[2], NodeType.GRID, true);	
				}
			}
		}
	}
	
	public static void resetWarning() {
		for (int x = 0; x < GRID_SIZE_X; x ++) {
			for (int y = 0; y < GRID_SIZE_Y; y ++) {
				if(grid[x][y].type == NodeType.WARNING)
				{
					SetColor(x, y, GridColor[0], GridColor[1], GridColor[2], NodeType.GRID, true);	
				}
			}
		}
	}
	
	public static int MaxSize() {
		return GRID_SIZE_X * GRID_SIZE_Y;
	}
		
	public static void SetColor(int x, int y, float _R, float _G, float _B, NodeType _type, boolean _walkable) {	
		grid[x][y].R = _R;
		grid[x][y].G = _G;
		grid[x][y].B = _B;
		grid[x][y].type = _type;
		grid[x][y].walkable = _walkable;
	}
	
	public static void SetColor(int x, int y, float _R, float _G, float _B, NodeType _type, int _penalty) {	
		grid[x][y].R = _R;
		grid[x][y].G = _G;
		grid[x][y].B = _B;
		grid[x][y].type = _type;
		grid[x][y].movementPenalty = _penalty;
	}
	
	public static Node getNode(int startX, int startY)
	{
		return grid[startX][startY];
	}

	public void Draw()
	{
		for (int x = 0; x < GRID_SIZE_X; x ++) {
			for (int y = 0; y < GRID_SIZE_Y; y ++) {
				Node t= grid[x][y];	
				DrawQuad(t.R, t.G, t.B, t.gridX*(WIDTH+GRID_PADDING)+MARGIN_WIDTH, t.gridY*(HEIGHT+GRID_PADDING)+MARGIN_HEIGHT, WIDTH, HEIGHT);				
			}
		}
	}
}
