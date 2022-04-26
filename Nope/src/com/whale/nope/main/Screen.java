package com.whale.nope.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Screen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH, HEIGHT;
	
	private BufferedImage image;
	
	private Listener listener;
	
	private Manager manager;
	
	public Screen(int width, int height, Manager manager) {
		WIDTH = width;
		HEIGHT = height;
		this.manager = manager;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		listener = new Listener(manager);
		setFocusable(true);
		addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addMouseWheelListener(listener);
		addFocusListener(listener);
	}
	
	public void paint(Graphics g) {
		Graphics g2 = g;
		g2 = image.createGraphics();
		manager.draw(g2);
		g.drawImage(image, 0, 0, null);
		g2.dispose();
	}
}
