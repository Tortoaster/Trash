package com.tortoaster.palettes;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Window extends JPanel implements Runnable {
	
	private static final int FPS = 60;
	
	private Main main = new Main();
	
	Window(int width, int height, String title) {
		JFrame frame = new JFrame(title);
		
		frame.add(this);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Thread(new Window(800, 800, "Palettes")).start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		main.draw(g);
	}
	
	@Override
	public void run() {
		while(true) {
			long time = System.currentTimeMillis();
			
			main.update();
			repaint();
			
			try {
				Thread.sleep(1000 / FPS - System.currentTimeMillis() + time);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
