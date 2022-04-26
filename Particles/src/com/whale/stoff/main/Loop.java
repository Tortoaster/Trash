package com.whale.stoff.main;

public class Loop implements Runnable {
	
	private boolean running = true;
	
	public static final int UPS = 60;
	
	private Manager manager;
	
	private Screen screen;
	
	public Loop(Manager manager, Screen screen) {
		this.manager = manager;
		this.screen = screen;
	}
	
	public void run() {
		while (running) {
			long time = System.currentTimeMillis();
			manager.update();
			screen.repaint();
			try {
				Thread.sleep(Math.max(0, 1000 / UPS - (System.currentTimeMillis() - time)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
