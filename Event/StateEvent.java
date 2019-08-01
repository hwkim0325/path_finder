package Event;

import Pathfinding.PathRequestManager;
import Pathfinding.WayPoints;
import Window.Info;
import Window.LoginWindow;
import Window.PathWindow;

import static Event.CheckInput.*;
import static Node_Frame.Grid.*;
import static Pathfinding.Pathfinding.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import javax.swing.JOptionPane;

import Database.UserDTO;

public class StateEvent{
	
	static private StateEvent instance;
	static Info info = new Info();
	static private PathWindow pathWindow;
	static private LoginWindow loginWindow;
	
	PathRequestManager pathRequestManager = PathRequestManager.getInstance();
	CheckInput checkInput = CheckInput.getInstance();
	UserDTO userDTO = UserDTO.getInstance();
	
	WayPoints pathStart;
	WayPoints pathEnd;
	
	public String state = "A.STAR";
	public static boolean isStarted = false;
	
	public StateEvent() {}
	
	public static StateEvent getInstance() {
		if(instance == null) {
			instance = new StateEvent();
		}
		return instance;
	}
	
	public void working() {
		state = "A.STAR";
	}

	public void startClicked() {
		if(userDTO.getId() != null && !userDTO.getId().equals("")) {
			if(isStarted)
				clear();
			else
				isStarted = true;
			
			pathStart = new WayPoints(CheckInput.getStartX_check(), CheckInput.getStartY_check());
			pathEnd = new WayPoints(CheckInput.getGoalX_check(), CheckInput.getGoalY_check());
			
			if((0 <= pathStart.x && pathStart.x <= 14) && (0 <= pathStart.y && pathStart.y <= 14) &&
					(0 <= pathEnd.x && pathEnd.x <= 14) && (0 <= pathEnd.y && pathEnd.y <= 14)) 
			pathRequestManager.RequestPath(pathStart, pathEnd);
			
			state = "STEPs... " + Integer.toString(step);
			step = 0;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "로그인을 해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		}
	}
	public void resetClicked() {
		state = "RESET";
		checkInput.startCount = 0;
		checkInput.goalCount = 0;
		resetPoints();
		setBarrierCount(0);
		resetBarrier();
		setWarningCount(0);
		resetWarning();
		reset();
		isStarted = false;
	}
	
	private void resetPoints() {
		CheckInput.setStartX_check(99);
		CheckInput.setStartY_check(99);
		CheckInput.setGoalX_check(99);
		CheckInput.setGoalY_check(99);
	}

	public void pathClicked() {
		if(userDTO.getId() != null && !userDTO.getId().equals("")) {
			state = "Path";
			if(pathWindow == null)
				pathWindow = new PathWindow();
		}else
		{
			JOptionPane.showMessageDialog(null, "로그인을 해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		}
	}
	public void loginClicked() {
		state = "Login";
		if(loginWindow == null)
			loginWindow = new LoginWindow();
	}
	public void infoClicked() {
		glClear(GL_COLOR_BUFFER_BIT);
		info.update();
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static void resetPathWindow() {
		pathWindow = null;
	}
	
	public static void resetLoginWindow() {
		loginWindow = null;
	}
}
