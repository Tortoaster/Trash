package com.rook.mechanics;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.rook.life.Entity;
import com.rook.world.Tile;
import com.rook.world.World;

public class Light {
	
	private int x, y, radius;
	
	private World world;
	
	private Entity master;
	
	public Light(int x, int y, int radius, World world) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.world = world;
	}
	
	public void follow(Entity e) {
		master = e;
	}
	
	public List<Point> cast(Tile[][] map) {
		List<Point> points = new ArrayList<>();
		for(double a = 0; a < 360; a += 25.0 / radius) {
			List<Point> ray = Ray.cast(x, y, x + (int) (radius * Math.cos(Math.toRadians(a))), y + (int) (radius * Math.sin(Math.toRadians(a))), false);
			for(int i = 0; i < ray.size(); i++) {
				Point p = ray.get(i);
				if(p.x >= 0 && p.x < map.length && p.y >= 0 && p.y < map[0].length) {
					points.add(p);
					if(!map[p.x][p.y].isTranslucent()) {
						break;
					}
				} else {
					break;
				}
			}
		}
		return points;
	}
	
	public boolean update() {
		if(world.getLife().contains(master)) {
			int c = x;
			int r = y;
			x = (int) (master.getX() / Tile.SIZE);
			y = (int) (master.getY() / Tile.SIZE);
			if(c != x || r != y) {
				return true;
			}
		} else if(master != null) {
			world.remove(this);
		}
		return false;
	}
}
