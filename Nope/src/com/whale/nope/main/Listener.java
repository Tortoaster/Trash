package com.whale.nope.main;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Listener implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener, FocusListener {
	
	private Manager manager;
	
	public Listener(Manager manager) {
		this.manager = manager;
	}
	
	public void keyPressed(KeyEvent e) {
		manager.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		manager.keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void mouseClicked(MouseEvent e) {
		manager.mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		manager.mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		manager.mouseDragged(e);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		
	}
	
	public void focusGained(FocusEvent e) {
		
	}
	
	public void focusLost(FocusEvent e) {
		
	}
}
