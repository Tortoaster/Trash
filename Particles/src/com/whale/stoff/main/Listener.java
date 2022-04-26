package com.whale.stoff.main;

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
		manager.keyTyped(e);
	}
	
	public void mousePressed(MouseEvent e) {
		manager.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		manager.mouseReleased(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		manager.mouseClicked(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		manager.mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e) {
		manager.mouseExited(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		manager.mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		manager.mouseDragged(e);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		manager.mouseWheelMoved(e);
	}
	
	public void focusGained(FocusEvent e) {
		manager.focusGained(e);
	}
	
	public void focusLost(FocusEvent e) {
		manager.focusLost(e);
	}
}
