package com.rook.life;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.rook.world.World;

public class Life {
	
	public static final int TEAM_PLAYER = 0, TEAM_ENEMY = 1;
	
	private Comparator<Entity> comparator = new Comparator<Entity>() {
		public int compare(Entity e1, Entity e2) {
			return e1.getIndex() < e2.getIndex() ? -1 : e1.getIndex() > e2.getIndex() ? 1 : 0;
		}
	};
	
	private List<Entity> entities = new ArrayList<>();
	private List<Creature> creatures = new ArrayList<>();
	private List<Projectile> projectiles = new ArrayList<>();
	
	private World world;
	
	public Life(World world) {
		this.world = world;
	}
	
	public void update() {
		entities.sort(comparator);
		creatures.sort(comparator);
		projectiles.sort(comparator);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.addVerticalAcceleration(getWorld().getGravity());
			e.update();
		}
		for(int i = 0; i < creatures.size(); i++) {
			Creature c = creatures.get(i);
			c.addVerticalAcceleration(getWorld().getGravity());
			c.update();
		}
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			p.addVerticalAcceleration(getWorld().getGravity());
			p.update();
		}
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).draw(g);
		}
		for(int i = 0; i < creatures.size(); i++) {
			creatures.get(i).draw(g);
		}
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void addCreature(Creature c) {
		creatures.add(c);
	}
	
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	public void removeCreature(Creature c) {
		for(int i = 0; i < creatures.size(); i++) {
			creatures.get(i).forget(c);
		}
		creatures.remove(c);
	}
	
	public void removeProjectile(Projectile p) {
		projectiles.remove(p);
	}
	
	public boolean contains(Entity e) {
		if(entities.contains(e) || creatures.contains(e) || projectiles.contains(e)) {
			return true;
		}
		return false;
	}
	
	public List<Creature> getNearCreatures(double x, double y, int radius) {
		List<Creature> near = new ArrayList<>();
		for(int i = 0; i < creatures.size(); i++) {
			Creature c = creatures.get(i);
			if(Math.abs(c.getX() - x) < radius && Math.abs(c.getY() - y) < radius) {
				near.add(c);
			}
		}
		return near;
	}
	
	public World getWorld() {
		return world;
	}
}
