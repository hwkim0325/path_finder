package Drawing;

import static Drawing.Draw.*;
import static Node_Frame.Grid.*;

import Event.CheckInput;
import Event.StateEvent;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import Database.UserDTO;
import Event.TickInput;
import Node_Frame.Grid;
import Window.LoginWindow;
import Window.MainMenu;

public class Boot {
	
	private final double DIVIDE_X = 41;
	private final double DIVIDE_Y = 40;
	
	private static Boot boot = null;
	
	public Boot() {	
			
		BeginSession();		

		Grid grid = Grid.getInstance();	
		MainMenu mainmenu = new MainMenu();
		TickInput tickInput = TickInput.getInstance();
		CheckInput checkinput = CheckInput.getInstance();
		StateEvent stateEvent = StateEvent.getInstance();
			
		int checkX;
		int checkY;
		
		while(!Display.isCloseRequested())
		{		
			glClear(GL_COLOR_BUFFER_BIT);
			
			
			tickInput.Tick();
			grid.Draw();
			mainmenu.update();
			
			if(tickInput.mouse1) 
			{
				checkX = calPosX(tickInput.mouseX);
				checkY = calPosY(tickInput.mouseY);
				
				if((0 <= checkX && checkX <= 14 ) && (0 <= checkY && checkY <= 14))
				{
					checkinput.SetStart(checkX, checkY);
					stateEvent.working();
				}
				else if(15 <= checkX && (checkY == 11 || checkY == 10))
					stateEvent.startClicked();
				else if(15 <= checkX && (checkY == 9 || checkY == 8))
					stateEvent.resetClicked();
				else if(15 <= checkX && (checkY == 7 || checkY == 6))
					stateEvent.pathClicked();
				else if(15 <= checkX && (checkY == 5 || checkY == 4))
					stateEvent.loginClicked();
				else if(15 <= checkX && (checkY == 3 || checkY == 2))
					stateEvent.infoClicked();
			}
			else if(tickInput.mouse2) {
				stateEvent.working();
				checkX = calPosX(tickInput.mouseX);
				checkY = calPosY(tickInput.mouseY);
				checkinput.SetGoal(checkX, checkY);
			}
			else if(tickInput.keys[Keyboard.KEY_Z]) {
				stateEvent.working();
				checkX = calPosX(tickInput.mouseX);
				checkY = calPosY(tickInput.mouseY);
				checkinput.SetBarrier(checkX, checkY);
			}
			else if(tickInput.keys[Keyboard.KEY_X]) {
				stateEvent.working();
				checkX = calPosX(tickInput.mouseX);
				checkY = calPosY(tickInput.mouseY);
				checkinput.SetWarning(checkX, checkY);
			}
			else if(tickInput.keys[Keyboard.KEY_C]) {
				stateEvent.working();
				checkX = calPosX(tickInput.mouseX);
				checkY = calPosY(tickInput.mouseY);
				checkinput.SetGrid(checkX,checkY);
			}
			Display.update();
		}
		Display.destroy();
	}
	
	public int calPosX(float tickInputX) {
		int checkX = (int)((tickInputX - MARGIN_WIDTH)/DIVIDE_X);
		return checkX;	
	}
	
	public int calPosY(float tickInputY) {
		int checkY = (int)((tickInputY - MARGIN_HEIGHT)/DIVIDE_Y);
		return checkY;
	}


	public static void main(String[] args)
	{
		if(boot == null)
			boot = new Boot();
	}	
}
