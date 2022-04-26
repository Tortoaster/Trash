package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;
import com.whale.nope.effects.Water;

public class Fountain extends Tile {
	
	public Fountain(World world) {
		super(false, world);
	}
	
	public void update(int x, int y) {
		int speed = random.nextInt(10) + 1;
		int direction = random.nextInt(21) + 260;
		world.getGame().getEffects().add(new Water(x * SIZE + SIZE / 2, y * SIZE + SIZE / 4, Math.cos(Math.toRadians(direction)) * speed, Math.sin(Math.toRadians(direction)) * speed, 10, world.getGame().getEffects()));
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(200, 200, 200));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
		g.setColor(new Color(80, 100, 200));
		g.fillRect(x * SIZE + SIZE / 8, y * SIZE + SIZE / 8, SIZE / 4 * 3, SIZE / 2);
	}
}
