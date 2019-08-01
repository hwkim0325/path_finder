package UI_Frame;

public class Button {

	private String name;
	private float R = 1.0f;
	private float G = 1.0f;
	private float B = 1.0f;
	private int x, y, width, height;
	
	Button(String name, int x, int y, int width, int height){
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	Button(String name, float R, float G, float B , int x, int y, int width,int height){
	this.name = name;
	this.R = R;
	this.G = G;
	this.B = B;
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	}

	public Button() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getR() {
		return R;
	}

	public void setR(float r) {
		R = r;
	}

	public float getG() {
		return G;
	}

	public void setG(float g) {
		G = g;
	}

	public float getB() {
		return B;
	}

	public void setB(float b) {
		B = b;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
