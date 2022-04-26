package com.whale.nee.main;

import java.awt.Graphics;

import com.whale.nee.life.Chicken;
import com.whale.nee.life.Life;
import com.whale.nee.world.Tile;
import com.whale.nee.world.World;

public class Game {
	
	public static final int SCALE = 3;
	
	private Life life = new Life(this);
	
	private World world = new World(this);
	
	public void click(int x, int y) {
		
	}
	
	public void update() {
		life.update();
		world.update();
		life.add(new Chicken((int) (Math.random() * world.WIDTH * Tile.SIZE), (int) (Math.random() * world.HEIGHT * Tile.SIZE), life));
	}
	
	public void draw(Graphics g) {
		world.draw(g);
		life.draw(g);
	}
}
