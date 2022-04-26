package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;

import com.whale.nope.effects.Fire;

public class Firepit extends Tile {
	
	private Fire fire;
	
	public Firepit(int x, int y, World world) {
		super(false, world);
		fire = new Fire(x * SIZE + SIZE / 2, y * SIZE + SIZE / 2, 50, new Color(1F, 1F, 0), new Color(1F, 0, 0), world.getGame().getEffects());
	}
	
	public void update(int x, int y) {
		fire.update();
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(60, 40, 50));
		g.fillRect(x * SIZE, y * SIZE + SIZE / 2, SIZE, SIZE / 2);
	}
	
	public void setStartColor(Color color) {
		fire.setStartColor(color);
	}
	
	public void setEndColor(Color color) {
		fire.setEndColor(color);
	}
}
