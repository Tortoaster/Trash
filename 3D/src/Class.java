import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Class extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean running = true;
	
	public static final int width = 600;
	public static final int height = 400;
	public static final int game = 0;
	public int state = game;
	private int fps = 60;
	
	private BufferedImage image;
	
	private State[] states = new State[1];
	
	public Class() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		states[game] = new Game(this);
		JFrame frame = new JFrame("Nee");
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(width * 2 + 6, height * 2 + 29);
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
		new Class();
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
		while (running) {
			repaint();
			states[state].update();
			try {
				Thread.sleep(1000 / fps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2 = image.createGraphics();
		states[state].draw(g2);
		g.drawImage(image, 0, 0, width * 2, height * 2, null);
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2.dispose();
	}
}
