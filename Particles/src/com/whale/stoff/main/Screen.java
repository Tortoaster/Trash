package com.whale.stoff.main;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
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
		g.fillRect(0, 0, 1000, 800);
		manager.draw(g);
	}
}
