package com.whale.nope.effects;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import com.whale.nope.audio.Sound;
import com.whale.nope.world.Tile;

public class Bubble extends Particle {
	
	private int size;
	
	private File[] sound = new File[5];
	
	public Bubble(double x, double y, double xV, double yV, int size, double time, Effects effects) {
		super(x, y, xV, yV, time, effects);
		this.size = size;
		for (int i = 0; i < 5; i++) sound[i] = new File("res/Plop " + (i + 1) + ".wav");
	}
	
	public void update() {
		yV = Math.max(-2, yV - 0.1);
		if (effects.getGame().getWorld().isBlocked((int) (x + xV), (int) y)) {
			effects.remove(this);
		}
		if (effects.getGame().getWorld().isBlocked((int) x, (int) (y + yV))) {
			effects.remove(this);
		}
		x += xV;
		y += yV;
		age++;
		if (age >= time) {
			Sound.playSound(sound[random.nextInt(5)], Math.sqrt((x - effects.getGame().getPlayer().getX()) * (x - effects.getGame().getPlayer().getX()) + (y - effects.getGame().getPlayer().getY()) * (y - effects.getGame().getPlayer().getY())) / Tile.SIZE);
			effects.remove(this);
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(0, 250, 250));
		g.drawOval((int) (x - size / 2), (int) (y - size / 2), size, size);
	}
}
