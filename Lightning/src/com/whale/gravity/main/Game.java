package com.whale.gravity.main;

import java.awt.Graphics;

import com.whale.gravity.effects.Lightning;

public class Game extends State {
	
	private Lightning l = new Lightning(100, 400, 900 , 400);
	
	public void update() {
		l = new Lightning(100, 400, 900 , 400);
	}
	
	public void draw(Graphics g) {
		l.draw(g);
	}
}
