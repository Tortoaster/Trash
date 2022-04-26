package com.rook.life;

import java.awt.Color;
import java.awt.Graphics;

import com.rook.Window;
import com.rook.state.Game;
import com.rook.world.Tile;
import com.rook.world.World;

public abstract class Entity {
	
	protected double x, y, velX, velY, spdX, spdY, accX, accY, weight;
	
	protected int width, height, index, team, time;
	
	protected boolean visible = true, phasing;
	
	protected Life handler;
	
	protected World world;
	
	public Entity(double x, double y, double weight, int width, int height, int team, World world) {
		this.x = x;
		this.y = y;
		this.weight = weight;
		this.width = width;
		this.height = height;
		this.team = team;
		this.world = world;
		handler = world.getLife();
	}
	
	public void collide(int side) {}
	
	public void onContact(Entity e) {}
	
	public void update() {
		velX += accX / Window.RATE;
		velY += accY / Window.RATE;
		accX = 0;
		accY = 0;
		double deltaX = (velX + spdX) / Window.RATE;
		if(deltaX != 0) {
			double maxDeltaX = world.getMaxHorizontalMovement(this, deltaX);
			if(maxDeltaX == 0) {
				if(deltaX > 0) {
					collide(Tile.SIDE_LEFT);
				} else {
					collide(Tile.SIDE_RIGHT);
				}
				velX = 0;
			}
			x += maxDeltaX;
		}
		double deltaY = (velY + spdY) / Window.RATE;
		if(deltaY != 0) {
			double maxDeltaY = world.getMaxVerticalMovement(this, deltaY);
			if(maxDeltaY == 0) {
				if(deltaY > 0) {
					collide(Tile.SIDE_TOP);
				} else {
					collide(Tile.SIDE_BOTTOM);
				}
				velY = 0;
			}
			y += maxDeltaY;
		}
		time++;
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(team));
		g.fillRect((int) (x * Game.SCALE), (int) (y * Game.SCALE), width  * Game.SCALE, height * Game.SCALE);
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setPhasing(boolean phasing) {
		this.phasing = phasing;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void addVerticalAcceleration(double dAccY) {
		accY += dAccY;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setTeam(int team) {
		this.team = team;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean isPhasing() {
		return phasing;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getTeam() {
		return team;
	}
	
	public int getIndex() {
		return index;
	}
	
	public Life getEntityHandler() {
		return handler;
	}
	
	public World getWorld() {
		return world;
	}
}
