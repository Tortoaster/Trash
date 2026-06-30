package com.testing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel implements MouseListener, MouseMotionListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean running = true;
	
	private static int current = State.GAME;
	public static int WIDTH, HEIGHT;
	public static final int RATE = 60;
	
	private State[] states = new State[] {new Game()};
	
	public Window(String title, int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		addMouseListener(this);
		addMouseMotionListener(this);
		JFrame frame = new JFrame(title);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(width + 6, height + 29);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(this).start();
	}
	
	public void mouseClicked(MouseEvent e) {states[current].mouseClicked(e);}
	
	public void mousePressed(MouseEvent e) {states[current].mousePressed(e);}
	
	public void mouseReleased(MouseEvent e) {states[current].mouseReleased(e);}
	
	public void mouseMoved(MouseEvent e) {states[current].mouseMoved(e);}
	
	public void mouseDragged(MouseEvent e) {states[current].mouseDragged(e);}
	
	public void mouseEntered(MouseEvent e) {states[current].mouseEntered(e);}
	
	public void mouseExited(MouseEvent e) {states[current].mouseExited(e);}
	
	public void run() {
		while (running) {
			long time = System.currentTimeMillis();
			states[current].update();
			repaint();
			try {
				Thread.sleep(Math.max(0, 1000 / RATE - (System.currentTimeMillis() - time)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		states[current].draw(g);
	}
	
	public static void setState(int state) {
		Window.current = state;
	}
}
