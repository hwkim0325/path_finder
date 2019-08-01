package Window;

import static Window.MainMenu.*;
import static Drawing.Draw.*;
import static Event.CheckInput.*;

import UI_Frame.UI;

public class Info {
	
	public static final int INFO_WIDTH = 30;
	public static final int INFO_TOP = 310;
	public static final int INFO_INTERVAL = 25;
	public static final int BOX_WIDTH =20;
	public static final int BOX_HEIGHT =20;
	public static final int BOX_Y = 181;
	public static final int BOX_X = 48;
	public static final int INTERVAL_X = 60;
	public static final int INTERVAL_Y = 26;
	public static final int SPACE = 10;
	
	private UI infoUI;
	
	public Info() {
		infoUI = new UI();
	}
	
	public void update() {
		
		infoUI.drawString("This is A.star Pathfinding Algorithm Program", INFO_WIDTH, INFO_TOP);
		infoUI.drawString("binary Heap, Queue, weight are apllied", INFO_WIDTH, INFO_TOP - INFO_INTERVAL);
		infoUI.drawString("And You Can Save Path, Replay them", INFO_WIDTH, INFO_TOP - 2*INFO_INTERVAL);
		infoUI.drawString("Thank You..!!!", INFO_WIDTH, INFO_TOP - 3*INFO_INTERVAL);
		
		infoUI.drawString("1. CONTROL", INFO_WIDTH, INFO_TOP - 4*INFO_INTERVAL);
		
		infoUI.drawString("     L.CLK.. START", INFO_WIDTH, INFO_TOP - 5*INFO_INTERVAL);
		infoUI.drawString("R.CLK.. GOAL", INFO_WIDTH + 17*SPACE, INFO_TOP - 5*INFO_INTERVAL);
		
		infoUI.drawString("     Key.Z... WALL", INFO_WIDTH, INFO_TOP - 6*INFO_INTERVAL);
		infoUI.drawString("KeyX.. PENALTY", INFO_WIDTH + 17*SPACE, INFO_TOP - 6*INFO_INTERVAL);
		infoUI.drawString("Key.C.. GRID", INFO_WIDTH + 30*SPACE, INFO_TOP - 6*INFO_INTERVAL);
		
		infoUI.drawString("2. PATHFINDING", INFO_WIDTH, INFO_TOP - 7*INFO_INTERVAL);
		infoUI.drawString("     NEIGHBOUR", INFO_WIDTH, INFO_TOP - 8*INFO_INTERVAL);
		infoUI.drawString("PATH", INFO_WIDTH + 17*SPACE, INFO_TOP - 8*INFO_INTERVAL);
		

		infoUI.drawString("THANK YOU", INFO_WIDTH, INFO_TOP - 10*INFO_INTERVAL);
		infoUI.drawString("20141522", BASE_X + PADDING + 2*SPACE , INFO_HEIGHT);
		
		DrawQuad(StartColor[0], StartColor[1], StartColor[2], BOX_X, BOX_Y, BOX_WIDTH, BOX_HEIGHT);
		DrawQuad(GoalColor[0], GoalColor[1], GoalColor[2], BOX_X*2 + BOX_WIDTH + INTERVAL_X, BOX_Y, BOX_WIDTH, BOX_HEIGHT);
		DrawQuad(BarrierColor[0], BarrierColor[1], BarrierColor[2], BOX_X, BOX_Y - INTERVAL_Y, BOX_WIDTH, BOX_HEIGHT);
		DrawQuad(WarningColor[0], WarningColor[1], WarningColor[2], BOX_X*2 + BOX_WIDTH + INTERVAL_X, BOX_Y - INTERVAL_Y, BOX_WIDTH, BOX_HEIGHT);
		DrawQuad(GridColor[0], GridColor[1], GridColor[2], BOX_X*3 + BOX_WIDTH*2 + INTERVAL_X*2, BOX_Y - INTERVAL_Y, BOX_WIDTH, BOX_HEIGHT);
		DrawQuad(SearchingColor[0], SearchingColor[1], SearchingColor[2], BOX_X, BOX_Y - INTERVAL_Y*3, BOX_WIDTH, BOX_HEIGHT);
		DrawQuad(PathColor[0], PathColor[1], PathColor[2], BOX_X*2 + BOX_WIDTH +INTERVAL_X, BOX_Y - INTERVAL_Y*3, BOX_WIDTH, BOX_HEIGHT);
	}
}
