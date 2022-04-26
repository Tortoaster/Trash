package com.whale.nope.misc;

import java.awt.Graphics;
import java.util.Random;
import com.whale.nope.main.Game;
import com.whale.nope.main.Loop;

public class Background {
	
	private int timer, current;
	
	private Random random = new Random();
	
	private Weather[] types;
	private Weather weather;
	
	public Background(Game game) {
		Weather[] types = {new Sunny(game), new Rain(game)};
		this.types = types;
		weather = types[current];
		timer = (random.nextInt(91) + 30) * Loop.UPS;
	}
	
	public void update() {
		weather.update();
		timer--;
		if (timer <= 0) {
			int type = current;
			while (type == current) {
				type = random.nextInt(types.length);
			}
			current = type;
			weather = types[current];
			timer = (random.nextInt(301) + 60) * Loop.UPS;
		}
	}
	
	public void draw(Graphics g) {
		weather.draw(g);
	}
}
