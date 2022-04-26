import java.awt.Graphics;
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

public class Window extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean running = true;
	
	private int width, height, scale, rate;
	
	private BufferedImage screen;
	
	private State[] states;
	private State state;
	
	public Window(int width, int height, int scale, int rate, String title) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.rate = rate;
		screen = new BufferedImage(width / scale, height / scale, BufferedImage.TYPE_INT_RGB);
		states = new State[] {new Game(this)};
		state = states[0];
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		JFrame frame = new JFrame(title);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(width + 6, height + 29);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(this).start();
	}
	
	public void keyPressed(KeyEvent e) {
		state.keyPressed(e);}
	
	public void keyReleased(KeyEvent e) {
		state.keyReleased(e);}
	
	public void keyTyped(KeyEvent e) {
		state.keyTyped(e);}
	
	public void mouseClicked(MouseEvent e) {
		state.mouseClicked(e);}
	
	public void mousePressed(MouseEvent e) {
		state.mousePressed(e);}
	
	public void mouseReleased(MouseEvent e) {
		state.mouseReleased(e);}
	
	public void mouseMoved(MouseEvent e) {
		state.mouseMoved(e);}
	
	public void mouseDragged(MouseEvent e) {
		state.mouseDragged(e);}
	
	public void mouseEntered(MouseEvent e) {
		state.mouseEntered(e);}
	
	public void mouseExited(MouseEvent e) {
		state.mouseExited(e);}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		state.mouseWheelMoved(e);}
	
	public void run() {
		while (running) {
			long time = System.currentTimeMillis();
			state.update();
			repaint();
			try {
				Thread.sleep(Math.max(0, (int) (1000.0 / rate - (System.currentTimeMillis() - time))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g) {
		Graphics g2 = screen.getGraphics();
		state.draw(g2);
		g.drawImage(screen, 0, 0, width, height, null);
		g2.dispose();
	}
	
	public void setState(int state) {
		this.state = states[state];
	}
	
	public int getScreenWidth() {
		return width / scale;
	}
	
	public int getScreenHeight() {
		return height / scale;
	}
	
	public int getRefreshRate() {
		return rate;
	}
}
