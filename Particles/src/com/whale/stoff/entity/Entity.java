package com.whale.stoff.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import com.whale.stoff.main.Game;

public class Entity {
	
	private Game game;
	
	private List<Creature> creatures = new ArrayList<Creature>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	
	public Entity(Game game) {
		this.game = game;
	}
	
	public void update() {
		for (int i = 0; i < creatures.size(); i++) if (creatures.get(i) != null) creatures.get(i).update();
		for (int i = 0; i < projectiles.size(); i++) if (projectiles.get(i) != null) projectiles.get(i).update();
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < creatures.size(); i++) if (creatures.get(i) != null) creatures.get(i).draw(g);
		for (int i = 0; i < projectiles.size(); i++) if (projectiles.get(i) != null) projectiles.get(i).draw(g);
	}
	
	public void add(Creature c) {
		creatures.add(c);
	}
	
	public void remove(Creature c) {
		creatures.remove(c);
	}
	
	public void add(Projectile p) {
		projectiles.add(p);
	}
	
	public void remove(Projectile p) {
		projectiles.remove(p);
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	public Game getGame() {
		return game;
	}
}
