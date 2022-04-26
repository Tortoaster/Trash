package com.whale.animation.main;

import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Manager {
	
	private int current = State.GAME;
	
	private State[] state = {new Menu(this), new Game(this)};
	
	public void keyPressed(KeyEvent e) {
		state[current].keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		state[current].keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {
		state[current].keyTyped(e);
	}
	
	public void mousePressed(MouseEvent e) {
		state[current].mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		state[current].mouseReleased(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		state[current].mouseClicked(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		state[current].mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e) {
		state[current].mouseExited(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		state[current].mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		state[current].mouseDragged(e);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		state[current].mouseWheelMoved(e);
	}
	
	public void focusGained(FocusEvent e) {
		state[current].focusGained(e);
	}
	
	public void focusLost(FocusEvent e) {
		state[current].focusLost(e);
	}
	
	public void update() {
		state[current].update();
	}
	
	public void draw(Graphics g) {
		state[current].draw(g);
	}
	
	public void setState(int state) {
		current = state;
	}
}
