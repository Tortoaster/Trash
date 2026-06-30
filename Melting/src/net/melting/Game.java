package net.melting;

import java.awt.Graphics;

public class Game extends State {
	
	private World world = new World(80, 45, 4, 7);
	
	public void update() {
		world.update();
	}
	
	public void draw(Graphics g) {
		world.draw(g);
	}
}
