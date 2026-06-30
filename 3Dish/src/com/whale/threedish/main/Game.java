package com.whale.threedish.main;

import java.awt.Graphics;

public class Game {
	
	private boolean pressed;
	private int mX, mY;
	
	private int[][] map = new int[150][100];
	private int size = 100;
	private double x = 50, y = 50, speed = 0.05;
	
	public Game() {
		for(int y = 0; y < map[0].length; y++) for(int x = 0; x < map.length; x++) {
			if(x % 3 == 0 && y % 2 == 0) map[x][y] = (int) (Math.random() * 201) - 50; else map[x][y] = 0;
		}
	}
	
	public void update() {
		if(pressed) {
			double a = Math.atan2(mY - Window.HEIGHT / 2, mX - Window.WIDTH / 2);
			x += speed * Math.cos(a);
			y += speed * Math.sin(a);
		}
	}
	
	public void draw(Graphics g) {
		g.translate((int) -(x * size) + Window.WIDTH / 2, (int) -(y * size) + Window.HEIGHT / 2);
		g.fillRect((int) (x * size) - 50, (int) (y * size) - 50, size, size);
		for(int y = 0; y < map[0].length; y++) for(int x = 0; x < map.length; x++) {
			if(map[x][y] != 0) {
				g.drawRect(x * size - size / 2, y * size - size / 2, size, size);
				g.drawRect(x * size - size / 2 + (int) (map[x][y] * (x - this.x)) - map[x][y] / 2, y * size - size / 2 + (int) (map[x][y] * (y - this.y)) - map[x][y] / 2, size + map[x][y], size + map[x][y]);
				g.drawLine(x * size - size / 2, y * size - size / 2, x * size - size / 2 + (int) (map[x][y] * (x - this.x)) - map[x][y] / 2, y * size - size / 2 + (int) (map[x][y] * (y - this.y)) - map[x][y] / 2);
				g.drawLine((x + 1) * size - size / 2, y * size - size / 2, (x + 1) * size - size / 2 + (int) (map[x][y] * (x - this.x)) + map[x][y] / 2, y * size - size / 2 + (int) (map[x][y] * (y - this.y)) - map[x][y] / 2);
				g.drawLine((x + 1) * size - size / 2, (y + 1) * size - size / 2, (x + 1) * size - size / 2 + (int) (map[x][y] * (x - this.x)) + map[x][y] / 2, (y + 1) * size - size / 2 + (int) (map[x][y] * (y - this.y)) + map[x][y] / 2);
				g.drawLine(x * size - size / 2, (y + 1) * size - size / 2, x * size - size / 2 + (int) (map[x][y] * (x - this.x)) - map[x][y] / 2, (y + 1) * size - size / 2 + (int) (map[x][y] * (y - this.y)) + map[x][y] / 2);
			}
		}
	}
	
	public void isPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public void setPosition(int x, int y) {
		mX = x;
		mY = y;
	}
}
