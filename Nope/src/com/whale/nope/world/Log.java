package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;

public class Log extends Tile {
	
	public Log(World world) {
		super(false, world);
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(100, 60, 40));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
