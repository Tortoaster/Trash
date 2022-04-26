package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Listener implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	private GSM gsm;
	
	public Listener(GSM gsm) {
		this.gsm = gsm;
	}
	
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {
		gsm.keyTyped(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		gsm.mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e) {
		gsm.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		gsm.mouseReleased(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		gsm.mouseDragged(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		gsm.mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e) {
		gsm.mouseExited(e);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		gsm.mouseWheelMoved(e);
	}
}
