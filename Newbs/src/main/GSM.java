package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class GSM {
	
	public static int menu = 0, play = 1;
	private int state = menu;
	
	private State[] states = new State[2];
	
	public GSM() {
		states[menu] = new Menu(this);
		states[play] = new Play(this);
	}
	
	public void keyPressed(KeyEvent e) {
		states[state].keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		states[state].keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {
		states[state].keyTyped(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		states[state].mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e) {
		states[state].mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		states[state].mouseReleased(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		states[state].mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		states[state].mouseDragged(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		states[state].mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e) {
		states[state].mouseExited(e);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		states[state].mouseWheelMoved(e);
	}
	
	public void update() {
		states[state].update();
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public void draw(Graphics g) {
		states[state].draw(g);
	}
}
