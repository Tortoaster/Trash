import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface State {
	
	void update();
	
	void draw(Graphics g);
	
	default void keyPressed(KeyEvent e) {}
	
	default void keyReleased(KeyEvent e) {}
	
	default void mousePressed(MouseEvent e) {}
	
	default void mouseReleased(MouseEvent e) {}
	
	default void mouseMoved(MouseEvent e) {}
	
	default void mouseWheelMoved(MouseWheelEvent e) {}
	
}
