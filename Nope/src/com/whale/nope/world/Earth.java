package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;

public class Earth extends Tile {
	
	public Earth(World world) {
		super(true, world);
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(140, 100, 80));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
