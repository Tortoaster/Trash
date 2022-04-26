package com.whale.nope.main;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Manager manager = new Manager();
	
	private Screen screen;
	
	private Loop loop;
	
	public Window(int width, int height, String title) {
		screen = new Screen(width, height, manager);
		loop = new Loop(manager, screen);
		add(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(width, height);
		setLocationRelativeTo(null);
		setTitle(title);
		setUndecorated(true);
		setVisible(true);
		new Thread(loop).start();
	}
}
