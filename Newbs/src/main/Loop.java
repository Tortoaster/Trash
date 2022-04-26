package main;

public class Loop implements Runnable {
	
	public static int FPS = 60;
	
	private GSM gsm;
	
	private Window window;
	
	public Loop(GSM gsm, Window window) {
		this.gsm = gsm;
		this.window = window;
	}
	
	public void run() {
		long time = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() > time + 1000 / FPS) {
				time = System.currentTimeMillis();
				gsm.update();
				window.repaint();
			}
		}
	}
}
