package Event;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class TickInput{
	
	static private TickInput instance = new TickInput();
	
	public boolean[] keys = new boolean[65536];

	public boolean mouse1= false;
	public boolean mouse2 = false;
	public boolean mouse3 = false;
	public float mouseX, mouseY;
	public boolean space = false;
	
	public static TickInput getInstance() {
		if(instance == null) {
			instance = new TickInput();
		}
		return instance;
	}
	
	private TickInput() {}
	
	public void Tick() {
			if(Mouse.isButtonDown(0)) mouse1 = true;
			else mouse1 = false;
			if(Mouse.isButtonDown(1)) mouse2 = true;
			else mouse2 = false;
			if(Mouse.isButtonDown(2)) mouse3 = true;
			else mouse3 = false;		
		
		if(Keyboard.next())
		{
			if(Keyboard.getEventKeyState()) {
				keys[Keyboard.getEventKey()] = true;
			}
			else
			{
				keys[Keyboard.getEventKey()] = false;
			}
		}
		
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
	}
}
