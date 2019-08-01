package UI_Frame;

import java.util.ArrayList;

import static Drawing.Draw.*;

public class UI {

	private ArrayList<Button> buttonList;
	
	public ArrayList<Button> getButtonList() {
		return buttonList;
	}

	public UI(){
		buttonList = new ArrayList<Button>();
	}
	
	public void drawString(String text, int x, int y)
	{
		SimpleText.drawString(text, x, y);
	}
	
	public void addButton(String name, String textureName, int x, int y, int width, int height)
	{
		buttonList.add(new Button(name, x, y, width, height));
	}
	
	public void draw() {
		for(Button b: buttonList) {
			DrawLine(b.getR(), b.getG(), b.getB(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
	}
}
