package net.melting;

import java.awt.Graphics;

public class Adventurer extends Creature {
	
	public Adventurer(int x, int y) {
		super(x, y);
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		g.drawRect(x * World.SCALE / Tile.SIZE, y * World.SCALE / Tile.SIZE, World.SCALE, World.SCALE);
	}
}
