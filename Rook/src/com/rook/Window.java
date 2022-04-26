package com.rook;

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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rook.state.Game;
import com.rook.state.State;

public class Window extends JPanel implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean running = true;
	
	private static int current = State.GAME;
	public static int WIDTH, HEIGHT;
	public static final int RATE = 60;
	
	private BufferedImage screen;
	
	private Graphics2D g2d;
	
	private State[] states = new State[] {new Game()};
	
	private BufferedImage[] animation = new BufferedImage[RATE * 10];
	private int frame = 0;
	private boolean record = false;
	
	public Window(int width, int height, String title) {
		WIDTH = width;
		HEIGHT = height;
		screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = screen.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
	
	public void keyPressed(KeyEvent e) {states[current].keyPressed(e);}
	
	public void keyReleased(KeyEvent e) {states[current].keyReleased(e);}
	
	public void keyTyped(KeyEvent e) {states[current].keyTyped(e);}
	
	public void mouseClicked(MouseEvent e) {states[current].mouseClicked(e);}
	
	public void mousePressed(MouseEvent e) {states[current].mousePressed(e);}
	
	public void mouseReleased(MouseEvent e) {states[current].mouseReleased(e);}
	
	public void mouseMoved(MouseEvent e) {states[current].mouseMoved(e);}
	
	public void mouseDragged(MouseEvent e) {states[current].mouseDragged(e);}
	
	public void mouseEntered(MouseEvent e) {states[current].mouseEntered(e);}
	
	public void mouseExited(MouseEvent e) {states[current].mouseExited(e);}
	
	public void mouseWheelMoved(MouseWheelEvent e) {states[current].mouseWheelMoved(e);}
	
	public void run() {
		while (running) {
			long time = System.currentTimeMillis();
			states[current].update();
			repaint();
			try {
				Thread.sleep(Math.max(0, (int) (1000.0 / RATE - (System.currentTimeMillis() - time))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g) {
		states[current].draw(g2d);
		g.drawImage(screen, 0, 0, WIDTH, HEIGHT, null);
		if(record) {
			if(frame < animation.length) {
				BufferedImage copy = new BufferedImage(WIDTH / Game.SCALE, HEIGHT / Game.SCALE, BufferedImage.TYPE_INT_RGB);
				Graphics g2 = copy.getGraphics();
				g2.drawImage(screen, 0, 0, WIDTH / Game.SCALE, HEIGHT / Game.SCALE, null);
				animation[frame] = copy;
			} else if(frame == animation.length) {
				try {
					ImageOutputStream output = new FileImageOutputStream(new File("res/animation.gif"));
					GifSequenceWriter writer = new GifSequenceWriter(output, animation[0].getType(), 1000 / RATE, false);
					for(int i = 0; i < animation.length; i++) {
						writer.writeToSequence(animation[i]);
					}
					writer.close();
					output.close();
					System.out.println("Done.");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			frame++;
		}
		g.dispose();
	}
	
	public static void setState(int state) {
		Window.current = state;
	}
}
