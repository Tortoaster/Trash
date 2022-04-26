package com.whale.nee.world;

import java.awt.Color;
import java.awt.Graphics;

import com.whale.nee.main.Assets;
import com.whale.nee.main.Game;
import com.whale.nee.main.Main;

public abstract class Tile {
	
	protected double density;
	
	public static final int SIZE = 8, TIME = 3;
	
	protected int x, y, z, strength, maxStrength, timer;
	
	private int id;
	
	private World world;
	
	public Tile(int x, int y, int z, double density, int strength, int id, World world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.density = density;
		this.strength = strength;
		maxStrength = strength;
		this.id = id;
		this.world = world;
	}
	
	public void update() {
		if (strength <= 0) world.setTile(x, y, z, new Air(x, y, z, world));
		timer--;
		if (timer <= 0) strength = maxStrength;
	}
	
	public void draw(Graphics g) {
		g.drawImage(Assets.TILES[id], x * SIZE * Game.SCALE, y * SIZE * Game.SCALE, SIZE * Game.SCALE, SIZE * Game.SCALE, null);
		if (z == 0) {
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(x * SIZE * Game.SCALE, y * SIZE * Game.SCALE, SIZE * Game.SCALE, SIZE * Game.SCALE);
		}
	}
	
	public void hit(int force) {
		strength -= force;
		timer = Main.UPS * TIME;
	}
}
