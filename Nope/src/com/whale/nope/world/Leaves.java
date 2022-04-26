package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;

public class Leaves extends Tile {
	
	public Leaves(World world) {
		super(false, world);
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(20, 150, 90));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
