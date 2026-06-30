package com.testing;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class State {
	
	public static final int GAME = 0;
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
}
