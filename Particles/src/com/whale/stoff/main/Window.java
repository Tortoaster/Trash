package com.whale.stoff.main;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Screen screen;
	
	public Window(int width, int height, String title) {
		screen = new Screen();
		add(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(width + 6, height + 29);
		setLocationRelativeTo(null);
		setTitle(title);
		setVisible(true);
	}
}
