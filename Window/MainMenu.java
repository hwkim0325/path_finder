package Window;

import UI_Frame.UI;
import static Event.CheckInput.*;

import Event.StateEvent;

public class MainMenu {

	public static final int BASE_X = 350;
	public static final int BASE_Y = 235;
	public static final int BUTTON_BASESIZE_X = 95;
	public static final int BUTTON_BASESIZE_Y = 40;
	public static final int PADDING = 6;
	public static final int STATE_HEIGHT = 315;
	public static final int TEXT_HEIGHT = 305;
	public static final int MENU_PADDING = -55;
	public static final int MENU_INTERVAL = 46;
	public static final int SPACE = 5;
	public static final int INFO_HEIGHT = 10;
	public static final int COUNT_HEIGHT = 285;
	public static final int ADJUST = 1;
	
	int info = 0;
	
	String[] buttonName= {"START","RESET","PATH","LOGIN", "INFO"};
	
	private UI menuUI;
	private StateEvent stateEvent = StateEvent.getInstance();
	
	public MainMenu() {
		
		menuUI = new UI();
		for(int i=0; i<5; i++)
		{
			menuUI.addButton(
					buttonName[i],
					"stored_path", 
					BASE_X  , 
					BASE_Y - i*(BUTTON_BASESIZE_Y + PADDING), 	
					BUTTON_BASESIZE_X , 
					BUTTON_BASESIZE_Y
					);
		}
	}
	
	public void update() {
		menuUI.draw();	
		menuUI.drawString(stateEvent.getState(), BASE_X + PADDING , STATE_HEIGHT);
		menuUI.drawString(buttonName[0], BASE_X + PADDING + 3*SPACE , TEXT_HEIGHT + MENU_PADDING);
		menuUI.drawString(buttonName[1], BASE_X + PADDING + 3*SPACE , TEXT_HEIGHT + MENU_PADDING - MENU_INTERVAL);
		menuUI.drawString(buttonName[2], BASE_X + PADDING + 3*SPACE , TEXT_HEIGHT + MENU_PADDING - MENU_INTERVAL*2);
		menuUI.drawString(buttonName[3], BASE_X + PADDING + 3*SPACE , TEXT_HEIGHT + MENU_PADDING - MENU_INTERVAL*3);
		menuUI.drawString(buttonName[4], BASE_X + PADDING + 3*SPACE , TEXT_HEIGHT + MENU_PADDING - MENU_INTERVAL*4);
		menuUI.drawString("20141522", BASE_X + PADDING + 2*SPACE , INFO_HEIGHT);
		menuUI.drawString("W : ", BASE_X + PADDING , COUNT_HEIGHT);
		menuUI.drawString(Integer.toString(validMAX - getBarrierCount()), BASE_X + 4*SPACE , COUNT_HEIGHT);
		menuUI.drawString("P : ", BASE_X + PADDING + 10*SPACE , COUNT_HEIGHT);
		menuUI.drawString(Integer.toString(validMAX - getWarningCount()), BASE_X + 14*SPACE - 2*ADJUST, COUNT_HEIGHT);
}
}
