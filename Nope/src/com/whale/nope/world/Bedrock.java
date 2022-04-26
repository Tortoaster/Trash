package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;

public class Bedrock extends Tile {
	
	public Bedrock(World world) {
		super(true, world);
	}
	
	public void onHit(int x, int y) {
		
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(40, 40, 40));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
