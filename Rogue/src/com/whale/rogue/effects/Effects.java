package com.whale.rogue.effects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.whale.rogue.main.Game;

public class Effects {
	
	private List<Particle> effects = new ArrayList<Particle>();
	
	public final Game game;
	
	public Effects(Game game) {
		this.game = game;
	}
	
	public void update(int x, int y, int width, int height) {
		sort(effects);
		for(int i = 0; i < effects.size(); i++) {
			Particle p = effects.get(i);
			if(p.getX() >= x && p.getY() >= y && p.getX() < x + width && p.getY() < y + height) {
				p.update();
			} else if(p.getY() >= y + height) {
				break;
			}
		}
	}
	
	public void draw(int x, int y, int width, int height, Graphics g) {
		for(int i = 0; i < effects.size(); i++) {
			Particle p = effects.get(i);
			if(p.getX() >= x && p.getY() >= y && p.getX() < x + width && p.getY() < y + height) {
				p.draw(g);
			} else if(p.getY() >= y + height) {
				break;
			}
		}
	}
	
	public void add(Particle p) {
		effects.add(p);
	}
	
	public void remove(Particle p) {
		effects.remove(p);
	}
	
	public void sort(List<Particle> effects) {
		effects.sort(new Comparator<Particle>() {
			public int compare(Particle p1, Particle p2) {
				if(p1.getY() > p2.getY()) return 1; else if(p1.getY() == p2.getY()) return 0; else return -1;
			}
		});
	}
}
