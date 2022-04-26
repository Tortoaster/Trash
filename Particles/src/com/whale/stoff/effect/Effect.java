package com.whale.stoff.effect;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import com.whale.stoff.main.Game;

public class Effect {
	
	private Game game;
	
	private List<Particle> particles = new ArrayList<Particle>();
	
	public Effect(Game game) {
		this.game = game;
	}
	
	public void update() {
		for (int i = 0; i < particles.size(); i++) if (particles.get(i) != null) particles.get(i).update();
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < particles.size(); i++) if (particles.get(i) != null) particles.get(i).draw(g);
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
