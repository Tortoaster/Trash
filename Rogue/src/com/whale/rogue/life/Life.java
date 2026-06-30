package com.whale.rogue.life;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.whale.rogue.main.Game;

public class Life {
	
private List<Entity> life = new ArrayList<Entity>();
	
	public final Game game;
	
	public Life(Game game) {
		this.game = game;
	}
	
	public void update(int x, int y, int width, int height) {
		sort(life);
		for(int i = 0; i < life.size(); i++) {
			Entity e = life.get(i);
			if(e.getX() >= x && e.getY() >= y && e.getX() < x + width && e.getY() < y + height) {
				e.update();
			} else if(e.getY() >= y + height) {
				break;
			}
		}
	}
	
	public void draw(int x, int y, int width, int height, Graphics g) {
		for(int i = 0; i < life.size(); i++) {
			Entity e = life.get(i);
			if(e.getX() >= x && e.getY() >= y && e.getX() < x + width && e.getY() < y + height) {
				e.draw(g);
			} else if(e.getY() >= y + height) {
				break;
			}
		}
	}
	
	public void add(Entity e) {
		life.add(e);
	}
	
	public void remove(Entity e) {
		life.remove(e);
	}
	
	public void sort(List<Entity> life) {
		life.sort(new Comparator<Entity>() {
			public int compare(Entity e1, Entity e2) {
				if(e1.getY() > e2.getY()) return 1; else if(e1.getY() == e2.getY()) return 0; else return -1;
			}
		});
	}
}
