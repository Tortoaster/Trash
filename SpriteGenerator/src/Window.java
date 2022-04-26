import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private final int width, height, rate;
	
	private State state;
	
	public Window(int width, int height, int rate, String title) {
		this.width = width;
		this.height = height;
		this.rate = rate;
		
		state = new State() {
			@Override
			public void update() {
				
			}
			
			@Override
			public void draw(Graphics g) {
				
			}
		};
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		setFocusable(true);
		requestFocus();
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(width + 6, height + 29);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(this);
	}
	
	@Override
	public void run() {
		while(true) {
			long time = System.nanoTime();
			state.update();
			repaint();
			try {
				Thread.sleep(Math.max(0, Math.round(1000F / rate) - System.nanoTime() + time));
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		state.draw(g);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		state.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		state.keyReleased(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {
		state.mousePressed(e);
		state.mouseMoved(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		state.mouseMoved(e);
		state.mouseReleased(e);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		state.mouseMoved(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		state.mouseMoved(e);
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		state.mouseWheelMoved(e);
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public int getScreenWidth() {
		return width;
	}
	
	public int getScreenHeight() {
		return height;
	}
	
	public int getRate() {
		return rate;
	}
	
	public State getState() {
		return state;
	}
}
