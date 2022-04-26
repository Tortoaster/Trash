package com.whale.nope.main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Manager {
	
	public enum Type {
		
		MENU(new Menu()), PLAY(new Game());
		
		private State state;
		
		Type(State state) {
			this.state = state;
		}
		
		public State getState() {
			return state;
		}
	}
	
	private Type type = Type.PLAY;
	
	public void keyPressed(KeyEvent e) {
		type.getState().keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		type.getState().keyReleased(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		type.getState().mouseClicked(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		type.getState().mouseDragged(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		type.getState().mouseMoved(e);
	}
	
	public void update() {
		type.getState().update();
	}
	
	public void draw(Graphics g) {
		type.getState().draw(g);
	}
}
