package com.whale.nope.misc;

import java.awt.Color;
import java.awt.Graphics;

import com.whale.nope.effects.Water;
import com.whale.nope.main.Game;
import com.whale.nope.main.Screen;

public class Rain extends Weather {
	
	private double intensity = 1;
	
	public Rain(Game game) {
		super(game);
	}
	
	public void update() {
		super.update();
		if (random.nextInt(500) == 0) intensity = Math.max(1, Math.min(8, random.nextInt(6) - 3));
		for (int i = 0; i < intensity; i++) game.getEffects().add(new Water(game.getXOffset() - Screen.WIDTH / 2 + random.nextInt(Screen.WIDTH) * 2, game.getYOffset(), wind, 5, 10, game.getEffects()));
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(50 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * 20), 75 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * 20), 125 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * 20)));
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
	}
}
