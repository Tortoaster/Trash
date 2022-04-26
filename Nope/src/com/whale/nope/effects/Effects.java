package com.whale.nope.effects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.whale.nope.main.Game;
import com.whale.nope.main.Screen;

public class Effects {
	
	private Game game;
	
	private List<Particle> particles = new ArrayList<Particle>();
	
	public Effects(Game game) {
		this.game = game;
	}
	
	public void update() {
		for (int i = 0; i < particles.size(); i++) if(particles.get(i) != null) particles.get(i).update();
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < particles.size(); i++) if (particles.get(i).getX() >= game.getXOffset() - 10 && particles.get(i).getX() < game.getXOffset() + Screen.WIDTH + 10 && particles.get(i).getY() >= game.getYOffset() - 10 && particles.get(i).getY() < game.getYOffset() + Screen.HEIGHT + 10) particles.get(i).draw(g);
	}
	
	public void add(Particle p) {
		particles.add(p);
	}
	
	public void remove(Particle p) {
		particles.remove(p);
	}
	
	public Game getGame() {
		return game;
	}
}
