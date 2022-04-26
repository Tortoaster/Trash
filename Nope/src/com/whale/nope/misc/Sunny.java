package com.whale.nope.misc;

import java.awt.Color;
import java.awt.Graphics;
import com.whale.nope.main.Game;
import com.whale.nope.main.Screen;

public class Sunny extends Weather {
	
	public Sunny(Game game) {
		super(game);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(60 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * 50), 100 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * 50), 180 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * 50)));
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
		g.setColor(new Color(250, 200, 100));
		g.fillOval(Screen.WIDTH / 2 + (int) (Math.sin(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * (Math.max(Screen.WIDTH, Screen.HEIGHT) / 2)) - 50, Screen.HEIGHT + (int) (Math.cos(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * (Math.max(Screen.WIDTH, Screen.HEIGHT) / 2)) - 50, 100, 100);
		g.setColor(new Color(220, 210, 240));
		g.fillOval(Screen.WIDTH / 2 - (int) (Math.sin(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * (Math.max(Screen.WIDTH, Screen.HEIGHT) / 2)) - 35, Screen.HEIGHT - (int) (Math.cos(System.currentTimeMillis() / 1000.0 * Math.PI * 2 / time) * (Math.max(Screen.WIDTH, Screen.HEIGHT) / 2)) - 35, 70, 70);
	}
}
