package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;

public class Rock extends Tile {
	
	public Rock(World world) {
		super(true, world);
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(130, 125, 135));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
