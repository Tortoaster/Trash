package com.whale.nee.main;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel implements Runnable, MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 896, HEIGHT = 768, UPS = 60;
	
	private Game game = new Game();
	
	public Main() {
		Assets.load();
		JFrame frame = new JFrame("Empire");
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(WIDTH + 6, HEIGHT + 29);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(this).start();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		game.click(e.getX(), e.getY());
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void run() {
		while (true) {
			long time = System.currentTimeMillis();
			game.update();
			repaint();
			try {
				Thread.sleep(Math.max(0, 1000 / UPS - (System.currentTimeMillis() - time)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		game.draw(g);
	}
}
