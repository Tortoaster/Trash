package com.whale.animation.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Menu extends State {
	
	public Menu(Manager manager) {
		super(manager);
	}
	
	public void mouseClicked(MouseEvent e) {
		manager.setState(State.GAME);
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(100, 100, 100));
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
	}
}
