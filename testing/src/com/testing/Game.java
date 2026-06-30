package com.testing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

public class Game extends State {
	
	boolean[][] map = new boolean[10][8];
	
	int x0 = 2, y0 = 2, x1 = 5, y1 = 6;
	
	int SIZE = 16, SCALE = 4;
	
	Random random = new Random();
	
	List<Point> ray = new ArrayList<>();
	
	public Game() {
		for(int y = 0; y < map[0].length; y++) for(int x = 0; x < map.length; x++) {
			map[x][y] = false;
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			x1 = e.getX() / SIZE / SCALE;
			y1 = e.getY() / SIZE / SCALE;
		} else if(SwingUtilities.isRightMouseButton(e)) {
			x0 = e.getX() / SIZE / SCALE;
			y0 = e.getY() / SIZE / SCALE;
		}
	}
	
	public List<Point> castRay(int startX, int startY, int endX, int endY) {
		List<Point> ray = new ArrayList<>();
		int error, previous;
		int x = startX, y = startY;
		int deltaX = Math.abs(endX - startX);
		int deltaY = Math.abs(endY - startY);
		int stepX = startX > endX ? -1 : 1;
		int stepY = startY > endY ? -1 : 1;
		ray.add(new Point(x, y));
		if(deltaX >= deltaY) {
			previous = deltaX;
			error = previous;
			for(int i = 0; i < deltaX; i++) {
				x += stepX;
				error += deltaY * 2;
				if(error > deltaX * 2) {
					y += stepY;
					error -= deltaX * 2;
					if(error + previous < deltaX * 2) {
						ray.add(new Point(x, y - stepY));
					} else if(error + previous > deltaX * 2) {
						ray.add(new Point(x - stepX, y));
					}
				}
				ray.add(new Point(x, y));
				previous = error;
			}
		} else {
			previous = deltaY;
			error = previous;
			for(int i = 0; i < deltaY; i++) {
				y += stepY;
				error += deltaX * 2;
				if(error > deltaY * 2) {
					x += stepX;
					error -= deltaY * 2;
					if(error + previous < deltaY * 2) {
						ray.add(new Point(x - stepX, y));
					} else if(error + previous > deltaY * 2) {
						ray.add(new Point(x, y - stepY));
					}
				}
				ray.add(new Point(x, y));
				previous = error;
			}
		}
		return ray;
	}
	
	public void update() {
		ray = castRay(x0, y0, x1, y1);
	}
	
	public void draw(Graphics g) {
		for(int y = 0; y < map[0].length; y++) for(int x = 0; x < map.length; x++) {
			if(map[x][y]) {
				g.setColor(Color.GRAY);
				g.fillRect(x * SIZE * SCALE, y * SIZE * SCALE, SIZE * SCALE, SIZE * SCALE);
				g.setColor(Color.BLACK);
				g.drawRect(x * SIZE * SCALE, y * SIZE * SCALE, SIZE * SCALE - 1, SIZE * SCALE - 1);
			} else {
				g.setColor(Color.WHITE);
				g.fillRect(x * SIZE * SCALE, y * SIZE * SCALE, SIZE * SCALE, SIZE * SCALE);
				g.setColor(Color.BLACK);
				g.drawRect(x * SIZE * SCALE, y * SIZE * SCALE, SIZE * SCALE - 1, SIZE * SCALE - 1);
			}
			for(int i = 0; i < ray.size(); i++) {
				Point r = ray.get(i);
				if(r.x == x && r.y == y) {
					g.setColor(new Color(255, 255, 0, 100));
					g.fillRect(x * SIZE * SCALE + 1, y * SIZE * SCALE + 1, SIZE * SCALE - 2, SIZE * SCALE - 2);
				}
			}
		}
		g.setColor(Color.RED);
		g.drawLine((int) ((x0 + 0.5) * SIZE) * SCALE, (int) ((y0 + 0.5) * SIZE) * SCALE, (int) ((x1 + 0.5) * SIZE) * SCALE, (int) ((y1 + 0.5) * SIZE) * SCALE);
	}
}
