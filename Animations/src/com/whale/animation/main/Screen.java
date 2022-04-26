package com.whale.animation.main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1000, HEIGHT = 800;
	
	private Manager manager = new Manager();
	
	private Loop loop = new Loop(manager, this);
	
	private Listener listener = new Listener(manager);
	
	public Screen() {
		new Thread(loop).start();
		addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addMouseWheelListener(listener);
		addFocusListener(listener);
		setFocusable(true);
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		manager.draw(g);
	}
}
