package com.whale.animation.main;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Screen screen;
	
	public Window() {
		screen = new Screen();
		add(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(Screen.WIDTH + 6, Screen.HEIGHT + 29);
		setLocationRelativeTo(null);
		setTitle("Never");
		setVisible(true);
	}
}
