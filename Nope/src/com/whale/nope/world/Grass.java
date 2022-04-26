package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;

public class Grass extends Tile {
	
	public Grass(World world) {
		super(true, world);
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(140, 100, 80));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
		g.setColor(new Color(90, 160, 90));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE / 2);
	}
}
