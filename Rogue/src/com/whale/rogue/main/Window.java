package com.whale.rogue.main;

import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel implements FocusListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean running = true;
	
	private static int current = State.GAME, time;
	public static int WIDTH, HEIGHT;
	public static final int RATE = 60;
	
	private State[] states = new State[] {new Game()};
	
	public Window(String title, int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		JFrame frame = new JFrame(title);
		frame.add(this);
		frame.addFocusListener(this);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.addMouseWheelListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(WIDTH + 6, HEIGHT + 29);
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
	
	public void focusGained(FocusEvent e) {states[current].focusGained(e);}
	
	public void focusLost(FocusEvent e) {states[current].focusLost(e);}
	
	public void run() {
		while (running) {
			long time = System.currentTimeMillis();
			states[current].update();
			repaint();
			Window.time++;
			try {
				Thread.sleep(Math.max(0, 1000 / RATE - (System.currentTimeMillis() - time)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g) {
		g.fillRect(0, 0, WIDTH, HEIGHT);
		states[current].draw(g);
	}
	
	public static void setState(int state) {
		Window.current = state;
	}
	
	public static int getTime() {
		return time;
	}
}
