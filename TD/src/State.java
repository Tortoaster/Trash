import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class State {
	
	private Window window;
	
	public State(Window window) {
		this.window = window;
	}
	
	public void keyPressed(KeyEvent e) {}
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mouseWheelMoved(MouseWheelEvent e) {}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
	
	public void setState(int state) {
		window.setState(state);
	}
	
	public int getScreenWidth() {
		return window.getScreenWidth();
	}
	
	public int getScreenHeight() {
		return window.getScreenHeight();
	}
	
	public int getRefreshRate() {
		return window.getRefreshRate();
	}
}
