package Drawing;

import static org.lwjgl.opengl.GL11.*;

//import java.io.IOException;
//import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;
//import org.newdawn.slick.util.ResourceLoader;

public class Draw {
	public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
	public static final int ORTHO_WIDTH = 450, ORTHO_HEIGHT = 350;	
	private static final int SHAPE_ADJUST = 1;
	
	public static void BeginSession() {
		Display.setTitle("PathFidingSystem");
		try {
			Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH,SCREEN_HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION);
		glOrtho(0, ORTHO_WIDTH, 0, ORTHO_HEIGHT, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
		glLoadIdentity();
		
	}
	
	public static void DrawLine(float R, float G, float B, int x, int y, int width ,int height) {
		glColor3f(R, B, B);
		glBegin(GL_LINES);
		glVertex2f(x, y);
		glVertex2f(x + width, y);
		glVertex2f(x + width, y);
		glVertex2f(x + width , y + height);
		glVertex2f(x + width , y + height);
		glVertex2f(x, y + height);
		glVertex2f(x, y + height);
		glVertex2f(x, y);
		glEnd();
	}
	
	public static void DrawQuad(float R, float G, float B, int x, int y, int width, int height) {
		glColor3f(R,G,B);
		glBegin(GL_POLYGON);
		glVertex2f(x , y + SHAPE_ADJUST);
		glVertex2f(x + width - 19*SHAPE_ADJUST ,y);
		glVertex2f(x + width - SHAPE_ADJUST ,y);
		glVertex2f(x + width , y + SHAPE_ADJUST);
		glVertex2f(x + width, y + height - SHAPE_ADJUST);
		glVertex2f(x + width - SHAPE_ADJUST ,y + height);
		glVertex2f(x + width - 19*SHAPE_ADJUST ,y + height);
		glVertex2f(x , y + 19*SHAPE_ADJUST);	
		glEnd();
		}
	
	// Bind Texture
	/* public static void DrawQuadTex(Texture tex, int x, int y, int width, int height) {
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0,0);
		glVertex2f(0,0);
		glTexCoord2f(1,0);
		glVertex2f(width,0);
		glTexCoord2f(1,1);
		glVertex2f(width,height);
		glTexCoord2f(0,1);
		glVertex2f(0,height);
		glEnd();
		glLoadIdentity();
		}
	*/	
	
	// Texture
	/*public static Texture LoadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}
	
	public static Texture QuickLoad(String name) {
		Texture tex = null;
		tex = LoadTexture("res/"+name+".png","PNG");
		return tex;
	}
	*/
}
