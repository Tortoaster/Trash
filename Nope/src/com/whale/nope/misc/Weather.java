package com.whale.nope.misc;

import java.awt.Graphics;
import java.util.Random;
import com.whale.nope.main.Game;

public abstract class Weather {
	
	protected int time = 60;
	
	protected double wind = 0;
	
	protected Game game;
	
	protected Random random = new Random();
	
	public Weather(Game game) {
		this.game = game;
	}
	
	public void update() {
		wind = Math.sin(System.currentTimeMillis() / 10000.0) * 10;
	}
	
	public abstract void draw(Graphics g);
}
