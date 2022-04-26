import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GSM extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean running = true;
	
	public static final int FPS = 60;
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int GAME = 0;
	public int state = GAME;
	
	private State[] states = new State[1];
	
	public GSM() {
		states[0] = new Game(this);
		JFrame frame = new JFrame("Nee");
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(WIDTH + 6, HEIGHT + 29);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		requestFocus();
		new Thread(this).start();
	}
	
	public static void main(String[] args) {
		new GSM();
	}
	
	public void keyPressed(KeyEvent e) {
		states[state].keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		states[state].keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {
		states[state].keyTyped(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		states[state].mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e) {
		states[state].mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		states[state].mouseReleased(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		states[state].mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e) {
		states[state].mouseExited(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		states[state].mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		states[state].mouseDragged(e);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		states[state].mouseWheelMoved(e);
	}
	
	public void run() {
		long time = System.currentTimeMillis();
		while (running) {
			if (System.currentTimeMillis() - time > 1000 / FPS) {
				states[state].update();
				time = System.currentTimeMillis();
				repaint();
			}
		}
	}
	
	public void paint(Graphics g) {
		states[state].draw(g);
	}
}
