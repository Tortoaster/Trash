package com.whale.nope.world;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import com.whale.nope.audio.Sound;
import com.whale.nope.effects.Flame;

public class Dynamite extends Tile {
	
	private int power = 5;
	
	private File sound = new File("res/Boom.wav");
	
	public Dynamite(World world) {
		super(true, world);
	}
	
	public void onHit(int x, int y) {
		super.onHit(x, y);
		Sound.playSound(sound, Math.sqrt((x * Tile.SIZE - world.getGame().getPlayer().getX()) * (x * Tile.SIZE - world.getGame().getPlayer().getX()) + (y * Tile.SIZE - world.getGame().getPlayer().getY()) * (y * Tile.SIZE - world.getGame().getPlayer().getY())) / Tile.SIZE);
		for (int a = 0; a < 360; a++) {
			for (int i = 1; i <= power; i++) {
				world.getTile((x + (int) (Math.cos(Math.toRadians(a)) * i)), (y + (int) (Math.sin(Math.toRadians(a)) * i))).onHit((x + (int) (Math.cos(Math.toRadians(a)) * i)), (y + (int) (Math.sin(Math.toRadians(a)) * i)));
			}
		}
		for (int i = 0; i < power * 400; i++) {
			int direction = random.nextInt(360);
			double speed = Math.max(0.1, random.nextDouble()) * (random.nextInt(power * 2) + 1);
			world.getGame().getEffects().add(new Flame(x * SIZE + SIZE / 2, y * SIZE + SIZE / 2, Math.cos(Math.toRadians(direction)) * speed * 4, Math.sin(Math.toRadians(direction)) * speed, new Color(1F, 1F, 0), new Color(1F, 0, 0), random.nextInt(2) + 1, world.getGame().getEffects()));
		}
	}
	
	public void draw(int x, int y, Graphics g) {
		g.setColor(new Color(100, 0, 0));
		g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
	}
}
